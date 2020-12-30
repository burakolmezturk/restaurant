package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.dto.CartDTO;
import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import com.restaurantapi.restaurantapi.dto.ProductDTO;
import com.restaurantapi.restaurantapi.convertor.CartDTOConvertor;
import com.restaurantapi.restaurantapi.convertor.ProductDTOConvertor;
import com.restaurantapi.restaurantapi.entity.Cart;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Product;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.mapper.ProductMapper;
import com.restaurantapi.restaurantapi.repository.CartRepository;
import com.restaurantapi.restaurantapi.repository.CategoryRepository;
import com.restaurantapi.restaurantapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

        return productMapper.toDTOList(productList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO addProduct(ProductDTO productDTO) {

        Product product = productMapper.toEntity(productDTO);

        int[] categoryIds = productDTO.getCategory();
        for (int i = 0; i < categoryIds.length; i++) {

            Optional<Category> category = categoryRepository.findById(categoryIds[i]);

            if (!category.isPresent()) throw new RecordNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);

            category.get().getProducts().add(product);
        }
        productRepository.save(product);
        return productDTO;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO editProduct(ProductDTO productDTO, int categoryId) {

        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());
        if (!optionalProduct.isPresent()) throw new RecordNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);

        optionalProduct.get().getCategories().forEach(category -> category.getProducts().remove(optionalProduct.get()));

        Product product = productMapper.toEntity(productDTO);

        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < productDTO.getCategoryIdList().size(); i++) {
            categoryList.add(categoryRepository.findById(productDTO.getCategoryIdList().get(i)).get());
        }

        product.setCategories(categoryList);

        categoryList.forEach(category -> categoryRepository.saveAndFlush(category));
        for (int i = 0; i < productDTO.getCategoryIdList().size(); i++) {
            Optional<Category> category = categoryRepository.findById(productDTO.getCategoryIdList().get(i));
            category.get().getProducts().add(product);
        }
        productRepository.saveAndFlush(product);
        return productDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean deleteProduct(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        productOptional.get().getCategories().stream().forEach(category -> category.getProducts().remove(productOptional.get()));
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    public ProductDTO getProductById(int id) {
        List<Integer> categories = new ArrayList<>();
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) throw new RecordNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);

        ProductDTO productDTO = productMapper.toDTO(productOptional.get());
        productOptional.get().getCategories().stream().forEach(category -> categories.add(category.getId()));
        productDTO.setCategoryIdList(categories);

        return productDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean sellProduct(List<CartDTO> cartDTOList) {
        List<Cart> cartList = new ArrayList<>();
        cartDTOList.stream().forEach(cartDTO -> cartList.add(CartDTOConvertor.dtoToCart(cartDTO)));
        List<Cart> carts = cartRepository.saveAll(cartList);
        if (carts.isEmpty()) return false;
        else return true;

    }

    public Set<ProductDTO> getProductsByCategoryId(int categoryId) {

        Optional<Category> category = categoryRepository.findById(categoryId);
        Set<ProductDTO> productDTOSet = new HashSet<>();

        if (!category.isPresent()) throw new RecordNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);

        List<Product> productSet = category.get().getProducts();
        productSet.forEach(product -> productDTOSet.add(productMapper.toDTO(product)));

        return productDTOSet;

    }

    public Page<ProductDTO> getFindPage(Pageable pageable) {

        Page<Product> productPage = productRepository.findAll(pageable);
        List<ProductDTO> productDTOList = new ArrayList<>();
        productPage.getContent().forEach(product -> productDTOList.add(productMapper.toDTO(product)));
        Page<ProductDTO> productDTOPage = new PageImpl(productDTOList, pageable, productPage.getTotalElements());

        return productDTOPage;
    }

    public Slice<ProductDTO> getProductWithSlice(Pageable pageable, int id) {
        Category category = categoryRepository.findById(id).get();
        Slice<Product> productSlice = productRepository.findProductByCategories(category, pageable);
        List<ProductDTO> productDTOList = new ArrayList<>();
        productSlice.getContent().forEach(product -> productDTOList.add(productMapper.toDTO(product)));
        Slice<ProductDTO> dtoSlice = new PageImpl(productDTOList, pageable, productSlice.getSize());

        return dtoSlice;
    }
}

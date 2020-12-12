package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.dto.CartDTO;
import com.restaurantapi.restaurantapi.dto.ProductDTO;
import com.restaurantapi.restaurantapi.convertor.CartDTOConvertor;
import com.restaurantapi.restaurantapi.convertor.ProductDTOConvertor;
import com.restaurantapi.restaurantapi.entity.Cart;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Product;
import com.restaurantapi.restaurantapi.repository.CartRepository;
import com.restaurantapi.restaurantapi.repository.CategoryRepository;
import com.restaurantapi.restaurantapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = ProductDTOConvertor.productListToDTOList(productList);
        return productDTOList;
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = ProductDTOConvertor.dtoToProduct(productDTO);
        int[] categoryId = productDTO.getCategory();
        for (int i = 0; i < categoryId.length; i++) {
            Optional<Category> category = categoryRepository.findById(categoryId[i]);
            if (!category.isPresent()) {
                return null;
            }

            // product.setCategory(category.get());
            category.get().getProducts().add(product);
        }


        productRepository.save(product);
        return productDTO;

    }

    public ProductDTO editProduct(ProductDTO productDTO, int categoryId) {

        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());
        optionalProduct.get().getCategories().forEach(category -> category.getProducts().remove(optionalProduct.get()));
        Product product =ProductDTOConvertor.dtoToProduct(productDTO);
        Set<Category> categoryList = new HashSet<>();
        for(int i=0; i<productDTO.getCategoryIdList().size(); i++){
            categoryList.add(categoryRepository.findById(productDTO.getCategoryIdList().get(i)).get());
        }
        product.setCategories(categoryList);
        categoryList.forEach(category -> categoryRepository.saveAndFlush(category));
        for(int i=0; i<productDTO.getCategoryIdList().size();i++){
            Optional<Category> category = categoryRepository.findById(productDTO.getCategoryIdList().get(i));
            category.get().getProducts().add(product);
        }
        productRepository.saveAndFlush(product);
        return productDTO;
    }

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
        if (!productOptional.isPresent()) {
            return null;
        }
        ProductDTO productDTO = ProductDTOConvertor.productToDto(productOptional.get());
        productOptional.get().getCategories().stream().forEach(category -> categories.add(category.getId()));
        productDTO.setCategoryIdList(categories);

        return productDTO;
    }

    public boolean sellProduct(List<CartDTO> cartDTOList) {
        List<Cart> cartList = new ArrayList<>();
        cartDTOList.stream().forEach(cartDTO -> cartList.add(CartDTOConvertor.dtoToCart(cartDTO)));
        List<Cart> carts = cartRepository.saveAll(cartList);
        if (carts.isEmpty()) return false;
        else return true;

    }

    public Set<ProductDTO> getProductsByCategoryId(int categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isPresent()) {
            return ProductDTOConvertor.setProductToSetDTO(category.get().getProducts());
        } else {
            return Collections.emptySet();
        }

    }

}

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

    public ProductDTO addProduct(ProductDTO productDTO, int categoryId) {

        Optional<Category> category = categoryRepository.findById(categoryId);
        if (!category.isPresent()) {
            return null;
        }
        Product product = ProductDTOConvertor.dtoToProduct(productDTO);
        product.setCategory(category.get());

        productRepository.save(product);
        return productDTO;

    }

    public ProductDTO editProduct(ProductDTO productDTO, int categoryId) {

        Optional<Category> category = categoryRepository.findById(categoryId);

        if (!category.isPresent()) {
            return null;
        }
        Product product = ProductDTOConvertor.dtoToProduct(productDTO);
        product.setCategory(category.get());
        productRepository.saveAndFlush(product);

        return productDTO;
    }

    public Boolean deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    public ProductDTO getProductById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            return null;
        }

        return ProductDTOConvertor.productToDto(productOptional.get());
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
            return ProductDTOConvertor.setProductToSetDTO(category.get().getProductSet());
        } else {
            return Collections.emptySet();
        }

    }

}

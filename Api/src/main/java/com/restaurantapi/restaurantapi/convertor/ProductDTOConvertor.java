package com.restaurantapi.restaurantapi.convertor;


import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.dto.ProductDTO;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.entity.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDTOConvertor {

    private ProductDTOConvertor() {
    }

    public static Product dtoToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPurchasePrice(productDTO.getPurchasePrice());
        product.setSalesPrice(productDTO.getSalesPrice());
        if (productDTO.getImage() != null)
            product.setImage(MediaDTOConvertor.dtoToMedia(productDTO.getImage()));
        else product.setImage(new Media());
        return product;
    }

    public static ProductDTO productToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPurchasePrice(product.getPurchasePrice());
        productDTO.setSalesPrice(product.getSalesPrice());
        if (product.getImage() != null)
            productDTO.setImage(MediaDTOConvertor.mediaToDTO(product.getImage()));
        else productDTO.setImage(new MediaDTO());
        return productDTO;
    }

    public static Set<ProductDTO> setProductToSetDTO(Set<Product> products) {
        ProductDTO productDTO;
        Set<ProductDTO> productDTOSet = new HashSet<>();

        for (Product product : products) {
            productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setPurchasePrice(product.getPurchasePrice());
            productDTO.setSalesPrice(product.getSalesPrice());
            productDTO.setCategories(CategoryDTOConvertor.categoryListToDTOList(product.getCategories()));
            if (product.getImage() != null)
                productDTO.setImage(MediaDTOConvertor.mediaToDTO(product.getImage()));
            else productDTO.setImage(new MediaDTO());
            productDTOSet.add(productDTO);
        }
        return productDTOSet;
    }

    public static List<ProductDTO> productListToDTOList(List<Product> products) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        ProductDTO productDTO;


        for (Product product : products) {
            productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setPurchasePrice(product.getPurchasePrice());
            productDTO.setSalesPrice(product.getSalesPrice());
            productDTO.setCategories(CategoryDTOConvertor.categoryListToDTOList(product.getCategories()));
            if (product.getImage() != null)
                productDTO.setImage(MediaDTOConvertor.mediaToDTO(product.getImage()));
            else productDTO.setImage(new MediaDTO());

            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    public static List<Product> dtoListToProductList(List<ProductDTO> productDTOS) {
        List<Product> productList = new ArrayList<>();
        Product product;


        for (ProductDTO productDTO : productDTOS) {
            product = new Product();
            product.setId(productDTO.getId());
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPurchasePrice(productDTO.getPurchasePrice());
            product.setSalesPrice(productDTO.getSalesPrice());

            if (productDTO.getImage()!= null)
                product.setImage(MediaDTOConvertor.dtoToMedia(productDTO.getImage()));
            else product.setImage(new Media());
            productList.add(product);
        }
        return productList;
    }

}

package com.restaurantapi.restaurantapi.builder;


import com.restaurantapi.restaurantapi.dto.ProductDTO;
import com.restaurantapi.restaurantapi.entity.Category;

import java.util.HashSet;
import java.util.Set;

public class ProductDTOBuilder extends Builder {

    private String name;
    private String description;
    private double salesPrice;
    private double purchasePrice;
    private Set<Category> categories = new HashSet<>();
    private int[] category;


    @Override
    public ProductDTO build() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(super.id);
        productDTO.setPurchasePrice(this.purchasePrice);
        productDTO.setSalesPrice(this.salesPrice);
        productDTO.setName(this.name);
        productDTO.setDescription(this.description);
        productDTO.setCategory(this.category);
       // productDTO.setCategories(this.categories);
        return productDTO;
    }

    public ProductDTOBuilder id(int id) {
        super.id = id;
        return this;
    }

    public ProductDTOBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductDTOBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductDTOBuilder salesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
        return this;
    }

    public ProductDTOBuilder purchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
        return this;
    }
    public ProductDTOBuilder categories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }
    public ProductDTOBuilder categoryId(int[] category) {
        this.category = category;
        return this;
    }


}

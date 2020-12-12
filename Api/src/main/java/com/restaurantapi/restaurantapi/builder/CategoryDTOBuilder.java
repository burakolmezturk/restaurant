package com.restaurantapi.restaurantapi.builder;

import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.entity.Product;

import java.util.HashSet;
import java.util.Set;


public class CategoryDTOBuilder extends Builder{

    private int id;
    private String name;
    private String description;
    private MediaDTO image;
    private Set<Product> products = new HashSet<>();

    @Override
    public CategoryDTO build() {
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setId(super.id);
        categoryDTO.setImage(this.image);
        categoryDTO.setDescription(this.description);
        categoryDTO.setName(this.name);
      //  categoryDTO.setProducts(this.products);
        return categoryDTO;
    }
    public CategoryDTOBuilder id(int id){
        super.id=id;
        return this;
    }
    public CategoryDTOBuilder name(String name){
        this.name=name;
        return this;
    }
    public CategoryDTOBuilder description(String description){
        this.description=description;
        return this;
    }
    public CategoryDTOBuilder image(MediaDTO image){
        this.image=image;
        return this;
    }
    public CategoryDTOBuilder products(Set<Product> products){
        this.products=products;
        return this;
    }
}

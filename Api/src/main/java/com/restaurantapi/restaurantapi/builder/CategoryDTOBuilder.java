package com.restaurantapi.restaurantapi.builder;

import com.restaurantapi.restaurantapi.dto.CategoryDTO;



public class CategoryDTOBuilder extends Builder{

    private int id;
    private String name;
    private String description;
    private String image;

    @Override
    public CategoryDTO build() {
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setId(super.id);
        categoryDTO.setImage(this.image);
        categoryDTO.setDescription(this.description);
        categoryDTO.setName(this.name);
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
    public CategoryDTOBuilder image(String image){
        this.image=image;
        return this;
    }
}

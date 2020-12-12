package com.restaurantapi.restaurantapi.dto;
import java.util.HashSet;
import java.util.Set;

public class CategoryDTO {

    private int id;
    private String name;
    private String description;
    private MediaDTO image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MediaDTO getImage() {
        return image;
    }

    public void setImage(MediaDTO image) {
        this.image = image;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }

    private Set<ProductDTO> products = new HashSet<>();
}

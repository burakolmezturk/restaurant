package com.restaurantapi.restaurantapi.builder;


import com.restaurantapi.restaurantapi.dto.ProductDTO;

public class ProductDTOBuilder extends Builder{

    private int id;
    private String name;
    private String description;
    private double salesPrice;
    private double purchasePrice;
    private String categoryName;
    private int categoryId;
    private String categoryDescription;
    private String categoryImage;


    @Override
    public ProductDTO build() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(super.id);
        productDTO.setCategoryImage(this.categoryImage);
        productDTO.setCategoryDescription(this.categoryDescription);
        productDTO.setCategoryId(this.categoryId);
        productDTO.setCategoryName(this.categoryName);
        productDTO.setPurchasePrice(this.purchasePrice);
        productDTO.setSalesPrice(this.salesPrice);
        productDTO.setName(this.name);
        productDTO.setDescription(this.description);
        return productDTO;
    }
    public ProductDTOBuilder id(int id){
        super.id=id;
        return this;
    }
    public ProductDTOBuilder name(String name){
        this.name=name;
        return this;
    }
    public ProductDTOBuilder description(String description){
        this.description=description;
        return this;
    }
    public ProductDTOBuilder categoryName(String categoryName){
        this.categoryName=categoryName;
        return this;
    }
    public ProductDTOBuilder categoryDescription(String categoryDescription){
        this.categoryDescription=categoryDescription;
        return this;
    }
    public ProductDTOBuilder categoryImage(String categoryImage){
        this.categoryImage=categoryImage;
        return this;
    }
    public ProductDTOBuilder salesPrice(double salesPrice){
        this.salesPrice=salesPrice;
        return this;
    }
    public ProductDTOBuilder purchasePrice(double purchasePrice){
        this.purchasePrice=purchasePrice;
        return this;
    }
    public ProductDTOBuilder categoryId(int categoryId){
        this.categoryId=categoryId;
        return this;
    }

}

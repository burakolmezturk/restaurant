package com.restaurantapi.restaurantapi.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@SQLDelete(sql =
        "UPDATE product " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product extends BaseEntity{

    private String name;
    private String description;
    private double salesPrice;
    private double purchasePrice;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media image;
    @JsonManagedReference
    @ManyToMany(mappedBy = "products")
    private List<Category> categories=new ArrayList<>();
    @Override
    public String toString() {
        return "Product{" +

                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", salesPrice=" + salesPrice +
                ", purchasePrice=" + purchasePrice +
                ", image=" + image +
                '}';
    }
}

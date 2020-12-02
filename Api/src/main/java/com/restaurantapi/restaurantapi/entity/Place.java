package com.restaurantapi.restaurantapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public int getTableCount() {
        return tableCount;
    }

    public void setTableCount(int tableCount) {
        this.tableCount = tableCount;
    }

    private int tableCount;

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



   /* public Set<RestaurantTable> getRestaurantTableSet() {
        return restaurantTableSet;
    }

    public void setRestaurantTableSet(Set<RestaurantTable> restaurantTableSet) {
        this.restaurantTableSet = restaurantTableSet;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="place_id")
    private Set<RestaurantTable> restaurantTableSet ;*/
}

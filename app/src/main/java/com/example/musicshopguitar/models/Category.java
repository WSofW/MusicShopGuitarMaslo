package com.example.musicshopguitar.models;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    private String name;
    private List<Product> products;
    private int imageResource;

    public Category(String name, List<Product> products, int imageResource) {
        this.name = name;
        this.products = products;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getImageResource() {
        return imageResource;
    }
}

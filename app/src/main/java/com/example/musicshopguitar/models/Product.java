package com.example.musicshopguitar.models;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String brand;
    private double price;
    private int quantity;
    private int imageResourceId;

    public Product(String name, String brand, double price, int quantity, int imageResourceId) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


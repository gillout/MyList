package com.example.mylist.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String brand;
    @ColumnInfo
    private double price;
    @ColumnInfo
    private String category;
    @ColumnInfo
    private String store;

    public Product(int id, String name, String brand, double price, String category, String store) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.store = store;
    }

    public Product(String name, String brand, double price, String category, String store) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.store = store;
    }

    public Product() {}

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                ", categories=" + category +
                ", stores=" + store +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                name.equals(product.name) &&
                brand.equals(product.brand) &&
                price == product.price &&
                category.equals(product.category) &&
                store.equals(product.store);
    }

}

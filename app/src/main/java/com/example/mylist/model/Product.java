package com.example.mylist.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Classe représentant un produit depuis l'entité de la BD locale
 */
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
    @ColumnInfo
    private int shoppinglistId;

    /**
     * Constructeur
     * @param id
     * @param name
     * @param brand
     * @param price
     * @param category
     * @param store
     * @param shoppinglistId
     */
    public Product(int id, String name, String brand, double price, String category, String store, int shoppinglistId) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.store = store;
        this.shoppinglistId = shoppinglistId;
    }

    /**
     * Constructeur sans l'id, pour la création en vue de l'insertion en BD, si besoin
     * @param name
     * @param brand
     * @param price
     * @param category
     * @param store
     * @param shoppinglistId
     */
    public Product(String name, String brand, double price, String category, String store, int shoppinglistId) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.store = store;
        this.shoppinglistId = shoppinglistId;
    }

    /**
     * Constructeur sans l'id et sans liaison à une "shoppinglist", pour la création en vue de l'insertion en BD, si besoin
     * @param name
     * @param brand
     * @param price
     * @param category
     * @param store
     */
    public Product(String name, String brand, double price, String category, String store) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.store = store;
    }

    /**
     * Constructeur par défaut
     */
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

    public int getShoppinglistId() {
        return shoppinglistId;
    }

    public void setShoppinglistId(int shoppinglistId) {
        this.shoppinglistId = shoppinglistId;
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
                ", shoppinglistId=" + shoppinglistId +
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
                store.equals(product.store) &&
                shoppinglistId == product.shoppinglistId;
    }

}

package com.example.mylist.model;

/**
 * Classe représentant un produit depuis l'entité de la BD distante
 */
public class ProductWs {

    private int id;
    private String prodName;
    private String brand;
    private double price;
    private String idCat;
    private String idStore;
    private String catName;
    private String storeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
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

    public String getIdCat() {
        return idCat;
    }

    public void setIdCat(String idCat) {
        this.idCat = idCat;
    }

    public String getIdStore() {
        return idStore;
    }

    public void setIdStore(String idStore) {
        this.idStore = idStore;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return "ProductWs{" +
                "id=" + id +
                ", name='" + prodName + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", idCat='" + idCat + '\'' +
                ", idStore='" + idStore + '\'' +
                ", catName='" + catName + '\'' +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}

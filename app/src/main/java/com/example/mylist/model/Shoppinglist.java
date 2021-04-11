package com.example.mylist.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Classe représentant l'entité "shoppinglist" de la BD locale
 */
@Entity
public class Shoppinglist {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private boolean shared;

    /**
     * Constructeur
     * @param id
     * @param name
     * @param shared
     */
    public Shoppinglist(int id, String name, boolean shared) {
        this.id = id;
        this.name = name;
        this.shared = shared;
    }

    /**
     * Constructeur sans l'id, pour l'insertion en BD, si besoin
     * @param name
     * @param shared
     */
    public Shoppinglist(String name, boolean shared) {
        this.name = name;
        this.shared = shared;
    }

    /**
     * Constructeur avec la propriété "shared" par défaut à false
     * pour ne pas que les "shoppinglist" soient partagées à leur création
     * @param name
     */
    public Shoppinglist(String name) {
        this.name = name;
        this.shared = false;
    }

    /**
     * Constructeur par défaut
     */
    public Shoppinglist() {
    }

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

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shared='" + shared + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shoppinglist shoppinglist = (Shoppinglist) o;
        return id == shoppinglist.id &&
                name.equals(shoppinglist.name) &&
                shared == shoppinglist.shared;
    }

}


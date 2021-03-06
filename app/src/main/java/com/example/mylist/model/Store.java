package com.example.mylist.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Classe représentant l'entité "store" de la BD locale
 */
@Entity
public class Store {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo
    private String name;

    /**
     * Constructeur
     * @param id
     * @param name
     */
    public Store(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructeur sans l'id, pour l'insertion en BD, si besoin
     * @param name
     */
    public Store(String name) {
        this.name = name;
    }

    /**
     * Constructeur par défaut
     */
    public Store() {
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

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return id == store.id &&
                name.equals(store.name);
    }

}


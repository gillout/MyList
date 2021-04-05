package com.example.mylist.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Shoppinglist {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo
    private String name;

    public Shoppinglist(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
        Shoppinglist shoppinglist = (Shoppinglist) o;
        return id == shoppinglist.id &&
                name.equals(shoppinglist.name);
    }

}


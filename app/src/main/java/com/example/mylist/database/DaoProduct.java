package com.example.mylist.database;

import com.example.mylist.model.Product;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoProduct {
    @Insert
        // Méthode qui mappe les objets avec la table et les ajoute
    void insertAll(List<Product> items);

    @Insert // Méthode qui mappe l'objet avec la table et l'ajoute
    void insert(Product item);

    @Update
        // Méthode qui mappe l'objet avec la table et le met à jour
    void update(Product item);

    @Delete
        // Méthode qui mappe l'objet avec la table et le supprime
    void delete(Product item);

    @Delete
        // Méthode qui mappe les objets avec la table et les supprime
    void deleteAll(List<Product> items);

    // Définition d'une requète pour interroger la BD et récupérer les données
    @Query("SELECT * FROM product ORDER BY name")
    List<Product> getAll();

    @Query("SELECT * FROM product WHERE id = :id")
    Product findById(int id);

    @Query("SELECT COUNT(*) FROM product")
    int countItems();

    @Query("SELECT SUM(price) FROM product")
    double totalPrice();

}

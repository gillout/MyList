package com.example.mylist.database;
import com.example.mylist.model.Shoppinglist;
import com.example.mylist.model.Store;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoShoppinglist {
    @Insert
        // Méthode qui mappe les objets avec la table et les ajoute
    void insertAll(List<Shoppinglist> items);

    @Insert // Méthode qui mappe l'objet avec la table et l'ajoute
    void insert(Shoppinglist item);

    @Update
        // Méthode qui mappe l'objet avec la table et le met à jour
    void update(Shoppinglist item);

    @Delete
        // Méthode qui mappe l'objet avec la table et le supprime
    void delete(Shoppinglist item);

    @Delete
        // Méthode qui mappe les objets avec la table et les supprime
    void deleteAll(List<Shoppinglist> items);

    // Définition d'une requète pour interroger la BD et récupérer les données
    @Query("SELECT * FROM shoppinglist ORDER BY name")
    List<Shoppinglist> getAll();

    @Query("SELECT * FROM shoppinglist WHERE id = :id")
    Store findById(int id);

    @Query("SELECT COUNT(*) FROM shoppinglist")
    int countItems();
}

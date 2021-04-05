package com.example.mylist.database;

import com.example.mylist.model.Category;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
@Dao
public interface DaoCategory {
    @Insert
        // Méthode qui mappe les objets avec la table et les ajoute
    void insertAll(List<Category> items);

    @Insert // Méthode qui mappe l'objet avec la table et l'ajoute
    void insert(Category item);

    @Update
        // Méthode qui mappe l'objet avec la table et le met à jour
    void update(Category item);

    @Delete
        // Méthode qui mappe l'objet avec la table et le supprime
    void delete(Category item);

    @Delete
        // Méthode qui mappe les objets avec la table et les supprime
    void deleteAll(List<Category> items);

    // Définition d'une requète pour interroger la BD et récupérer les données
    @Query("SELECT * FROM category ORDER BY name")
    List<Category> getAll();

    @Query("SELECT * FROM category WHERE id = :id")
    Category findById(int id);

    @Query("SELECT COUNT(*) FROM category")
    int countItems();
}

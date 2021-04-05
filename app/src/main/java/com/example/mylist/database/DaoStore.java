package com.example.mylist.database;
import com.example.mylist.model.Store;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
@Dao
public interface DaoStore {
    @Insert
        // Méthode qui mappe les objets avec la table et les ajoute
    void insertAll(List<Store> items);

    @Insert // Méthode qui mappe l'objet avec la table et l'ajoute
    void insert(Store item);

    @Update
        // Méthode qui mappe l'objet avec la table et le met à jour
    void update(Store item);

    @Delete
        // Méthode qui mappe l'objet avec la table et le supprime
    void delete(Store item);

    @Delete
        // Méthode qui mappe les objets avec la table et les supprime
    void deleteAll(List<Store> items);

    // Définition d'une requète pour interroger la BD et récupérer les données
    @Query("SELECT * FROM store ORDER BY name")
    List<Store> getAll();

    @Query("SELECT * FROM store WHERE id = :id")
    Store findById(int id);

    @Query("SELECT COUNT(*) FROM store")
    int countItems();
}

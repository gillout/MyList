package com.example.mylist.database.daoLocalDb;

import com.example.mylist.model.Category;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Classe permettant de réaliser les opérations de CRUD sur l'entité "category" de la BD locale
 */
@Dao
public interface DaoCategory {

    @Insert // Méthode qui mappe les objets avec la table et les ajoute
    void insertAll(List<Category> items);

    @Insert // Méthode qui mappe l'objet avec la table et l'ajoute
    void insert(Category item);

    @Update // Méthode qui mappe l'objet avec la table et le met à jour
    void update(Category item);

    @Delete // Méthode qui mappe l'objet avec la table et le supprime
    void delete(Category item);

    @Delete // Méthode qui mappe les objets avec la table et les supprime
    void deleteAll(List<Category> items);

    // Requète qui récupère les catégories triées par leur nom
    @Query("SELECT * FROM category ORDER BY name")
    List<Category> getAll();

    // Requète qui récupère la catégorie dont l'id est passé en paramètre
    @Query("SELECT * FROM category WHERE id = :id")
    Category findById(int id);

    // Requète qui compte le nombre de catégorie
    @Query("SELECT COUNT(*) FROM category")
    int countItems();
}

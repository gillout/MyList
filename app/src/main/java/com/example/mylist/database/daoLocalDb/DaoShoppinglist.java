package com.example.mylist.database.daoLocalDb;
import com.example.mylist.model.Shoppinglist;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Classe permettant de réaliser les opérations de CRUD sur l'entité "shoppinglist" de la BD locale
 */
@Dao
public interface DaoShoppinglist {

    @Insert // Méthode qui mappe les objets avec la table et les ajoute
    void insertAll(List<Shoppinglist> items);

    @Insert // Méthode qui mappe l'objet avec la table et l'ajoute
    void insert(Shoppinglist item);

    @Update // Méthode qui mappe l'objet avec la table et le met à jour
    void update(Shoppinglist item);

    @Delete // Méthode qui mappe l'objet avec la table et le supprime
    void delete(Shoppinglist item);

    @Delete // Méthode qui mappe les objets avec la table et les supprime
    void deleteAll(List<Shoppinglist> items);

    // Requète qui récupère les "shoppinglist" triées par leur nom
    @Query("SELECT * FROM shoppinglist ORDER BY name")
    List<Shoppinglist> getAll();

    // Requète qui récupère la "shoppinglist" dont l'id est passé en paramètre
    @Query("SELECT * FROM shoppinglist WHERE id = :id")
    Shoppinglist findById(int id);

    // Requète qui récupère la "shoppinglist" dont le nom est passé en paramètre
    @Query("SELECT * FROM shoppinglist WHERE name = :name")
    Shoppinglist findByName(String name);

    // Requète qui compte le nombre de "shoppinglist"
    @Query("SELECT COUNT(*) FROM shoppinglist")
    int countItems();
}

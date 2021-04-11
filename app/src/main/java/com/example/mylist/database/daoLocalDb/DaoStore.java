package com.example.mylist.database.daoLocalDb;
import com.example.mylist.model.Store;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Classe permettant de réaliser les opérations de CRUD sur l'entité "store" de la BD locale
 */
@Dao
public interface DaoStore {

    @Insert // Méthode qui mappe les objets avec la table et les ajoute
    void insertAll(List<Store> items);

    @Insert // Méthode qui mappe l'objet avec la table et l'ajoute
    void insert(Store item);

    @Update // Méthode qui mappe l'objet avec la table et le met à jour
    void update(Store item);

    @Delete // Méthode qui mappe l'objet avec la table et le supprime
    void delete(Store item);

    @Delete // Méthode qui mappe les objets avec la table et les supprime
    void deleteAll(List<Store> items);

    // Requète qui récupère les magasins triés par leur nom
    @Query("SELECT * FROM store ORDER BY name")
    List<Store> getAll();

    // Requète qui récupère le magasin dont l'id est passé en paramètre
    @Query("SELECT * FROM store WHERE id = :id")
    Store findById(int id);

    // Requète qui compte le nombre de magasin
    @Query("SELECT COUNT(*) FROM store")
    int countItems();
}

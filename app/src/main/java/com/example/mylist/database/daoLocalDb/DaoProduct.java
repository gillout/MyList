package com.example.mylist.database.daoLocalDb;

import com.example.mylist.model.Product;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Classe permettant de réaliser les opérations de CRUD sur l'entité "product" de la BD locale
 */
@Dao
public interface DaoProduct {

    @Insert // Méthode qui mappe les objets avec la table et les ajoute
    void insertAll(List<Product> items);

    @Insert // Méthode qui mappe l'objet avec la table et l'ajoute
    void insert(Product item);

    @Update // Méthode qui mappe l'objet avec la table et le met à jour
    void update(Product item);

    @Delete // Méthode qui mappe l'objet avec la table et le supprime
    void delete(Product item);

    @Delete // Méthode qui mappe les objets avec la table et les supprime
    void deleteAll(List<Product> items);

    // Requète qui récupère les produits triés par leur nom
    @Query("SELECT * FROM product ORDER BY name")
    List<Product> getAll();

    // Requète qui récupère le produit dont l'id est passé en paramètre
    @Query("SELECT * FROM product WHERE id = :id")
    Product findById(int id);

    // Requète qui récupère les produits dont la shoppinglistId est passée en paramètre
    @Query("SELECT * FROM product WHERE shoppinglistId = :idList")
    List<Product> getProductByShoppingListId(int idList);

    // Requète qui compte le nombre de produits de la liste dont l'id est passé en paramètre
    @Query("SELECT COUNT(*) FROM product WHERE shoppinglistId = :idList")
    int countItems(int idList);

    // Requète qui fait la somme des prix des produits de la liste dont l'id est passé en paramètre
    @Query("SELECT SUM(price) FROM product WHERE shoppinglistId = :idList")
    double totalPrice(int idList);

}

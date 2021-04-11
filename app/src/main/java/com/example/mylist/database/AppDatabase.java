package com.example.mylist.database;

import android.content.Context;

import com.example.mylist.database.daoLocalDb.DaoProduct;
import com.example.mylist.database.daoLocalDb.DaoShoppinglist;
import com.example.mylist.model.Product;
import com.example.mylist.model.Shoppinglist;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Classe permettant de gérer l'instance de la base de données
 */
@Database(entities = {Product.class, Shoppinglist.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract DaoProduct productItemDao();
    public abstract DaoShoppinglist shoppinglistItemDao();

    /**
     * Crée une instance de la base de données si elle n'exite pas déjà, sinon récupère celle existante
     * @param context
     * @return L'instance de la base de données
     */
    public static AppDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app-database").
                    allowMainThreadQueries().
                    fallbackToDestructiveMigration().
                    build();
        }
        return instance;
    }

    /**
     * Détruit l'instance de la base de données
     */
    public static void destroyInstance(){
        instance = null;
    }
}

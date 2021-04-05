package com.example.mylist.database;

import android.content.Context;

import com.example.mylist.model.Category;
import com.example.mylist.model.Product;
import com.example.mylist.model.Shoppinglist;
import com.example.mylist.model.Store;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class, Shoppinglist.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract DaoProduct productItemDao();
    public abstract DaoShoppinglist shoppinglistItemDao();

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

    public static void destroyInstance(){
        instance = null;
    }
}

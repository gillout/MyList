package com.example.mylist.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mylist.R;
import com.example.mylist.admin.form.FormCategory;
import com.example.mylist.admin.form.FormProduct;
import com.example.mylist.admin.form.FormShoppinglist;
import com.example.mylist.admin.form.FormStore;
import com.example.mylist.admin.list.ListCategory;
import com.example.mylist.admin.list.ListProduct;
import com.example.mylist.admin.list.ListShoppinglist;
import com.example.mylist.admin.list.ListStore;

/**
 * Permet d'afficher la page d'accueil de l'admin
 */
public class HomeAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        getSupportActionBar().setTitle("Administration");
    }

    /**
     * Affiche l'Activity du formulaire des catégories
     * @param view
     */
    public void categoryForm(View view) {
        Intent i = new Intent(this, FormCategory.class);
        startActivity(i);
    }

    /**
     * Affiche l'Activity du formulaire des produits
     * @param view
     */
    public void productForm(View view) {
        Intent i = new Intent(this, FormProduct.class);
        startActivity(i);
    }

    /**
     * Affiche l'Activity du formulaire des magasins
     * @param view
     */
    public void storeForm(View view) {
        Intent i = new Intent(this, FormStore.class);
        startActivity(i);
    }

    /**
     * Affiche l'Activity du formulaire des listes de courses
     * @param view
     */
    public void shoppinglistForm(View view) {
        Intent i = new Intent(this, FormShoppinglist.class);
        startActivity(i);
    }

    /**
     * Affiche l'Activity de la liste des catégories
     * @param view
     */
    public void catList(View view) {
        Intent i = new Intent(this, ListCategory.class);
        startActivity(i);
    }

    /**
     * Affiche l'Activity de la liste des produits
     * @param view
     */
    public void prodList(View view) {
        Intent i = new Intent(this, ListProduct.class);
        startActivity(i);
    }

    /**
     * Affiche l'Activity de la liste des magasins
     * @param view
     */
    public void storeList(View view) {
        Intent i = new Intent(this, ListStore.class);
        startActivity(i);
    }

    /**
     * Affiche l'Activity de la liste des listes de courses
     * @param view
     */
    public void shoppinglistList(View view) {
        Intent i = new Intent(this, ListShoppinglist.class);
        startActivity(i);
    }
}
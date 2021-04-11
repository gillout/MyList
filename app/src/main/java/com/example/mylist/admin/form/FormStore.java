package com.example.mylist.admin.form;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylist.R;
import com.example.mylist.database.AppDatabase;
import com.example.mylist.admin.list.ListStore;
import com.example.mylist.model.Store;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Permet d'afficher un formulaire de magasin (partie admin)
 */
public class FormStore extends AppCompatActivity {

    private AppDatabase db;

    TextInputEditText storeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_store);
        getSupportActionBar().setTitle("Magasin");

        storeName = findViewById(R.id.eStoreName);
    }

    /**
     * Ajoute un enregistrement dans la table "store" en BD
     * @param view
     */
    public void storeRegistration(View view) {
        String name = storeName.getText().toString();
        Store store = new Store(name);
        db = AppDatabase.getInstance(this);
//        db.storeItemDao().insert(store);
        Intent i = new Intent(this, ListStore.class);
        startActivity(i);
    }
}
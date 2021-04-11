package com.example.mylist.admin.form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mylist.R;
import com.example.mylist.database.AppDatabase;
import com.example.mylist.admin.list.ListShoppinglist;
import com.example.mylist.model.Shoppinglist;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Permet d'afficher un formulaire de liste de courses (partie admin)
 */
public class FormShoppinglist extends AppCompatActivity {

    private AppDatabase db;

    TextInputEditText shoplistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_shoppinglist);
        getSupportActionBar().setTitle("Liste de courses");

        shoplistName = findViewById(R.id.eShoppinglistName);
    }

    /**
     * Ajoute un enregistrement dans la table "shoppinglist" en BD
     * @param view
     */
    public void shoppinglistRegistration(View view) {
        String name = shoplistName.getText().toString();
        Shoppinglist shoppinglist = new Shoppinglist(name);
        db = AppDatabase.getInstance(this);
//        db.shoppinglistItemDao().insert(shoppinglist);
        Intent i = new Intent(this, ListShoppinglist.class);
        startActivity(i);
    }
}
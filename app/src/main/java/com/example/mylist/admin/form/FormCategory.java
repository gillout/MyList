package com.example.mylist.admin.form;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylist.R;
import com.example.mylist.database.AppDatabase;
import com.example.mylist.admin.list.ListCategory;
import com.example.mylist.model.Category;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Permet d'afficher un formulaire de catégorie (partie admin)
 */
public class FormCategory extends AppCompatActivity {

    private AppDatabase db;

    TextInputEditText categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_category);
        getSupportActionBar().setTitle("Catégorie");

        categoryName = findViewById(R.id.eCategoryName);
    }

    /**
     * Ajoute un enregistrement dans la table "category" en BD
     * @param view
     */
    public void categoryRegistration(View view) {
        String name = categoryName.getText().toString();
        Category category = new Category(name);
        db = AppDatabase.getInstance(this);
//        db.categoryItemDao().insert(category);
        Intent i = new Intent(this, ListCategory.class);
        startActivity(i);
    }

}
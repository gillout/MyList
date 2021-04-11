package com.example.mylist.admin.form;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylist.R;
import com.example.mylist.database.AppDatabase;
import com.example.mylist.admin.list.ListProduct;
import com.example.mylist.model.Product;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Permet d'afficher un formulaire de produit (partie admin)
 */
public class FormProduct extends AppCompatActivity {

    private AppDatabase db;

    TextInputEditText productName, productBrand, productPrice, productCategory, productStore;
    ArrayAdapter aAdaptSpinCategory, aAdaptSpinStore;
    Spinner spinCategory, spinStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product);
        getSupportActionBar().setTitle("Produit");

        productName = findViewById(R.id.eProductName);
        productBrand = findViewById(R.id.eProductBrand);
        productPrice = findViewById(R.id.eProductPrice);
        productCategory = findViewById(R.id.eProductCategory);
        productStore = findViewById(R.id.eProductStore);
    }

    /**
     * Ajoute un enregistrement dans la table "product" en BD
     * @param view
     */
    public void productRegistration(View view) {
        String name = productName.getText().toString();
        String brand = productBrand.getText().toString();
        double price = Double.parseDouble(productBrand.getText().toString());
        String category = productBrand.getText().toString();
        String store = productBrand.getText().toString();
        Product product = new Product(name, brand, price, category, store);
        db = AppDatabase.getInstance(this);
        db.productItemDao().insert(product);
        Intent i = new Intent(this, ListProduct.class);
        startActivity(i);
    }

    /*
    editTxtName.editText?.doOnTextChanged { inputText, _, _, _ ->
        // Respond to input text change
    }
    */

}
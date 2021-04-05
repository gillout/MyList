package com.example.mylist.form;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylist.R;
import com.example.mylist.list.ListProduct;

public class FormProduct extends AppCompatActivity {

    EditText productName, productBrand, productPrice, productCategory, productStore;
    ArrayAdapter aAdaptSpinCategory, aAdaptSpinStore;
    Spinner spinCategory, spinStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product);
        productName = findViewById(R.id.eProductName);
        productBrand = findViewById(R.id.eProductBrand);
        productPrice = findViewById(R.id.eProductPrice);
        productCategory = findViewById(R.id.eProductCategory);
        productStore = findViewById(R.id.eProductStore);
    }

    public void productRegistration(View view) {
        String name = productName.getText().toString();
        String brand = productBrand.getText().toString();
        Double price = Double.parseDouble(productBrand.getText().toString());
        String category = productBrand.getText().toString();
        String store = productBrand.getText().toString();
        Intent i = new Intent(this, ListProduct.class);
        startActivity(i);
    }



    /*
    editTxtName.editText?.doOnTextChanged { inputText, _, _, _ ->
        // Respond to input text change
    }
    */

}
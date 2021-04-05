package com.example.mylist.form;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylist.R;
import com.example.mylist.list.ListCategory;

public class FormCategory extends AppCompatActivity {

    EditText categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_category);
        categoryName = findViewById(R.id.eCategoryName);
    }

    public void categoryRegistration(View view) {
        String name = categoryName.getText().toString();
        Intent i = new Intent(this, ListCategory.class);
        startActivity(i);
    }

}
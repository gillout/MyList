package com.example.mylist.form;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylist.R;
import com.example.mylist.list.ListStore;

public class FormStore extends AppCompatActivity {

    EditText storeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_store);
        storeName = findViewById(R.id.eStoreName);
    }

    public void storeRegistration(View view) {
        String name = storeName.getText().toString();
        Intent i = new Intent(this, ListStore.class);
        startActivity(i);
    }
}
package com.example.mylist.admin.list;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylist.R;

/**
 * Permet d'afficher la liste des magasins (partie admin)
 */
public class ListStore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_store);
        getSupportActionBar().setTitle("Magasins");
    }
}
package com.example.mylist.admin.list;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylist.R;

/**
 * Permet d'afficher la liste des listes de courses (partie admin)
 */
public class ListShoppinglist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_shoppinglist);
        getSupportActionBar().setTitle("Listes de courses");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
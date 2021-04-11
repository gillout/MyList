package com.example.mylist.list;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mylist.R;
import com.example.mylist.database.AppDatabase;
import com.example.mylist.model.Shoppinglist;
import com.example.mylist.util.Asynchrone;

 /**
  * Permet d'afficher la liste de tous les produits (en base distante)
  */
public class MyListActivityWs extends AppCompatActivity {

    private AppDatabase db;
    public static Shoppinglist shoplist;

    String listName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_ws);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setTitle("Liste Produits");

        Intent i = getIntent();
        if (i != null) {
            listName = i.getStringExtra("listname");
            db = AppDatabase.getInstance(this);
            MyListActivityWs.shoplist = db.shoppinglistItemDao().findByName(listName);
        }
        Toast.makeText(this, "Coucou", Toast.LENGTH_SHORT).show();

        // Le traitement pour afficher la liste se fait dans la classe Asynchrone
        Asynchrone asynchrone = new Asynchrone(this, "get");
        String url = "https://www.pleguy.ovh/index.php/produit/";
        String[] chaine = {url};
        asynchrone.execute(chaine);
    }

    /**
     * Affiche l'Activity de la liste des "shoppinglist"
     * @param view
     */
    public void toShoppingLists(View view) {
        Intent i = new Intent(this, MyListsActivity.class);
        startActivity(i);
    }
    
}
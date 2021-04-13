package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mylist.database.AppDatabase;
import com.example.mylist.admin.HomeAdmin;
import com.example.mylist.list.MyListActivity;
import com.example.mylist.list.MyListActivityWs;
import com.example.mylist.list.MyListsActivity;
import com.example.mylist.model.Product;
import com.example.mylist.model.Shoppinglist;

import java.util.List;

/**
 * Point de départ de l'application (permet d'afficher la première page
 * sur laquelle l'utilisateur arrive au lancement de l'application)
 */
public class MainActivity extends AppCompatActivity {

    private CardView cardProds, cardLists;
    private AppDatabase db;

    List<Shoppinglist> shoppinglists;
    int nbShoppinglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardProds = findViewById(R.id.cardProds);
        cardLists = findViewById(R.id.cardLists);

        db = AppDatabase.getInstance(this);
        nbShoppinglist = db.shoppinglistItemDao().getAll().size();
        Log.d("result", String.valueOf(nbShoppinglist));
        if (nbShoppinglist >= 1 ) {
//            cardProds.setVisibility(View.GONE);
            cardProds.setEnabled(true);
        } else {
//            cardProds.setVisibility(View.INVISIBLE);
            cardProds.setEnabled(false);
        }

        /**
         * Affiche l'Activity de la liste des produits du WS (serveur distant) lors du clic
         */
        cardProds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardElevationAndDisplayActivity(cardLists, 3, MyListActivityWs.class);
            }
        });

        /**
         * Affiche l'Activity de la liste des "shoppinglist" lors du clic
         */
        cardLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardElevationAndDisplayActivity(cardLists, 3, MyListsActivity.class);
            }
        });
    }

    /**
     * Définit le niveau d'élévation fournie en paramètre et affiche l'Activity cible
     * @param cardView Nom de la CardView
     * @param elevation Niveau d'élévation (donne un effet de superposition)
     * @param activityName Activity cible
     */
    public void cardElevationAndDisplayActivity(CardView cardView, int elevation, Class activityName) {
        cardView.setCardElevation(elevation);
        Intent i = new Intent(getApplicationContext(), activityName);
        startActivity(i);
    }

    /**
     * Affiche l'Activity d'accueil de l'admin
     * @param view
     */
    public void homeAdmin(View view) {
        Intent i = new Intent(this, HomeAdmin.class);
        startActivity(i);
    }

    /**
     * Ajoute des "shoppinglist"
     */
    public void createShoppinglists() {
        Shoppinglist shoppinglist1 = new Shoppinglist("Samedi");
        Shoppinglist shoppinglist2 = new Shoppinglist("Mercredi", true);
        Shoppinglist shoppinglist3 = new Shoppinglist("Lundi");
        db.shoppinglistItemDao().insert(shoppinglist1);
        db.shoppinglistItemDao().insert(shoppinglist2);
        db.shoppinglistItemDao().insert(shoppinglist3);
    }

    /**
     * Ajoute des produits aux "shoppinglist"
     */
    public void createProducts() {
        Product product1 = new Product("Steak haché", "Charal", 4.5, "Viande", "Carrefour", 1);
        Product product2 = new Product("Haricots verts", "Champs", 3.5, "Fruits et Légumes", "Auchan", 2);
        Product product3 = new Product("Cerises", "Foots", 2.8, "Fruits et Légumes", "Cora", 2);
        Product product4 = new Product("Pommes de terre", "Terro", 1.25, "Fruits et Légumes", "Franprix", 3);
        Product product5 = new Product("Bar", "Findus", 7.75, "Poisson", "Monoprix", 1);
        Product product6 = new Product("Brocolis", "Paturage", 1, "Fruits et Légumes", "Carrefour", 1);
        Product product7 = new Product("Poires", "Champs", 2.99, "Fruits et Légumes", "Leclerc", 3);
        Product product8 = new Product("Côte d'agneau", "Charal", 2.4, "Viande", "Leclerc", 3);
        Product product9 = new Product("Dorade royale", "Poissonnière", 2.8, "Poisson", "Carrefour", 1);
        Product product10 = new Product("Lait demi-écrémé", "Candia", 1.99, "Produits laitiers", "Carrefour", 3);
        Product product11 = new Product("Farine de blé complet", "Francine", 2.50, "Farine", "Auchan", 1);
        Product product12 = new Product("Café doux", "Carte noire", 2.99, "Café et thé", "Carrefour", 1);
        db.productItemDao().insert(product1);
        db.productItemDao().insert(product2);
        db.productItemDao().insert(product3);
        db.productItemDao().insert(product4);
        db.productItemDao().insert(product5);
        db.productItemDao().insert(product6);
        db.productItemDao().insert(product7);
        db.productItemDao().insert(product8);
        db.productItemDao().insert(product9);
        db.productItemDao().insert(product10);
        db.productItemDao().insert(product11);
        db.productItemDao().insert(product12);
    }

}
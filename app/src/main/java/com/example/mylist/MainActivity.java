package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mylist.database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    private CardView cardProds, cardLists;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardProds = findViewById(R.id.cardProds);
        cardLists = findViewById(R.id.cardLists);
        db = AppDatabase.getInstance(this);
//        Product product = new Product("Steak haché", "Charal", 3.50, "Viande", "Carrefour");
//        Product product1 = new Product("Haricots verts", "test", 3.50, "Fruits et Légumes", "Auchan");
//        Product product2 = new Product("Cerises", "test", 2.80, "Fruits et Légumes", "Cora");
//        Product product3 = new Product("Pommes de terre", "test", 2.80, "Fruits et Légumes", "Franprix");
//        Product product4 = new Product("Bar", "test", 2.80, "Poisson", "Monoprix");
//        Product product5 = new Product("Brocolis", "test", 2.80, "Fruits et Légumes", "Carrefour");
//        Product product6 = new Product("Poires", "test", 2.80, "Fruits et Légumes", "Leclerc");
//        Product product7 = new Product("Côte d'agneau", "test", 2.80, "Viande", "Leclerc");
//        Product product8 = new Product("Dorade royale", "test", 2.80, "Poisson", "Carrefour");
//        Product product9 = new Product("Cerises", "test", 2.80, "Fruits et Légumes", "Auchan");
//        Product product10 = new Product("Lait demi-écrémé", "Candia", 1.99, "Produits laitiers", "Carrefour");
//        Product product11 = new Product("Farine de blé complet", "Francine", 2.50, "Farine", "Auchan");
//        Product product12 = new Product("Café doux", "Carte noire", 2.99, "Café et thé", "Carrefour");
//        db.productItemDao().insert(product);
//        db.productItemDao().insert(product1);
//        db.productItemDao().insert(product2);
//        db.productItemDao().insert(product3);
//        db.productItemDao().insert(product4);
//        db.productItemDao().insert(product5);
//        db.productItemDao().insert(product6);
//        db.productItemDao().insert(product7);
//        db.productItemDao().insert(product8);
//        db.productItemDao().insert(product9);
//        db.productItemDao().insert(product10);
//        db.productItemDao().insert(product11);
//        db.productItemDao().insert(product12);

//        ArrayList<Product> products = new ArrayList<>();
//        products.add(product);

        //db.productItemDao().delete(product);
        //db.productItemDao().deleteAll(products);
//        db.productItemDao().getAll();
        //db.productItemDao().update(product);
//        products = (ArrayList<Product>) db.productItemDao().getAll();
//        for (Product prod : products){
//            Log.d("resultat", String.valueOf(prod));
//        }

        cardProds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardProds.setCardElevation(3);
                Intent i = new Intent(getApplicationContext(), ProductsListActivity.class);
                startActivity(i);
            }
        });

        cardLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardLists.setCardElevation(3);
                Intent i = new Intent(getApplicationContext(), MyListActivity.class);
                startActivity(i);
            }
        });

    }

    public void redirectMyList(View view) {
        Intent i = new Intent(this, MyListActivity.class);
        startActivity(i);
    }
}
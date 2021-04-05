package com.example.mylist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylist.database.AppDatabase;
import com.example.mylist.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MyListActivity extends AppCompatActivity {

    private AppDatabase db;
    ListView listViewMyProducts;
    AndroidAdapter androidAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        listViewMyProducts = findViewById(R.id.myListView);
        db = AppDatabase.getInstance(this);

        ArrayList<Product> products = (ArrayList<Product>) db.productItemDao().getAll();
        androidAdapter = new AndroidAdapter(this, R.layout.row_my_list, products);
        listViewMyProducts.setAdapter((ListAdapter) androidAdapter);

        double sumPrice = db.productItemDao().totalPrice();
        double sumPriceRounded = Math.round(sumPrice * 100.0) / 100.0;
        Toast.makeText(this, String.valueOf(sumPriceRounded) + " €",Toast.LENGTH_SHORT).show();
        TextView totalPrice = findViewById(R.id.totalPrice);
        int countProduct = db.productItemDao().countItems();
        TextView nbproduct = findViewById(R.id.nbProduct);
        TextView lNbProduct = findViewById(R.id.txtNbProduct);

        if(countProduct > 1){
            lNbProduct.setText("Produits");
        }
        totalPrice.setText(sumPriceRounded + " €");
        nbproduct.setText(String.valueOf(countProduct));
    }

    // Creation de l'adapter personnalisé
    public class AndroidAdapter extends ArrayAdapter<Product> {
        private Context context;
        private int resource;
        private int textViewResourceId;
        private ArrayList<Product> myListProducts;

        /**
         * Constructeur
         * @param context
         * @param resource
         * @param myListProducts
         */
        public AndroidAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> myListProducts) {
            super(context, resource, myListProducts);
            this.context = context;
            this.resource = resource;
            this.myListProducts = myListProducts;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.row_my_list, parent, false);
            }

            TextView textnom = convertView.findViewById(R.id.lProduit);
            TextView textcategorie = convertView.findViewById(R.id.lCategorie);

            String magasin = myListProducts.get(position).getStore();
            String nomProduit = myListProducts.get(position).getName();
            String categorieProduit = myListProducts.get(position).getCategory();
            textcategorie.setText(categorieProduit);
            textnom.setText(nomProduit);

            ImageView imageGeoNearby = convertView.findViewById(R.id.geo);
            ImageView imageDelete = convertView.findViewById(R.id.delete);
            Button deleteAll = findViewById(R.id.deleteList);

            imageGeoNearby.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Search for restaurants nearby
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + magasin + "");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });

            imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idProduit = myListProducts.get(position).getId();
                    db.productItemDao().delete(db.productItemDao().findById(idProduit));
                    Intent i = new Intent(getContext(), MyListActivity.class);
                    startActivity(i);
                }
            });

            deleteAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setCancelable(true);
                    builder.setTitle("Supprimer la liste");
                    builder.setMessage("Etes vous certains de vouloir supprimer votre liste ?");
                    builder.setPositiveButton("Confirmer",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    List<Product> products = db.productItemDao().getAll();
                                    db.productItemDao().deleteAll(products);
                                    Intent i = new Intent(getContext(), MyListActivity.class);
                                    startActivity(i);
                                    Toast.makeText(getContext(),"Votre liste a bien été supprimée", Toast.LENGTH_SHORT).show();
                                }
                            });
                    builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            return convertView;
        }
    }

}
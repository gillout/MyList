package com.example.mylist.database;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mylist.MainActivity;
import com.example.mylist.R;
import com.example.mylist.list.MyListActivityWs;
import com.example.mylist.model.Product;
import com.example.mylist.model.ProductWs;
import com.example.mylist.model.Shoppinglist;

import java.util.List;

/**
 * Adapter personnalisé pour l'affichage de la liste des produits (stockés en BD distante)
 */
public class ListAdapterWs extends ArrayAdapter<ProductWs> {

    private AppDatabase db;
    private Context context;
    private int resource;
    private List<ProductWs> listProductWs;

    /**
     * Constructeur
     * @param context
     * @param resource
     * @param listProductWs
     */
    public ListAdapterWs(@NonNull Context context, int resource, @NonNull List<ProductWs> listProductWs) {
        super(context, resource, listProductWs);
        this.resource = resource;
        this.context = context;
        this.listProductWs = listProductWs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resource, null);
        }

        // Récupération des Views de l'Activity (chaque ligne de la liste)
        LinearLayout lineListWs = v.findViewById(R.id.lineListViewWs);
        TextView txtNom = v.findViewById(R.id.lProduitWs);
        TextView txtCategorie = v.findViewById(R.id.lCategorieWs);
        ImageView addProduct = v.findViewById(R.id.addProduct);

        // Initialisation de variables pour les valeurs des propriétés d'un produit depuis la liste
        String nom = listProductWs.get(position).getProdName();
        String marque = listProductWs.get(position).getBrand();
        Double prix = listProductWs.get(position).getPrice();
        String categorie = listProductWs.get(position).getCatName();
        String magasin = listProductWs.get(position).getStoreName();

        // Attribution des valeurs aux Views
        txtNom.setText(nom);
        txtCategorie.setText(categorie);
        addProduct.setImageResource(R.drawable.addproduct);

        lineListWs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shoppinglist shoppinglist = MyListActivityWs.shoplist;
                Toast.makeText(getContext(), "Salut", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * Ajoute le produit cliqué à la "shoppinglist"
         */
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shoppinglist shoppinglist = MyListActivityWs.shoplist;
                String nameList = shoppinglist.getName();
                int idList = shoppinglist.getId();
                db = AppDatabase.getInstance(getContext());
                Product product = new Product(nom, marque, prix, categorie, magasin, idList);
                db.productItemDao().insert(product);
                Toast.makeText(getContext(), nom + " a bien été ajouté à votre liste " + nameList, Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}

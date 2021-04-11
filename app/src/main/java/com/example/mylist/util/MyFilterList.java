package com.example.mylist.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mylist.R;
import com.example.mylist.database.AppDatabase;
import com.example.mylist.model.Product;

import java.util.ArrayList;

/**
 * Classe de test (non utilisé actuellement)
 */
public class MyFilterList extends AppCompatActivity {

    private AppDatabase db;
    private SearchView searchView;
    private ListView listView;
    private ArrayList<Product> products;
    private AndroidAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_filter_list);
        initSearchWidgets();

        listView = (ListView) findViewById(R.id.shapesListView);
        db = AppDatabase.getInstance(this);
        products = (ArrayList<Product>) db.productItemDao().getAll();
        adapter = new AndroidAdapter(getApplicationContext(), 0, products);
        listView.setAdapter(adapter);
    }

    /**
     * Permet de filtrer dynamiquement par rapport à ce qui est entré dans le champs de recherche
     */
    private void initSearchWidgets() {
        searchView = (SearchView) findViewById(R.id.shapeListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                ArrayList<Product> products = (ArrayList<Product>) db.productItemDao().getAll();
                ArrayList<Product> filteredShapes = new ArrayList<Product>();
                for(Product product: products)
                {
                    if(product.getName().toLowerCase().contains(s.toLowerCase()))
                    {
                        filteredShapes.add(product);
                    }
//                    if(product.getCategory().toLowerCase().contains(s.toLowerCase()))
//                    {
//                        filteredShapes.add(product);
//                    }
//                    if(product.getStore().toLowerCase().contains(s.toLowerCase()))
//                    {
//                        filteredShapes.add(product);
//                    }
                }
                AndroidAdapter adapter = new AndroidAdapter(getApplicationContext(), 0, filteredShapes);
                listView.setAdapter(adapter);

                return false;
            }
        });
    }

    /**
     * Création de l'Adapter personnalisé
     */
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
            TextView txtNom = convertView.findViewById(R.id.lProduit);
            TextView txtCategorie = convertView.findViewById(R.id.lCategorie);

            String nomProduit = myListProducts.get(position).getName();
            String categorieProduit = myListProducts.get(position).getCategory();

            txtCategorie.setText(categorieProduit);
            txtNom.setText(nomProduit);

            return convertView;
        }
    }

}
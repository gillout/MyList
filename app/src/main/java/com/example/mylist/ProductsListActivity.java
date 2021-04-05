package com.example.mylist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.mylist.database.AppDatabase;
import com.example.mylist.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsListActivity extends AppCompatActivity {

    private AppDatabase db;
    ListView listViewProducts;
    AndroidAdapter androidAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        listViewProducts = findViewById(R.id.productsListView);
        db = AppDatabase.getInstance(this);

        ArrayList<Product> products = (ArrayList<Product>) db.productItemDao().getAll();
        androidAdapt = new AndroidAdapter(this, R.layout.row_products_list, products);
        listViewProducts.setAdapter((ListAdapter) androidAdapt);

    }

    // Creation de l'adapter personnalisé
    public class AndroidAdapter extends ArrayAdapter<Product> {
        private Context context;
        private int resource;
        private int textViewResourceId;
        private ArrayList<Product> products;

        /**
         * Constructeur
         * @param context
         * @param resource
         * @param products
         */
        public AndroidAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> products) {
            super(context, resource, products);
            this.context = context;
            this.resource = resource;
            this.products = products;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.row_products_list, parent, false);
            }

            return convertView;
        }
    }

}
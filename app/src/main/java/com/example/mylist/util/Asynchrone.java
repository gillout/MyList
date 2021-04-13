package com.example.mylist.util;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.mylist.list.MyListActivityWs;
import com.example.mylist.R;
import com.example.mylist.database.AppDatabase;
import com.example.mylist.database.DaoProductWs;
import com.example.mylist.database.ListAdapterWs;
import com.example.mylist.model.ProductWs;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe permettant d'effectuer des opérations en tâches de fond
 */
public class Asynchrone extends AsyncTask<Object, Integer, String> {

    private AppDatabase db;

    private Activity activity;
    private String action;

    /**
     * Constructeur
     * @param activity
     * @param action
     */
    public Asynchrone(Activity activity, String action) {
        this.activity = activity;
        this.action = action;
    }

    /**
     * Permet de réaliser en tâches de fond les opérations de CRUD sur la BD distante
     * @param strings
     * @return Résultat de la requète
     */
    @Override
    protected String doInBackground(Object... strings) {
        String result = "";
        if(action.equals("create")){
            try {
                result = DaoProductWs.create((String) strings[0], (ProductWs)strings[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(action.equals("update")){
            try {
                result = DaoProductWs.update((String) strings[0], (ProductWs)strings[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(action.equals("delete")){
            try {
                result = DaoProductWs.delete((String) strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(action.equals("get")){
                try {
                    result = DaoProductWs.get((String) strings[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }

    /**
     * Convertit le JSON en objets de type ProductWs puis les met dans une liste
     * @param result Résultat retourné par la méthode doInBackground
     */
    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        if (action.equals("create")){
            Intent intent = new Intent(activity.getApplication(), MyListActivityWs.class);
            activity.startActivity(intent);
        }
        // Conversion du tableau JSON en Array de Product
        if (action.equals("get")){
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<ProductWs> productWs = new ArrayList<>();
                ProductWs[] arrayProduct = mapper.readValue(result, ProductWs[].class);
                productWs = Arrays.asList(arrayProduct);
                ListView listViewWs = activity.findViewById(R.id.listViewWs);

                ListAdapterWs aAdapterWs = new ListAdapterWs(activity.getApplicationContext(), R.layout.row_mylist_ws, productWs);
                listViewWs.setAdapter(aAdapterWs);
                initSearchWidgets(listViewWs,productWs);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Permet de filtrer dynamiquement en fonction de ce qui est entré dans le champs de recherche
     * @param listViewWs
     * @param productWs
     */
    private void initSearchWidgets(ListView listViewWs, List<ProductWs> productWs) {
        SearchView searchView = (SearchView) activity.findViewById(R.id.shapeListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) // s corresponds au text saisie dans le champs de recherche
            {
                // filteredShapes corresponds à la liste des objets filtrés
                ArrayList<ProductWs> filteredShapes = new ArrayList<ProductWs>();

                for(ProductWs product: productWs)
                {
                    // Comparaison du nom, de la catégorie, du magasin, à la chaine tapée dans le searchView
                    if (product.getProdName().toLowerCase().contains(s.toLowerCase()) ||
                            product.getCatName().toLowerCase().contains(s.toLowerCase()) ||
                            product.getStoreName().toLowerCase().contains(s.toLowerCase()))
                    {
                        filteredShapes.add(product);
                    }
                }
                ListAdapterWs adapter = new ListAdapterWs(activity.getApplicationContext(), R.layout.row_mylist_ws, filteredShapes);
                listViewWs.setAdapter(adapter);

                return false;
            }
        });
    }
}
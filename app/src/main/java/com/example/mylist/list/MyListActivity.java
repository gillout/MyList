package com.example.mylist.list;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylist.R;
import com.example.mylist.database.AppDatabase;
import com.example.mylist.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Permet d'afficher la liste des produits d'une liste de courses de l'utilisateur
 */
public class MyListActivity extends AppCompatActivity {

    private AppDatabase db;
    private SearchView searchView;
    private int countProduct;
    private double sumPrice, sumPriceRounded;

    Button addProd, listsOfLists, deleteAll;
    ListView listViewMyProducts;
    TextView nbproduct, lNbProduct, totalPrice;
    String sumPriceTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setTitle("Ma Liste");
        initSearchWidgets();
        addProd = findViewById(R.id.addProd);
        listsOfLists = findViewById(R.id.listsOfLists);
        listViewMyProducts = findViewById(R.id.myListView);
        deleteAll = findViewById(R.id.deleteList);
        db = AppDatabase.getInstance(this);

        ArrayList<Product> products = (ArrayList<Product>) db.productItemDao().getAll();

        AndroidAdapter adapter = new AndroidAdapter(this, R.layout.row_my_list, products);
        listViewMyProducts.setAdapter((ListAdapter) adapter);

        countProduct = db.productItemDao().countItems();
        nbproduct = findViewById(R.id.nbProduct);
        lNbProduct = findViewById(R.id.txtNbProduct);
        sumPrice = db.productItemDao().totalPrice();
        sumPriceRounded = Math.round(sumPrice * 100.0) / 100.0;
        totalPrice = findViewById(R.id.totalPrice);

        if(countProduct > 1){
            lNbProduct.setText("Produits");
        }
        nbproduct.setText(String.valueOf(countProduct));
        sumPriceTxt = sumPriceRounded + " €";
        totalPrice.setText(sumPriceTxt);
    }

    /**
     * Permet de filtrer dynamiquement en fonction de ce qui est entré dans le champs de recherche
     */
    private void initSearchWidgets() {
        searchView = (SearchView) findViewById(R.id.shapeListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) // s corresponds au text saisie dans le champs de recherche
            {
                // products corresponds à la liste afficher au début
                ArrayList<Product> products = (ArrayList<Product>) db.productItemDao().getAll();

                // filteredShapes corresponds à la liste des objets filtré
                ArrayList<Product> filteredShapes = new ArrayList<Product>();

                for(Product product: products)
                {
                    // on compare le nom, catégorie, magasin à la chaine taper dans le searchView
                    if(product.getName().toLowerCase().contains(s.toLowerCase()) ||
                            product.getCategory().toLowerCase().contains(s.toLowerCase()) ||
                            product.getStore().toLowerCase().contains(s.toLowerCase()))
                    {
                        filteredShapes.add(product);
                    }
                }

                AndroidAdapter adapter = new AndroidAdapter(getApplicationContext(), 0, filteredShapes);
                listViewMyProducts.setAdapter(adapter);

                return false;
            }
        });
    }

    /**
     * Affiche l'Activity de la liste des produits du WS (serveur distant)
     * @param view
     */
    public void toProductsWs(View view) {
        Intent i = new Intent(this, MyListActivityWs.class);
        startActivity(i);
    }

    /**
     * Affiche l'Activity de la liste des "shoppinglist"
     * @param view
     */
    public void toShoppingLists(View view) {
        Intent i = new Intent(this, MyListsActivity.class);
        startActivity(i);
    }

    /**
     * Adapter personnalisé pour l'affichage des listes de produits (stockés en BD locale)
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

            TextView textnom = convertView.findViewById(R.id.lProduit);
            TextView textcategorie = convertView.findViewById(R.id.lCategorie);

            String magasin = myListProducts.get(position).getStore();
            String nomProduit = myListProducts.get(position).getName();
            String categorieProduit = myListProducts.get(position).getCategory();

            textcategorie.setText(categorieProduit);
            textnom.setText(nomProduit);

            ImageView imageGeoNearby = convertView.findViewById(R.id.location);
            ImageView imageDelete = convertView.findViewById(R.id.delete);

            /**
             * Lance Google Maps afin de localiser les magasins les plus proches
             * correspondant au nom du magasin du produit cliqué
             */
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

            /**
             * Supprime le produit cliqué de la "shoppinglist"
             */
            imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idProduit = myListProducts.get(position).getId();
                    db.productItemDao().delete(db.productItemDao().findById(idProduit));
                    // Suppression du produit de la liste
                    myListProducts.remove(position);
                    // On soustrait un au nombre de produits
                    nbproduct.setText(String.valueOf(Integer.parseInt(nbproduct.getText().toString()) - 1));
                    // Soustraction du prix du produit supprimé au total
                    sumPrice -= myListProducts.get(position).getPrice();
                    sumPriceRounded = Math.round(sumPrice * 100.0) / 100.0;
                    sumPriceTxt = sumPriceRounded + " €";
                    totalPrice.setText(sumPriceTxt);
                    // Rafraîchissement de la vue
                    listViewMyProducts.invalidateViews();
                }
            });

            /**
             * Supprime la "shoppinglist" et tous les produits qui y sont liés
             */
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
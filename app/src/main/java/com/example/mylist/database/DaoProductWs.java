package com.example.mylist.database;

import com.example.mylist.model.ProductWs;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Classe permettant de réaliser les opérations de CRUD sur l'entité "product" de la BD distante
 */
public class DaoProductWs {

    // Instanciation d'un objet de type OkHttpClient
    static OkHttpClient client = new OkHttpClient();
    // Création d'un JSONArray pour stocker des objets sous forme JSON
    JSONArray jarray;

    /**
     * Définit le type de contenu
     */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Envoie une requète Http, d'ajout d'un produit, au serveur
     * @param url Adressse du serveur
     * @param productWs
     * @return Réponse du serveur
     * @throws IOException
     */
    public static String create(String url, ProductWs productWs) throws IOException {
        // Création d'un objet (librairie Jackson) pour la conversion (objet <=> JSON)
        ObjectMapper mapper = new ObjectMapper();
        // Conversion d'un objet en chaine JSON
        String chainejson = mapper.writeValueAsString(productWs);
        // Création de la requète à laquelle on passe la chaine JSON et l'URL du serveur ainsi que le type de requète Http
        RequestBody body = RequestBody.create(JSON, chainejson);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        // Exécution de la requète
        Response response = client.newCall(request).execute();
        // Retour de la réponse
        return response.body().string();
    }

    /**
     * Envoie une requète Http, de mise à jour d'un produit, au serveur
     * @param url Adresse du serveur
     * @param productWs
     * @return Réponse du serveur
     * @throws IOException
     */
    public static String update(String url, ProductWs productWs) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String chainejson = mapper.writeValueAsString(productWs);
        RequestBody body = RequestBody.create(JSON, chainejson);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * Envoie une requète Http, d'obtention des produits, au serveur
     * @param url Adresse du serveur
     * @return Réponse du serveur
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * Envoie une requète Http, de suppression d'un produit, au serveur
     * @param url Adresse du serveur
     * @return Réponse du serveur
     * @throws IOException
     */
    public static String delete(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

package javabasics;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javabasics.modelos.ModelOmdb;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do filme:");
        String input = "";
        String apikey = "383e2171";

        if (sc.hasNextLine()) {
            input = sc.nextLine();
            String endereco = "https://www.omdbapi.com/?t=" + input + "&apikey=" + apikey;
            System.out.println(endereco);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .create();
            ModelOmdb model = gson.fromJson(json, ModelOmdb.class);
            System.out.println(model);
        }

        sc.close();
    }
}
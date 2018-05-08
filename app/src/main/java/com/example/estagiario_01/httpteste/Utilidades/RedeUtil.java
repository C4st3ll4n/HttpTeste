package com.example.estagiario_01.httpteste.Utilidades;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RedeUtil {

    final static String GITHUB_URL =
            "https://api.github.com/search/repositories";

    final static String PARAMETRO_BUSCA = "q";

    final static String PARAMETRO_ORGANIZADOR = "sort";
    final static String organizarPor = "stars";

    public static URL buildUrl(String campoPesquisa){
        Uri construtor = Uri.parse(GITHUB_URL)
                .buildUpon()
                .appendQueryParameter(PARAMETRO_BUSCA,campoPesquisa)
                .appendQueryParameter(PARAMETRO_ORGANIZADOR,organizarPor)
                .build();

        URL url = null;
        try {
            url = new URL(construtor.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String pegarRespostaHttp(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

package com.example.estudando.service;

import android.os.AsyncTask;

import com.example.estudando.entidades.Curso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class HTTPService extends AsyncTask<Void, Void, Curso> {
    private final String nome;

    public HTTPService(String nome){
        this.nome = nome;
    }

    @Override
    protected Curso doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        if (this.nome != null && !(this.nome =="") && this.nome.length() == 8){
            try {

                URL url = new URL("http://192.168.124.12/eagleuniversity/api/api.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }

            } catch (MalformedURLException e){
                    e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Gson().fromJson(resposta.toString(), Curso.class);
    }
}

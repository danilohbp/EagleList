package com.example.estudando;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Application;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetalhesCursoBradesco extends AppCompatActivity {

    private ImageView bannerCurso;
    private TextView tituloCurso, detalhesCursoView;
    private String descricaoCurso;
    private String baseUrl = "https://www.ev.org.br";
    private String detalhesUrl;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_curso);

        Toolbar detalhesCursoToolbar = (Toolbar) findViewById(R.id.barra_app_detalhes_curso);
        detalhesCursoToolbar.setTitle(" ");
        setSupportActionBar(detalhesCursoToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        bannerCurso = findViewById(R.id.bannerCurso);
        tituloCurso = findViewById(R.id.tituloCurso);
        detalhesCursoView = findViewById(R.id.descricaoCurso);

        tituloCurso.setText(getIntent().getStringExtra("titulo"));
//        bannerCurso.setImageResource(R.drawable.bradesco);
        //Picasso.get().load(getIntent().getStringExtra("imagem")).into(bannerCurso);
        String teste = getIntent().getStringExtra("fundacao");

        Conteudo conteudo = new Conteudo();
        conteudo.execute();
    }

    private class Conteudo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            detalhesCursoView.setText(descricaoCurso);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                detalhesUrl = getIntent().getStringExtra("detalhes");
                url = baseUrl + detalhesUrl;
                Document doc = Jsoup.connect(url).get();
                Elements data = doc.select("div.o-internal-desc_txt");
                descricaoCurso = data
                        .select("p")
                        .text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_details, menu);
        return true;
    }

    public void acessarSite(View v){
        detalhesUrl = getIntent().getStringExtra("detalhes");
        url = baseUrl + detalhesUrl;
        Uri url1 = Uri.parse(url);
        Intent acessar = new Intent(Intent.ACTION_VIEW, url1);
        startActivity(acessar);
    }

    public void compartilharLink(View v) {
        detalhesUrl = getIntent().getStringExtra("detalhes");
        url = baseUrl + detalhesUrl;
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, url);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
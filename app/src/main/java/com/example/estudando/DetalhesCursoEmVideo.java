package com.example.estudando;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DetalhesCursoEmVideo extends AppCompatActivity {


    private ImageView bannerCurso;
    private TextView tituloCurso, detalhesCursoView, outrosDetalhesCursoView;
    private String descricaoCurso, outrosDetalhesCurso;
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
        //bannerCurso.setImageResource(R.drawable.cursoemvideo);
        Picasso.get().load(getIntent().getStringExtra("imagemDetalhes")).into(bannerCurso);
        String teste = getIntent().getStringExtra("fundacao");

        Conteudo2 conteudo2 = new Conteudo2();
        conteudo2.execute();
    }

    private class Conteudo2 extends AsyncTask<Void, Void, Void> {

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
                url = detalhesUrl;
                Document doc = Jsoup.connect(url).get();
                Elements data = doc.select("div.ld-tab-content");
                descricaoCurso = data
                        .select("p")
                        .eq(0)
                        .text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void acessarSite(View v){
        detalhesUrl = getIntent().getStringExtra("detalhes");
        url = detalhesUrl;
        Uri url1 = Uri.parse(url);
        Intent acessar = new Intent(Intent.ACTION_VIEW, url1);
        startActivity(acessar);
    }

    public void compartilharLink(View v) {
        detalhesUrl = getIntent().getStringExtra("detalhes");
        url = detalhesUrl;
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, url);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}

package com.example.estudando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DetalhesCurso extends AppCompatActivity {

    private ImageView bannerCurso;
    private TextView tituloCurso, detalhesCursoView, outrosDetalhesCursoView;
    private String descricaoCurso, outrosDetalhesCurso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_curso);

        bannerCurso = findViewById(R.id.bannerCurso);
        tituloCurso = findViewById(R.id.tituloCurso);
        detalhesCursoView = findViewById(R.id.descricaoCurso);
        outrosDetalhesCursoView = findViewById(R.id.outrosDetatalhes);

        tituloCurso.setText(getIntent().getStringExtra("titulo"));
        Picasso.get().load(getIntent().getStringExtra("imagem")).into(bannerCurso);

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
            outrosDetalhesCursoView.setText(outrosDetalhesCurso);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String baseUrl = "https://www.portalgsti.com.br/";
                String detalhesUrl = getIntent().getStringExtra("detalhes");

                String url = baseUrl + detalhesUrl;
                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("div.course__about");

                descricaoCurso = data.select("div.course__about__text")
                        .text();

                outrosDetalhesCurso = data.select("ul.course_about_list")
                        .select("li")
                        .text();





            } catch (IOException e) {
                e.printStackTrace();

            }
            return null;
        }


    }

    public void acessarSite(View v){

        String google = "https://www.portalgsti.com.br/";

        Uri url = Uri.parse(google);

        Intent acessar = new Intent(Intent.ACTION_VIEW, url);
        startActivity(acessar);

    }

}
package com.example.estudando.fragmentos;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.estudando.AdapterCursos;
import com.example.estudando.MainActivity;
import com.example.estudando.R;
import com.example.estudando.entidades.Curso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CursosEad extends Fragment {

    public AdapterCursos adapterCursos;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView texto;
    ArrayList<Curso> parseItem = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cursos_ead, container, false);
        recyclerView = view.findViewById(R.id.recyclerV);

        progressBar = view.findViewById(R.id.progressBar);
        texto = view.findViewById(R.id.textoAguarde);

        mostrarDados();
        return view;
    }

    public void mostrarDados(){

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterCursos = new AdapterCursos(getContext(), parseItem);
        recyclerView.setAdapter(adapterCursos);

        if (parseItem.isEmpty()){
            Conteudo conteudo = new Conteudo();
            conteudo.execute();

            //Site2 site2 = new Site2();
            //site2.execute();
        }
    }

    private class Conteudo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (parseItem.isEmpty()){
                progressBar.setVisibility(View.VISIBLE);
                texto.setVisibility(View.VISIBLE);
                progressBar.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in));
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            texto.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out));
            adapterCursos.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                String url = "https://www.portalgsti.com.br/cursos/?title=gratuito&category=";

                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("a.thumb__course");

                int size = data.size();

                for (int i=0; i<size; i++){

                    String nomeCurso = data.select("span.thumb__course__body")
                            .select("span.thumb__course__title")
                            .eq(i)
                            .text();

                    String especialidade = data.select("span.thumb__course__categories")
                            .eq(i)
                            .text();

                    String imagemCurso = data.select("figure")
                            .select("img")
                            .eq(i)
                            .attr("src");

                    String detalhesUrl = data.select("a.thumb__course")
                            .eq(i)
                            .attr("href");

                    parseItem.add(new Curso(nomeCurso, especialidade, "", "", url, imagemCurso, R.id.img_star, detalhesUrl));
                }


            } catch (IOException e) {
                e.printStackTrace();

                Intent intent = new Intent(getContext(), ErroConexao.class);
                startActivity(intent);
            }
            return null;
        }
    }


    /*private class Site2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (parseItem.isEmpty()){
                progressBar.setVisibility(View.VISIBLE);
                progressBar.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in));
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out));
            adapterCursos.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                String url = "http://www.brasilmaisdigital.org.br/index.php/pt-br/cursos-online/";

                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("div.media-body");

                int size = data.size();

                for (int i=0; i<size; i++){

                    String nomeCurso = data.select("h2.media-heading")
                            .select("a")
                            .eq(i)
                            .text();

                    String especialidade = data.select("div.article-aside")
                            .select("span")
                            .eq(i)
                            .text();

                    String imageUrl = "https://img.portalgsti.com.br/kPN_QWko_hlpXm4dcjotEcVdv1Q=/270x160/https://www.portalgsti.com.br/media/uploads/course/loiane-groner/2017/01/31/curso-java-basico.png";

                    parseItem.add(new Curso(nomeCurso, especialidade, "", "", url, imageUrl, R.id.img_star, ""));
                }

            } catch (IOException e) {
                e.printStackTrace();

                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
            return null;
        }
    }*/
}

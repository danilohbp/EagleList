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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.estudando.AdapterCursos;
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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapterCursos);

        if (parseItem.isEmpty()){
            Conteudo conteudo = new Conteudo();
            conteudo.execute();

            Site2 site2 = new Site2();
            site2.execute();
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
                String url = "https://www.ev.org.br/areas-de-interesse/tecnologia";
                Document doc = Jsoup.connect(url).get();
                Elements data = doc.select("div.o-internal-area_cards");
                int size = data.size();
                for (int i=0; i<11; i++){
                    String nomeCurso = data
                            .select("article.o-internal-area_card")
                            .select("div.m-card_ctn")
                            .select("div.m-card_hld")
                            .select("h3.m-card_title")
                            .eq(i)
                            .text();

                    String descricao = data
                            .select("article.o-internal-area_card")
                            .select("div.m-card_ctn")
                            .select("div.m-card_hld")
                            .select("m-card_desc")
                            .eq(i)
                            .text();

                    String detalhesUrl = data
                            .select("article.o-internal-area_card")
                            .select("a.m-card_link")
                            .eq(i)
                            .attr("href");

                    String imgUrl = " ";
                    parseItem.add(new Curso(nomeCurso, R.id.curso, R.id.img_star, imgUrl, detalhesUrl, "bradesco"));
                }
            } catch (IOException e) {
                e.printStackTrace();
                /*Intent intent = new Intent(getContext(), ErroConexao.class);
                startActivity(intent);*/
            }
            return null;
        }
    }


    private class Site2 extends AsyncTask<Void, Void, Void> {
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

                String url = "https://www.cursoemvideo.com/cursos/";
                Document doc = Jsoup.connect(url).get();
                Elements data = doc.select("div.ld-course-list-items");
                int size = data.size();
                for (int i=0; i<20; i++){
                    String nomeCurso = data
                            .select("div.ld_course_grid")
                            .select("article.course")
                            .select("div.caption")
                            .select("h3.entry-title")
                            .eq(i)
                            .text();

                    String detalhesUrl = data
                            .select("div.ld-course-list-items")
                            .select("div.ld_course_grid")
                            .eq(i)
                            .select("article")
                            .select("a")
                            .attr("href");

                    String imgUrl = data
                            .select("div.ld_course_grid")
                            .eq(i)
                            .select("article")
                            .select("a")
                            .select("img")
                            .attr("src");

                    parseItem.add(new Curso(nomeCurso, R.id.curso, R.id.img_star, imgUrl, detalhesUrl, "cursoEmVideo"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

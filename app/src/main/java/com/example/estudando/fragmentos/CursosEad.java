package com.example.estudando.fragmentos;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

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

    ArrayList<Curso> parseItem = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cursos_ead, container, false);
        recyclerView = view.findViewById(R.id.recyclerV);

        progressBar = view.findViewById(R.id.progressBar);

        mostrarDados();
        return view;
    }

    public void mostrarDados(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Se houver algum erro troque para este dados.carregarLista()
        adapterCursos = new AdapterCursos(getContext(), parseItem);
        recyclerView.setAdapter(adapterCursos);

        Conteudo conteudo = new Conteudo();
        conteudo.execute();
    }

    private class Conteudo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out));
            adapterCursos.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://www.portalgsti.com.br/cursos";
                Document doc = Jsoup.connect(url).get();

                //Elements data = doc.select("div.data");
                Elements data = doc.select("a.thumb__course");

                int size = data.size();

                for (int i=0; i<size; i++){
                    String nomeCurso = data.select("a.thumb__course")
                            .select("span.thumb__course__title")
                            .eq(i)
                            .text();
                            //.attr("title");

                    String imagemCurso = data.select("a.thumb__course")
                            .select("img")
                            .eq(i)
                            .attr("src");

                    String detalhesUrl = data.select("a.thumb__course")
                            .attr("href");

                    parseItem.add(new Curso(nomeCurso, "", "", "", url, imagemCurso, R.id.img_star, detalhesUrl));
                }
            } catch (IOException e) {
                e.printStackTrace();

                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
            return null;
        }
    }
}

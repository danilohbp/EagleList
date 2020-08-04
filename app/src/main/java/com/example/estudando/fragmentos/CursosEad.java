package com.example.estudando.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.estudando.AdapterCursos;
import com.example.estudando.R;
import com.example.estudando.modelo.Curso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CursosEad extends Fragment {

    AdapterCursos adapterCursos;
    RecyclerView recyclerView;
    ArrayList<Curso> listaCurso;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cursos_ead, container, false);
        recyclerView = view.findViewById(R.id.recyclerV);
        listaCurso = new ArrayList<>();

        carregarLista();
        mostrarDados();
        return view;
    }

    public void carregarLista(){
        listaCurso.add(new Curso("WEB DESIGN","CSS, HTML e JavaScript", "Português/Inglês","20h", "Fundação Bradesco", R.id.img_star));
        listaCurso.add(new Curso("ADB","Administração de Banco de Dados", "Português/Inglês","40h", "Fundação Bradesco", R.id.img_star));
        listaCurso.add(new Curso("Docker","Infraestrutura de aplicações", "Português/Inglês","200h", "Fundação Bradesco", R.id.img_star));
        listaCurso.add(new Curso("Machine Learning","Python para I. A.", "Português/Inglês","100h", "Fundação Bradesco", R.id.img_star));
        listaCurso.add(new Curso("Linux Terminal","Shell Script", "Português/Inglês","120h", "Fundação Bradesco", R.id.img_star));
        listaCurso.add(new Curso("Microsoft Office","Pacote Office", "Português/Inglês","10h", "Fundação Bradesco", R.id.img_star));
        listaCurso.add(new Curso(".NET Framework","C Sharp", "Português/Inglês","15h", "Fundação Bradesco", R.id.img_star));
    }

    public void mostrarDados(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterCursos = new AdapterCursos(getContext(), listaCurso);
        recyclerView.setAdapter(adapterCursos);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        if (adapterCursos != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    adapterCursos.getFilter().filter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapterCursos.getFilter().filter(newText);
                    return false;
                }
            });
        }

        super.onCreateOptionsMenu(menu, inflater);
    }
}

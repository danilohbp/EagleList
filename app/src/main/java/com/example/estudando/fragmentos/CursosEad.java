package com.example.estudando.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.estudando.MainActivity;
import com.example.estudando.R;
import com.example.estudando.AdapterCursos;
import com.example.estudando.entidades.Curso;

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
}

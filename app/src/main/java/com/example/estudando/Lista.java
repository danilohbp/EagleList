package com.example.estudando;

import com.example.estudando.entidades.Curso;

import java.util.ArrayList;

public class Lista {

    ArrayList<Curso> listaCurso;

    public ArrayList listagem(){
        listaCurso = new ArrayList<>();
        listaCurso.add(new Curso("WEB DESIGN","CSS, HTML e JavaScript", "Português/Inglês","20h", "Fundação Bradesco", R.id.img_star));
        listaCurso.add(new Curso("ADB","Administração de Banco de Dados", "Português/Inglês","40h", "Fundação Bradesco", R.id.img_star));
        listaCurso.add(new Curso("Docker","Infraestrutura de aplicações", "Português/Inglês","200h", "Fundação Bradesco", R.id.img_star));
        listaCurso.add(new Curso("Machine Learning","Python para I. A.", "Português/Inglês","100h", "Fundação Bradesco", R.id.img_star));
        listaCurso.add(new Curso("Linux Terminal","Shell Script", "Português/Inglês","120h", "Fundação Bradesco", R.id.img_star));
        listaCurso.add(new Curso("Microsoft Office","Pacote Office", "Português/Inglês","10h", "Fundação Bradesco", R.id.img_star));
        listaCurso.add(new Curso(".NET Framework","C Sharp", "Português/Inglês","15h", "Fundação Bradesco", R.id.img_star));
        return listaCurso;
    }
}

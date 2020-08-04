package com.example.estudando;

import android.widget.Filter;

import com.example.estudando.modelo.Curso;

import java.util.ArrayList;

public class Filtro extends Filter {

    ArrayList<Curso> filtro;
    AdapterCursos adapter;

    public Filtro(ArrayList<Curso> filtro, AdapterCursos adapter) {
        this.filtro = filtro;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        FilterResults results = new FilterResults();

        if (charSequence != null && charSequence.length() > 0){
            charSequence = charSequence.toString().toUpperCase();
            ArrayList<Curso> filtroModelos = new ArrayList<>();

            for (int i = 0; i < filtro.size(); i++){
                if (filtro.get(i).getCurso().toUpperCase().contains(charSequence)){
                    filtroModelos.add(filtro.get(i));
                }
            }

            results.count = filtroModelos.size();
            results.values = filtroModelos;
        }

        else{
            results.count = filtro.size();
            results.values = filtro;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.model = (ArrayList<Curso>) filterResults.values;
        adapter.notifyDataSetChanged();
    }
}

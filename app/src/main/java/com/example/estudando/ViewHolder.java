package com.example.estudando;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView curso, especialidade, idioma, duracao, site;
    ImageView imageid, icon;
    ItemClickListener itemClickListener;

    ViewHolder(@NonNull View itemView) {
        super(itemView);
        curso = itemView.findViewById(R.id.curso);
        especialidade = itemView.findViewById(R.id.especialidade);
        idioma = itemView.findViewById(R.id.idioma_value);
        duracao = itemView.findViewById(R.id.duracao_value);
        site = itemView.findViewById(R.id.site_value);
        imageid = itemView.findViewById(R.id.img_star);


    }

    /*@Override
    public void onClick(View v) {
        this.itemClickListener.onIemClickListener(v, getLayoutPosition());
    }
    */
    public void setItemClickListener(ItemClickListener ic){

    }

    @Override
    public void onClick(View v) {

    }
}

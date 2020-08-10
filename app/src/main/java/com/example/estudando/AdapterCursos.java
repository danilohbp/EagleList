
package com.example.estudando;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.estudando.entidades.Curso;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterCursos extends RecyclerView.Adapter<AdapterCursos.ViewHolder> implements View.OnClickListener, Filterable {

    LayoutInflater inflater;
    ArrayList<Curso> model, filtroLista;  // Adicão dos parâmetros da classe Model num array
    Filtro filtro;
    private Context context;

    private View.OnClickListener listener;

    public AdapterCursos(Context context, ArrayList<Curso> model){
        this.context = context;
        //this.inflater = ;
        this.filtroLista = model;
        this.model = model;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView curso, especialidade, idioma, duracao, site;
        ImageView imageid;
        CircleImageView imageUrl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            curso = itemView.findViewById(R.id.curso);
            //especialidade = itemView.findViewById(R.id.especialidade_value);
            //idioma = itemView.findViewById(R.id.idioma_value);
            //duracao = itemView.findViewById(R.id.duracao_value);
            //site = itemView.findViewById(R.id.site_value);
            imageid = itemView.findViewById(R.id.img_star);
            imageUrl = itemView.findViewById(R.id.imagemCurso);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Curso cursoItem = model.get(position);

            Intent intent = new Intent(context, DetalhesCurso.class);
            intent.putExtra("titulo", cursoItem.getCurso());
            intent.putExtra("imagem", cursoItem.getImageUrl());
            intent.putExtra("detalhes", cursoItem.getDetalhes());
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    //public void setOnClickListener(View.OnClickListener listener) {
      //  this.listener = listener;
    //}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String curso = model.get(position).getCurso();
        //String especialidade = model.get(position).getEspecialidade();
        //String idioma = model.get(position).getIdioma();
        //String duracao = model.get(position).getDuracao();
        String site = model.get(position).getSite();

        int image = model.get(position).getImageid();
        holder.curso.setText(curso);
        //holder.especialidade.setText(especialidade);
        //holder.idioma.setText(idioma);
        //holder.duracao.setText(duracao);
        Picasso.get().load(model.get(position).getImageUrl()).into(holder.imageUrl);
        //holder.site.setText(site);
        holder.imageid.setImageResource(R.drawable.ic_star_border_black_24dp);

    }

    @Override
    public int getItemCount() {
        return model.size();
    }



    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }


    @Override
    public Filter getFilter() {
        if (filtro == null){
            filtro = new Filtro(filtroLista,this);
        }
        return filtro;
    }
}

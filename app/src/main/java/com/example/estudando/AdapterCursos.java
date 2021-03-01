
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
import android.widget.Toast;

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
        CircleImageView imagemCurso;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            curso = itemView.findViewById(R.id.curso);
            //especialidade = itemView.findViewById(R.id.especialidade_valor);
            //idioma = itemView.findViewById(R.id.idioma_value);
            //duracao = itemView.findViewById(R.id.duracao_valor);
            //site = itemView.findViewById(R.id.site_value);
            imageid = itemView.findViewById(R.id.img_star);
            //imageUrl = itemView.findViewById(R.id.imagemCurso);
            imagemCurso = itemView.findViewById(R.id.imagemCurso);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Curso cursoItem = model.get(position);

            if (cursoItem.getFundacao() == "bradesco"){
                Intent intent = new Intent(context, DetalhesCursoBradesco.class);
                intent.putExtra("titulo", cursoItem.getCurso());
                intent.putExtra("imagem", cursoItem.getImageUrl());
                intent.putExtra("detalhes", cursoItem.getDetalhes());
                context.startActivity(intent);
            }
            else if(cursoItem.getFundacao() == "cursoEmVideo"){
                Intent intent = new Intent(context, DetalhesCursoEmVideo.class);
                intent.putExtra("titulo", cursoItem.getCurso());
                intent.putExtra("imagem", cursoItem.getImageUrl());
                intent.putExtra("imagemDetalhes", cursoItem.getImgDetalhesUrl());
                intent.putExtra("detalhes", cursoItem.getDetalhes());
                context.startActivity(intent);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        String curso = model.get(position).getCurso();
        //String especialidade = model.get(position).getEspecialidade();
        //String idioma = model.get(position).getIdioma();
        //String duracao = model.get(position).getDuracao();
        String site = model.get(position).getSite();

        int image = model.get(position).getImageid();
        holder.curso.setText(curso);
        //holder.especialidade.setText(especialidade);
        //holder.duracao.setText(duracao);

        if (position<11){
            holder.imagemCurso.setImageResource(R.drawable.curso);
        }
        else{
            //Picasso.get().load(model.get(position).getImageUrl()).into(holder.imagemCurso);
            holder.imagemCurso.setImageResource(R.drawable.cursoemvideo);
        }
        //holder.imageid.setImageResource(R.drawable.star_icon_black);

        holder.imageid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Esse item é: " + holder.curso.getText().toString(), Toast.LENGTH_LONG).show();
                holder.imageid.setImageResource(R.drawable.yellow_star);
            }
        });

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

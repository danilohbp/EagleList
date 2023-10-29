
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

        TextView curso;
        ImageView imageid;
        CircleImageView imagemCurso;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            curso = itemView.findViewById(R.id.curso);
            imageid = itemView.findViewById(R.id.img_star);
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
            } else if(cursoItem.getFundacao() == "cursoEmVideo"){
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
        String site = model.get(position).getSite();

        int image = model.get(position).getImageid();
        holder.curso.setText(curso);

        if (position<11){
            holder.imagemCurso.setImageResource(R.drawable.curso);
        } else{
//            Picasso.get().load(model.get(position).getImageUrl()).into(holder.imagemCurso);
            holder.imagemCurso.setImageResource(R.drawable.cursoemvideo);
        }

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

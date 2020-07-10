
package com.example.estudando;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.estudando.entidades.Curso;
import com.example.estudando.ContentCourseEadActivity;
import java.util.ArrayList;

public class AdapterCursos extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener{

    LayoutInflater inflater;
    ArrayList<Curso> model;

    private View.OnClickListener listener;

    public AdapterCursos(Context context, ArrayList<Curso> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_courses, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String curso = model.get(position).getNome();
        String site = model.get(position).getSite();
        String especialidade = model.get(position).getEspecialidade();
        String duracao = model.get(position).getDuracao();
        String descricao = model.get(position).getDescricao();
        String data = model.get(position).getData();
        //int image = model.get(position).getImageid();
        holder.curso.setText(curso);
        holder.site.setText(site);
        holder.especialidade.setText(especialidade);
        holder.duracao.setText(duracao);
        holder.
        //holder.imageid.setImageResource(R.drawable.ic_star_border_black_24dp);



        /*holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onIemClickListener(View v, int position) {
                String gTitle = model.get(position).getCurso();
                String gDescription = model.get(position).getEspecialidade();

                Context c = null;
                Intent intent = new Intent(c, ContentCourseEadActivity.class);
                intent.putExtra("iTitle", gTitle);
                intent.putExtra("iDesc", gDescription);
                c.startActivity(intent);
            }
        });*/

        /*holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onIemClickListener(View v, int position) {

            }
        });*/


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

    /*public class ViewHolder extends RecyclerView.ViewHolder{

        TextView curso, especialidade, idioma, duracao, site;
        ImageView imageid, icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            curso = itemView.findViewById(R.id.curso);
            especialidade = itemView.findViewById(R.id.especialidade);
            idioma = itemView.findViewById(R.id.idioma_value);
            duracao = itemView.findViewById(R.id.duracao_value);
            site = itemView.findViewById(R.id.site_value);
            imageid = itemView.findViewById(R.id.img_star);
            
        }
    }*/
}

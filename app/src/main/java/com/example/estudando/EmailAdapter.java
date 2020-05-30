package com.example.estudando;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.estudando.model.Email;

import java.util.List;
import java.lang.*;

class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.EmailViewHolder> {

    private final List<Email> emails;

    public EmailAdapter(List<Email> emails){
        this.emails = emails;
    }

    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list, parent, false);
        return new EmailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder holder, int position) {
        Email email = emails.get(position);
        holder.bind(email);
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    class EmailViewHolder extends RecyclerView.ViewHolder {

        TextView txtUser;
        TextView subject;
        TextView preview;
        ImageView imgStar;

        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUser = itemView.findViewById(R.id.curso);
            subject = itemView.findViewById(R.id.idioma);
            preview = itemView.findViewById(R.id.txt_preview);
            imgStar = itemView.findViewById(R.id.img_star);
        }

        public void bind(Email email){
            txtUser.setText(email.getUser());
        }
    }
}

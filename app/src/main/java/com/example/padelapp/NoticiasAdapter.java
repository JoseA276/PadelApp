package com.example.padelapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.NoticiaViewHolder> {

    private List<Noticia> noticiasList;
    private Context context;

    public NoticiasAdapter(Context context, List<Noticia> noticiasList) {
        this.context = context;
        this.noticiasList = noticiasList;
    }

    @NonNull
    @Override
    public NoticiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_noticia, parent, false);
        return new NoticiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiaViewHolder holder, int position) {
        Noticia noticia = noticiasList.get(position);
        holder.tituloTextView.setText(noticia.getTitulo());

        // Load image with Glide
        Glide.with(context)
                .load(noticia.getUrlImagen())
                .placeholder(R.drawable.noticias)

                .into(holder.imagenImageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalleNoticiaActivity.class);
            intent.putExtra("titulo", noticia.getTitulo());
            intent.putExtra("contenido", noticia.getContenido());
            intent.putExtra("imagenUrl", noticia.getUrlImagen());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return noticiasList.size();
    }

    public static class NoticiaViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenImageView;
        TextView tituloTextView;

        public NoticiaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenImageView = itemView.findViewById(R.id.imagenImageView);
            tituloTextView = itemView.findViewById(R.id.tituloTextView);
        }
    }
}

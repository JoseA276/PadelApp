package com.example.padelapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.padelapp.Player.Player;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyAdapterTorneos extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<Torneo> torneoList;

    public MyAdapterTorneos(Context context, List<Torneo> torneoList) {
        this.context = context;
        this.torneoList = torneoList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_torneo, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Torneo torneo = torneoList.get(position);

        holder.recNombreTorneo.setText(torneoList.get(position).getNombreTorneo());
        holder.recLocalidad.setText(torneoList.get(position).getLocalidadTorneo());


        holder.recCardTorneo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                String fecha = dateFormat.format(torneo.getFechaTorneo()); // Formatea la fecha como una cadena

                try {
                    // Intenta analizar la cadena de fecha
                    Date parsedDate = dateFormat.parse(fecha);
                    holder.recFecha.setText(fecha); // Actualiza el TextView con la fecha formateada

                    // Crea el intent y agrega los datos
                    Intent intent = new Intent(context, BracketTorneosActivity.class);
                    intent.putExtra("Nombre torneo", torneoList.get(holder.getAdapterPosition()).getNombreTorneo());
                    intent.putExtra("Localidad Torneo", torneoList.get(holder.getAdapterPosition()).getLocalidadTorneo());
                    intent.putExtra("Fecha Torneo", parsedDate.getTime()); // Pasar tiempo en milisegundos
                    context.startActivity(intent);
                } catch (ParseException e) {
                    // Maneja la excepción de análisis aquí, como mostrar un mensaje de error
                    e.printStackTrace();
                    Toast.makeText(context, "Error al parsear la fecha", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return torneoList.size();
    }
    public void searchTorneoList(ArrayList<Torneo> searchTorneoList){
        torneoList = searchTorneoList;
        notifyDataSetChanged();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder {
    TextView recNombreTorneo, recLocalidad, recFecha;
    CardView recCardTorneo;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recCardTorneo = itemView.findViewById(R.id.recCardTorneo);
        recNombreTorneo = itemView.findViewById(R.id.recNombreTorneo);
        recLocalidad = itemView.findViewById(R.id.recLocalidadTorneo);
        recFecha = itemView.findViewById(R.id.recFecha);

    }
}
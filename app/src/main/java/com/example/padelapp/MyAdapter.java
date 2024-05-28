package com.example.padelapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.padelapp.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<Player> playerList;

    public MyAdapter(Context context, List<Player> playerList) {
        this.context = context;
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Player player = playerList.get(position);

        Glide.with(context).load(playerList.get(position).getImagePlayer()).into(holder.recImage);
        holder.recNombre.setText(playerList.get(position).getNombre());
        holder.recApellido.setText(playerList.get(position).getApellido());






        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edad = String.valueOf(player.getEdad());
                Integer.parseInt(edad);
                holder.recEdad.setText(edad);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image", playerList.get(holder.getAdapterPosition()).getImagePlayer());
                intent.putExtra("Apellido", playerList.get(holder.getAdapterPosition()).getApellido());
                intent.putExtra("Nombre", playerList.get(holder.getAdapterPosition()).getNombre());
                intent.putExtra("Edad", edad);
                context.startActivity(intent);


            }
        });
    }


    @Override
    public int getItemCount() {
        return playerList.size();
    }
    public void searchPlayerList(ArrayList<Player> searchPlayerList){
        playerList = searchPlayerList;
        notifyDataSetChanged();
    }
class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView recImage;
        TextView recNombre, recApellido, recEdad;
        CardView recCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recImage = itemView.findViewById(R.id.recImage);
            recCard = itemView.findViewById(R.id.recCard);
            recNombre = itemView.findViewById(R.id.recNombre);
            recApellido = itemView.findViewById(R.id.recApellido);
            recEdad = itemView.findViewById(R.id.recEdad);
        }
    }
}





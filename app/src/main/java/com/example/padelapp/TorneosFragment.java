package com.example.padelapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.padelapp.Player.Player;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TorneosFragment extends Fragment {
    FloatingActionButton fab;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<Torneo> torneoList;
    MyAdapterTorneos myAdapterTorneos;
    SearchView searchView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_torneos, container, false);
            fab= view.findViewById(R.id.fab);
            recyclerView = view.findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        searchView = view.findViewById(R.id.search);
        searchView.clearFocus();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setView(R.layout.progres_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        torneoList = new ArrayList<>();
        myAdapterTorneos = new MyAdapterTorneos(getActivity(), torneoList);
        recyclerView.setAdapter(myAdapterTorneos);

        databaseReference = FirebaseDatabase.getInstance().getReference("Torneos");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                torneoList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()){
                    Torneo torneo = itemSnapshot.getValue(Torneo.class);
                    torneoList.add(torneo);
                }
                myAdapterTorneos.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            dialog.dismiss();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), NewTorneoActivity.class);
                    startActivity(intent);
                }
            });
        return view;
    }
    public void searchList(String text){
        ArrayList<Torneo> searchList = new ArrayList<>();
        for (Torneo torneo: torneoList){
            if (torneo.getNombreTorneo().toLowerCase().contains(text.toLowerCase())){
                searchList.add(torneo);
            }
        }
        myAdapterTorneos.searchTorneoList(searchList);
    }
}
package com.example.padelapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NoticiasFragment extends Fragment {

    private RecyclerView recyclerView;
    private NoticiasAdapter noticiasAdapter;
    private List<Noticia> noticiasList = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference noticiasRef = db.collection("Noticia");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_noticias, container, false);

        recyclerView = rootView.findViewById(R.id.noticiasRecyclerView);
        noticiasAdapter = new NoticiasAdapter(getActivity(), noticiasList);
        recyclerView.setAdapter(noticiasAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        cargarNoticiasDesdeFirestore();

        return rootView;
    }

    private void cargarNoticiasDesdeFirestore() {
        noticiasRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                // Limpiar la lista de noticias antes de agregar nuevas
                noticiasList.clear();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Noticia noticia = documentSnapshot.toObject(Noticia.class);
                    noticiasList.add(noticia);
                }
                noticiasAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Manejar el error si la lectura falla
            }
        });
    }

}

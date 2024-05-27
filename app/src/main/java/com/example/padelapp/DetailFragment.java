package com.example.padelapp;

import static android.content.Intent.getIntent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



public class DetailFragment extends Fragment {
    TextView detailNombre, detailApellido, detailAltura, detailNacimiento,detailPosicion, detailPareja;
    ImageView detailImage;
    String imageUrl= "";


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
            detailNombre = view.findViewById(R.id.detailNombre);
            detailApellido = view.findViewById(R.id.detailApellido);
            detailAltura = view.findViewById(R.id.detailAltura);
            detailNacimiento = view.findViewById(R.id.detailNacimiento);
            detailPosicion = view.findViewById(R.id.detailPosicion);
            detailPareja = view.findViewById(R.id.detailPareja);

            Bundle bundle = getArguments();
            if (bundle != null){
                detailNombre.setText(bundle.getString("Nombre"));
                detailApellido.setText(bundle.getString("Apellido"));
                detailAltura.setText(bundle.getString("Altura"));
                detailNacimiento.setText(bundle.getString("Nacimiento"));
                detailPosicion.setText(bundle.getString("Posicion"));
                detailPareja.setText(bundle.getString("Pareja"));

                Glide.with(this).load(bundle.getString("Image")).into(detailImage);
            }

        return view;
    }
}
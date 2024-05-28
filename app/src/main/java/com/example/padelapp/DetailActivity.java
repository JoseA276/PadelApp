package com.example.padelapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    TextView detailNombre, detailApellido, detailAltura, detailNacimiento, detailPosicion, detailPareja;
    ImageView detailImage;
    String imageUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // Make sure you have a layout file named activity_detail.xml

        detailNombre = findViewById(R.id.detailNombre);
        detailApellido = findViewById(R.id.detailApellido);
        detailAltura = findViewById(R.id.detailAltura);
        detailNacimiento = findViewById(R.id.detailNacimiento);
        detailPosicion = findViewById(R.id.detailPosicion);
        detailPareja = findViewById(R.id.detailPareja);
        detailImage = findViewById(R.id.detailImagen);

        Intent intent = getIntent();
        if (intent != null) {
            detailNombre.setText(intent.getStringExtra("Nombre"));
            detailApellido.setText(intent.getStringExtra("Apellido"));
            detailAltura.setText(intent.getStringExtra("Altura"));
            detailNacimiento.setText(intent.getStringExtra("Nacimiento"));
            detailPosicion.setText(intent.getStringExtra("Posicion"));
            detailPareja.setText(intent.getStringExtra("Pareja"));
            imageUrl = intent.getStringExtra("Image");

            Glide.with(this).load(imageUrl).into(detailImage);
        }
    }
}

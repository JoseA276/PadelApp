package com.example.padelapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    TextView detailNombre, detailApellido, detailEdad;
    ImageView detailImage;
    String imageUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // Make sure you have a layout file named activity_detail.xml

        detailNombre = findViewById(R.id.detailNombre);
        detailApellido = findViewById(R.id.detailApellido);
        detailEdad = findViewById(R.id.detailEdad);

        detailImage = findViewById(R.id.detailImagen);

        Intent intent = getIntent();
        if (intent != null) {
            detailNombre.setText(intent.getStringExtra("Nombre"));
            detailApellido.setText(intent.getStringExtra("Apellido"));
            detailEdad.setText(intent.getStringExtra("Edad"));
            imageUrl = intent.getStringExtra("Image");

            Glide.with(this).load(imageUrl).into(detailImage);
        }
    }
}

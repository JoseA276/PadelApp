package com.example.padelapp;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetalleNoticiaActivity extends AppCompatActivity {

    private TextView tituloTextView;
    private TextView contenidoTextView;
    private ImageView imagenImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_noticia);

        tituloTextView = findViewById(R.id.tituloTextView);
        contenidoTextView = findViewById(R.id.contenidoTextView);
        imagenImageView = findViewById(R.id.imagenImageView);

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String contenido = intent.getStringExtra("contenido");
        String imagenUrl = intent.getStringExtra("imagenUrl");

        tituloTextView.setText(titulo);
        contenidoTextView.setText(contenido);

        Glide.with(this)
                .load(imagenUrl)
                .placeholder(R.drawable.prueba)  // Imagen de marcador de posición
                .into(imagenImageView);
    }
}

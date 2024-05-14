package com.example.padelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button button;
    Button calendarButton;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        calendarButton = findViewById(R.id.goCalendar);
        //Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        // Verificar si el usuario ha iniciado sesión
        if (user == null) {
            // Redirigir a la actividad de inicio de sesión si el usuario no ha iniciado sesión
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Mostrar el correo electrónico del usuario
            textView.setText(user.getEmail());
        }

        // Establecer listener de clic en el botón de cerrar sesión
        button.setOnClickListener(v -> {
            // Cerrar sesión y redirigir a la actividad de inicio de sesión
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Establecer listener de clic en el botón Ir al calendario
        calendarButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(intent);
        });

    }
}

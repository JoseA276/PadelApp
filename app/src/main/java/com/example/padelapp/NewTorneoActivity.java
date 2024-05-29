package com.example.padelapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.padelapp.Player.Player;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewTorneoActivity extends AppCompatActivity {
    Button guardarBoton;
    EditText nombreTorneo, fechaTorneo, localidadTorneo;

    Torneo torneo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_torneo);

        nombreTorneo = findViewById(R.id.torneoNombre);
        fechaTorneo = findViewById(R.id.fechaTorneo);
        localidadTorneo = findViewById(R.id.localidadTorneo);
        guardarBoton = findViewById(R.id.guardarButton);


        fechaTorneo.setOnClickListener(v -> showDatePickerDialog());

        guardarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    public void saveData() {
        uploadData();

    }

    public void uploadData() {

        String nombreJugador = nombreTorneo.getText().toString();
        String ubicacionTorneo = localidadTorneo.getText().toString();
        String fechaTorneoString = fechaTorneo.getText().toString();

        if (fechaTorneo == null) {
            Toast.makeText(this, "Selecciona una fecha para el torneo", Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date fechaTorneo;
        try {
            fechaTorneo = dateFormat.parse(fechaTorneoString);

        } catch (ParseException e) {
            // Si hay un error al convertir la fecha, muestra un mensaje de error
            Toast.makeText(this, "Formato de fecha incorrecto", Toast.LENGTH_SHORT).show();
            return;
        }



        Torneo torneo = new Torneo(nombreJugador, ubicacionTorneo, fechaTorneo);

        //We are changing the child from title to currentDate,
        // because we will be updating title as well and it may affect child value.

        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

            FirebaseDatabase.getInstance().getReference("Torneos").child(currentDate)
                .setValue(torneo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(NewTorneoActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NewTorneoActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void showDatePickerDialog() {
        // Obtén la fecha actual
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                NewTorneoActivity.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Muestra la fecha seleccionada en el EditText en formato español
                    calendar.set(selectedYear, selectedMonth, selectedDay);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String selectedDate = dateFormat.format(calendar.getTime());
                    fechaTorneo.setText(selectedDate);
                },
                year, month, day);

        datePickerDialog.show();
    }

}
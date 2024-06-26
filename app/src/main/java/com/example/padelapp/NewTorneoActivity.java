package com.example.padelapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.padelapp.Player.Player;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewTorneoActivity extends AppCompatActivity {
    Button guardarBoton;
    EditText nombreTorneo, fechaTorneo, localidadTorneo;
    List<Player> playerList;

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
        String nombreTorneoStr = nombreTorneo.getText().toString();
        String ubicacionTorneoStr = localidadTorneo.getText().toString();
        String fechaTorneoStr = fechaTorneo.getText().toString();

        if (fechaTorneoStr.isEmpty()) {
            Toast.makeText(this, "Selecciona una fecha para el torneo", Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date fechaTorneo;
        try {
            fechaTorneo = dateFormat.parse(fechaTorneoStr);
        } catch (ParseException e) {
            Toast.makeText(this, "Formato de fecha incorrecto", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener jugadores y luego crear y guardar el torneo
        obtenerJugadoresDesdeFirebase(nombreTorneoStr, ubicacionTorneoStr, fechaTorneo);
    }

    private void obtenerJugadoresDesdeFirebase(String nombreTorneo, String ubicacionTorneo, Date fechaTorneo) {
        DatabaseReference jugadoresRef = FirebaseDatabase.getInstance().getReference("Jugadores");
        jugadoresRef.limitToFirst(8).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playerList = new ArrayList<>();
                for (DataSnapshot jugadorSnapshot : snapshot.getChildren()) {
                    Player jugador = jugadorSnapshot.getValue(Player.class);
                    playerList.add(jugador);
                }
                if (playerList.size() == 8) {
                    crearYGuardarTorneo(nombreTorneo, ubicacionTorneo, fechaTorneo, playerList);
                } else {
                    Toast.makeText(NewTorneoActivity.this, "No hay suficientes jugadores en la base de datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NewTorneoActivity.this, "Error al obtener jugadores: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void crearYGuardarTorneo(String nombreTorneo, String ubicacionTorneo, Date fechaTorneo, List<Player> playerList) {
        Torneo torneo = new Torneo(nombreTorneo, ubicacionTorneo, fechaTorneo, playerList);

        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Torneos").child(currentDate)
                .setValue(torneo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(NewTorneoActivity.this, "Torneo guardado exitosamente", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NewTorneoActivity.this, "Error al guardar el torneo: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                NewTorneoActivity.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    calendar.set(selectedYear, selectedMonth, selectedDay);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String selectedDate = dateFormat.format(calendar.getTime());
                    fechaTorneo.setText(selectedDate);
                },
                year, month, day);
        datePickerDialog.show();
    }
}
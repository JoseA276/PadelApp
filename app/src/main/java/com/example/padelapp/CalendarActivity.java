package com.example.padelapp;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Inicializar vistas
        calendarView = findViewById(R.id.calendarView);
        // Inicializar calendario
        calendar = Calendar.getInstance();

        // Establecer la fecha actual
        setDateToToday();

        // Mostrar fecha actual en un Toast
        getDate();

        // Establecer listener de cambio de fecha
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Nota: el mes es 0-basado, por lo que necesitamos agregar 1
                Toast.makeText(CalendarActivity.this, dayOfMonth + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });



    }
    public void getDate() {
        // Obtener la fecha actual del CalendarView
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selected_date = simpleDateFormat.format(calendar.getTime());
        // Mostrar la fecha seleccionada en un Toast
        Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();
    }

    public void setDate(int day, int month, int year) {
        // Establecer la fecha en el objeto Calendar
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // El mes es 0-basado
        calendar.set(Calendar.DAY_OF_MONTH, day);
        // Establecer la fecha en el CalendarView
        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }

    public void setDateToToday() {
        // Obtener la fecha actual del sistema
        Calendar today = Calendar.getInstance();
        // Establecer la fecha actual en el CalendarView
        calendarView.setDate(today.getTimeInMillis());
    }
}
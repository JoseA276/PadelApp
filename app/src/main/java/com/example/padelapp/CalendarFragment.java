package com.example.padelapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.padelapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarFragment extends Fragment {

    CalendarView calendarView;
    Calendar calendar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        // Establecer Locale para que comience la semana en lunes
        Locale.setDefault(new Locale("es", "ES")); // Ejemplo para España
        calendarView = view.findViewById(R.id.calendarView);
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
                Toast.makeText(getActivity(), dayOfMonth + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void getDate() {
        // Obtener la fecha actual del CalendarView
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selected_date = simpleDateFormat.format(calendar.getTime());
        // Mostrar la fecha seleccionada en un Toast
        Toast.makeText(getActivity(), selected_date, Toast.LENGTH_SHORT).show();
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

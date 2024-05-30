package com.example.padelapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    private RecyclerView recyclerView;
    private MyAdapterTorneos adapterTorneos;
    private List<Torneo> torneoList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        recyclerView = view.findViewById(R.id.recyclerViewTorneos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        torneoList = new ArrayList<>();
        adapterTorneos = new MyAdapterTorneos(getContext(), torneoList);
        recyclerView.setAdapter(adapterTorneos);

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year);
            fetchTorneosForDate(selectedDate);
        });

        return view;
    }

    private void fetchTorneosForDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date selectedDate = dateFormat.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);
            int year = calendar.get(Calendar.YEAR) - 1900;
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            FirebaseDatabase.getInstance().getReference("Torneos")
                    .orderByChild("fechaTorneo/time") // Ordenar por el campo `time`
                    .startAt(calendar.getTimeInMillis())
                    .endAt(calendar.getTimeInMillis() + 86400000) // 86400000 ms en un día
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            torneoList.clear();
                            for (DataSnapshot torneoSnapshot : snapshot.getChildren()) {
                                Torneo torneo = torneoSnapshot.getValue(Torneo.class);
                                if (torneo != null) {
                                    torneoList.add(torneo);
                                }
                            }
                            if (torneoList.isEmpty()) {
                                Toast.makeText(getContext(), "No hay torneos para la fecha seleccionada", Toast.LENGTH_SHORT).show();
                            }
                            adapterTorneos.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getContext(), "Error al cargar los torneos", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al parsear la fecha seleccionada", Toast.LENGTH_SHORT).show();
        }
    }
}

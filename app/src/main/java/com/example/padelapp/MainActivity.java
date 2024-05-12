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
    TextView textView;
    FirebaseUser user;
    CalendarView calendarView;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();
        setDate(1,1,2024);
        getDate();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int dayOfMonth, int month, int year) {
                Toast.makeText(MainActivity.this, dayOfMonth + "/" + month+1  +"/" + year, Toast.LENGTH_SHORT).show();
            }
        });


        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            textView.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    public void getDate(){
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selected_date = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();


    }
    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }
}
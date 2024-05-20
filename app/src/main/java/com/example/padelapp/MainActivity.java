package com.example.padelapp;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.Button;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.padelapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button button;
    Button calendarButton;
    TextView textView;
    FirebaseUser user;
    ActivityMainBinding binding;
NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());
        setSupportActionBar(binding.toolbar);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                // Top-level destinations:
                R.id.noticiasFragment, R.id.rankingFragment, R.id.torneosFragment, R.id.calendarFragment)
                .setOpenableLayout(binding.drawerLayout)
                .build();



        NavController navController = ((NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        NavigationUI.setupWithNavController(binding.drawerNavView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        //------------------------------------
        /*button = findViewById(R.id.logout);
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
        });*/

    }
}

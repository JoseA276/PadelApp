package com.example.padelapp;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;

import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.padelapp.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

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

    }
}

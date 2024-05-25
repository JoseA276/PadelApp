package com.example.padelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.padelapp.databinding.ActivityMainBinding;
import com.example.padelapp.databinding.ActivityRankingBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RankingActivity extends AppCompatActivity {
    ActivityRankingBinding binding;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityRankingBinding.inflate(getLayoutInflater())).getRoot());
        fab = findViewById(R.id.fab);
        //setContentView(R.layout.activity_ranking);
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                // Top-level destinations:
                R.id.rankingActivity,R.id.torneosFragment, R.id.calendarFragment, R.id.noticiasFragment)
                .setOpenableLayout(binding.drawerLayout)
                .build();
        NavController navController = ((NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        NavigationUI.setupWithNavController(binding.drawerNavView, navController);
        NavigationUI.setupWithNavController(binding.toolbar ,navController, appBarConfiguration);*/



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });



    }
}
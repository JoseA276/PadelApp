package com.example.padelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PerfilFragment extends Fragment {

        private Button button;
        private TextView textView;
        private FirebaseAuth auth;
        private FirebaseUser user;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_perfil, container, false);

            // Inicializar vistas
            button = view.findViewById(R.id.logout);
            textView = view.findViewById(R.id.user_details);

            // Inicializar Firebase Auth
            auth = FirebaseAuth.getInstance();
            user = auth.getCurrentUser();

            // Verificar si el usuario ha iniciado sesión
            if (user == null) {
                // Redirigir a la actividad de inicio de sesión si el usuario no ha iniciado sesión
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                if (getActivity() != null) {
                    getActivity().finish();
                }
            } else {
                // Mostrar el correo electrónico del usuario
                textView.setText(user.getEmail());
            }

            // Establecer listener de clic en el botón de cerrar sesión
            button.setOnClickListener(v -> {
                // Cerrar sesión y redirigir a la actividad de inicio de sesión
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                if (getActivity() != null) {
                    getActivity().finish();
                }
            });

            return view;
        }
}
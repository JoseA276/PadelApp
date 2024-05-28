package com.example.padelapp.Player;

import com.google.type.Date;

public class Player {
    //NAME, SURNAME, ALTURA, NACIMIENTO, FECHA NACIMIENTO, POSICION, COMPAÑERO
    private String nombre;
    private String apellido;
    private int  edad;
    private String imagePlayer;

    public Player(){}

    public Player(String nombre, String apellido, int edad,  String imagePlayer) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.imagePlayer=imagePlayer;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getImagePlayer() {
        return imagePlayer;
    }

    public void setImagePlayer(String imagePlayer) {
        this.imagePlayer = imagePlayer;
    }

    @Override
    public String toString() {
        return "Player{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                '}';
    }
}

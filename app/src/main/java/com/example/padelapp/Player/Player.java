package com.example.padelapp.Player;

import com.google.type.Date;

public class Player {
    //NAME, SURNAME, ALTURA, NACIMIENTO, FECHA NACIMIENTO, POSICION, COMPAÑERO
    private String nombre;
    private String apellido;
    private double altura;
    private String nacimiento;
    private String posicion;
    private String pareja;
    private String imagePlayer;

    public Player(){}

    public Player(String nombre, String apellido, double altura, String nacimiento, String posicion, String pareja, String imagePlayer) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.altura = altura;
        this.nacimiento = nacimiento;
        this.posicion = posicion;
        this.pareja = pareja;
        this.imagePlayer=imagePlayer;
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

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getPareja() {
        return pareja;
    }

    public void setPareja(String pareja) {
        this.pareja = pareja;
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
                ", altura=" + altura +
                ", nacimiento=" + nacimiento +
                ", posicion='" + posicion + '\'' +
                ", pareja='" + pareja + '\'' +
                '}';
    }
}

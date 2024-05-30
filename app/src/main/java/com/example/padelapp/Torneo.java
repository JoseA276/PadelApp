package com.example.padelapp;

import com.example.padelapp.Player.Player;

import java.util.Date;
import java.util.List;

public class Torneo {
    private String nombreTorneo, localidadTorneo;
    private Date fechaTorneo;
    List<Player> playerList;
    public Torneo(){}

    public Torneo(String nombreTorneo, String localidadTorneo, Date fechaTorneo, List<Player> playerList) {
        this.nombreTorneo = nombreTorneo;
        this.localidadTorneo = localidadTorneo;
        this.fechaTorneo = fechaTorneo;
        this.playerList = playerList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public String getLocalidadTorneo() {
        return localidadTorneo;
    }

    public void setLocalidadTorneo(String localidadTorneo) {
        this.localidadTorneo = localidadTorneo;
    }

    public Date getFechaTorneo() {
        return fechaTorneo;
    }

    public void setFechaTorneo(Date fechaTorneo) {
        this.fechaTorneo = fechaTorneo;
    }

}




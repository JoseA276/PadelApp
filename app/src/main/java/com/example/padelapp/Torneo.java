package com.example.padelapp;

import java.util.Date;

public class Torneo {
    private String nombreTorneo, localidadTorneo;
    private Date fechaTorneo;
    public Torneo(){}

    public Torneo(String nombreTorneo, String localidadTorneo, Date fechaTorneo) {
        this.nombreTorneo = nombreTorneo;
        this.localidadTorneo = localidadTorneo;
        this.fechaTorneo = fechaTorneo;
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




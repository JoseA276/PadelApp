package com.example.padelapp;

public class Noticia {
    private String titulo;
    private String contenido;
    private String urlImagen; // Nuevo campo para la URL de la imagen

    public Noticia() {
        // Constructor vacío requerido por Firebase para convertir los documentos en objetos
    }

    public Noticia(String titulo, String contenido, String urlImagen) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.urlImagen = urlImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
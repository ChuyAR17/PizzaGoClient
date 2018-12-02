package com.chuy.pizzagoclient.models;

public class Locales {

    private Double latitud;
    private Double longitud;
    private String titulo;

    public Locales() {
    }

    public Locales(Double latitud, Double longitud, String titulo) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.titulo = titulo;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

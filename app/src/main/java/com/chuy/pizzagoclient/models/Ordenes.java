package com.chuy.pizzagoclient.models;

public class Ordenes {
    private String contenido;
    private Long costo;
    private Boolean hecha;
    private String domicilio;

    public Ordenes() {
    }

    public Ordenes(String contenido, Long costo, Boolean hecha, String domicilio) {
        this.contenido = contenido;
        this.costo = costo;
        this.hecha = hecha;
        this.domicilio = domicilio;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }

    public Boolean getHecha() {
        return hecha;
    }

    public void setHecha(Boolean hecha) {
        this.hecha = hecha;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
}

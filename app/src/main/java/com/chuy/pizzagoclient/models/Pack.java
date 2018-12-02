package com.chuy.pizzagoclient.models;

public class Pack {

    private String nombre;
    private String imagen;
    private Long costo;
    private String pizza;
    private String bebida;
    private String aperitivo;

    public Pack() {
    }

    public Pack(String nombre, String imagen, Long costo, String pizza, String bebida, String aperitivo) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.costo = costo;
        this.pizza = pizza;
        this.bebida = bebida;
        this.aperitivo = aperitivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getAperitivo() {
        return aperitivo;
    }

    public void setAperitivo(String aperitivo) {
        this.aperitivo = aperitivo;
    }
}

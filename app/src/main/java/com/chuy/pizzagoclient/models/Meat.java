package com.chuy.pizzagoclient.models;

public class Meat {

    private String imagen;
    private String nombre;
    private Long costo;

    public Meat() { }

    public Meat(String imagen, String nombre, Long costo) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.costo = costo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }
}

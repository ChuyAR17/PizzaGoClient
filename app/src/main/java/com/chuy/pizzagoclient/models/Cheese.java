package com.chuy.pizzagoclient.models;

public class Cheese {

    private String nombre;
    private String imagen;
    private Long costo;

    public Cheese(){ }

    public Cheese(String cheeseName, String cheesePicture, Long cheeseExtraCost) {
        this.nombre = cheeseName;
        this.imagen= cheesePicture;
        this.costo = cheeseExtraCost;
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
}

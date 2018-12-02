package com.chuy.pizzagoclient.models;

public class ExtraIngredient {

    private String nombre;
    private String imagen;
    private Long costo;

    public ExtraIngredient() {
    }

    public ExtraIngredient(String nombre, String imagen, Long costo) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.costo = costo;
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

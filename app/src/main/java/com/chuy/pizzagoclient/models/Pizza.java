package com.chuy.pizzagoclient.models;

import java.util.HashMap;
import java.util.List;

public class Pizza {

    private String nombre;
    private String imagen;
    private Long costo;
    private HashMap<Integer,String> ingredientes;

    public Pizza() {
    }

    public Pizza(String nombre, String imagen, Long costo, HashMap<Integer, String> ingredientes) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.costo = costo;
        this.ingredientes = ingredientes;
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

    public HashMap<Integer, String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(HashMap<Integer, String> ingredientes) {
        this.ingredientes = ingredientes;
    }
}

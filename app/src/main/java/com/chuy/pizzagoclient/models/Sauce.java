package com.chuy.pizzagoclient.models;

public class Sauce {

    //private String id;
    private String nombre;
    private String imagen;

    public Sauce(){ }

    public Sauce(String nombreSalsa, String imageSalsa){
        this.nombre = nombreSalsa;
        this.imagen = imageSalsa;
    }

    public String getNombreSalsa() {
        return nombre;
    }

    public void setNombreSalsa(String nombreSalsa) {
        this.nombre = nombreSalsa;
    }

    public String getImageSalsa() {
        return imagen;
    }

    public void setImageSalsa(String imageSalsa) {
        this.imagen= imageSalsa;
    }

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/
}

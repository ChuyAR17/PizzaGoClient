package com.chuy.pizzagoclient.models;

public class Sauce {

    private String nombreSalsa;
    private String imageSalsa;

    public Sauce(String nombreSalsa, String imageSalsa) {
        this.nombreSalsa = nombreSalsa;
        this.imageSalsa = imageSalsa;
    }

    public String getNombreSalsa() {
        return nombreSalsa;
    }

    public void setNombreSalsa(String nombreSalsa) {
        this.nombreSalsa = nombreSalsa;
    }

    public String getImageSalsa() {
        return imageSalsa;
    }

    public void setImageSalsa(String imageSalsa) {
        this.imageSalsa = imageSalsa;
    }
}

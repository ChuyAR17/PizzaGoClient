package com.chuy.pizzagoclient.models;

public class Aperitive {

    private String aperitivePicture;
    private String aperitiveTittle;
    private String aperitiveCost;

    public Aperitive(String aperitivePicture, String aperitiveTittle, String aperitiveCost) {
        this.aperitivePicture = aperitivePicture;
        this.aperitiveTittle = aperitiveTittle;
        this.aperitiveCost = aperitiveCost;
    }

    public String getAperitivePicture() {
        return aperitivePicture;
    }

    public void setAperitivePicture(String aperitivePicture) {
        this.aperitivePicture = aperitivePicture;
    }

    public String getAperitiveTittle() {
        return aperitiveTittle;
    }

    public void setAperitiveTittle(String aperitiveTittle) {
        this.aperitiveTittle = aperitiveTittle;
    }

    public String getAperitiveCost() {
        return aperitiveCost;
    }

    public void setAperitiveCost(String aperitiveCost) {
        this.aperitiveCost = aperitiveCost;
    }
}

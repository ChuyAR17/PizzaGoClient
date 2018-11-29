package com.chuy.pizzagoclient.models;

public class Meat {

    private String meatPicture;
    private String meatTittle;

    public Meat(String meatPicture, String meatTittle) {
        this.meatPicture = meatPicture;
        this.meatTittle = meatTittle;
    }

    public String getMeatPicture() {
        return meatPicture;
    }

    public void setMeatPicture(String meatPicture) {
        this.meatPicture = meatPicture;
    }

    public String getMeatTittle() {
        return meatTittle;
    }

    public void setMeatTittle(String meatTittle) {
        this.meatTittle = meatTittle;
    }
}

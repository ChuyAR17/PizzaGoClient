package com.chuy.pizzagoclient.models;

public class Pack {

    private String packPicture;
    private String packTittle;
    private String packCost;

    public Pack(String packPicture, String packTittle, String packCost) {
        this.packPicture = packPicture;
        this.packTittle = packTittle;
        this.packCost = packCost;
    }

    public String getPackPicture() {
        return packPicture;
    }

    public void setPackPicture(String packPicture) {
        this.packPicture = packPicture;
    }

    public String getPackTittle() {
        return packTittle;
    }

    public void setPackTittle(String packTittle) {
        this.packTittle = packTittle;
    }

    public String getPackCost() {
        return packCost;
    }

    public void setPackCost(String packCost) {
        this.packCost = packCost;
    }
}

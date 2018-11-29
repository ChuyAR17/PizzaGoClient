package com.chuy.pizzagoclient.models;

public class Cheese {

    private String cheeseName;
    private String cheesePicture;
    private String cheeseExtraCost;

    public Cheese(String cheeseName, String cheesePicture, String cheeseExtraCost) {
        this.cheeseName = cheeseName;
        this.cheesePicture = cheesePicture;
        this.cheeseExtraCost = cheeseExtraCost;
    }

    public String getCheeseName() {
        return cheeseName;
    }

    public void setCheeseName(String cheeseName) {
        this.cheeseName = cheeseName;
    }

    public String getCheesePicture() {
        return cheesePicture;
    }

    public void setCheesePicture(String cheesePicture) {
        this.cheesePicture = cheesePicture;
    }

    public String getCheeseExtraCost() {
        return cheeseExtraCost;
    }

    public void setCheeseExtraCost(String cheeseExtraCost) {
        this.cheeseExtraCost = cheeseExtraCost;
    }
}

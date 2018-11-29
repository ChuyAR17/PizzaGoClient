package com.chuy.pizzagoclient.models;

public class Drink {

    private String drinkPicture;
    private String drinkTittle;
    private String drinkCost;

    public Drink(String drinkPicture, String drinkTittle, String drinkCost) {
        this.drinkPicture = drinkPicture;
        this.drinkTittle = drinkTittle;
        this.drinkCost = drinkCost;
    }

    public String getDrinkPicture() {
        return drinkPicture;
    }

    public void setDrinkPicture(String drinkPicture) {
        this.drinkPicture = drinkPicture;
    }

    public String getDrinkTittle() {
        return drinkTittle;
    }

    public void setDrinkTittle(String drinkTittle) {
        this.drinkTittle = drinkTittle;
    }

    public String getDrinkCost() {
        return drinkCost;
    }

    public void setDrinkCost(String drinkCost) {
        this.drinkCost = drinkCost;
    }
}

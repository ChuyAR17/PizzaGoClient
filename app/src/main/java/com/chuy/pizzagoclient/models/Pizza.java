package com.chuy.pizzagoclient.models;

public class Pizza {
    private String pizzaTittle;
    private String pizzaPicture;
    private String pizzaCost;

    public Pizza(String pizzaTittle, String pizzaPicture, String pizzaCost) {
        this.pizzaTittle = pizzaTittle;
        this.pizzaPicture = pizzaPicture;
        this.pizzaCost = pizzaCost;
    }

    public String getPizzaTittle() {
        return pizzaTittle;
    }

    public void setPizzaTittle(String pizzaTittle) {
        this.pizzaTittle = pizzaTittle;
    }

    public String getPizzaPicture() {
        return pizzaPicture;
    }

    public void setPizzaPicture(String pizzaPicture) {
        this.pizzaPicture = pizzaPicture;
    }

    public String getPizzaCost() {
        return pizzaCost;
    }

    public void setPizzaCost(String pizzaCost) {
        this.pizzaCost = pizzaCost;
    }
}

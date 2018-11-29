package com.chuy.pizzagoclient.models;

public class ExtraIngredient {

    private String extraIngredientPicture;
    private String extraIngredientTittle;

    public ExtraIngredient(String extraIngredientPicture, String extraIngredientTittle) {
        this.extraIngredientPicture = extraIngredientPicture;
        this.extraIngredientTittle = extraIngredientTittle;
    }

    public String getExtraIngredientPicture() {
        return extraIngredientPicture;
    }

    public void setExtraIngredientPicture(String extraIngredientPicture) {
        this.extraIngredientPicture = extraIngredientPicture;
    }

    public String getExtraIngredientTittle() {
        return extraIngredientTittle;
    }

    public void setExtraIngredientTittle(String extraIngredientTittle) {
        this.extraIngredientTittle = extraIngredientTittle;
    }
}

package com.chuy.pizzagoclient.adapters;

import android.app.Activity;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.models.ExtraIngredient;

import java.util.ArrayList;

public class ExtraIngredientAdapterRecycler extends RecyclerView.Adapter<ExtraIngredientAdapterRecycler.ExtraIngredientViewHolder> {

    private ArrayList<ExtraIngredient> ingredients;
    private int resources;
    private Activity activity;

    public ExtraIngredientAdapterRecycler(ArrayList<ExtraIngredient> ingredients, int resources, Activity activity) {
        this.ingredients = ingredients;
        this.resources = resources;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ExtraIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resources, viewGroup, false);
        return new ExtraIngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExtraIngredientViewHolder extraIngredientViewHolder, int i) {
        ExtraIngredient ingredient = ingredients.get(i);

        extraIngredientViewHolder.extraIngredientTittle.setText(ingredient.getExtraIngredientTittle());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ExtraIngredientViewHolder extends RecyclerView.ViewHolder {

        ImageView extraIngredientPicture;
        TextView extraIngredientTittle;

        public ExtraIngredientViewHolder(@NonNull View itemView) {
            super(itemView);

            extraIngredientTittle = itemView.findViewById(R.id.ExtraIngredientTittle);
        }
    }
}

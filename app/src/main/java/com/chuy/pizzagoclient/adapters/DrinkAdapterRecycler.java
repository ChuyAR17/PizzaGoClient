package com.chuy.pizzagoclient.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.models.Drink;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DrinkAdapterRecycler extends RecyclerView.Adapter<DrinkAdapterRecycler.DrinkViewHolder> {

    private ArrayList<Drink> drinks;
    private int resource;
    private Activity activity;

    public DrinkAdapterRecycler(ArrayList<Drink> drinks, int resource, Activity activity) {
        this.drinks = drinks;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder drinkViewHolder, int i) {
        Drink drink = drinks.get(i);
        drinkViewHolder.drinkTittle.setText(drink.getDrinkTittle());
        drinkViewHolder.drinkCost.setText(drink.getDrinkCost());
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public class DrinkViewHolder extends RecyclerView.ViewHolder {

        ImageView drinkPicture;
        TextView drinkTittle;
        TextView drinkCost;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);

            drinkTittle = itemView.findViewById(R.id.CardMenuDrinkTittle);
            drinkCost = itemView.findViewById(R.id.CardMenuDrinkCost);
        }
    }
}

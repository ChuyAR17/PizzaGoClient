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
import com.chuy.pizzagoclient.models.Sauce;

import java.util.ArrayList;

public class SauceAdapterRecycler extends RecyclerView.Adapter<SauceAdapterRecycler.SauceViewHolder> {

    private ArrayList<Sauce> sauces;
    private int resource;
    private Activity activity;

    public SauceAdapterRecycler(ArrayList<Sauce> sauces, int resource, Activity activity) {
        this.sauces = sauces;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SauceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        return new SauceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SauceViewHolder sauceViewHolder, int i) {
        Sauce sauce = sauces.get(i);
        sauceViewHolder.tittleSauce.setText(sauce.getNombreSalsa());
    }

    @Override
    public int getItemCount() {
        return sauces.size();
    }

    public class SauceViewHolder extends RecyclerView.ViewHolder {

        private  ImageView pictureSauce;
        private TextView tittleSauce;

        public SauceViewHolder(@NonNull View itemView) {
            super(itemView);

            tittleSauce = itemView.findViewById(R.id.TypeOfSauceTittle);
        }
    }
}

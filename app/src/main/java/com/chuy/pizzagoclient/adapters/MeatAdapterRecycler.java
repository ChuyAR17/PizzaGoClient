package com.chuy.pizzagoclient.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.models.Meat;

import java.util.ArrayList;

public class MeatAdapterRecycler extends RecyclerView.Adapter<MeatAdapterRecycler.MeatViewHolder> {

    private ArrayList<Meat> meats;
    private int resources;
    private Activity activity;

    public MeatAdapterRecycler(ArrayList<Meat> meats, int resources, Activity activity) {
        this.meats = meats;
        this.resources = resources;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MeatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resources, viewGroup, false);
        return new MeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeatViewHolder meatViewHolder, int i) {
        Meat meat = meats.get(i);
        meatViewHolder.meatTittle.setText(meat.getMeatTittle());
    }

    @Override
    public int getItemCount() {
        return meats.size();
    }

    public class MeatViewHolder extends RecyclerView.ViewHolder {

        private ImageView meatPicture;
        private TextView meatTittle;

        public MeatViewHolder(@NonNull View itemView) {
            super(itemView);

            meatTittle = itemView.findViewById(R.id.TypeOfMeatTittle);
        }
    }
}

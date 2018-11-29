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
import com.chuy.pizzagoclient.models.Aperitive;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AperitiveAdapterRecycler extends RecyclerView.Adapter<AperitiveAdapterRecycler.AperitiveViewHolder> {

    private ArrayList<Aperitive> aperitives;
    private int resource;
    private Activity activity;

    public AperitiveAdapterRecycler(ArrayList<Aperitive> aperitives, int resource, Activity activity) {
        this.aperitives = aperitives;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AperitiveViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        return new AperitiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AperitiveViewHolder aperitiveViewHolder, int i) {
        Aperitive aperitive = aperitives.get(i);
        aperitiveViewHolder.aperitiveTittle.setText(aperitive.getAperitiveTittle());
        aperitiveViewHolder.aperitiveCost.setText(aperitive.getAperitiveCost());
    }

    @Override
    public int getItemCount() {
        return aperitives.size();
    }

    public class AperitiveViewHolder extends RecyclerView.ViewHolder {

        ImageView aperitivePicture;
        TextView aperitiveTittle;
        TextView aperitiveCost;

        public AperitiveViewHolder(@NonNull View itemView) {
            super(itemView);

            aperitiveTittle = itemView.findViewById(R.id.CardMenuAperitivesTittle);
            aperitiveCost = itemView.findViewById(R.id.CardMenuAperitivesCost);
        }
    }
}

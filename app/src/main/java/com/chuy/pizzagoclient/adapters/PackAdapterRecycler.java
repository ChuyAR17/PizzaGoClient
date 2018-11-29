package com.chuy.pizzagoclient.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.fragments.FragmentPackView;
import com.chuy.pizzagoclient.models.Pack;

import java.util.ArrayList;

public class PackAdapterRecycler extends RecyclerView.Adapter<PackAdapterRecycler.PackViewHolder> {

    private ArrayList<Pack> packs;
    private int resource;
    private Activity activity;

    public PackAdapterRecycler(ArrayList<Pack> packs, int resource, Activity activity) {
        this.packs = packs;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        return new PackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackViewHolder packViewHolder, int i) {
        Pack pack = packs.get(i);

        packViewHolder.packTittle.setText(pack.getPackTittle());
        packViewHolder.packCost.setText(pack.getPackCost());
    }

    @Override
    public int getItemCount() {
        return packs.size();
    }

    public class PackViewHolder extends RecyclerView.ViewHolder {

        ImageView packPicture;
        LinearLayout card;
        TextView packTittle;
        TextView packCost;

        public PackViewHolder(@NonNull View itemView) {
            super(itemView);

            packTittle = itemView.findViewById(R.id.CardMenuPackTittle);
            packCost = itemView.findViewById(R.id.CardMenuPackCost);
            card = itemView.findViewById(R.id.cardPack);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();

                    Toast.makeText(v.getContext(),"Funciona", Toast.LENGTH_SHORT).show();

                    FragmentPackView fragmentPackView = new FragmentPackView();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.ContainerFragmentPacks, fragmentPackView)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                }
            });
        }
    }
}

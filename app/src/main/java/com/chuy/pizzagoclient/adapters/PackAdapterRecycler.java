package com.chuy.pizzagoclient.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PackAdapterRecycler extends RecyclerView.Adapter<PackAdapterRecycler.PackViewHolder> {

    private ArrayList<Pack> packs;
    private int resource;
    private Activity activity;
    private String rutaImagen;

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

        packViewHolder.packTittle.setText(pack.getNombre());
        packViewHolder.packCost.setText("$"+String.valueOf(pack.getCosto()));
        Picasso.get().load(pack.getImagen()).resize(135,135).into(packViewHolder.packPicture);

        rutaImagen = pack.getImagen();
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
            packPicture = itemView.findViewById(R.id.CardMenuPackPicture);
            card = itemView.findViewById(R.id.cardPack);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();

                    savePreferences();

                    FragmentPackView fragmentPackView = new FragmentPackView();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.ContainerFragmentPacks, fragmentPackView)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                }
            });
        }

        private void savePreferences() {
            SharedPreferences preferences = activity.getSharedPreferences("Pack-seleccionado", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();

            editor.clear();
            editor.putString("nombre", packTittle.getText().toString());
            editor.putString("costo", packCost.getText().toString());
            editor.putString("imagen", rutaImagen);
            editor.apply();
        }

    }
}

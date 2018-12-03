package com.chuy.pizzagoclient.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.models.Meat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MeatAdapterRecycler extends RecyclerView.Adapter<MeatAdapterRecycler.MeatViewHolder> {

    private ArrayList<Meat> meats;
    private int resources;
    private Activity activity;

    private int porciones;
    private String numero;

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
        meatViewHolder.meatTittle.setText(meat.getNombre());
        meatViewHolder.meatCost.setText(String.valueOf(meat.getCosto()));
        Picasso.get().load(meat.getImagen()).resize(135, 135).into(meatViewHolder.meatPicture);
    }

    @Override
    public int getItemCount() {
        return meats.size();
    }

    public class MeatViewHolder extends RecyclerView.ViewHolder {

        private ImageView meatPicture;
        private TextView meatTittle;
        private TextView meatCost;

        private Button mas, menos;
        public TextView noPociones;

        public MeatViewHolder(@NonNull View itemView) {
            super(itemView);

            meatTittle = itemView.findViewById(R.id.TypeOfMeatTittle);
            meatPicture = itemView.findViewById(R.id.TypeOfMeatPicture);
            meatCost = itemView.findViewById(R.id.TypeOfMeatExtraCost);

            mas = itemView.findViewById(R.id.MeatButtonMas);
            menos = itemView.findViewById(R.id.MeatButtonMenos);
            noPociones = itemView.findViewById(R.id.MeatNoPorciones);

            mas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numero = noPociones.getText().toString();
                    sumar();
                    noPociones.setText(String.valueOf(porciones));
                }
            });

            menos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numero = noPociones.getText().toString();
                    restar();
                    noPociones.setText(String.valueOf(porciones));
                }
            });
        }

        private void restar() {
            if (numero == null)
                numero = "0";
            porciones = Integer.parseInt(numero);
            if ( porciones > 0) {
                porciones--;
            }else {
                porciones = 0;
            }

        }

        private void sumar() {
            if (numero == null)
                numero = "0";
            porciones = Integer.parseInt(numero);
            porciones += 1;
        }

    }

}

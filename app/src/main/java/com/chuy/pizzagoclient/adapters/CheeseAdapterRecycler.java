package com.chuy.pizzagoclient.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.models.Cheese;

import java.util.ArrayList;

public class CheeseAdapterRecycler extends RecyclerView.Adapter<CheeseAdapterRecycler.CheeseViewHolder> {

    private ArrayList<Cheese> cheeses;
    private int resource;
    private Activity activity;

    private int porciones;
    private String numero;

    public CheeseAdapterRecycler(ArrayList<Cheese> cheeses, int resource, Activity activity) {
        this.cheeses = cheeses;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CheeseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);

        return new CheeseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheeseViewHolder cheeseViewHolder, int i) {
        Cheese cheese = cheeses.get(i);
        cheeseViewHolder.cheeseTittle.setText(cheese.getCheeseName());
        cheeseViewHolder.cheeseExtraCost.setText(cheese.getCheeseExtraCost());

        //Falta asignar la imagen del queso!!!!!!!!!
    }

    @Override
    public int getItemCount() {
        return cheeses.size();
    }

    public class CheeseViewHolder extends RecyclerView.ViewHolder {

        private ImageView cheesePictureCard;
        private TextView cheeseTittle;
        private TextView cheeseExtraCost;

        private Button mas, menos;
        public TextView noPociones;

        public CheeseViewHolder(@NonNull View itemView) {
            super(itemView);

            cheesePictureCard = itemView.findViewById(R.id.TypeOfCheeseImage);
            cheeseTittle = itemView.findViewById(R.id.TypeOfCheeseTittle);
            cheeseExtraCost = itemView.findViewById(R.id.TypeOfCheeseExtraCost);

            mas = itemView.findViewById(R.id.CheeseButtonMas);
            menos = itemView.findViewById(R.id.CheeseButtonMenos);
            noPociones = itemView.findViewById(R.id.CheeseNoPorciones);

            //numero = noPociones.getText().toString();

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

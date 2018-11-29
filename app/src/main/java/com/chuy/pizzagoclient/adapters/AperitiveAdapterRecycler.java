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
import com.chuy.pizzagoclient.models.Aperitive;

import java.util.ArrayList;

public class AperitiveAdapterRecycler extends RecyclerView.Adapter<AperitiveAdapterRecycler.AperitiveViewHolder> {

    private ArrayList<Aperitive> aperitives;
    private int resource;
    private Activity activity;

    private int porciones;
    private String numero;

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

        private Button mas, menos;
        public TextView noPociones;

        public AperitiveViewHolder(@NonNull View itemView) {
            super(itemView);

            aperitiveTittle = itemView.findViewById(R.id.CardMenuAperitivesTittle);
            aperitiveCost = itemView.findViewById(R.id.CardMenuAperitivesCost);

            mas = itemView.findViewById(R.id.AperitivesButtonMas);
            menos = itemView.findViewById(R.id.AperitivesButtonMenos);
            noPociones = itemView.findViewById(R.id.AperitivesNoCantidad);

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

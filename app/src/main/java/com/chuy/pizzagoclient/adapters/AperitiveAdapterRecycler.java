package com.chuy.pizzagoclient.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.models.Aperitive;
import com.squareup.picasso.Picasso;

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
        aperitiveViewHolder.aperitiveTittle.setText(aperitive.getNombre());
        aperitiveViewHolder.aperitiveCost.setText(String.valueOf(aperitive.getCosto()));
        Picasso.get().load(aperitive.getImagen()).resize(135,135).into(aperitiveViewHolder.aperitivePicture);
    }

    @Override
    public int getItemCount() {
        return aperitives.size();
    }

    public class AperitiveViewHolder extends RecyclerView.ViewHolder {

        ImageView aperitivePicture;
        TextView aperitiveTittle;
        TextView aperitiveCost;
        LinearLayout card;

        private Button mas, menos;
        public TextView noPociones;

        public AperitiveViewHolder(@NonNull View itemView) {
            super(itemView);

            aperitiveTittle = itemView.findViewById(R.id.CardMenuAperitivesTittle);
            aperitiveCost = itemView.findViewById(R.id.CardMenuAperitivesCost);
            aperitivePicture = itemView.findViewById(R.id.CardMenuAperitivesPicture);
            card = itemView.findViewById(R.id.CardAperitivos);

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

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = activity.getSharedPreferences("CrearOrden", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.clear();

                    int total = Integer.parseInt(noPociones.getText().toString()) * Integer.parseInt(aperitiveCost.getText().toString());
                    editor.putString(aperitiveTittle.getText().toString(), "-" + total);

                    editor.apply();
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

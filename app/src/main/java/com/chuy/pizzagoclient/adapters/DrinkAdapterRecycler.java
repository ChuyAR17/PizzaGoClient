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
import com.chuy.pizzagoclient.models.Drink;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DrinkAdapterRecycler extends RecyclerView.Adapter<DrinkAdapterRecycler.DrinkViewHolder> {

    private ArrayList<Drink> drinks;
    private int resource;
    private Activity activity;

    private int porciones;
    private String numero;

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
        drinkViewHolder.drinkTittle.setText(drink.getNombre());
        drinkViewHolder.drinkCost.setText(String.valueOf(drink.getCosto()));
        Picasso.get().load(drink.getImagen()).resize(135,135).into(drinkViewHolder.drinkPicture);
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public class DrinkViewHolder extends RecyclerView.ViewHolder {

        ImageView drinkPicture;
        TextView drinkTittle;
        TextView drinkCost;
        LinearLayout card;

        private Button mas, menos;
        public TextView noPociones;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);

            drinkTittle = itemView.findViewById(R.id.CardMenuDrinkTittle);
            drinkCost = itemView.findViewById(R.id.CardMenuDrinkCost);
            drinkPicture = itemView.findViewById(R.id.CardMenuDrinkPicture);
            card = itemView.findViewById(R.id.cardDrink);

            mas = itemView.findViewById(R.id.DrinkButtonMas);
            menos = itemView.findViewById(R.id.DrinkButtonMenos);
            noPociones = itemView.findViewById(R.id.DrinkNoCantidad);

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

                    int total = Integer.parseInt(noPociones.getText().toString()) * Integer.parseInt(drinkCost.getText().toString());
                    editor.putString(drinkTittle.getText().toString(), "-" + total);

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

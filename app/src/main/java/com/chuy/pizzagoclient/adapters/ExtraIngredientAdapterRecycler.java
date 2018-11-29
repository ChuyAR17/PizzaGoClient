package com.chuy.pizzagoclient.adapters;

import android.app.Activity;
import android.media.Image;
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
import com.chuy.pizzagoclient.models.ExtraIngredient;

import java.util.ArrayList;

public class ExtraIngredientAdapterRecycler extends RecyclerView.Adapter<ExtraIngredientAdapterRecycler.ExtraIngredientViewHolder> {

    private ArrayList<ExtraIngredient> ingredients;
    private int resources;
    private Activity activity;

    private int porciones;
    private String numero;

    public ExtraIngredientAdapterRecycler(ArrayList<ExtraIngredient> ingredients, int resources, Activity activity) {
        this.ingredients = ingredients;
        this.resources = resources;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ExtraIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resources, viewGroup, false);
        return new ExtraIngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExtraIngredientViewHolder extraIngredientViewHolder, int i) {
        ExtraIngredient ingredient = ingredients.get(i);

        extraIngredientViewHolder.extraIngredientTittle.setText(ingredient.getExtraIngredientTittle());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ExtraIngredientViewHolder extends RecyclerView.ViewHolder {

        ImageView extraIngredientPicture;
        TextView extraIngredientTittle;

        private Button mas, menos;
        public TextView noPociones;

        public ExtraIngredientViewHolder(@NonNull View itemView) {
            super(itemView);

            extraIngredientTittle = itemView.findViewById(R.id.ExtraIngredientTittle);

            mas = itemView.findViewById(R.id.ExtrasButtonMas);
            menos = itemView.findViewById(R.id.ExtrasButtonMenos);
            noPociones = itemView.findViewById(R.id.ExtrasNoPorciones);

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

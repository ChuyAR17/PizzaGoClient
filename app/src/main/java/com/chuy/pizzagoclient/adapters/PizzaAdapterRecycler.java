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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.fragments.FragmentMenuPizzas;
import com.chuy.pizzagoclient.models.Pizza;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PizzaAdapterRecycler extends RecyclerView.Adapter<PizzaAdapterRecycler.PizzaViewHolder> {

    private ArrayList<Pizza> pizzas;
    private int resource;
    private Activity activity;
    private String rutaImagen, ingredientes;

    public PizzaAdapterRecycler(ArrayList<Pizza> pizzas, int resource, Activity activity) {
        this.pizzas = pizzas;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PizzaViewHolder pizzaViewHolder, int i) {
        Pizza pizza = pizzas.get(i);

        pizzaViewHolder.pizzaTittle.setText(pizza.getNombre());
        pizzaViewHolder.pizzaCost.setText(String.valueOf(pizza.getCosto()));
        Picasso.get().load(pizza.getImagen()).resize(135,135).into(pizzaViewHolder.pizzaPicture);
        pizzaViewHolder.rutaImagen.setText(pizza.getImagen());
        pizzaViewHolder.pizzaIngredients.setText(pizza.getIngredientes());

    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public class PizzaViewHolder extends RecyclerView.ViewHolder {

        ImageView pizzaPicture;
        TextView pizzaTittle;
        TextView pizzaCost;
        TextView pizzaIngredients;
        TextView rutaImagen;
        LinearLayout card;

        public PizzaViewHolder(@NonNull final View itemView) {
            super(itemView);

            pizzaTittle = itemView.findViewById(R.id.CardMenuPizzaTittle);
            pizzaCost = itemView.findViewById(R.id.CardMenuPizzaCost);
            pizzaPicture = itemView.findViewById(R.id.CardMenuPizzaPicture);
            rutaImagen = itemView.findViewById(R.id.CardMenuPizzaRuta);
            pizzaIngredients = itemView.findViewById(R.id.CardMenuPizzaIngredients);
            card = itemView.findViewById(R.id.CardMenuPizza);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();

                    SharedPreferences preferences = activity.getSharedPreferences("Pizza-seleccionada", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.clear();
                    editor.putString("nombre", pizzaTittle.getText().toString());
                    editor.putString("costo", pizzaCost.getText().toString());
                    editor.putString("imagen", rutaImagen.getText().toString());
                    editor.putString("ingredientes", pizzaIngredients.getText().toString());

                    editor.apply();

                    FragmentMenuPizzas fragmentMenuPizzas = new FragmentMenuPizzas();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.ContainerFragmentPizzas, fragmentMenuPizzas)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                }
            });
        }
    }
}

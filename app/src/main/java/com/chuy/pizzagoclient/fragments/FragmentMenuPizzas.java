package com.chuy.pizzagoclient.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuy.pizzagoclient.MenuNormal;
import com.chuy.pizzagoclient.R;
import com.squareup.picasso.Picasso;

public class FragmentMenuPizzas extends Fragment {

    private int porciones;
    private String numero;
    private Button mas, menos;
    public TextView noPociones, subtotal;
    private String nombre, costo, imagen, ingredientes;

    private TextView pizzaName, pizzaCost, pizzaIngredients, rutaImagen;
    private ImageView pizzaImage;
    private Button agregar;

    public FragmentMenuPizzas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_menu_pizzas, container, false);

        setRetainInstance(true);

        getSharedPreferences();

        pizzaName = view.findViewById(R.id.MenuPizzaViewTittle);
        pizzaCost = view.findViewById(R.id.MenuPizzaViewCost);
        pizzaImage = view.findViewById(R.id.MenuPizzaViewImage);
        pizzaIngredients = view.findViewById(R.id.MenuPizzaViewIngredients);
        subtotal = view.findViewById(R.id.PizzaSubtotal);
        rutaImagen = view.findViewById(R.id.PizzaRutaImagen);
        agregar = view.findViewById(R.id.PizzaAgregarOrden);

        Picasso.get().load(imagen).fit().into(pizzaImage);
        pizzaName.setText(nombre);
        pizzaCost.setText(costo);
        pizzaIngredients.setText(ingredientes);
        rutaImagen.setText(imagen);

        mas = view.findViewById(R.id.PizzaButtonMas);
        menos = view.findViewById(R.id.PizzaButtonMenos);
        noPociones = view.findViewById(R.id.PizzaNoCantidad);

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = noPociones.getText().toString();
                sumar();
                int total = Integer.parseInt(pizzaCost.getText().toString()) * porciones;
                noPociones.setText(String.valueOf(porciones));
                subtotal.setText(String.valueOf(total));

            }
        });

        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = noPociones.getText().toString();
                restar();
                noPociones.setText(String.valueOf(porciones));
                if (porciones>0){
                    int total =Integer.parseInt(subtotal.getText().toString()) - Integer.parseInt(pizzaCost.getText().toString());
                    subtotal.setText(String.valueOf(total));
                }else if (noPociones.getText().toString().equals("0")){
                    subtotal.setText("0");
                }
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("CrearOrden", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = preferences.edit();

                editor.clear();

                editor.putString(pizzaName.getText().toString(), "-" + Integer.parseInt(subtotal.getText().toString()));

                editor.apply();

                startActivity(new Intent(getContext(), MenuNormal.class));
            }
        });

        return view;
    }

    private void getSharedPreferences() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Pizza-seleccionada", Context.MODE_PRIVATE);

        nombre = preferences.getString("nombre", "Pizza default");
        costo = preferences.getString("costo", "150.00");
        imagen = preferences.getString("imagen", "no hay imagen");
        ingredientes = preferences.getString("ingredientes", "no hay");

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

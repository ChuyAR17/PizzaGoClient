package com.chuy.pizzagoclient.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chuy.pizzagoclient.R;
import com.squareup.picasso.Picasso;

public class FragmentMenuPizzas extends Fragment {

    private int porciones;
    private String numero;
    private Button mas, menos;
    public TextView noPociones;
    private String nombre, costo, imagen, ingredientes;

    private TextView pizzaName, pizzaCost;
    private ImageView pizzaImage;

    public FragmentMenuPizzas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_menu_pizzas, container, false);

        getSharedPreferences();

        pizzaName = view.findViewById(R.id.MenuPizzaViewTittle);
        pizzaCost = view.findViewById(R.id.MenuPizzaViewCost);
        pizzaImage = view.findViewById(R.id.MenuPizzaViewImage);

        Picasso.get().load(imagen).fit().into(pizzaImage);
        pizzaName.setText(nombre);
        pizzaCost.setText(costo + " mxn");

        mas = view.findViewById(R.id.PizzaButtonMas);
        menos = view.findViewById(R.id.PizzaButtonMenos);
        noPociones = view.findViewById(R.id.PizzaNoCantidad);

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

        return view;
    }

    private void getSharedPreferences() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Pizza-seleccionada", Context.MODE_PRIVATE);

        nombre = preferences.getString("nombre", "Pizza default");
        costo = preferences.getString("costo", "150.00");
        imagen = preferences.getString("imagen", "no hay imagen");

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

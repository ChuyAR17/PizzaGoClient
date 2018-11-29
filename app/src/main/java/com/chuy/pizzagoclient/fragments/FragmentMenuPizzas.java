package com.chuy.pizzagoclient.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chuy.pizzagoclient.R;

public class FragmentMenuPizzas extends Fragment {

    private int porciones;
    private String numero;
    private Button mas, menos;
    public TextView noPociones;

    public FragmentMenuPizzas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_menu_pizzas, container, false);

        mas = view.findViewById(R.id.PizzaButtonMas);
        menos = view.findViewById(R.id.PizzaButtonMenos);
        noPociones = view.findViewById(R.id.PizzaNoCantidad);

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

        return view;
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

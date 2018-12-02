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
import android.widget.TextView;

import com.chuy.pizzagoclient.R;
import com.squareup.picasso.Picasso;

public class FragmentPackView extends Fragment {

    private int porciones;
    private String numero;
    private Button mas, menos;
    public TextView noPociones;
    private TextView nombre, costo;
    private ImageView imagen;
    String nombrePack, costoPack, imagenPack;

    public FragmentPackView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_pack_view, container, false);

        mas = view.findViewById(R.id.PackButtonMas);
        menos = view.findViewById(R.id.PackButtonMenos);
        noPociones = view.findViewById(R.id.PackNoCantidad);

        nombre = view.findViewById(R.id.MenuPackViewTittle);
        costo = view.findViewById(R.id.MenuPackViewCost);
        imagen = view.findViewById(R.id.MenuPackViewImage);

        getSharedPreferences();
        nombre.setText(nombrePack);
        costo.setText(costoPack+" mxn");
        Picasso.get().load(imagenPack).fit().into(imagen);

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

    private void getSharedPreferences() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Pack-seleccionado", Context.MODE_PRIVATE);

        nombrePack = preferences.getString("nombre", "Pack default");
        costoPack = preferences.getString("costo", "350.00");
        imagenPack = preferences.getString("imagen", "no hay imagen");

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

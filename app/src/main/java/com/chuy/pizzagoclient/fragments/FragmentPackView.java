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

public class FragmentPackView extends Fragment {

    private int porciones;
    private String numero;
    private Button mas, menos;
    public TextView noPociones;
    private TextView nombre, costo;
    private ImageView imagen;
    private TextView contenido;
    private TextView subtotal;
    private Button agregar;
    String nombrePack, costoPack, imagenPack, aperitivoPack, bebidaPack, pizzaPack;

    public FragmentPackView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_pack_view, container, false);

        setRetainInstance(true);

        mas = view.findViewById(R.id.PackButtonMas);
        menos = view.findViewById(R.id.PackButtonMenos);
        noPociones = view.findViewById(R.id.PackNoCantidad);
        subtotal = view.findViewById(R.id.PackSubtotal);
        agregar = view.findViewById(R.id.packAgregar);

        nombre = view.findViewById(R.id.MenuPackViewTittle);
        costo = view.findViewById(R.id.MenuPackViewCost);
        imagen = view.findViewById(R.id.MenuPackViewImage);
        contenido = view.findViewById(R.id.MenuPackViewContent);

        getSharedPreferences();
        nombre.setText(nombrePack);
        costo.setText(costoPack);
        Picasso.get().load(imagenPack).fit().into(imagen);
        contenido.setText(pizzaPack + "\n" + aperitivoPack + "\n" + bebidaPack);

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = noPociones.getText().toString();
                sumar();
                int total = Integer.parseInt(costo.getText().toString()) * porciones;
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
                    int total =Integer.parseInt(subtotal.getText().toString()) - Integer.parseInt(costo.getText().toString());
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

                int total = Integer.parseInt(noPociones.getText().toString()) * Integer.parseInt(subtotal.getText().toString());
                editor.putString(nombre.getText().toString(), "-" + total);

                editor.apply();

                startActivity(new Intent(getContext(), MenuNormal.class));
            }
        });

        return view;
    }

    private void getSharedPreferences() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Pack-seleccionado", Context.MODE_PRIVATE);

        nombrePack = preferences.getString("nombre", "Pack default");
        costoPack = preferences.getString("costo", "350.00");
        imagenPack = preferences.getString("imagen", "no hay imagen");
        aperitivoPack = preferences.getString("aperitivo", "no hay");
        bebidaPack = preferences.getString("bebida", "no hay");
        pizzaPack = preferences.getString("pizza", "no hay");

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

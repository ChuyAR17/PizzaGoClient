package com.chuy.pizzagoclient.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.adapters.SauceAdapterRecycler;
import com.chuy.pizzagoclient.models.Sauce;

import java.util.ArrayList;

public class FragmentSauce extends Fragment {

    public FragmentSauce() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_sauce, container, false);

        RecyclerView sauceRecycler = view.findViewById(R.id.list_sauces);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        sauceRecycler.setLayoutManager(linearLayoutManager);

        SauceAdapterRecycler sauceAdapterRecycler = new SauceAdapterRecycler(buildSauces(), R.layout.cardview_sauce, getActivity());

        sauceRecycler.setAdapter(sauceAdapterRecycler);

        return view;
    }

    private ArrayList<Sauce> buildSauces() {
        ArrayList<Sauce> sauces =  new ArrayList<>();

        sauces.add(new Sauce("Salsa Especial", "Imagen normal"));
        sauces.add(new Sauce("Salsa Picante", "Imagen normal"));

        return sauces;
    }

}

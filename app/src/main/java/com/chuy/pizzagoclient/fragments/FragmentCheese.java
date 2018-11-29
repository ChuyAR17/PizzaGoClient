package com.chuy.pizzagoclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.adapters.CheeseAdapterRecycler;
import com.chuy.pizzagoclient.models.Cheese;

import java.util.ArrayList;


public class FragmentCheese extends Fragment {

    public FragmentCheese() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_cheese, container, false);

        RecyclerView cheeseRecycler = view.findViewById(R.id.list_cheeses);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        cheeseRecycler.setLayoutManager(linearLayoutManager);

        CheeseAdapterRecycler cheeseAdapterRecycler = new CheeseAdapterRecycler(buildCheeses(), R.layout.carview_cheese, getActivity());

        cheeseRecycler.setAdapter(cheeseAdapterRecycler);

        return view;
    }

    public ArrayList<Cheese> buildCheeses(){
        ArrayList<Cheese> cheeses = new ArrayList<>();
        cheeses.add(new Cheese("Queso Menonita", "http://www.novalandtours.com/images/guide/guilin.jpg", "10.0"));
        cheeses.add(new Cheese("Queso Oaxaca", "http://www.novalandtours.com/images/guide/guilin.jpg", "10.0"));
        cheeses.add(new Cheese("Queso Badota","http://www.novalandtours.com/images/guide/guilin.jpg", "15.0"));

        return cheeses;
    }
}

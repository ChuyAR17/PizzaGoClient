package com.chuy.pizzagoclient.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.adapters.PizzaAdapterRecycler;
import com.chuy.pizzagoclient.models.Pizza;

import java.util.ArrayList;

public class FragmentListMenuPizzas extends Fragment {

    public FragmentListMenuPizzas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_list_menu_pizzas, container, false);

        RecyclerView listPizzasMenu = view.findViewById(R.id.list_pizzas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listPizzasMenu.setLayoutManager(linearLayoutManager);

        PizzaAdapterRecycler pizzaAdapterRecycler = new PizzaAdapterRecycler(buildPizzas(), R.layout.cardview_pizza, getActivity());

        listPizzasMenu.setAdapter(pizzaAdapterRecycler);

        return view;
    }

    private ArrayList<Pizza> buildPizzas() {
        ArrayList<Pizza> pizzas = new ArrayList<>();

        pizzas.add(new Pizza("Pepperoni", "not implemented!!", "120.00"));
        pizzas.add(new Pizza("Hawaiana", "not implemented!!", "110.00"));
        pizzas.add(new Pizza("Carnes frias", "not implemented!!", "130.00"));
        return pizzas;
    }

}

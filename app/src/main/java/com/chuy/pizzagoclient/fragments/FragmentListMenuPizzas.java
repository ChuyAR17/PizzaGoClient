package com.chuy.pizzagoclient.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.adapters.CheeseAdapterRecycler;
import com.chuy.pizzagoclient.adapters.PizzaAdapterRecycler;
import com.chuy.pizzagoclient.models.Cheese;
import com.chuy.pizzagoclient.models.Pizza;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentListMenuPizzas extends Fragment {

    private DatabaseReference database;
    private String NODO_PIZZAS = "/pizza";
    private ArrayList<Pizza> pizzas;
    private PizzaAdapterRecycler pizzaAdapterRecycler;
    private RecyclerView pizzaRecycler;

    public FragmentListMenuPizzas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_list_menu_pizzas, container, false);

        database = FirebaseDatabase.getInstance().getReference();

        pizzaRecycler = view.findViewById(R.id.list_pizzas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        pizzaRecycler.setLayoutManager(linearLayoutManager);

        pizzaAdapterRecycler = new PizzaAdapterRecycler(buildPizzas(), R.layout.cardview_pizza, getActivity());

        pizzaRecycler.setAdapter(pizzaAdapterRecycler);

        return view;
    }

    private ArrayList<Pizza> buildPizzas() {
        pizzas = new ArrayList<>();

        database.child(NODO_PIZZAS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                pizzas.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Pizza pizza = snapshot.getValue(Pizza.class);
                        pizzas.add(new Pizza(pizza.getNombre(), pizza.getImagen(), pizza.getCosto(), pizza.getIngredientes()));
                    }
                }
                pizzaAdapterRecycler.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return pizzas;
    }

}

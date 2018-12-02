package com.chuy.pizzagoclient.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.adapters.CheeseAdapterRecycler;
import com.chuy.pizzagoclient.adapters.SauceAdapterRecycler;
import com.chuy.pizzagoclient.models.Cheese;
import com.chuy.pizzagoclient.models.Sauce;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentCheese extends Fragment {

    private DatabaseReference database;
    private String NODO_QUESO = "/tipo_queso";
    private ArrayList<Cheese> cheeses;
    private CheeseAdapterRecycler cheeseAdapterRecycler;
    private RecyclerView cheeseRecycler;

    public FragmentCheese() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_cheese, container, false);

        cheeseRecycler = view.findViewById(R.id.list_cheeses);

        database = FirebaseDatabase.getInstance().getReference();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        cheeseRecycler.setLayoutManager(linearLayoutManager);

        cheeseAdapterRecycler = new CheeseAdapterRecycler(buildCheeses(), R.layout.carview_cheese, getActivity());

        cheeseRecycler.setAdapter(cheeseAdapterRecycler);

        return view;
    }

    public ArrayList<Cheese> buildCheeses(){
        cheeses = new ArrayList<>();

        database.child(NODO_QUESO).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                cheeses.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Cheese cheese = snapshot.getValue(Cheese.class);
                        cheeses.add(new Cheese(cheese.getNombre(), cheese.getImagen(), cheese.getCosto()));
                    }
                }
                cheeseAdapterRecycler.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return cheeses;
    }
}

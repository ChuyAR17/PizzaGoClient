package com.chuy.pizzagoclient.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.adapters.SauceAdapterRecycler;
import com.chuy.pizzagoclient.models.Sauce;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentSauce extends Fragment {

    private DatabaseReference database;
    private String NODO_SALSA = "/salsa";
    private ArrayList<Sauce> sauces;
    private SauceAdapterRecycler sauceAdapterRecycler;
    private RecyclerView sauceRecycler;

    public FragmentSauce() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_sauce, container, false);

        database = FirebaseDatabase.getInstance().getReference();

        sauceRecycler = view.findViewById(R.id.list_sauces);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        sauceRecycler.setLayoutManager(linearLayoutManager);

        sauceAdapterRecycler = new SauceAdapterRecycler(buildSauces(), R.layout.cardview_sauce, getActivity());

        sauceRecycler.setAdapter(sauceAdapterRecycler);

        return view;
    }

    private ArrayList<Sauce> buildSauces() {
        sauces =  new ArrayList<>();

        database.child(NODO_SALSA).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                sauces.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Sauce salsa = snapshot.getValue(Sauce.class);
                        sauces.add(new Sauce(salsa.getNombreSalsa(), salsa.getImageSalsa()));
                    }
                }
                sauceAdapterRecycler.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return sauces;
    }

}

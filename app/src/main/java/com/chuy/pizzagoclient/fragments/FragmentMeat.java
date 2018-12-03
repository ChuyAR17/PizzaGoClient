package com.chuy.pizzagoclient.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.adapters.MeatAdapterRecycler;
import com.chuy.pizzagoclient.models.Meat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentMeat extends Fragment {

    private DatabaseReference database;
    private String NODO_CARNE = "/carnes";
    private ArrayList<Meat> meats;
    private MeatAdapterRecycler meatAdapterRecycler;
    private RecyclerView meatRecycler;

    public FragmentMeat() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_meat, container, false);

        setRetainInstance(true);

        meatRecycler = view.findViewById(R.id.list_meats);

        database = FirebaseDatabase.getInstance().getReference();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        meatRecycler.setLayoutManager(linearLayoutManager);

        meatAdapterRecycler= new MeatAdapterRecycler(buildMeats(), R.layout.cardview_meat, getActivity());

        meatRecycler.setAdapter(meatAdapterRecycler);

        return view;
    }

    private ArrayList<Meat> buildMeats() {
        final ArrayList<Meat> meats = new ArrayList<>();

        database.child(NODO_CARNE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                meats.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Meat meat = snapshot.getValue(Meat.class);
                        meats.add(new Meat(meat.getImagen(), meat.getNombre(), meat.getCosto()));
                    }
                }
                meatAdapterRecycler.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return meats;
    }
}

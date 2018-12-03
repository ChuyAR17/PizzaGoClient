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
import com.chuy.pizzagoclient.adapters.PackAdapterRecycler;
import com.chuy.pizzagoclient.models.Pack;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentPackList extends Fragment {

    private DatabaseReference database;
    private String NODO_PAQUETE = "/paquetes";
    private ArrayList<Pack> packs;
    private PackAdapterRecycler packAdapterRecycler;
    private RecyclerView packRecycler;

    public FragmentPackList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_pack_list, container, false);

        setRetainInstance(true);

        database = FirebaseDatabase.getInstance().getReference();

        packRecycler = view.findViewById(R.id.list_packs);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        packRecycler.setLayoutManager(linearLayoutManager);

        packAdapterRecycler = new PackAdapterRecycler(buildPack(), R.layout.cardview_pack, getActivity());

        packRecycler.setAdapter(packAdapterRecycler);

        return view;
    }

    private ArrayList<Pack> buildPack() {
        packs = new ArrayList<>();

        database.child(NODO_PAQUETE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                packs.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Pack pack = snapshot.getValue(Pack.class);
                        packs.add(new Pack(pack.getNombre(), pack.getImagen(), pack.getCosto(), pack.getPizza(), pack.getBebida(), pack.getAperitivo()));
                    }
                }
                packAdapterRecycler.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        return packs;
    }

}

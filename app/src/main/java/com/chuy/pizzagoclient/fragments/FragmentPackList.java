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
import com.chuy.pizzagoclient.adapters.PackAdapterRecycler;
import com.chuy.pizzagoclient.models.Pack;

import java.util.ArrayList;

public class FragmentPackList extends Fragment {

    public FragmentPackList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_pack_list, container, false);

        RecyclerView listPacksMenu = view.findViewById(R.id.list_packs);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listPacksMenu.setLayoutManager(linearLayoutManager);

        PackAdapterRecycler packAdapterRecycler = new PackAdapterRecycler(buildPack(), R.layout.cardview_pack, getActivity());

        listPacksMenu.setAdapter(packAdapterRecycler);

        return view;
    }

    private ArrayList<Pack> buildPack() {
        ArrayList<Pack> packs = new ArrayList<>();

        packs.add(new Pack("no hay", "Fiesta", "280.00"));
        packs.add(new Pack("no hay", "Romantico", "350.00"));
        packs.add(new Pack("no hay", "Graduacion", "250.00"));

        return packs;
    }

}

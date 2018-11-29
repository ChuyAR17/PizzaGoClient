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
import android.widget.Button;
import android.widget.TextView;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.adapters.MeatAdapterRecycler;
import com.chuy.pizzagoclient.models.Meat;

import java.util.ArrayList;

public class FragmentMeat extends Fragment {

    public FragmentMeat() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_meat, container, false);

        RecyclerView meatRecycler = view.findViewById(R.id.list_meats);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        meatRecycler.setLayoutManager(linearLayoutManager);

        MeatAdapterRecycler meatAdapterRecycler= new MeatAdapterRecycler(buildMeats(), R.layout.cardview_meat, getActivity());

        meatRecycler.setAdapter(meatAdapterRecycler);

        return view;
    }

    private ArrayList<Meat> buildMeats() {
        ArrayList<Meat> meats = new ArrayList<>();
        meats.add(new Meat("Not picture yet!!", "Pepperoni"));
        meats.add(new Meat("Not picture yet!!", "Chorizo"));
        meats.add(new Meat("Not picture yet!!", "Salchicha"));
        meats.add(new Meat("Not picture yet!!", "Tocino"));

        return meats;
    }
}

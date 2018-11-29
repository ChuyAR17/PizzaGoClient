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
import com.chuy.pizzagoclient.adapters.ExtraIngredientAdapterRecycler;
import com.chuy.pizzagoclient.models.ExtraIngredient;

import java.util.ArrayList;

public class FragmentExtras extends Fragment {

    public FragmentExtras() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_extras, container, false);

        RecyclerView extraIngredientRecycler = view.findViewById(R.id.list_extras);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        extraIngredientRecycler.setLayoutManager(linearLayoutManager);

        ExtraIngredientAdapterRecycler extraIngredientAdapterRecycler = new ExtraIngredientAdapterRecycler(buildExtraIngredients(), R.layout.cardview_extras, getActivity());

        extraIngredientRecycler.setAdapter(extraIngredientAdapterRecycler);

        return view;
    }

    private ArrayList<ExtraIngredient> buildExtraIngredients() {
        ArrayList<ExtraIngredient> ingredients = new ArrayList<>();
        ingredients.add(new ExtraIngredient("No hay foto aun", "Champiñon"));
        ingredients.add(new ExtraIngredient("No hay foto aun", "Tomate"));
        ingredients.add(new ExtraIngredient("No hay foto aun", "Aceitunas"));

        return ingredients;
    }

}

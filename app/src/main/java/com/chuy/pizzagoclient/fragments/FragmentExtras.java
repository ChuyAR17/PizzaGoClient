package com.chuy.pizzagoclient.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.chuy.pizzagoclient.adapters.ExtraIngredientAdapterRecycler;
import com.chuy.pizzagoclient.models.ExtraIngredient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentExtras extends Fragment {

    private DatabaseReference database;
    private String NODO_INGREDIENTE_EXTRA = "/ingredientes";
    private ArrayList<ExtraIngredient> ingredients;
    private ExtraIngredientAdapterRecycler ingredientsAdapterRecycler;
    private RecyclerView extraRecycler;

    public FragmentExtras() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_extras, container, false);

        extraRecycler = view.findViewById(R.id.list_extras);

        database = FirebaseDatabase.getInstance().getReference();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        extraRecycler.setLayoutManager(linearLayoutManager);

        ingredientsAdapterRecycler = new ExtraIngredientAdapterRecycler(buildExtraIngredients(), R.layout.cardview_extras, getActivity());

        extraRecycler.setAdapter(ingredientsAdapterRecycler);

        return view;
    }

    private ArrayList<ExtraIngredient> buildExtraIngredients() {
        final ArrayList<ExtraIngredient> ingredients = new ArrayList<>();

        database.child(NODO_INGREDIENTE_EXTRA).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ingredients.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ExtraIngredient ingredient = snapshot.getValue(ExtraIngredient.class);
                        ingredients.add(new ExtraIngredient(ingredient.getNombre(), ingredient.getImagen(), ingredient.getCosto()));
                    }
                }
                ingredientsAdapterRecycler.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return ingredients;
    }

}

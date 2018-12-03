package com.chuy.pizzagoclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuy.pizzagoclient.R;


public class FragmentDough extends Fragment {

    public FragmentDough() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_dough, container, false);
        setRetainInstance(true);

        return view;
    }
}

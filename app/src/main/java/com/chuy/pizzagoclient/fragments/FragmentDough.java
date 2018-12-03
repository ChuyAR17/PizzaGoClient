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
import android.widget.Toolbar;

import com.chuy.pizzagoclient.R;
import com.chuy.pizzagoclient.adapters.SauceAdapterRecycler;
import com.chuy.pizzagoclient.models.Sauce;

import java.util.ArrayList;


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

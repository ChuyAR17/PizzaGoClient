package com.chuy.pizzagoclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chuy.pizzagoclient.adapters.AperitiveAdapterRecycler;
import com.chuy.pizzagoclient.models.Aperitive;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

public class Aperitives extends AppCompatActivity {

    private BottomBar bottomBar;
    private ImageView backButton, carButton;
    TextView tittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aperitives);

        backButton = findViewById(R.id.backButton);
        tittle = findViewById(R.id.text_toolbar);
        carButton = findViewById(R.id.carButton);

        showToolbar(backButton, tittle, carButton);

        RecyclerView listMenuAperitives = findViewById(R.id.list_aperitives);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listMenuAperitives.setLayoutManager(linearLayoutManager);

        AperitiveAdapterRecycler aperitiveAdapterRecycler = new AperitiveAdapterRecycler(buildAperitives(), R.layout.cardview_aperitives, this);

        listMenuAperitives.setAdapter(aperitiveAdapterRecycler);

    }

    private ArrayList<Aperitive> buildAperitives() {
        ArrayList<Aperitive> aperitives = new ArrayList<>();
        aperitives.add(new Aperitive("no hay", "Boneless", "50.00"));
        aperitives.add(new Aperitive("no hay", "Papas Fritas", "30.00"));
        aperitives.add(new Aperitive("no hay", "Palitos de queso", "45.00"));

        return aperitives;
    }

    private void showToolbar(ImageView back, TextView tittle, ImageView car) {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MenuNormal.class));
            }
        });

        tittle.setText(R.string.aperitives);

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Todavia no aplicado!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

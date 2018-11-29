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

import com.chuy.pizzagoclient.adapters.DrinkAdapterRecycler;
import com.chuy.pizzagoclient.models.Drink;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

public class Drinks extends AppCompatActivity {

    private BottomBar bottomBar;
    private ImageView backButton, carButton;
    TextView tittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        backButton = findViewById(R.id.backButton);
        tittle = findViewById(R.id.text_toolbar);
        carButton = findViewById(R.id.carButton);

        showToolbar(backButton, tittle, carButton);

        RecyclerView listMenuDrinks = findViewById(R.id.list_drinks);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listMenuDrinks.setLayoutManager(linearLayoutManager);

        DrinkAdapterRecycler drinkAdapterRecycler = new DrinkAdapterRecycler(buildDrinks(), R.layout.cardview_drink, this);

        listMenuDrinks.setAdapter(drinkAdapterRecycler);
    }

    private ArrayList<Drink> buildDrinks() {
        ArrayList<Drink> drinks = new ArrayList<>();
        drinks.add(new Drink("no hay", "Boneless", "50.00"));
        drinks.add(new Drink("no hay", "Papas Fritas", "30.00"));
        drinks.add(new Drink("no hay", "Palitos de queso", "45.00"));

        return drinks;
    }

    private void showToolbar(ImageView back, TextView tittle, ImageView car) {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MenuNormal.class));
            }
        });

        tittle.setText(R.string.drinks);

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Todavia no aplicado!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

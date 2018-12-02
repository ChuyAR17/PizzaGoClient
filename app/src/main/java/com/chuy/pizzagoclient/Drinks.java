package com.chuy.pizzagoclient;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

public class Drinks extends AppCompatActivity {

    private BottomBar bottomBar;
    private ImageView backButton, carButton;
    TextView tittle;

    private DatabaseReference database;
    private String NODO_BEBIDA = "/bebidas";
    private ArrayList<Drink> drinks;
    private DrinkAdapterRecycler drinkAdapterRecycler;
    private RecyclerView drinkRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        backButton = findViewById(R.id.backButton);
        tittle = findViewById(R.id.text_toolbar);
        carButton = findViewById(R.id.carButton);

        showToolbar(backButton, tittle, carButton);

        database = FirebaseDatabase.getInstance().getReference();

        drinkRecycler = findViewById(R.id.list_drinks);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        drinkRecycler.setLayoutManager(linearLayoutManager);

        drinkAdapterRecycler = new DrinkAdapterRecycler(buildDrinks(), R.layout.cardview_drink, this);

        drinkRecycler.setAdapter(drinkAdapterRecycler);
    }

    private ArrayList<Drink> buildDrinks() {
        final ArrayList<Drink> drinks = new ArrayList<>();

        database.child(NODO_BEBIDA).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                drinks.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Drink drink = snapshot.getValue(Drink.class);
                        drinks.add(new Drink(drink.getNombre(), drink.getImagen(), drink.getCosto()));
                    }
                }
                drinkAdapterRecycler.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

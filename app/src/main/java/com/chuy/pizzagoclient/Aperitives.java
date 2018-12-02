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

import com.chuy.pizzagoclient.adapters.AperitiveAdapterRecycler;
import com.chuy.pizzagoclient.models.Aperitive;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

public class Aperitives extends AppCompatActivity {

    private BottomBar bottomBar;
    private ImageView backButton, carButton;
    TextView tittle;

    private DatabaseReference database;
    private String NODO_APERITIVOS = "/aperitivos";
    private ArrayList<Aperitive> aperitives;
    private AperitiveAdapterRecycler aperitiveAdapterRecycler;
    private RecyclerView aperitiveRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aperitives);

        backButton = findViewById(R.id.backButton);
        tittle = findViewById(R.id.text_toolbar);
        carButton = findViewById(R.id.carButton);

        showToolbar(backButton, tittle, carButton);

        database = FirebaseDatabase.getInstance().getReference();

        aperitiveRecycler = findViewById(R.id.list_aperitives);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        aperitiveRecycler.setLayoutManager(linearLayoutManager);

        aperitiveAdapterRecycler = new AperitiveAdapterRecycler(buildAperitives(), R.layout.cardview_aperitives, this);

        aperitiveRecycler.setAdapter(aperitiveAdapterRecycler);

    }

    private ArrayList<Aperitive> buildAperitives() {
        final ArrayList<Aperitive> aperitives = new ArrayList<>();

        database.child(NODO_APERITIVOS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                aperitives.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Aperitive aperitive = snapshot.getValue(Aperitive.class);
                        aperitives.add(new Aperitive(aperitive.getNombre(), aperitive.getImagen(), aperitive.getCosto()));
                    }
                }
                aperitiveAdapterRecycler.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

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

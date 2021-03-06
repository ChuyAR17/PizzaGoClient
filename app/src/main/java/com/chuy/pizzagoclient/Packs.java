package com.chuy.pizzagoclient;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chuy.pizzagoclient.fragments.FragmentPackList;
import com.roughike.bottombar.BottomBar;

public class Packs extends AppCompatActivity {

    private BottomBar bottomBar;
    private ImageView backButton, carButton;
    TextView tittle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packs);

        backButton = findViewById(R.id.backButton);
        tittle = findViewById(R.id.text_toolbar);
        carButton = findViewById(R.id.carButton);

        Window window = getWindow();
        Explode explode = new Explode();
        Fade fade = new Fade();
        window.setReturnTransition(explode);
        window.setEnterTransition(fade);

        showToolbar(backButton, tittle, carButton);

        FragmentPackList listMenuPacks = new FragmentPackList();
        getSupportFragmentManager().beginTransaction().replace(R.id.ContainerFragmentPacks, listMenuPacks)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
    }

    private void showToolbar(ImageView back, TextView tittle, ImageView car) {
        back.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MenuNormal.class));
            }
        });

        tittle.setText(R.string.packs);

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Todavia no aplicado!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

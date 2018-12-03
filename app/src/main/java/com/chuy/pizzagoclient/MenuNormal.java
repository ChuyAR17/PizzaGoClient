package com.chuy.pizzagoclient;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chuy.pizzagoclient.models.Pack;
import com.chuy.pizzagoclient.models.Pizza;
import com.roughike.bottombar.BottomBar;

public class MenuNormal extends AppCompatActivity {

    private BottomBar bottomBar;
    private ImageView backButton, carButton;
    private LinearLayout pizzas, aperitives, drinks, packs;
    TextView tittle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_normal);

        backButton  = findViewById(R.id.backButton);
        tittle      = findViewById(R.id.text_toolbar);
        carButton   = findViewById(R.id.carButton);

        pizzas      = findViewById(R.id.menu_normal_pizzas);
        aperitives  = findViewById(R.id.menu_normal_aperitivos);
        drinks      = findViewById(R.id.menu_normal_bebidas);
        packs       = findViewById(R.id.menu_normal_paquetes);

        Window window = getWindow();
        Explode explode = new Explode();
        Fade fade = new Fade();
        window.setReturnTransition(explode);
        window.setEnterTransition(fade);

        showToolbar(backButton, tittle, carButton);

        pizzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Pizzas.class));
            }
        });

        packs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Packs.class));
            }
        });

        aperitives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Aperitives.class));
            }
        });

        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Drinks.class));
            }
        });

    }

    private void showToolbar(ImageView back, TextView tittle, ImageView car) {
        back.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Menus.class));
            }
        });

        tittle.setText(R.string.menu_normal);

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DetalleOrden.class));
            }
        });
    }
}

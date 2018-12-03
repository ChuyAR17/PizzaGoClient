package com.chuy.pizzagoclient;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;

public class Menus extends AppCompatActivity {

    LinearLayout armaPizza, menuNormal;

    private BottomBar bottomBar;
    private ImageView backButton, carButton;
    TextView tittle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        armaPizza = findViewById(R.id.arma_pizza);
        menuNormal = findViewById(R.id.menu_normal);

        backButton = findViewById(R.id.backButton);
        tittle = findViewById(R.id.text_toolbar);
        carButton = findViewById(R.id.carButton);

        Window window = getWindow();
        Explode explode = new Explode();
        Fade fade = new Fade();
        window.setReturnTransition(explode);
        window.setEnterTransition(fade);

        showToolbar(backButton, tittle, carButton);

        armaPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MakeYourOwn.class));
            }
        });

        menuNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MenuNormal.class));
            }
        });

    }

    private void showToolbar(ImageView back, TextView tittle, ImageView car) {
        back.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), type_of_service.class));
            }
        });

        tittle.setText(R.string.menu);

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Todavia no aplicado!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

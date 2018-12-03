package com.chuy.pizzagoclient;

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

public class type_of_service extends AppCompatActivity {

    ImageView local, domicilio;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_service);

        local = findViewById(R.id.service_on_local);
        domicilio = findViewById(R.id.service_address);

        Window window = getWindow();
        Explode explode = new Explode();
        Fade fade = new Fade();
        window.setReturnTransition(explode);
        window.setEnterTransition(fade);

        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeService.class));
            }
        });

        domicilio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PickOnLocal.class));
            }
        });

    }
}

package com.chuy.pizzagoclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class type_of_service extends AppCompatActivity {

    ImageView local, domicilio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_service);

        local = findViewById(R.id.service_on_local);
        domicilio = findViewById(R.id.service_address);

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

package com.chuy.pizzagoclient;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OrderReady extends AppCompatActivity {

    private static final long SPLASH_TIME = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ready);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(OrderReady.this, Menus.class));
            }
        }, SPLASH_TIME);
    }
}

package com.chuy.pizzagoclient;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.ConnectException;
import java.security.Permission;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.INTERNET;

public class SplashScreen extends AppCompatActivity {

    private static final int INTERNET_CODE = 1;
    private String[] permission = new String[]{Manifest.permission.INTERNET};
    private static final int SPLASH_TIME = 1500;
    private ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        loader = findViewById(R.id.loader_splash_screen);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), permission[0]) == PackageManager.PERMISSION_GRANTED) {
            changeScreen();
        }else if (ContextCompat.checkSelfPermission(getApplicationContext(), permission[0]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashScreen.this, permission, INTERNET_CODE);
        }

    }

    private boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResutls) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResutls);
        switch (requestCode) {
            case INTERNET_CODE:
                if (grantResutls[0] == PackageManager.PERMISSION_GRANTED){
                    changeScreen();
                }else {
                    Toast.makeText(getApplicationContext(), "No hay permisos para internet", Toast.LENGTH_SHORT).show();
                    loader.setVisibility(View.INVISIBLE);
                    finish();
                }
                break;
        }
    }

    private void changeScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkInternetConnection()){
                    startActivity(new Intent(SplashScreen.this, type_of_service.class));
                } else{
                    Toast.makeText(getApplicationContext(), "Sin conexión a Internet", Toast.LENGTH_SHORT).show();
                    loader.setVisibility(View.INVISIBLE);
                    finish();
                }
            }
        }, SPLASH_TIME);
    }


}

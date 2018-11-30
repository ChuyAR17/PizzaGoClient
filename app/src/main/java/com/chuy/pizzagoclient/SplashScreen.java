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

    private static final int MULTIPLE_PERMISSIONS_CODE = 3;
    private String[] permissions = new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    private static final int SPLASH_TIME = 1500;
    private ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        loader = findViewById(R.id.loader_splash_screen);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(), permissions[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(), permissions[2]) == PackageManager.PERMISSION_GRANTED) {
            changeScreen();
        }else if (ContextCompat.checkSelfPermission(getApplicationContext(), permissions[0]) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getApplicationContext(), permissions[1]) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getApplicationContext(), permissions[2]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashScreen.this, permissions, MULTIPLE_PERMISSIONS_CODE);
        }

    }

    private boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS_CODE:
                if (validatePermissions(grantResults)){
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

    private boolean validatePermissions(int[] grantResutls) {
        boolean allGanted = false;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResutls[i] == PackageManager.PERMISSION_GRANTED) {
                allGanted = true;
            } else {
                allGanted = false;
                break;
            }
        }
        return allGanted;
    }


}

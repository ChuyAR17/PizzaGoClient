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
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.ConnectException;
import java.security.Permission;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.MANAGE_DOCUMENTS;

public class SplashScreen extends AppCompatActivity {

    private static final int MULTIPLE_PERMISSIONS = 3;
    private String[] permission = new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int SPLASH_TIME = 1000;
    private ProgressBar loader;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Window window = getWindow();
        Explode explode = new Explode();
        Fade fade = new Fade();
        window.setReturnTransition(explode);
        window.setEnterTransition(fade);

        loader = findViewById(R.id.loader_splash_screen);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), permission[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(), permission[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(), permission[2]) == PackageManager.PERMISSION_GRANTED) {
            changeScreen();
        }else if (ContextCompat.checkSelfPermission(getApplicationContext(), permission[0]) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getApplicationContext(), permission[1]) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getApplicationContext(), permission[2]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashScreen.this, permission, MULTIPLE_PERMISSIONS);
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
            case MULTIPLE_PERMISSIONS:
                if (validatePermission(grantResutls)){
                    changeScreen();
                }else {
                    Toast.makeText(getApplicationContext(), "No hay permisos para internet", Toast.LENGTH_SHORT).show();
                    loader.setVisibility(View.INVISIBLE);
                    finish();
                }
                break;
        }
    }

    private boolean validatePermission(int[] grantResutls) {
        boolean allGranted = false;
        for (int i =0; i < permission.length; i++) {
            if (grantResutls[i] == PackageManager.PERMISSION_GRANTED){
                allGranted = true;
            }else {
                allGranted = false;
                Toast.makeText(getApplicationContext(), "Tenemos que saber tu ubicacion...", Toast.LENGTH_SHORT).show();
            }
        }
        return allGranted;
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

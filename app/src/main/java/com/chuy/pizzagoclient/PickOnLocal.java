package com.chuy.pizzagoclient;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roughike.bottombar.BottomBar;

public class PickOnLocal extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private static final int LOCATION_PERMISSIONS = 2;
    private GoogleMap mMap;
    LocationManager locationManager;
    double longitudeGPS, latitudeGPS;
<<<<<<< HEAD
    private String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    Location location;
=======
>>>>>>> ed98f608cb998a7e9fd997c59e6e823329e4a011

    private BottomBar bottomBar;
    private ImageView backButton, carButton;
    TextView tittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_on_local);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.MapOnHome);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
<<<<<<< HEAD
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PickOnLocal.this, permissions, LOCATION_PERMISSIONS);
            return;
        }

        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
=======
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
>>>>>>> ed98f608cb998a7e9fd997c59e6e823329e4a011

        latitudeGPS = location.getLatitude();
        longitudeGPS = location.getLongitude();

        backButton = findViewById(R.id.MapBakcButton);
        tittle = findViewById(R.id.MapTittleToolbar);
        carButton = findViewById(R.id.MapCheckOption);

        showToolbar(backButton, tittle, carButton);

    }

    private void showToolbar(ImageView back, TextView tittle, ImageView car) {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), type_of_service.class));
            }
        });

        tittle.setText(R.string.pick_on_local);

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Menus.class));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResutls) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResutls);
        switch (requestCode) {
            case LOCATION_PERMISSIONS:
                if (validatePermission(grantResutls)){
                    Toast.makeText(getApplicationContext(), "Ubicacion localizada", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Ocurrio un problema con los permisos!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, type_of_service.class));
                }
                break;
        }
    }

    private boolean validatePermission(int[] grantResutls) {
        boolean allGranted = false;
        for (int i =0; i < permissions.length; i++) {
            if (grantResutls[i] == PackageManager.PERMISSION_GRANTED){
                allGranted = true;
            }else {
                allGranted = false;
                Toast.makeText(getApplicationContext(), "Tenemos que saber tu ubicacion...", Toast.LENGTH_SHORT).show();
            }
        }
        return allGranted;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();

        MarkerOptions markerOptions = new MarkerOptions( );
<<<<<<< HEAD
        LatLng latLng = new LatLng(latitudeGPS, longitudeGPS);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            return;
        }
=======
>>>>>>> ed98f608cb998a7e9fd997c59e6e823329e4a011

        LatLng latLng = new LatLng(latitudeGPS, longitudeGPS);
        markerOptions.position(latLng);
        markerOptions.title("Aqui estas tu!");

        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.addMarker(markerOptions);

        mMap.setOnMyLocationButtonClickListener(this);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }
}

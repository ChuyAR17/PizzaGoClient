package com.chuy.pizzagoclient;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roughike.bottombar.BottomBar;

public class PickOnLocal extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, LocationSource.OnLocationChangedListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    double longitudeGPS, latitudeGPS;
    Location location;

    private BottomBar bottomBar;
    private ImageView backButton, carButton;
    TextView tittle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_on_local);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Window window = getWindow();
        Explode explode = new Explode();
        Fade fade = new Fade();
        window.setEnterTransition(fade);
        window.setReturnTransition(explode);
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        //location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        latitudeGPS = location.getLatitude();
        longitudeGPS = location.getLongitude();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.MapOnHome);
        mapFragment.getMapAsync(this);

        backButton = findViewById(R.id.MapBakcButton);
        tittle = findViewById(R.id.MapTittleToolbar);
        carButton = findViewById(R.id.MapCheckOption);


        showToolbar(backButton, tittle, carButton);

    }

    private void showToolbar(ImageView back, TextView tittle, ImageView car) {
        back.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(latitudeGPS, longitudeGPS);
        MarkerOptions markerOptions = new MarkerOptions( );
        CameraPosition position = new CameraPosition.Builder()
                .target(latLng)
                .zoom(16)
                .bearing(0)
                .tilt(45)
                .build();

        markerOptions.position(latLng)
                .title("TU")
                .snippet("Esta es tu ubicacion");
        markerOptions.draggable(true);

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
        mMap.addMarker(markerOptions);

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latitudeGPS = location.getLatitude();
        longitudeGPS = location.getLongitude();
    }
}

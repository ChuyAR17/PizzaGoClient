package com.chuy.pizzagoclient;

import android.Manifest;
import android.annotation.TargetApi;
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
import android.widget.Toast;

import com.chuy.pizzagoclient.models.Locales;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.Locale;

public class HomeService extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private static final int LOCATION_PERMISSIONS = 2;
    private GoogleMap mMap;
    LocationManager locationManager;
    double longitudeGPS, latitudeGPS;
    private String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    Location location;

    private DatabaseReference database;
    private String NODO_QUESO = "/locales";

    private BottomBar bottomBar;
    private ImageView backButton, carButton;
    TextView tittle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_service);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(HomeService.this, permissions, LOCATION_PERMISSIONS);
            return;
        }

        Window window = getWindow();
        Explode explode = new Explode();
        Fade fade = new Fade();
        window.setReturnTransition(explode);
        window.setEnterTransition(fade);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.MapOnLocal);
        mapFragment.getMapAsync(this);

        database = FirebaseDatabase.getInstance().getReference();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        latitudeGPS = location.getLatitude();
        longitudeGPS = location.getLongitude();

        backButton = findViewById(R.id.MapBakcButton);
        tittle = findViewById(R.id.MapTittleToolbar);
        carButton = findViewById(R.id.MapCheckOption);

        showToolbar(backButton, tittle, carButton);
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

        final ArrayList<Locales> locales = new ArrayList<>();

        database.child(NODO_QUESO).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locales.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Locales local = snapshot.getValue(Locales.class);
                        locales.add(new Locales(local.getLatitud(), local.getLongitud(), local.getTitulo()));
                    }
                }
                for (int i = 0; i < locales.size(); i++) {
                    agregarMarcador(locales.get(i).getLatitud(), locales.get(i).getLongitud(), locales.get(i).getTitulo());
                }
                LatLng latLng = new LatLng(locales.get(0).getLatitud(), locales.get(0).getLongitud());
                CameraPosition position = new CameraPosition.Builder()
                        .target(latLng)
                        .zoom(14)
                        .bearing(0)
                        .tilt(45)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private Marker agregarMarcador(double latitud, double longitud, String titulo) {
        return mMap.addMarker(new MarkerOptions().position(new LatLng(latitud, longitud)).anchor(0.5f, 0.5f).title(titulo));
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }
}

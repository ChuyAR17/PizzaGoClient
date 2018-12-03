package com.chuy.pizzagoclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chuy.pizzagoclient.models.Ordenes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class DetalleOrden extends AppCompatActivity {

    private TextView detalles;
    private Button comprar;
    private String NODO_ORDEN = "/ordenes";
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_orden);

        database = FirebaseDatabase.getInstance().getReference();

        comprar = findViewById(R.id.Comprar);
        detalles = findViewById(R.id.ContenidoDetalle);

        final SharedPreferences preferences = getSharedPreferences("CrearOrden", Context.MODE_PRIVATE);

        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            detalles.append(entry.getKey() + " " + entry.getValue().toString() + "\n");
        }

        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ordenes orden = new Ordenes();

                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                startActivity(new Intent(getApplicationContext(), OrderReady.class));
            }
        });

    }

}

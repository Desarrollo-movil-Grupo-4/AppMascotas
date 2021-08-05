package com.nallis.clubanimals.views;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nallis.clubanimals.R;

import java.util.HashMap;
import java.util.Map;

public class InicioActivityView extends AppCompatActivity {

    private Button btn_cerrarsesion;

    private LocationManager ubicacion;

    FirebaseAuth auth;
    DatabaseReference db;
    private TextView tv_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_view);

        //metodo para cargar a la base de datos y la ubicacion


        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        btn_cerrarsesion = findViewById(R.id.btn_deslogueo);

        tv_result = (TextView)findViewById(R.id.nomPerfil);

        btn_cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(InicioActivityView.this, MainActivity.class));
                finish();
            }
        });

        infoUsuario();
        localizacion();
    }

    private void localizacion() {

        ubicacion = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, 100);
        }
        Location location = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {

            Map<String, Object> coordenadas = new HashMap<>();
            coordenadas.put("latitud", location.getLatitude());
            coordenadas.put("longitud", location.getLongitude());

            String id = auth.getCurrentUser().getUid();
            db.child("Users").child(id).updateChildren(coordenadas);
        }

    }

    private void infoUsuario(){

        String id = auth.getCurrentUser().getUid();
        db.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String name = snapshot.child("name").getValue().toString();
                    //String email = snapshot.child("name").getValue().toString();

                    tv_result.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void goToPerfil(View view) {
        Intent intent = new Intent(this, PerfilUsuarioView.class);
        startActivity(intent);
    }

    public void goToVeterinaria(View view){
        //Intent intent = new Intent(this, VeterinariaActivity.class);
        Intent intent = new Intent(this, ListadoVeterinarias.class);
        startActivity(intent);

    }
    public void goToServiciolocal(View view) {
        Intent intent = new Intent(this, UbicacionActivity.class);
        startActivity(intent);
    }
    public void goToLocalizacion(View view) {
        Intent intent = new Intent(this, serviciolocal.class);
        startActivity(intent);
    }
}
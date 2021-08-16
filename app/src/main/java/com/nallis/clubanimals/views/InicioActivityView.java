package com.nallis.clubanimals.views;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nallis.clubanimals.R;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class InicioActivityView extends AppCompatActivity {


    private Button btn_cerrarsesion, btn_consulta_general, btn_odontologia, btn_cirugia;

    private LocationManager ubicacion;

    FirebaseAuth auth;
    DatabaseReference db;
    private TextView tv_result;
    String direccion;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_view);

        direccion = "";
        name = "";

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();
        FirebaseUser currentUser = auth.getCurrentUser();

        btn_cerrarsesion = findViewById(R.id.btn_deslogueo);
        btn_consulta_general = findViewById(R.id.btn_consulta_general);
        btn_odontologia = findViewById(R.id.btn_odontologia);
        btn_cirugia = findViewById(R.id.btn_cirugia);

        tv_result = (TextView)findViewById(R.id.nomPerfil);

        btn_cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // auth.signOut();
                AuthUI.getInstance()
                        .signOut(InicioActivityView.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // ...
                                startActivity(new Intent(InicioActivityView.this, MainActivity.class));
                                finish();
                            }
                        });

            }
        });

        btn_consulta_general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToListado(view, "s01");
            }
        });
        btn_odontologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToListado(view, "s02");
            }
        });
        btn_cirugia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToListado(view, "s03");
            }
        });

        infoUsuario();
        //metodo para cargar a la base de datos y la ubicacion
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


            //Obtener la direccion de la calle a partir de la latitud y la longitud
            if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
                try {
                    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                    List<Address> list = geocoder.getFromLocation(
                            location.getLatitude(), location.getLongitude(), 1);
                    if (!list.isEmpty()) {
                        Address DirCalle = list.get(0);
                        direccion= DirCalle.getAddressLine(0);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        if (location != null) {

            Map<String, Object> coordenadas = new HashMap<>();
            coordenadas.put("latitud", location.getLatitude());
            coordenadas.put("longitud", location.getLongitude());
            coordenadas.put("localizacion", direccion);

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

    public void goToListado(View view, String idServicio){
        //Intent intent = new Intent(this, VeterinariaActivity.class);
        Intent intent = new Intent(this, ListadoVeterinarias.class);
        intent.putExtra("servicio", idServicio);
        startActivity(intent);
    }

    public void goToPerfil(View view) {
        Intent intent = new Intent(this, PerfilUsuarioView.class);
        startActivity(intent);
    }

    public void goToVeterinaria(View view){
        //Intent intent = new Intent(this, VeterinariaActivity.class);
        Intent intent = new Intent(this, serviciolocal.class);
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
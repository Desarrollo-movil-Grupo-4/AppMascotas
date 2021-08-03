package com.nallis.clubanimals.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nallis.clubanimals.R;

public class VeterinariaActivity extends AppCompatActivity {

    private TextView nombre_veterinaria;
    private ImageView logo;
    private ImageView imagen_cirugia;
    private ImageView imagen_general;
    private TextView correo;
    private EditText direccion;
    private EditText telefono;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinaria);

        nombre_veterinaria = (TextView)findViewById(R.id.nombre_vet);
        correo = findViewById(R.id.correo);
        direccion = findViewById(R.id.direccion_vet);
        telefono = findViewById(R.id.telefono_vet);

        db = FirebaseDatabase.getInstance().getReference();

        db.child("Veterinaria").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String name = snapshot.child("nombre").getValue().toString();
                    String email = snapshot.child("correo").getValue().toString();
                    String address = snapshot.child("direccion").getValue().toString();
                    String phone = snapshot.child("telefono").getValue().toString();
                    nombre_veterinaria.setText(name);
                    correo.setText(email);
                    direccion.setText(address);
                    telefono.setText(phone);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void goToContratar (View view){
        Intent intent = new Intent(this, ContratarActivityView.class);
        startActivity(intent);
    }
}
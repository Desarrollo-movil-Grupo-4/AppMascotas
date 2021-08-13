package com.nallis.clubanimals.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nallis.clubanimals.R;

import java.util.HashMap;
import java.util.Map;

public class ContratarActivityView extends AppCompatActivity {

    private EditText nombreUsuario, tipoMascota, mensaje;
    private Button btn_contratar;
    DatabaseReference db;
    private String nameVet = "";
    private String name = "";
    private String mascota = "";
    private String descipcion = "";

    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratar_view);

        btn_contratar = findViewById(R.id.btn_enviar);
        nombreUsuario = findViewById(R.id.et_nombre);
        tipoMascota  = findViewById(R.id.et_tipo_mascota);
        mensaje = findViewById(R.id.et_mensaje);

        db = FirebaseDatabase.getInstance().getReference().child("Contratos");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid = snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn_contratar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameVet = "";
                name = nombreUsuario.getText().toString();
                mascota = tipoMascota.getText().toString();
                descipcion = mensaje.getText().toString();

                if (!name.isEmpty() && !mascota.isEmpty() && !descipcion.isEmpty() ) {
                        registrarSolicitud();
                    } else {
                        Toast.makeText(ContratarActivityView.this, "Llenar todos los campos.", Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }

    public void registrarSolicitud(){

        Map<String, Object> map = new HashMap<>();
        map.put( "nameVet", nameVet);
        map.put( "name", name);
        map.put( "tipo mascota", mascota);
        map.put( "Mensaje", descipcion);
        db.child(String.valueOf(maxid+1)).setValue(map);

        Intent intent = new Intent( ContratarActivityView.this, ResumenContratarView.class);
        startActivity(intent);
        finish();
    }

}
package com.nallis.clubanimals.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nallis.clubanimals.R;

public class ContratarActivityView extends AppCompatActivity {

    private EditText nombreUsuario, tipoMascota, mensaje;
    private Button btn_contratar;
    DatabaseReference db;
    private String nameVet = "";
    private String name = "";
    private String mascota = "";
    private String descipcion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratar_view);

        btn_contratar = findViewById(R.id.btn_enviar);
        nombreUsuario = findViewById(R.id.et_nombre);
        tipoMascota  = findViewById(R.id.et_tipo_mascota);
        mensaje = findViewById(R.id.et_mensaje);

        db = FirebaseDatabase.getInstance().getReference().child("Contratos");

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

    }

    /*llevar a resumen de contratacion*/
    public void goToResummen(View view) {
        Intent intent = new Intent( this, ResumenContratarView.class);
        startActivity(intent);
    }
}
package com.nallis.clubanimals.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nallis.clubanimals.R;
public class ResumenContratarView extends AppCompatActivity {
    private String nameVet;
    private String direccionVet;
    private String whatsappVet;
    private String nombreMascota;
    private String tipoMascota;

    private TextView tv_nombreVet, tv_direccionVet, tv_whatsappVet, tv_nombreMascota, tv_tipoMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_contratar_view);

        nameVet = getIntent().getStringExtra("nombrevet");
        direccionVet = getIntent().getStringExtra("direccionVet");
        whatsappVet = getIntent().getStringExtra("whatsappVet");
        nombreMascota = getIntent().getStringExtra("nombreMascota");
        tipoMascota = getIntent().getStringExtra("tipoMascota");

        tv_nombreVet  = findViewById(R.id.veterinaria);
        tv_direccionVet = findViewById(R.id.secondary_text);
        tv_whatsappVet = findViewById(R.id.supporting_text);
        tv_nombreMascota = findViewById(R.id.mascota_text);
        tv_tipoMascota = findViewById(R.id.mascota_text4);

        tv_nombreVet.setText(nameVet);
        tv_direccionVet.setText(direccionVet);
        tv_whatsappVet.setText(whatsappVet);
        tv_nombreMascota.setText(nombreMascota);
        tv_tipoMascota.setText(tipoMascota);


    }
    public void goToInicio(View view){
        Intent intent = new Intent(this, InicioActivityView.class);
        startActivity(intent);
    }
    public void goToContratar(View view){
        Intent intent = new Intent(this, ContratarActivityView.class);
        startActivity(intent);
    }
}
package com.nallis.clubanimals.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.nallis.clubanimals.R;

public class Localizacion_ActividadView extends AppCompatActivity {

    private Spinner spinner1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacion_actividad);

        spinner1 = (Spinner)findViewById(R.id.spinner);


        String [] opciones = { "CONSULTA","CIRUGIA", "PELUQUERIA", "VACUNACION"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,opciones);
        spinner1.setAdapter(adapter);
    }


    public void goTomenu(View view){
        Intent intent = new Intent(this, InicioActivityView .class);
        startActivity(intent);
    }
}
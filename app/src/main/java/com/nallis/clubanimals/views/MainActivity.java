package com.nallis.clubanimals.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nallis.clubanimals.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToVeterinaria (View view){
        Intent intent = new Intent(this, VeterinariaActivity.class);
        startActivity(intent);
    }
/*
    public void goToRegistro (View view){
        Intent intent = new Intent(this, ContratarActivityView.class);
        startActivity(intent);
    }
     /*
    public void goToContrasena(View view) {
        Intent intent = new Intent( this, OlvideContrasena.class);
        startActivity(intent);
    }*/
}
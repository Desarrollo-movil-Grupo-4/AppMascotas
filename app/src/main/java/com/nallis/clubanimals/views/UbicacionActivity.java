package com.nallis.clubanimals.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nallis.clubanimals.R;

public class UbicacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);
    }
    public void goToVeterinaria (View view){
        Intent intent = new Intent(this, VeterinariaActivity.class);
        startActivity(intent);
    }
    public void goToServiciolocal (View view){
        Intent intent = new Intent(this, serviciolocal.class);
        startActivity(intent);
    }
    public void goToLogin (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goToInicio(View view) {
        Intent intent = new Intent(this, InicioActivityView.class);
        startActivity(intent);
    }
}
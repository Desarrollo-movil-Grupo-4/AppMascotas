package com.nallis.clubanimals.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.GeolocationPermissions;

import com.nallis.clubanimals.R;

public class InicioActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_view);
    }

    public void goToPerfil(View view) {
        Intent intent = new Intent(this, PerfilUsuarioView.class);
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
    public void goToVeterinaria(View view) {
        Intent intent = new Intent(this, VeterinariaActivity.class);
        startActivity(intent);
    }
    public void goToLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goToInicio(View view) {
        Intent intent = new Intent(this, InicioActivityView.class);
        startActivity(intent);
    }
}
package com.nallis.clubanimals.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nallis.clubanimals.R;

public class ContratarActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratar_view);
    }
    /*llevar a resumen de contratacion*/
    public void goToResummen(View view) {
        Intent intent = new Intent( this, ResumenContratarView.class);
        startActivity(intent);
    }
    public void goToPerfil(View view) {
        Intent intent = new Intent( this, PerfilUsuarioView.class);
        startActivity(intent);
    }
}
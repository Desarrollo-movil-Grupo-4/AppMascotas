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
    /*llevar a whatsapp*/
    public void goToInicio(View view) {
        Intent intent = new Intent( this, InicioActivityView.class);
        startActivity(intent);
    }
}
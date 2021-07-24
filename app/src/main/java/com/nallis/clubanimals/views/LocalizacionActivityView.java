package com.nallis.clubanimals.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nallis.clubanimals.R;

import static com.nallis.clubanimals.R.layout.activity_localizacion_view;

public class LocalizacionActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_localizacion_view);
    }
    public void goToVeterinaria (View view){
        Intent intent = new Intent(this, VeterinariaActivity.class);
        startActivity(intent);
    }
}
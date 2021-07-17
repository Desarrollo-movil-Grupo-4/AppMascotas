package com.nallis.clubanimals.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nallis.clubanimals.R;

public class RegistroaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registroa);
    }
    // metodo para enviar a la activity Ingresar
    public void Ingresar (View view){
        Intent ingresar = new Intent(this, MainActivity.class);
        startActivity(ingresar);
    }
}

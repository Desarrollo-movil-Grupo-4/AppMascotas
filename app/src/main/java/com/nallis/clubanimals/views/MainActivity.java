package com.nallis.clubanimals.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.nallis.clubanimals.R;

public class MainActivity extends AppCompatActivity {

    private Button btn_logo;
    private CheckBox sesion;
    private EditText email;
    private EditText contra;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_logo = findViewById(R.id.buttonLogin);
        sesion = findViewById(R.id.sesion);
        email = findViewById(R.id.Email);
        contra = findViewById(R.id.Password);

        preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        editor = preferences.edit();


       /* if(revisarSesion()){
            startActivity(new Intent(this, InicioActivityView.class));
        }else{
            Toast.makeText(getApplicationContext(), "debes iniciar sesion", Toast.LENGTH_SHORT).show();
        }*/
        email.setText(preferences.getString("email", " "));
        contra.setText(preferences.getString("contra", " "));
        btn_logo.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                //editor.putBoolean("sesion", sesion.isChecked());
                editor.putString("email", email.getText().toString());
                editor.putString("contra", contra.getText().toString());
                editor.apply();

                startActivity(new Intent(MainActivity.this, InicioActivityView.class));
            }
        });
    }
    private boolean revisarSesion(){
        return this.preferences.getBoolean("sesion", false);
    }

    public void goToVeterinaria(View view) {
        Intent intent = new Intent(this, VeterinariaActivity.class);
        startActivity(intent);
    }

    public void goToRegistrar(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
}
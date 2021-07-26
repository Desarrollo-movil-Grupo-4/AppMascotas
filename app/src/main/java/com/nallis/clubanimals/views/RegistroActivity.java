package com.nallis.clubanimals.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nallis.clubanimals.R;

public class RegistroActivity extends AppCompatActivity {
    // Crear objetos
    private EditText nombreEditText;
    private EditText correoEditText;
    private EditText contrasenaEditText;
    private EditText confirmarContrasenaEditText;
    Button registrarseBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registroa);

        // Conexion parte logica con la grafica
        nombreEditText = findViewById(R.id.txt_nombre);
        correoEditText = findViewById(R.id.txt_correo);
        contrasenaEditText = findViewById(R.id.txt_contrasena);
        confirmarContrasenaEditText = findViewById(R.id.txt_confirmar_contrasena);

        // Escucha del evento Enter en nombre y se lanza a la funcion de comprobar Nombre
        nombreEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
               if (actionId == EditorInfo.IME_NULL){
                   comprobarNombre();
                   return true;
               }return false;
            }
        });
    }

    // metodo para combrobar Nombre
    public void comprobarNombre(){
        // Castear el nombre,
        String nombre = nombreEditText.getText().toString();
        // validar el string introducido no este vacio
        if (nombre.isEmpty()){
            // mostar en el editext nombre el error
            nombreEditText.setError("Introducir Nombre por favor");
            return ;
        }
    }

    // metodo para pasar a la activity de Ingresar
    public void goToIngresar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // metodo para pasar a la activity del Inicio
    public void goToInicio (View view) {
        Intent intent = new Intent(this, InicioActivityView.class);
        startActivity(intent);
    }
}

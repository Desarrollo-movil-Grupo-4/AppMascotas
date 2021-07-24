package com.nallis.clubanimals.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nallis.clubanimals.R;

public class MainActivity extends AppCompatActivity {

    //Crear variables
    public static String usuario = "ejemplo@ejemplo.com";
    public static String contrasena = "ejemplo123";

    //Crear objetos
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Comunicarse parte logica y grafica
        email = (EditText) findViewById(R.id.Email);
        password = (EditText)findViewById(R.id.Password);


        // Escucha del evento Enter y se lanza a la funcion de comprobarUsuario
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.buttonLogin || actionId == EditorInfo.IME_NULL){
                    comprobarUsuario();
                    return true;
                }
                return false;
            }
        });

    }

    // metodo para validar el formulario de ingreso

    private boolean comprobarUsuario(){
        // comprobar si el usuario esta vacio o no esta registrado
        if (email.length() == 0 && email.toString() != usuario){
            return false;
        }
        // comprobar la contrasena
        else if(password.length() <= 8 && password.toString() != contrasena){
            return false;
        }
        return true;
    }


    // metodo para ir a la activity de Registro
    public void goToRegistro(View view){
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    // metodo para ir a Inicio
    public void goToInicio(View view){
        if (comprobarUsuario()) {
            Intent intent = new Intent(this, InicioActivityView.class);
            startActivity(intent);
        }else {
            Toast mensaje = Toast.makeText(getApplicationContext(),"Usuario o contraseña invalidos",Toast.LENGTH_LONG );
            mensaje.show();
        };
    }

    // metodo para ir a la activity RecuperarContrasena
    public void goToContrasena(View view){
        Intent intent = new Intent(this, RecuperarContrasenaView.class);
        startActivity(intent);
    }
}
package com.nallis.clubanimals.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static String contrasenas = "ejemplo123";

    //Crear objetos
    private EditText email;
    private EditText password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Comunicarse parte logica y grafica
        email = (EditText) findViewById(R.id.Email);
        password = (EditText)findViewById(R.id.Password);

        preferences = getSharedPreferences("Usuarios", Context.MODE_PRIVATE);
        editor = preferences.edit();


        // metodo para comprobar si ya fue logeado y llevar al inicio
        if(! revisarSesion()){
            Intent intent = new Intent(this, InicioActivityView.class);
            startActivity(intent);
        };



    }

    // metodo para validar el formulario de ingreso

    private boolean comprobarUsuario(){

        // Castear la informacion introducida como usuario y contrasena
        String correo = email.getText().toString();
        String contrasena = password.getText().toString();

        // comprobar si el usuario esta vacio o no esta registrado
        if (correo.length() == 0 && correo != usuario){
            return false;
        }
        // comprobar la contrasena
        else if(contrasena.length() <= 8 && contrasena != contrasenas){
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
        if (this.comprobarUsuario()) {
            // Castear la informacion introducida como usuario y contrasena
            String correo = email.getText().toString();
            String contrasena = password.getText().toString();

            //Guardar en sharedpreferences el correo y la contrasena
            editor.putString("correo",contrasena);
            editor.apply();
            Intent intent = new Intent(this, InicioActivityView.class);
            startActivity(intent);
        }else {
            Toast mensaje = Toast.makeText(getApplicationContext(),"Usuario o contraseña invalidos",Toast.LENGTH_SHORT );
            mensaje.show();
        };
    }

    // metodo para ir a la activity RecuperarContrasena
    public void goToContrasena(View view){
        Intent intent = new Intent(this, RecuperarContrasenaView.class);
        startActivity(intent);
    }

    //metodo para comprobar si fue logeado
    private boolean revisarSesion (){
        return this.preferences.getString("correo","").length() ==0;
    }
}
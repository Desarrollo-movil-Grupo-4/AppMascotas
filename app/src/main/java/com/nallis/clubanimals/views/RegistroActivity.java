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

        // Escucha del evento Enter en correo y se lanza a la funcion de comprobar correo
        correoEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL){
                    comprobarCorreo();
                    return true;
                }return false;
            }
        });

        // Escucha del evento Enter en contrasena y se lanza a la funcion de comprobar contrasena
        contrasenaEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL){
                    comprobarContrasena();
                    return true;
                }return false;
            }
        });

        // Escucha del evento Enter en confirmar contrasena y se lanza a la funcion de comprobar confirmar contrasena
        confirmarContrasenaEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL){
                    confirmarContrasena();
                    return true;
                }return false;
            }
        });
    }

    // metodo para combrobar Nombre
    public boolean comprobarNombre(){
        // Castear el nombre,
        String nombre = nombreEditText.getText().toString();
        // validar el string introducido no este vacio
        if (nombre.isEmpty()){
            // mostar en el editext nombre el error
            nombreEditText.setError("Introducir Nombre por favor");
            return false;
        }return true;
    }

    //metodo para comprobar email
    public boolean comprobarCorreo (){
        // Castear el email, .trim() para quitar espacios
        String email = correoEditText.getText().toString().trim();
        // validar el string introducido sea email
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            // mostar en el editext email el error
            correoEditText.setError("Correo inválido");
            return false ;
        }return true;
    }

    //metodo para comprobar contrasena
    public boolean comprobarContrasena (){
        // Castear la contrasena
        String contrasena = contrasenaEditText.getText().toString().trim();
        // validar el string introducido sea contrasena  minimo de 8 digitos, 1 mayuscula, 1 caracter
        if (contrasena.isEmpty()
              || contrasena.matches("(?=.*[@#$%^&+=])")
              || contrasena.matches("(?=.*[0-9])")
              || contrasena.matches("(?=.*[a-z])")
              || contrasena.matches("(?=.*[A-Z])")
              || !contrasena.matches(".{8,15}")
              || contrasena.matches(".*\\s.*")

        ){
            // mostar en el editext contrasena el error
            contrasenaEditText.setError("Ingresar contraseña mínimo 8 caracteres, 1 Mayúscula, 1 número y 1 carácter especial");
            return false ;
        }return true;
    }

    //metodo para comprobar confirmar contrasena
    public boolean confirmarContrasena (){
        // Castear la confirmar contrasena
        String confirmarContrasena = confirmarContrasenaEditText.getText().toString().trim();
        // Castear la contrasena
        String contrasena = contrasenaEditText.getText().toString().trim();
        // validar el string introducido sea igual a la contrasena
        if (confirmarContrasena.isEmpty() || !confirmarContrasena.equals(contrasena)){
            // mostar en el editext confirmar contrasena el error
            confirmarContrasenaEditText.setError("Las contraseñas no coinciden");
            return false ;
        }return true;
    }

    // metodo para pasar a la activity de Ingresar
    public void goToIngresar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // metodo para pasar a la activity del Inicio
    public void goToInicio (View view) {
        if (this.comprobarNombre() && this.comprobarCorreo() && this.comprobarContrasena() && this.confirmarContrasena()) {
            Intent intent = new Intent(this, InicioActivityView.class);
            startActivity(intent);
        }else {
            Toast mensaje = Toast.makeText(getApplicationContext(),"Por favor llenar todos los campos obligatorios",Toast.LENGTH_SHORT );
            mensaje.show();
        };
    }
}

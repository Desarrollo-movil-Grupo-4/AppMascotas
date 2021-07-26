package com.nallis.clubanimals.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nallis.clubanimals.R;

import java.util.regex.Pattern;

public class RecuperarContrasenaView extends AppCompatActivity {

    // Crear objetos
    Button recuperarBoton;
    EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena_view);

        // Comunicarse parte logica y grafica
        recuperarBoton = findViewById(R.id.btn_recuperar);
        emailEditText = findViewById(R.id.etxt_email);

        //Poner un litener al boton recuperar
        recuperarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });
    }

    // metodo para validar que sea un correo electronico
    public void validar (){
        // Castear el email, .trim() para quitar espacios
        String email = emailEditText.getText().toString().trim();
        // validar el string introducido sea email
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            // mostar en el editext email el error
            emailEditText.setError("Correo inválido");
            return ;
        }else{
            Toast mensaje = Toast.makeText(getApplicationContext(),"Correo enviado satisfactoriament a: "+ email,Toast.LENGTH_LONG );
            mensaje.show();
        }
    }

    // metodo para devolerse a ingresar
    public void goToIngresar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
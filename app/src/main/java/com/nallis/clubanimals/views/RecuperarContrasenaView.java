package com.nallis.clubanimals.views;

import static android.view.Gravity.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.nallis.clubanimals.R;

import java.util.regex.Pattern;


public class RecuperarContrasenaView extends AppCompatActivity {

    // Crear objetos
    Button recuperarBoton;
    EditText emailEditText;
    private String email;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena_view);

        // Comunicarse parte logica y grafica
        recuperarBoton = findViewById(R.id.btn_recuperar);
        emailEditText = findViewById(R.id.etxt_email);

        mAuth = FirebaseAuth.getInstance();
        //Poner un listener al boton recuperar
        recuperarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast mensaje = Toast.makeText(getApplicationContext(),
                        "No se pudo válidar la información, por favor intente más tarde",
                        Toast.LENGTH_SHORT);
                mensaje.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
                mensaje.show();
                if (validar()) {
                    resetPassword();
                }
            }
        });
    }

    //metodo para reiniciar contrasena
    private void resetPassword() {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast mensaje = Toast.makeText(RecuperarContrasenaView.this,
                            "Correo enviado satisfactoriament a: "+ email,Toast.LENGTH_LONG );
                            mensaje.show();
                }else{
                    Toast mensaje = Toast.makeText(RecuperarContrasenaView.this,
                            "No se pudo válidar la información, por favor intente más tarde",
                            Toast.LENGTH_SHORT);
                    mensaje.show();
                }
            }
        });
    }

    // metodo para validar que sea un correo electronico
    public boolean validar (){
        // Castear el email, .trim() para quitar espacios
        email = emailEditText.getText().toString().trim();
        // validar el string introducido sea email
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            // mostar en el editext email el error
            emailEditText.setError("Correo inválido");
            return false;
        }return true;
    }

    // metodo para devolerse a ingresar
    public void goToIngresar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
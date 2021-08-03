package com.nallis.clubanimals.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nallis.clubanimals.R;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
    // Crear objetos
    private EditText nombreEditText;
    private EditText correoEditText;
    private EditText contrasenaEditText;
    private EditText confirmarContrasenaEditText;
    Button registrarseBoton;

    private EditText nombre, correo, contrasena, confcontrasena;
    private Button btn_registrar;

    //variables de datos a registrar
    private String name = "";
    private String email = "";
    private String pass = "";
    private String conpass = "";

    //firebase
    FirebaseAuth auth;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registroa);


        //instanciar firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        // Conexion parte logica con la grafica
        nombre = (EditText) findViewById(R.id.txt_nombre);
        nombreEditText = (EditText)findViewById(R.id.txt_nombre);
        correo = (EditText) findViewById(R.id.txt_correo);
        contrasena = (EditText) findViewById(R.id.txt_contrasena);
        confcontrasena = (EditText) findViewById(R.id.txt_confirmar_contrasena);
        btn_registrar = findViewById(R.id.btn__registrarse);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nombre.getText().toString();
                email = correo.getText().toString();
                pass = contrasena.getText().toString();
                conpass = confcontrasena.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !conpass.isEmpty()) {
                    if (pass.length() >= 6 && conpass.length() >= 6) {
                        registrarUsuario();
                    } else {
                        Toast.makeText(RegistroActivity.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
        private void registrarUsuario(){
            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        Map<String, Object> map = new HashMap<>();
                        map.put( "name", name);
                        map.put( "email", email);
                        map.put( "pass", pass);
                        map.put( "conpass", conpass);

                        String id = auth.getCurrentUser().getUid();

                        db.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task2) {

                                if(task2.isSuccessful()){
                                    startActivity(new Intent(RegistroActivity.this, InicioActivityView.class));
                                    finish();
                                }else{
                                    Toast.makeText(RegistroActivity.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                        else{
                        Toast.makeText(RegistroActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        // Escucha del evento Enter en nombre y se lanza a la funcion de comprobar Nombre
/*
        nombre.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
            if (actionId == EditorInfo.IME_NULL){
                comprobarNombre();
                return true;
            }return false;
        }
    });

        // Escucha del evento Enter en correo y se lanza a la funcion de comprobar correo

        correo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL){
                    comprobarCorreo();
                    return true;
                }return false;
            }
        });

        // Escucha del evento Enter en contrasena y se lanza a la funcion de comprobar contrasena
        contrasena.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL){
                    comprobarContrasena();
                    return true;
                }return false;
            }
        });

        // Escucha del evento Enter en confirmar contrasena y se lanza a la funcion de comprobar confirmar contrasena
        confcontrasena.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL){
                    confirmarContrasena();
                    return true;
                }return false;
            }
        });

*/

    // metodo para combrobar Nombre
    public boolean comprobarNombre(){
        // Castear el nombre,
        String name = nombre.getText().toString();
        // validar el string introducido no este vacio
        if (name.isEmpty()){
            // mostar en el editext nombre el error
            nombre.setError("Introducir Nombre por favor");
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

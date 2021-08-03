package com.nallis.clubanimals.views;


import android.content.Intent;
import android.os.Bundle;
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
import com.nallis.clubanimals.R;

public class MainActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText txt_email;
    private EditText txt_contra;
    
    //Variables de inicio de sesión

    private String correo;
    private String contrasena;

    FirebaseAuth auth;

    //Crear variables
    //public static String usuario = "ejemplo@ejemplo.com";
    //public static String contrasenas = "ejemplo123";

    //Crear objetos
    //private EditText email;
    //private EditText password;
    //SharedPreferences preferences;
    //SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        auth = FirebaseAuth.getInstance();

        //sesion = findViewById(R.id.sesion);
        txt_email = findViewById(R.id.Email);
        txt_contra = findViewById(R.id.Password);
        btn_login = findViewById(R.id.buttonLogin);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo =  txt_email.getText().toString();
                contrasena = txt_contra.getText().toString();

                if (!correo.isEmpty() && !contrasena.isEmpty()){
                    loginUsuario();
                }else{
                    Toast.makeText(MainActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUsuario(){
        auth.signInWithEmailAndPassword(correo, contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, InicioActivityView.class));
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "No se pudo iniciar sesion, intenta nuevamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//si tiene logueo que lo lleve al inicio asi cierre la app
    @Override
    protected void onStart() {
        super.onStart();

        if(auth.getCurrentUser() != null){
           startActivity (new Intent(MainActivity.this, InicioActivityView.class));
            fileList();
        }
    }
/*
    public void goToRegistrar(View view) {

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
 */
    public void goToRegistrar(View view){
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
    public void goToContrasena(View view){
        Intent intent = new Intent(this, RecuperarContrasenaView.class);
        startActivity(intent);
    }

}

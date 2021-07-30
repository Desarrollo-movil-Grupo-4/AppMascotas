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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nallis.clubanimals.R;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

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


        nombre = (EditText) findViewById(R.id.txt_nombre);
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
}

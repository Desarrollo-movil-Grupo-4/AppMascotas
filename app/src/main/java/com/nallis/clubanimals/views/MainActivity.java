package com.nallis.clubanimals.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private CheckBox sesion;
    private EditText txt_email;
    private EditText txt_contra;
    //SharedPreferences preferences;
    //SharedPreferences.Editor editor;
    //Variables de inicio de sesión

    private String email;
    private String password;

    FirebaseAuth auth;

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
                email =  txt_email.getText().toString();
                password = txt_contra.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    loginUsuario();
                }else{
                    Toast.makeText(MainActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUsuario(){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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

    public void goToRegistrar(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

}
//clase 22 julio
        /*
        preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        editor = preferences.edit();


        if(revisarSesion()){
            startActivity(new Intent(this, InicioActivityView.class));
        }else{
            Toast.makeText(getApplicationContext(), "debes iniciar sesion", Toast.LENGTH_SHORT).show();
        }
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
}*/

package com.nallis.clubanimals.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nallis.clubanimals.R;

public class InicioActivityView extends AppCompatActivity {

    private Button btn_cerrarsesion;


    FirebaseAuth auth;
    DatabaseReference db;
    private TextView tv_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_view);


        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        btn_cerrarsesion = findViewById(R.id.btn_deslogueo);

        tv_result = (TextView)findViewById(R.id.nomPerfil);

        btn_cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();

                startActivity(new Intent(InicioActivityView.this, MainActivity.class));
                finish();
            }
        });
        infoUsuario();
    }

    private void infoUsuario(){

        String id = auth.getCurrentUser().getUid();
        db.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String name = snapshot.child("name").getValue().toString();
                    //String email = snapshot.child("name").getValue().toString();

                    tv_result.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void goToPerfil(View view) {
        Intent intent = new Intent(this, PerfilUsuarioView.class);
        startActivity(intent);
    }

    public void goToVeterinaria(View view){
        Intent intent = new Intent(this, VeterinariaActivity.class);

    }
    public void goToServiciolocal(View view) {
        Intent intent = new Intent(this, UbicacionActivity.class);

        startActivity(intent);
    }
    public void goToLocalizacion(View view) {
        Intent intent = new Intent(this, serviciolocal.class);
        startActivity(intent);
    }
    public void goToVeterinaria(View view) {
        Intent intent = new Intent(this, VeterinariaActivity.class);
        startActivity(intent);
    }
    public void goToLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goToInicio(View view) {
        Intent intent = new Intent(this, InicioActivityView.class);
        startActivity(intent);
    }
}
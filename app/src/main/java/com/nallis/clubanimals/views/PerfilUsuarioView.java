package com.nallis.clubanimals.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.nallis.clubanimals.R;

import java.util.HashMap;
import java.util.Map;

public class PerfilUsuarioView extends AppCompatActivity {

    private Button btn_img, btn_cancelar, btn_guardar;
    private EditText tv_result;
    private EditText etxt_correo, etxt_telefono, etxt_localizacion, etxt_contrasena;
    //private static final int GALERIA = 1;

    StorageReference stores;
    FirebaseAuth auth;
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario_view);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();
        tv_result = findViewById(R.id.txt_perfil_nombre);
        etxt_correo = findViewById(R.id.etxt_correo) ;
        etxt_telefono = findViewById(R.id.etxt_telefono);
        etxt_localizacion = findViewById(R.id.etxt_localizacion);
        etxt_contrasena = findViewById(R.id.etxt_contrasena);

        btn_cancelar = findViewById(R.id.btn_perfil_cancelar);
        btn_guardar = findViewById(R.id.btn_perfil_guardar);

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerfilUsuarioView.this, InicioActivityView.class));
                finish();
            }
        });

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarPerfil();
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
                    String email = snapshot.child("email").getValue().toString();
                    String localizacion = snapshot.child("localizacion").getValue().toString();
                    String contrasena = snapshot.child("pass").getValue().toString();
                    String telefono = snapshot.child("telefono").getValue().toString();

                    tv_result.setText(name);
                    etxt_correo.setText(email);
                    etxt_telefono.setText(telefono);
                    etxt_localizacion.setText(localizacion);
                    etxt_contrasena.setText(contrasena);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void actualizarPerfil(){

        Map<String, Object> map = new HashMap<>();
        map.put("email", etxt_correo.getText());
        map.put("telefono", etxt_telefono.getText());

        String id = auth.getCurrentUser().getUid();
        db.child("Users").child(id).updateChildren(map);
    }
    public void goToInicio(View v){
            Intent intent = new Intent(this, InicioActivityView.class);
            startActivity(intent);
        }
    }
/*
        btn_img = (Button)findViewById(R.id.btn_image);

        stores = FirebaseStorage.getInstance().getReference();

        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALERIA);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALERIA && resultCode == RESULT_OK);

        Uri uri = data.getData();

        StorageReference imagen = stores.child("fotos").child(uri.getLastPathSegment());

        imagen.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(PerfilUsuarioView.this, "Se publico exitosamente la foto", Toast.LENGTH_SHORT).show();
            }
        });
    }


 */

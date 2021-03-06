package com.nallis.clubanimals.views;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nallis.clubanimals.R;

import java.util.HashMap;
import java.util.Map;

public class PerfilUsuarioView extends AppCompatActivity {



    private static final int File = 1;


    private Button btn_img, btn_cancelar, btn_guardar;
    private EditText tv_result;
    private EditText etxt_correo, etxt_telefono, etxt_localizacion, etxt_contrasena;
    private ImageView img_perfil;
    private ProgressDialog dialog;

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

        img_perfil = (ImageView) findViewById(R.id.imageView3);
        img_perfil.setOnClickListener(view -> fileUpload());
        dialog = new ProgressDialog(this);

        infoUsuario();
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

        etxt_contrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerfilUsuarioView.this, RecuperarContrasenaView.class));
                finish();
            }
        });
    }

    public void fileUpload(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,File);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == File){
            if(resultCode == RESULT_OK){

                dialog.setTitle("Subiendo...");
                dialog.setMessage("Subiendo foto");
                dialog.setCancelable(false);
                dialog.show();

                Uri FileUri = data.getData();
                StorageReference Folder = FirebaseStorage.getInstance().getReference().child("Users");
                final StorageReference file_name = Folder.child("file"+FileUri.getLastPathSegment());

                file_name.putFile(FileUri).addOnCompleteListener(taskSnapshot -> file_name.getDownloadUrl().addOnSuccessListener(uri -> {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("photo", String.valueOf(uri));

                    String id = auth.getCurrentUser().getUid();
                    db.child("Users").child(id).updateChildren(map);
                    dialog.dismiss();
                    String foto = String.valueOf(uri);

                }));
            }
        }
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
                    String foto = snapshot.child("photo").getValue().toString();
                    tv_result.setText(name);
                    etxt_correo.setText(email);
                    etxt_telefono.setText(telefono);
                    etxt_localizacion.setText(localizacion);
                    etxt_contrasena.setText(contrasena);
                    Glide.with(PerfilUsuarioView.this)
                            .load(foto)
                            .fitCenter()
                            .centerCrop()
                            .into(img_perfil);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void actualizarPerfil(){

        Map<String, Object> map = new HashMap<>();
        map.put("name", tv_result.getText().toString());
        map.put("email", etxt_correo.getText().toString());
        map.put("telefono", etxt_telefono.getText().toString());

        String id = auth.getCurrentUser().getUid();
        db.child("Users").child(id).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    startActivity(new Intent(PerfilUsuarioView.this, InicioActivityView.class));
                    finish();
                }else{
                    Toast.makeText(PerfilUsuarioView.this, "No se pudieron actualizar los datos correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void goToInicio(View v){
            Intent intent = new Intent(this, InicioActivityView.class);
            startActivity(intent);
        }
    }

package com.nallis.clubanimals.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.StorageReference;
import com.nallis.clubanimals.R;

public class PerfilUsuarioView extends AppCompatActivity {

    private Button btn_img;
    private TextView tv_result;

    //private static final int GALERIA = 1;

    StorageReference stores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario_view);
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

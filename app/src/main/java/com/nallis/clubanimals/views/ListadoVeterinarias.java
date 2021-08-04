package com.nallis.clubanimals.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nallis.clubanimals.R;
import com.nallis.clubanimals.views.Objetos.Adapter;
import com.nallis.clubanimals.views.Objetos.Veterinaria;

import java.util.ArrayList;
import java.util.List;

public class ListadoVeterinarias extends AppCompatActivity {

    // Creacion de objetos
    RecyclerView rv;
    List<Veterinaria> veterinarias;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_veterinarias);

        // conexion parte logica y grafica
        rv=(RecyclerView) findViewById(R.id.recycler);

        rv.setLayoutManager(new LinearLayoutManager(this));

        veterinarias = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        adapter = new Adapter(veterinarias);
        rv.setAdapter(adapter);


        database.getReference().getRoot().child("Veterinaria").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                veterinarias.removeAll(veterinarias);
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {

                    Veterinaria veterinaria = snapshot.getValue(Veterinaria.class);
                    if(veterinaria.getS01().length() != 0){

                    veterinarias.add(veterinaria);};


                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
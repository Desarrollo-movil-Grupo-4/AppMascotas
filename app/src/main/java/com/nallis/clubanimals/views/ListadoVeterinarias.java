package com.nallis.clubanimals.views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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
    InicioActivityView inicio;
    TextView tv_servicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_veterinarias);

        tv_servicio = findViewById(R.id.textView2);

        String nombreServicio = getIntent().getStringExtra("nombreServicio");

        tv_servicio.setText(nombreServicio);
        // conexion parte logica y grafica
        rv=(RecyclerView) findViewById(R.id.recycler);

        rv.setLayoutManager(new LinearLayoutManager(this));

        veterinarias = new ArrayList<>();

        inicio = new InicioActivityView();


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

                    String servicio = getIntent().getStringExtra("servicio");

                    if (servicio.equals("s01")){
                        if(veterinaria.getS01() .length() != 0){

                            veterinarias.add(veterinaria);};

                    }else if (servicio.equals("s02")){
                        if(veterinaria.getS02().length() != 0){

                            veterinarias.add(veterinaria);};

                    }else if (servicio.equals("s03")){
                        if(veterinaria.getS03().length() != 0){

                            veterinarias.add(veterinaria);};
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
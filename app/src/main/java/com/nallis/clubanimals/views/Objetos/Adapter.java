package com.nallis.clubanimals.views.Objetos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nallis.clubanimals.R;
import com.nallis.clubanimals.views.ContratarActivityView;
import com.nallis.clubanimals.views.MapActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.VeterinariasHolder>{
    private List<Veterinaria> veterinarias;


    static class VeterinariasHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //creacion de contexto
        Context context;
        // Creacion de objetos que muestra la tarjeta
        TextView textViewNombreVet, textViewDireccionVet, textViewS01;
        Button btnPerfil, btnLocalizacion, btnContratar;

        public VeterinariasHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            // conexion con la vista
            textViewNombreVet = itemView.findViewById(R.id.textview_nombrevet);
            textViewDireccionVet = itemView.findViewById(R.id.textview_direccionvet);
            textViewS01 = itemView.findViewById(R.id.textview_s01);
            btnPerfil = itemView.findViewById(R.id.perfil_veterinaria);
            btnLocalizacion = itemView.findViewById(R.id.btn_localizar);
            btnContratar = itemView.findViewById(R.id.btn_contratar);
        }

        // Crear metodo que tiene los eventos de click
        public void setOnClickListeners() {
            btnPerfil.setOnClickListener(this);
            btnLocalizacion.setOnClickListener(this);
            btnContratar.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // encontrar en que boton se dio click
            switch (view.getId()) {
                case R.id.perfil_veterinaria:
                    break;
                case R.id.btn_localizar:
                    Intent i = new Intent(context, MapActivity.class);
                    context.startActivity(i);
                    break;
                case R.id.btn_contratar:
                    Intent intent = new Intent(context, ContratarActivityView.class);
                    context.startActivity(intent);
                    break;
            }
        }
    }
    public Adapter(List<Veterinaria> Lveterinarias) {
            veterinarias = Lveterinarias;
    }


        @Override
        public VeterinariasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_recycler, parent, false);
            //VeterinariasHolder holder = new VeterinariasHolder(v);
            //return holder;
            return new VeterinariasHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VeterinariasHolder holder, int position) {
            Veterinaria veterinaria = veterinarias.get(position);
            holder.textViewNombreVet.setText(veterinaria.getNombre());
            holder.textViewDireccionVet.setText(veterinaria.getDireccion());
            //holder.textViewS01.setText(veterinaria.getS01());
            // colocar evento
            holder.setOnClickListeners();

        }

        @Override
        public int getItemCount() {
            return veterinarias.size();
        }

    }


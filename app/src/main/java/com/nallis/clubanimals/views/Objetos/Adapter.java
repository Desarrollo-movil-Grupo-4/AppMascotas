package com.nallis.clubanimals.views.Objetos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nallis.clubanimals.R;
import com.nallis.clubanimals.views.ContratarActivityView;
import com.nallis.clubanimals.views.MapActivity;
import com.nallis.clubanimals.views.VeterinariaActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.VeterinariasHolder>{
    private List<Veterinaria> veterinarias;


    static class VeterinariasHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //creacion de contexto
        Context context;
        double latitud;
        double longitud;
        String whatsapp;
        String correo;
        String foto;
        // Creacion de objetos que muestra la tarjeta
        TextView textViewNombreVet, textViewDireccionVet, textViewS01;
        Button btnPerfil, btnLocalizacion, btnContratar;
        ImageView fotoVet;

        public VeterinariasHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            // conexion con la vista
            textViewNombreVet = itemView.findViewById(R.id.textview_nombrevet);
            textViewDireccionVet = itemView.findViewById(R.id.textview_direccionvet);

           // textViewS01 = itemView.findViewById(R.id.textview_s01);
            btnPerfil = itemView.findViewById(R.id.perfil_veterinaria);

            btnLocalizacion = itemView.findViewById(R.id.btn_localizar);
            btnContratar = itemView.findViewById(R.id.btn_contratar);
            fotoVet = itemView.findViewById(R.id.fotoVet);
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
                case R.id.btnperfil_veterinaria:
                    Intent in = new Intent(context, VeterinariaActivity.class);
                    in.putExtra("nombrevet",textViewNombreVet.getText());
                    in.putExtra("direccionVet",textViewDireccionVet.getText());
                    in.putExtra("whatsappVet",whatsapp);
                    in.putExtra("correoVet",correo);
                    context.startActivity(in);
                    break;
                case R.id.btn_localizar:
                    Intent i = new Intent(context, MapActivity.class);
                    i.putExtra("nombrevet",textViewNombreVet.getText());
                    i.putExtra("latitudVet",latitud);
                    i.putExtra("longitudVet",longitud);
                    context.startActivity(i);
                    break;
                case R.id.btn_contratar:
                    Intent intent = new Intent(context, ContratarActivityView.class);
                    intent.putExtra("nombrevet",textViewNombreVet.getText());
                    intent.putExtra("direccionVet",textViewDireccionVet.getText());
                    intent.putExtra("whatsappVet",whatsapp);
                    intent.putExtra("correoVet",correo);
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
            return new VeterinariasHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VeterinariasHolder holder, int position) {
            Veterinaria veterinaria = veterinarias.get(position);
            holder.latitud = veterinaria.getLatitud();
            holder.longitud = veterinaria.getLongitud();
            holder.whatsapp = veterinaria.getWhatsapp();
            holder.correo = veterinaria.getCorreo();
            holder.foto = veterinaria.getFoto();
            holder.textViewNombreVet.setText(veterinaria.getNombre());
            holder.textViewDireccionVet.setText(veterinaria.getDireccion());

            //holder.textViewS01.setText(nombreServicio);
            // colocar evento
            holder.setOnClickListeners();
            Glide.with(holder.fotoVet)
                    .load(holder.foto)
                    .fitCenter()
                    //.centerCrop()
                    .into(holder.fotoVet);

        }

        @Override
        public int getItemCount() {
            return veterinarias.size();
        }

    }


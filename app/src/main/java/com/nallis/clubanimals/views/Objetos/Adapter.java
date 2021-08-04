package com.nallis.clubanimals.views.Objetos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nallis.clubanimals.R;
import com.nallis.clubanimals.views.ListadoVeterinarias;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.VeterinariasHolder> {
    List<Veterinaria> veterinarias;

    public Adapter(List<Veterinaria> veterinarias) {
        this.veterinarias = veterinarias;
    }

    @NonNull
    @Override
    public VeterinariasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler,parent,false);
        VeterinariasHolder holder = new VeterinariasHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VeterinariasHolder holder, int position) {
        Veterinaria veterinaria = veterinarias.get(position);
        holder.textViewNombreVet.setText(veterinaria.getNombre());
        holder.textViewDireccionVet.setText(veterinaria.getDireccion());
        holder.textViewS01.setText(veterinaria.getS01());
    }

    @Override
    public int getItemCount() {
        return veterinarias.size();
    }

    public static class VeterinariasHolder extends RecyclerView.ViewHolder{
        // Creacion de objetos que muestra la tarjeta
        TextView textViewNombreVet, textViewDireccionVet, textViewS01;
        public VeterinariasHolder(@NonNull View itemView) {
            super(itemView);
            // conexion con la vista
            textViewNombreVet = itemView.findViewById(R.id.textview_nombrevet);
            textViewDireccionVet = itemView.findViewById(R.id.textview_direccionvet);
            textViewS01 = itemView.findViewById(R.id.textview_s01);
        }
    }
}

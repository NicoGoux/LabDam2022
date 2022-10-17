package com.mdgz.dam.labdam2022;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolder> {

    private final ArrayList<Alojamiento> listaDatos;

    public AdapterDatos(ArrayList<Alojamiento> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.asignarDatos(listaDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titulo;
        TextView descripcion;
        TextView capacidad;
        TextView precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo_item);
            descripcion = itemView.findViewById(R.id.descripcion_item);
            capacidad = itemView.findViewById(R.id.capacidad_item);
            precio = itemView.findViewById(R.id.precio_item);
        }

        public void asignarDatos(Alojamiento alojamiento) {
            if (alojamiento instanceof Habitacion) {
                titulo.setText(((Habitacion) alojamiento).getHotel().getNombre());
            }
            else {
                titulo.setText(alojamiento.getTitulo());
            }

            descripcion.setText(alojamiento.getDescripcion());
            capacidad.setText("Capacidad: " + alojamiento.getCapacidad());
            precio.setText("AR$"+alojamiento.getPrecioBase());
        }
    }
}

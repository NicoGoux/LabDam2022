package com.mdgz.dam.labdam2022.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.retrofit.AppRetrofit;
import com.mdgz.dam.labdam2022.data.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.data.factory.FavoritoRepositoryFactory;
import com.mdgz.dam.labdam2022.data.repository.FavoritoRepository;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;

import java.util.ArrayList;
import java.util.UUID;

public class AdapterAlojamiento extends RecyclerView.Adapter<AdapterAlojamiento.ViewHolder> {

    private final ArrayList<Alojamiento> listaDatos;

    public AdapterAlojamiento(ArrayList<Alojamiento> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_alojamiento,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Alojamiento alojamiento = listaDatos.get(position);

        // TODO hardcodeado porque no sabiamos como obtener un UUID de la aplicacion
        UUID user_id = UUID.fromString("ba6dbe60-387b-412e-8fbb-0971f6f0c21a");

        // Se crean ambos repository (uno para API y otro para DB)
        FavoritoRepository frDb = FavoritoRepositoryFactory.create(holder.context);
        FavoritoRepository frApi = FavoritoRepositoryFactory.create();
        holder.asignarDatos(alojamiento, frDb, user_id);

        holder.favorito.setOnClickListener((View view1) -> {
            if(holder.favorito.getColorFilter() == null){
                holder.favorito.setColorFilter(Color.RED);

                OnResult<Favorito> favoritoCallback = new OnResult<Favorito>() {
                    @Override
                    public void onSuccess(Favorito result) {
                    }

                    @Override
                    public void onError(Throwable exception) {
                        exception.printStackTrace();
                    }
                };

                Favorito fav = new Favorito(alojamiento.getId(), user_id);

                AppDataBase.EXECUTOR_DB.execute(() -> frDb.guardarFavorito(fav, favoritoCallback));
                AppRetrofit.EXECUTOR_API.execute(() -> frApi.guardarFavorito(fav, favoritoCallback));
            }
            else{
                holder.favorito.setColorFilter(null);
                OnResult<Favorito> favoritoCallback = new OnResult<Favorito>() {
                    @Override
                    public void onSuccess(Favorito result) {
                    }

                    @Override
                    public void onError(Throwable exception) {
                        exception.printStackTrace();
                    }
                };

                Favorito fav = new Favorito(alojamiento.getId(), user_id);

                AppDataBase.EXECUTOR_DB.execute(() -> frDb.eliminarFavorito(fav, favoritoCallback));
                AppRetrofit.EXECUTOR_API.execute(() -> frApi.eliminarFavorito(fav, favoritoCallback));
            }
        });

        holder.itemView.setOnClickListener(view -> {
            Bundle args = new Bundle();
            Alojamiento seleccionado = listaDatos.get(holder.getLayoutPosition());
            args.putParcelable("alojamiento_seleccionado", seleccionado);
            Navigation.findNavController(view).navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment, args);
        });
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
        ImageButton favorito;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo_item);
            descripcion = itemView.findViewById(R.id.descripcion_item);
            capacidad = itemView.findViewById(R.id.capacidad_item);
            precio = itemView.findViewById(R.id.precio_item);
            favorito = itemView.findViewById(R.id.favoriteButton);
            context = itemView.getContext();
        }

        public void asignarDatos(Alojamiento alojamiento, FavoritoRepository fr, UUID user_id) {
            if (alojamiento instanceof Habitacion) {
                titulo.setText(((Habitacion) alojamiento).getHotel().getNombre());
            }
            else {
                titulo.setText(alojamiento.getTitulo());
            }

            descripcion.setText(alojamiento.getDescripcion());
            capacidad.setText("Capacidad: " + alojamiento.getCapacidad());
            precio.setText("ARS$"+alojamiento.getPrecioBase());

            OnResult<Boolean> perteneceCallback = new OnResult<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    if (result) {
                        favorito.setColorFilter(Color.RED);
                    }
                    else {
                        favorito.setColorFilter(null);
                    }
                }
                @Override
                public void onError(Throwable exception) {
                    exception.printStackTrace();
                }
            };

            AppDataBase.EXECUTOR_DB.execute(() -> fr.perteneceFavorito(new Favorito(alojamiento.getId(), user_id),perteneceCallback));

        }
    }
}

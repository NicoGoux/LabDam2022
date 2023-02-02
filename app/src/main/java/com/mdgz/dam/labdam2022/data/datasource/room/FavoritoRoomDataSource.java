package com.mdgz.dam.labdam2022.data.datasource.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.FavoritoDataSource;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.FavoritoDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.data.datasource.room.mapper.FavoritoMapper;
import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;
import java.util.UUID;

public class FavoritoRoomDataSource implements FavoritoDataSource {

    private final FavoritoDAO favoritoDAO;

    public FavoritoRoomDataSource(final Context context) {
        this(AppDataBase.getInstance(context));
    }

    public FavoritoRoomDataSource(final AppDataBase db) {
        favoritoDAO = db.favoritoDAO();
    }

    @Override
    public void guardarFavorito(Favorito favorito, OnResult<Favorito> callback) {
        try {


            favoritoDAO.insertar(FavoritoMapper.toEntity(favorito));
            callback.onSuccess(favorito);
        }
        catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void eliminarFavorito(Favorito favorito, OnResult<Favorito> callback) {
        try {
            favoritoDAO.eliminar(FavoritoMapper.toEntity(favorito));
            callback.onSuccess(favorito);
        }
        catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void perteneceFavorito(Favorito favorito, OnResult<Boolean> callback) {
        try {
            UUID alojamiento_id = favoritoDAO.pertenece(favorito.getAlojamientoId(), favorito.getUsuarioID());
            if (alojamiento_id != null) {
                callback.onSuccess(true);
            }
            else {
                callback.onSuccess(false);
            }
        }
        catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void limpiarFavoritos(OnResult<Boolean> callback) {
        try {
            favoritoDAO.clearTable();
            callback.onSuccess(true);
        }
        catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void consultarFavoritos(UUID userId, OnResult<List<Favorito>> callback) {}
}

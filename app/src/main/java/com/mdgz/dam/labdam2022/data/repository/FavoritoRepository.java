package com.mdgz.dam.labdam2022.data.repository;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.FavoritoDataSource;
import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;
import java.util.UUID;

public class FavoritoRepository implements FavoritoDataSource {

    private final FavoritoDataSource ds;

    public FavoritoRepository(final FavoritoDataSource ds) {
        this.ds = ds;
    }

    @Override
    public void guardarFavorito(Favorito favorito, OnResult<Favorito> callback) {
        ds.guardarFavorito(favorito,callback);
    }

    @Override
    public void eliminarFavorito(Favorito favorito, OnResult<Favorito> callback) {
        ds.eliminarFavorito(favorito,callback);
    }

    @Override
    public void perteneceFavorito(Favorito favorito, OnResult<Boolean> callback) {
        ds.perteneceFavorito(favorito,callback);
    }

    public void consultarFavoritos(UUID userId, OnResult<List<Favorito>> callback) {
        ds.consultarFavoritos(userId, callback);
    }

    public void limpiarFavoritos(OnResult<Boolean> callback) {
        ds.limpiarFavoritos(callback);
    }

}

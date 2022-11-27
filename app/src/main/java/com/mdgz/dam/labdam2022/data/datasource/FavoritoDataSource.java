package com.mdgz.dam.labdam2022.data.datasource;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;

public interface FavoritoDataSource {
    /**
     * Guarda un nuevo favorito
     **/
    void guardarFavorito(Favorito favorito, OnResult<Favorito> callback);

    void eliminarFavorito(Favorito favorito, OnResult<Favorito> callback);

    void perteneceFavorito(Favorito favorito, OnResult<Boolean> callback);

    /**
     * Recupera la lista de favoritos
     **/
    void recuperarFavoritos(OnResult<List<Favorito>> callback);
}

package com.mdgz.dam.labdam2022.data.datasource;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;
import java.util.UUID;

public interface FavoritoDataSource {
    /**
     * Guarda un nuevo favorito
     **/
    void guardarFavorito(Favorito favorito, OnResult<Favorito> callback);

    /**
     * elimina un favorito
     **/
    void eliminarFavorito(Favorito favorito, OnResult<Favorito> callback);

    /**
     * Consulta si el elemento se encuentra en los favoritos
     **/
    void perteneceFavorito(Favorito favorito, OnResult<Boolean> callback);

    void consultarFavoritos(UUID userId, OnResult<List<Favorito>> callback);

    void limpiarFavoritos(OnResult<Boolean> callback);
}

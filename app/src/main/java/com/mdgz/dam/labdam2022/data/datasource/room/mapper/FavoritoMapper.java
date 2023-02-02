package com.mdgz.dam.labdam2022.data.datasource.room.mapper;

import com.mdgz.dam.labdam2022.data.datasource.room.entities.FavoritoEntity;
import com.mdgz.dam.labdam2022.model.Favorito;

public class FavoritoMapper {
    private FavoritoMapper() {
    }

    public static FavoritoEntity toEntity(final Favorito favorito) {
        return new FavoritoEntity(
                favorito.getAlojamientoId(),
                favorito.getUsuarioID()
        );
    }

    public static Favorito fromEntity(FavoritoEntity favorito) {
        return new Favorito(
                favorito.getAlojamientoId(),
                favorito.getUsuarioId()
        );
    }
}

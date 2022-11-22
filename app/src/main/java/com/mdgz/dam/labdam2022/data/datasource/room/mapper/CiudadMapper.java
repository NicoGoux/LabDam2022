package com.mdgz.dam.labdam2022.data.datasource.room.mapper;

import com.mdgz.dam.labdam2022.data.datasource.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.model.Ciudad;

public class CiudadMapper {
    private CiudadMapper() {
    }

    public static CiudadEntity toEntity(final Ciudad ciudad) {
        return new CiudadEntity(
                ciudad.getId(),
                ciudad.getNombre(),
                ciudad.getAbreviatura()
        );
    }

    public static Ciudad fromEntity(CiudadEntity ciudad) {
        return new Ciudad(
                ciudad.getId(),
                ciudad.getNombre(),
                ciudad.getAbreviatura()
        );
    }
}

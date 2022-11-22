package com.mdgz.dam.labdam2022.data.datasource.room.mapper;

import com.mdgz.dam.labdam2022.data.datasource.room.entities.UbicacionEntity;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Ubicacion;

public class UbicacionMapper {
    private UbicacionMapper() {
    }

    public static UbicacionEntity toEntity(final Ubicacion ubicacion) {
        return new UbicacionEntity(
                ubicacion.getLat(),
                ubicacion.getLng(),
                ubicacion.getCalle(),
                ubicacion.getNumero(),
                ubicacion.getCiudad().getId()
        );
    }

    public static Ubicacion fromEntity(UbicacionEntity ubicacion, Ciudad ciudad) {
        return new Ubicacion(
                ubicacion.getLat(),
                ubicacion.getLng(),
                ubicacion.getCalle(),
                ubicacion.getNumero(),
                ciudad
        );
    }
}

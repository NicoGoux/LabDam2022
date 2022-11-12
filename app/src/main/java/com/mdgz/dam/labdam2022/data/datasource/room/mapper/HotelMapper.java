package com.mdgz.dam.labdam2022.data.datasource.room.mapper;

import com.mdgz.dam.labdam2022.data.datasource.room.entities.HotelEntity;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.UUID;

public class HotelMapper {

    private HotelMapper() {
    }

    public static HotelEntity toEntity(final Hotel hotel, UUID ubicacion_id) {
        return new HotelEntity(
                hotel.getNombre(),
                hotel.getCategoria(),
                ubicacion_id
        );
    }

    public static Hotel fromEntity(HotelEntity hotel, Ubicacion ubicacion) {
        return new Hotel(
                hotel.getId(),
                hotel.getNombre(),
                hotel.getCategoria(),
                ubicacion
        );
    }
}

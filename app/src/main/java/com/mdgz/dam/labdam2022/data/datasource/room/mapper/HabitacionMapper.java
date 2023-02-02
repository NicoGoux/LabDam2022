package com.mdgz.dam.labdam2022.data.datasource.room.mapper;

import com.mdgz.dam.labdam2022.data.datasource.room.entities.AlojamientoEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.HabitacionEntity;
import com.mdgz.dam.labdam2022.model.Habitacion;

import java.util.UUID;

public class HabitacionMapper {

    private HabitacionMapper() {
    }

    public static HabitacionEntity toEntity(final Habitacion habitacion, final UUID alojamientoId, final UUID hotelId) {
        return new HabitacionEntity(
            habitacion.getId() == null? UUID.randomUUID(): habitacion.getId(),
            habitacion.getCamasIndividuales(),
            habitacion.getCamasMatrimoniales(),
            habitacion.getTieneEstacionamiento(),
            alojamientoId, hotelId
        );
    }

    public static Habitacion fromEntity(HabitacionEntity habitacion, AlojamientoEntity alojamiento) {
        // TODO se le asigna el AlojamientoId para corresponder con una sola tabla tanto para la habitacion como para el departamento
        return new Habitacion(
            alojamiento.getId(),
            alojamiento.getTitulo(),
            alojamiento.getDescripcion(),
            alojamiento.getCapacidad(),
            alojamiento.getPrecioBase(),
            habitacion.getCamasIndividuales(),
            habitacion.getCamasMatrimoniales(),
            habitacion.getTieneEstacionamiento(),
            null
        );
    }
}

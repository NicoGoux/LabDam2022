package com.mdgz.dam.labdam2022.data.datasource.room.mapper;

import com.mdgz.dam.labdam2022.data.datasource.room.entities.AlojamientoEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.DepartamentoEntity;
import com.mdgz.dam.labdam2022.model.Departamento;

import java.util.UUID;

public class DepartamentoMapper {
    private DepartamentoMapper() {
    }

    public static DepartamentoEntity toEntity(final Departamento departamento, final UUID alojamientoId, final UUID ubicacionId) {
        return new DepartamentoEntity(
            departamento.getId() == null ? UUID.randomUUID() : departamento.getId(),
            departamento.getTieneWifi(),
            departamento.getCostoLimpieza(),
            departamento.getCantidadHabitaciones(),
            alojamientoId, ubicacionId
        );
    }

    public static Departamento fromEntity(DepartamentoEntity departamento, AlojamientoEntity alojamiento) {
        // TODO se le asigna el AlojamientoId para corresponder con una sola tabla tanto para la habitacion como para el departamento
        return new Departamento(
            alojamiento.getId(),
            alojamiento.getTitulo(),
            alojamiento.getDescripcion(),
            alojamiento.getCapacidad(),
            alojamiento.getPrecioBase(),
            departamento.getTieneWifi(),
            departamento.getCostoLimpieza(),
            departamento.getCantidadHabitaciones(),
            null
        );
    }
}

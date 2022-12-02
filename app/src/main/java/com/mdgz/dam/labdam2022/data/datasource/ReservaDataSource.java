package com.mdgz.dam.labdam2022.data.datasource;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;
import java.util.UUID;

public interface ReservaDataSource {
    /**
     * Guarda una nueva reserva
     **/
    void guardarReserva(Reserva reserva, OnResult<Reserva> callback);

    /**
     * Consulta las reservas de un usuario
     **/
    void consultarReservas(UUID userId, OnResult<List<Reserva>> callback);
}

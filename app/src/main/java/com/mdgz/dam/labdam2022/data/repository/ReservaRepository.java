package com.mdgz.dam.labdam2022.data.repository;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.ReservaDataSource;
import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;
import java.util.UUID;

public class ReservaRepository implements ReservaDataSource {

    private final ReservaDataSource ds;

    public ReservaRepository(final ReservaDataSource ds) {
        this.ds = ds;
    }

    @Override
    public void guardarReserva(Reserva reserva, OnResult<Reserva> callback) {
        ds.guardarReserva(reserva, callback);
    }

    @Override
    public void consultarReservas(UUID userId, OnResult<List<Reserva>> callback) {
        ds.consultarReservas(userId, callback);
    }

    public void limpiarReservas(OnResult<Boolean> callback) {
        ds.limpiarReservas(callback);
    }
}

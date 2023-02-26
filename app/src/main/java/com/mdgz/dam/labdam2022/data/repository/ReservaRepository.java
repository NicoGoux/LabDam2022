package com.mdgz.dam.labdam2022.data.repository;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.ReservaDataSource;
import com.mdgz.dam.labdam2022.data.datasource.room.ReservaRoomDataSource;
import com.mdgz.dam.labdam2022.model.Reserva;

public class ReservaRepository implements ReservaDataSource {

    private final ReservaDataSource ds;

    public ReservaRepository(final ReservaRoomDataSource ds) {
        this.ds = ds;
    }

    @Override
    public void guardarReserva(Reserva reserva, OnResult<Reserva> callback) {
        ds.guardarReserva(reserva, callback);
    }
}

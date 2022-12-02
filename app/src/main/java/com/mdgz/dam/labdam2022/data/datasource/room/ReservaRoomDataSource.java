package com.mdgz.dam.labdam2022.data.datasource.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.ReservaDataSource;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.ReservaDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.data.datasource.room.mapper.ReservaMapper;
import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;
import java.util.UUID;

public class ReservaRoomDataSource implements ReservaDataSource {

    private final ReservaDAO reservaDAO;
    public ReservaRoomDataSource(final Context context) {
        this(AppDataBase.getInstance(context));
    }

    public ReservaRoomDataSource(final AppDataBase db) {
        reservaDAO = db.reservaDAO();
    }

    @Override
    public void guardarReserva(Reserva reserva, OnResult<Reserva> callback) {
        try {
            reservaDAO.insertar(ReservaMapper.toEntity(reserva));
            callback.onSuccess(reserva);
        }
        catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void consultarReservas(UUID userId, OnResult<List<Reserva>> callback) {

    }
}

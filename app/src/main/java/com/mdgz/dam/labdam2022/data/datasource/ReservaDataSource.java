package com.mdgz.dam.labdam2022.data.datasource;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.model.Reserva;

public interface ReservaDataSource {
    /**
     * Guarda una nueva reserva
     **/
    void guardarReserva(Reserva reserva, OnResult<Reserva> callback);
}

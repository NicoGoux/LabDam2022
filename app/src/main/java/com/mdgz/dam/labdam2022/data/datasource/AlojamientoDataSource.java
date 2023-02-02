package com.mdgz.dam.labdam2022.data.datasource;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;

import java.util.List;

public interface AlojamientoDataSource {
    /**
     * Guarda una nueva habitacion
     **/
    void guardarHabitacion(Habitacion habitacion, OnResult<Habitacion> callback);

    /**
     * Guarda un nuevo departamentos
     **/
    void guardarDepartamento(Departamento departamento, OnResult<Departamento> callback);

    /**
     * Recupera la lista de habitaciones
     **/
    void recuperarHabitaciones(OnResult<List<Habitacion>> callback);

    /**
     * Recupera la lista de departamentos
     **/
    void recuperarDepartamentos(OnResult<List<Departamento>> callback);

    /**
     * Recupera la lista de alojamientos independiente si son Habitacion o Departamento
     **/
    void recuperarAlojamientos(OnResult<List<Alojamiento>> callback);

    /*
     * En este codigo se dejaron afuera otros metodos interesantes como
     * la eliminacion y modificacion de datos.
     * */
}

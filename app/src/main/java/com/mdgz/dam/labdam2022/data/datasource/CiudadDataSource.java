package com.mdgz.dam.labdam2022.data.datasource;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.List;

public interface CiudadDataSource {
    /**
     * Guarda una nueva ciudad
     **/
    void guardarCiudad(Ciudad ciudad, OnResult<Ciudad> callback);

    /**
     * Recupera la lista de ciudades
     **/
    void recuperarCiudades(OnResult<List<Ciudad>> callback);

    /**
     * Recupera ciudad por id
     **/
    void recuperaCiudadPorId(Integer id_ciudad, OnResult<Ciudad> callback);
}

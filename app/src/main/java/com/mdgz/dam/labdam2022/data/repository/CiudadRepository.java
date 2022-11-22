package com.mdgz.dam.labdam2022.data.repository;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.CiudadDataSource;
import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.List;

public class CiudadRepository implements CiudadDataSource{

    private final CiudadDataSource ds;

    public CiudadRepository(final CiudadDataSource ds) {
        this.ds = ds;
    }

    @Override
    public void guardarCiudad(Ciudad ciudad, OnResult<Ciudad> callback) {
        ds.guardarCiudad(ciudad, callback);
    }

    @Override
    public void recuperarCiudades(OnResult<List<Ciudad>> callback) {
        ds.recuperarCiudades(callback);
    }

    @Override
    public void recuperaCiudadPorId(Integer id_ciudad, OnResult<Ciudad> callback) {
        ds.recuperaCiudadPorId(id_ciudad,callback);
    }
}




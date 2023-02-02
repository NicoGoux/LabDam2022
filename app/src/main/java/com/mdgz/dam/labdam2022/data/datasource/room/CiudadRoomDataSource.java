package com.mdgz.dam.labdam2022.data.datasource.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.CiudadDataSource;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.CiudadDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.mapper.CiudadMapper;
import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.ArrayList;
import java.util.List;

public class CiudadRoomDataSource implements CiudadDataSource {
    private final CiudadDAO ciudadDAO;

    public CiudadRoomDataSource(final Context context) {
        this(AppDataBase.getInstance(context));
    }

    public CiudadRoomDataSource(final AppDataBase db) {
        ciudadDAO = db.ciudadDAO();
    }

    @Override
    public void guardarCiudad(Ciudad ciudad, OnResult<Ciudad> callback) {
        try {
            final CiudadEntity ce = CiudadMapper.toEntity(ciudad);
            ciudadDAO.insertar(ce);
            callback.onSuccess(ciudad);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarCiudades(OnResult<List<Ciudad>> callback) {
        try {
            final CiudadEntity[] ceCollection = ciudadDAO.recuperarCiudades();
            final List<Ciudad> ciudades = new ArrayList<>();
            for (final CiudadEntity ce : ceCollection) {
                ciudades.add(CiudadMapper.fromEntity(ce));
            }
            callback.onSuccess(ciudades);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperaCiudadPorId(Integer id_ciudad, OnResult<Ciudad> callback) {
        try {
            final CiudadEntity ce = ciudadDAO.recuperarCiudadPorId(id_ciudad);
            final Ciudad ciudad = CiudadMapper.fromEntity(ce);
            callback.onSuccess(ciudad);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }
}

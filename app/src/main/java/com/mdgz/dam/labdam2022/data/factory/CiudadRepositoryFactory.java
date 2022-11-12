package com.mdgz.dam.labdam2022.data.factory;

import android.content.Context;

import com.mdgz.dam.labdam2022.data.datasource.room.CiudadRoomDataSource;
import com.mdgz.dam.labdam2022.data.repository.CiudadRepository;

public final class CiudadRepositoryFactory {
    private CiudadRepositoryFactory() {
    }

    public static CiudadRepository create(final Context context) {
        return new CiudadRepository(new CiudadRoomDataSource(context));
    }
}

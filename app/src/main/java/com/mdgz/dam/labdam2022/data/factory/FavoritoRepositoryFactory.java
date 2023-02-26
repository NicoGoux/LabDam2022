package com.mdgz.dam.labdam2022.data.factory;

import android.content.Context;

import com.mdgz.dam.labdam2022.data.datasource.room.FavoritoRoomDataSource;
import com.mdgz.dam.labdam2022.data.repository.FavoritoRepository;

public class FavoritoRepositoryFactory {
    private FavoritoRepositoryFactory() {
    }

    public static FavoritoRepository create(final Context context) {
        return new FavoritoRepository(new FavoritoRoomDataSource(context));
    }
}

package com.mdgz.dam.labdam2022.data.factory;

import android.content.Context;

import com.mdgz.dam.labdam2022.data.datasource.room.ReservaRoomDataSource;
import com.mdgz.dam.labdam2022.data.repository.ReservaRepository;

public class ReservaRepositoryFactory {
    private ReservaRepositoryFactory() {
    }

    public static ReservaRepository create(final Context context) {
        return new ReservaRepository(new ReservaRoomDataSource(context));
    }
}

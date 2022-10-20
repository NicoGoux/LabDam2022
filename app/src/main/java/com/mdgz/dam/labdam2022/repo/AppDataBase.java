package com.mdgz.dam.labdam2022.repo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.mdgz.dam.labdam2022.model.*;
import com.mdgz.dam.labdam2022.utilities.Converters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Alojamiento.class, Departamento.class, Habitacion.class, Hotel.class, Ubicacion.class, Ciudad.class, Reserva.class, Favorito.class},
        version = 1,
        exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {
    //public abstract AlojamientoDAO alojamientoDAO().;
    private static final String databaseName = "sistema_alojamientos";
    private static AppDataBase instance;

    // TODO ver explicacion de threads en power
    private static final Integer nThreads = 4;
    public static final ExecutorService executorDB = Executors.newFixedThreadPool(nThreads);

    public static AppDataBase getinstance(final Context context) {
        if (instance == null) {
            synchronized (AppDataBase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(), AppDataBase.class,
                            databaseName).build();
                }
            }
        }
        return instance;
    }
}

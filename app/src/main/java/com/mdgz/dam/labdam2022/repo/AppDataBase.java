package com.mdgz.dam.labdam2022.repo;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mdgz.dam.labdam2022.model.*;
import com.mdgz.dam.labdam2022.model.modelDAO.AlojamientoDAO;
import com.mdgz.dam.labdam2022.utilities.Converters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Departamento.class, Habitacion.class},
        version = 1,
        exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {

    abstract AlojamientoDAO alojamientoDAO();

    private static final String databaseName = "sistema_alojamientos";
    private static AppDataBase INSTANCE;

    // TODO ver explicacion de threads en power
    private static final Integer nThreads = 4;
    public static final ExecutorService executorDB = Executors.newFixedThreadPool(nThreads);

    public synchronized static AppDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static AppDataBase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, databaseName).addCallback(mRoomCallback).build();
    }

    private static final RoomDatabase.Callback mRoomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            executorDB.execute(() -> {
                AlojamientoDAO dao = INSTANCE.alojamientoDAO();

//                final Ubicacion ubicacion1 = new Ubicacion(-42.6,-38.3,"San Martin","1989", CiudadRepository._CIUDADES.get(0));
//                final Ubicacion ubicacion2 = new Ubicacion(-42.25,-38.2,"Lopez y Planes","2007",CiudadRepository._CIUDADES.get(1));
//
//                final Departamento departamento = new Departamento(
//                        1,
//                        "Depto 1",
//                        "luminoso y amplio",
//                        6,
//                        300.0,
//                        true,
//                        1500.0,
//                        3,
//                        ubicacion1);
//
//                final Habitacion habitacion = new Habitacion(
//                        2,
//                        "Habitacion 2",
//                        "Espectacular suite",
//                        4,
//                        1200.0,
//                        2,
//                        1,
//                        false,
//                        new Hotel(1,"Hotel 1",3,ubicacion2) );
//
//                dao.insertarDepartamento(departamento);
//                dao.insertarHabitacion(habitacion);

                Log.i("Funciona", "funciona");
            });
        }
    };

    public void clearTables() {
        executorDB.execute(() -> {
            this.clearAllTables();
        });
    }
}

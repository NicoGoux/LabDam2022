package com.mdgz.dam.labdam2022.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mdgz.dam.labdam2022.database.modelDAO.CiudadDAO;
import com.mdgz.dam.labdam2022.database.modelDAO.UbicacionDAO;
import com.mdgz.dam.labdam2022.model.*;
import com.mdgz.dam.labdam2022.database.modelDAO.AlojamientoDAO;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repo.CiudadRepository;
import com.mdgz.dam.labdam2022.utilities.Converters;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Alojamiento.class, Departamento.class, Habitacion.class, Ciudad.class, Ubicacion.class},
        version = 1,
        exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {

    public abstract AlojamientoDAO alojamientoDAO();
    public abstract CiudadDAO ciudadDAO();
    public abstract UbicacionDAO ubicacionDAO();

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
                AlojamientoDAO alojamientoDAO = INSTANCE.alojamientoDAO();
                CiudadDAO ciudadDAO = INSTANCE.ciudadDAO();
                UbicacionDAO ubicacionDAO = INSTANCE.ubicacionDAO();

                //Inserto las ciudades
                ciudadDAO.insert(CiudadRepository.populateData());

                ArrayList<Alojamiento> listaAlojamientos = AlojamientoRepository.populateData();

                for (Alojamiento alojamiento : listaAlojamientos) {
                    alojamientoDAO.insertar(alojamiento);
                    if ((alojamiento instanceof Habitacion)) {
                        Habitacion habitacion = (Habitacion) alojamiento;
                        habitacion.setIdAlojamiento(alojamiento.getId());

                        //Insercion hotel

                        //Insercion ubicacion hotel

                        //Insercion habitacion
                        alojamientoDAO.insertar(habitacion);
                    } else {
                        Departamento departamento = (Departamento) alojamiento;
                        departamento.setIdAlojamiento(alojamiento.getId());

                        //Insercion de la ubicacion del departamento
                        ubicacionDAO.insert(departamento.getUbicacion());

                        //Insercion del departamento
                        alojamientoDAO.insertar(departamento);
                    }
                }
            });
        }
    };

    public void clearTables() {
        executorDB.execute(this::clearAllTables);
    }
}

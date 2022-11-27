package com.mdgz.dam.labdam2022.data.datasource.room.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.room.AlojamientoRoomDataSource;
import com.mdgz.dam.labdam2022.data.datasource.room.CiudadRoomDataSource;
import com.mdgz.dam.labdam2022.data.datasource.room.UUIDConverter;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.AlojamientoDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.CiudadDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.DepartamentoDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.FavoritoDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.HabitacionDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.HotelDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.UbicacionDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.AlojamientoEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.DepartamentoEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.FavoritoEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.HabitacionEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.HotelEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.UbicacionEntity;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {
                AlojamientoEntity.class,
                DepartamentoEntity.class,
                HabitacionEntity.class,
                CiudadEntity.class,
                UbicacionEntity.class,
                HotelEntity.class,
                FavoritoEntity.class
        },
        version = 1,
        exportSchema = false)
@TypeConverters({ UUIDConverter.class })
public abstract class AppDataBase extends RoomDatabase {

    public abstract AlojamientoDAO alojamientoDAO();

    public abstract DepartamentoDAO departamentoDAO();

    public abstract HabitacionDAO habitacionDAO();

    public abstract CiudadDAO ciudadDAO();

    public abstract UbicacionDAO ubicacionDAO();

    public abstract HotelDAO hotelDAO();

    public abstract FavoritoDAO favoritoDAO();

    private static final String DATABASE_NAME = "db_sistema_alojamientos";
    private static AppDataBase instance;

    public static final ExecutorService EXECUTOR_DB = Executors.newSingleThreadExecutor();

    public static synchronized AppDataBase getInstance(final Context context) {
        if (instance == null) {
            instance = buildDatabase(context);

            //Hace una consulta inicial que permite que se cree la base de datos (es la forma de activar el onCreate del mRoomCallback)
            Runnable r = () -> instance.alojamientoDAO().recuperarAlojamientos();
            instance.getQueryExecutor().execute(r);
        }
        return instance;
    }

    private static AppDataBase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME).addCallback(mRoomCallback).build();
    }

    private static final Callback mRoomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull final SupportSQLiteDatabase db) {
            super.onCreate(db);
            EXECUTOR_DB.execute(() -> {
                final OnResult<Departamento> departamentoCallback = new OnResult<Departamento>() {

                    @Override
                    public void onSuccess(final Departamento result) {
                        // noop
                    }

                    @Override
                    public void onError(final Throwable exception) {
                        // noop
                    }
                };

                final OnResult<Habitacion> habitacionCallback = new OnResult<Habitacion>() {

                    @Override
                    public void onSuccess(final Habitacion result) {
                        // noop
                    }

                    @Override
                    public void onError(final Throwable exception) {
                        // noop
                    }
                };

                final OnResult<Ciudad> ciudadCallback = new OnResult<Ciudad>() {

                    @Override
                    public void onSuccess(final Ciudad result) {
                        // noop
                    }

                    @Override
                    public void onError(final Throwable exception) {
                        // noop
                    }
                };


                // Se cargan las ciudades
                Ciudad ciudad1 = new Ciudad(1,"Ciudad 1","ABC1");
                Ciudad ciudad2 = new Ciudad(2,"Ciudad 2","ABC2");
                Ciudad ciudad3 = new Ciudad(3,"Ciudad 3","ABC3");
                Ciudad ciudad4 = new Ciudad(4,"Ciudad 4","ABC4");
                Ciudad ciudad5 = new Ciudad(5,"Ciudad 5","ABC5");

                final CiudadRoomDataSource ciudadDataSource = new CiudadRoomDataSource(instance);
                ciudadDataSource.guardarCiudad(ciudad1, ciudadCallback);
                ciudadDataSource.guardarCiudad(ciudad2, ciudadCallback);
                ciudadDataSource.guardarCiudad(ciudad3, ciudadCallback);
                ciudadDataSource.guardarCiudad(ciudad4, ciudadCallback);
                ciudadDataSource.guardarCiudad(ciudad5, ciudadCallback);

                // Ubicaciones
                Ubicacion ubicacion1 = new Ubicacion(-42.6,-38.3,"San Martin","1989", ciudad1);
                Ubicacion ubicacion2 = new Ubicacion(-42.25,-38.2,"Lopez y Planes","2007",ciudad2);
                Ubicacion ubicacion3 = new Ubicacion(-42.6,-60.3,"Lopez y Planes","3000", ciudad2);
                Ubicacion ubicacion4 = new Ubicacion(-36,-60.3,"Hernandarias","2021", ciudad3);

                // Hoteles
                Hotel hotel1 = new Hotel(UUID.randomUUID(),"Hotel 1",3,ubicacion1);
                Hotel hotel2 = new Hotel(UUID.randomUUID(),"Hotel 2",2,ubicacion3);
                Hotel hotel3 = new Hotel(UUID.randomUUID(),"Hotel 3",1,ubicacion1);


                // Se cargan los alojamientos
                final AlojamientoRoomDataSource alojamientoDataSource = new AlojamientoRoomDataSource(instance);
                alojamientoDataSource.guardarDepartamento(new Departamento(
                    "Departamento 1",
                    "Este departamente esta copado a pesar de tener un nombre no muy original",
                    0,
                    12.0,
                    true,
                    1.0,
                    1,
                    ubicacion1
                ), departamentoCallback);
                alojamientoDataSource.guardarHabitacion(new Habitacion(
                    "Habitación 1",
                    "Esta es una habitación con una cama",
                    1,
                    12.0,
                    1,
                    0,
                    true,
                    hotel1
                ), habitacionCallback);
                alojamientoDataSource.guardarDepartamento(new Departamento(
                    "Departamento 2",
                    "Este departamento tiene pileta",
                    0,
                    12.0,
                    true,
                    1.0,
                    2,
                    ubicacion2
                ), departamentoCallback);
                alojamientoDataSource.guardarHabitacion(new Habitacion(
                    "Habitación 2",
                    "Esta es una habitación con dos camas",
                    1,
                    12.0,
                    2,
                    0,
                    true,
                    hotel1
                ), habitacionCallback);
                alojamientoDataSource.guardarDepartamento(new Departamento(
                    "Departamento 3",
                    "Este departamento no esta muy bueno pero es barato",
                    0,
                    12.0,
                    true,
                    1.0,
                    2,
                    ubicacion3
                ), departamentoCallback);
                alojamientoDataSource.guardarDepartamento(new Departamento(
                    "Departamento 4",
                    "En este departamento se pueden tener mascotas",
                    0,
                    12.0,
                    true,
                    1.0,
                    2,
                    ubicacion4
                ), departamentoCallback);
                alojamientoDataSource.guardarDepartamento(new Departamento(
                    "Departamento 5",
                    "En este departamento es el 5",
                    0,
                    12.0,
                    true,
                    1.0,
                    2,
                    ubicacion4
                ), departamentoCallback);
                alojamientoDataSource.guardarDepartamento(new Departamento(
                    "Departamento 6",
                    "En este departamento es el 6",
                    0,
                    12.0,
                    true,
                    1.0,
                    2,
                    ubicacion2
                ), departamentoCallback);
                alojamientoDataSource.guardarDepartamento(new Departamento(
                    "Departamento 7",
                    "En este departamento es el 7",
                    0,
                    12.0,
                    true,
                    1.0,
                    2,
                    ubicacion2
                ), departamentoCallback);
                alojamientoDataSource.guardarHabitacion(new Habitacion(
                    "Habitación 3",
                    "Esta es una habitación 3",
                    1,
                    12.0,
                    1,
                    0,
                    true,
                    hotel2
                ), habitacionCallback);
                alojamientoDataSource.guardarHabitacion(new Habitacion(
                    "Habitación 4",
                    "Esta es una habitación 4",
                    1,
                    12.0,
                    1,
                    0,
                    true,
                    hotel2
                ), habitacionCallback);
                alojamientoDataSource.guardarHabitacion(new Habitacion(
                    "Habitación 5",
                    "Esta es una habitación 5",
                    1,
                    12.0,
                    1,
                    0,
                    true,
                    hotel3
                ), habitacionCallback);
            });
        }
    };

    public void clearTables() {
        EXECUTOR_DB.execute(this::clearAllTables);
    }
}

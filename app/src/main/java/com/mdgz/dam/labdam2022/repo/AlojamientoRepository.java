package com.mdgz.dam.labdam2022.repo;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.model.modelDAO.AlojamientoDAO;

import java.util.ArrayList;
import java.util.List;

public class AlojamientoRepository {

    private static AlojamientoRepository alojamientoRepository = null;
    private final AlojamientoDAO alojamientoDAO;

    private AlojamientoRepository(Context context) {
        AppDataBase db = AppDataBase.getInstance(context);
        alojamientoDAO = db.alojamientoDAO();
    }

    public static AlojamientoRepository getInstance(Context context) {
        if (alojamientoRepository == null) {
            alojamientoRepository = new AlojamientoRepository(context);
        }
        return alojamientoRepository;
    }

    private static final Ubicacion ubicacion1 = new Ubicacion(-42.6,-38.3,"San Martin","1989",CiudadRepository._CIUDADES.get(0));
    private static final Ubicacion ubicacion2 = new Ubicacion(-42.25,-38.2,"Lopez y Planes","2007",CiudadRepository._CIUDADES.get(1));

    public static final List<Alojamiento> _ALOJAMIENTOS = List.of(
            new Departamento(1, "Depto 1", "luminoso y amplio", 6, 300.0,true, 1500.0, 3,ubicacion1),
            new Habitacion(2, "Habitacion 2", "Espectacular suite",4, 1200.0, 2,1,false,new Hotel(1,"Hotel 1",3,ubicacion2) )
    );

    public List<Alojamiento> listaAlojamientos(){
        return  _ALOJAMIENTOS;
    }

    public void insertarListaAlojamientos(ArrayList<Alojamiento> alojamientos) {
        AppDataBase.executorDB.execute(() -> {
            for (Alojamiento alojamiento : alojamientos) {
                if ((alojamiento instanceof Habitacion)) {
                    alojamientoDAO.insertar((Habitacion) alojamiento);
                } else {
                    alojamientoDAO.insertar((Departamento) alojamiento);
                }
            }
        });
    }

    public void insertarAlojamiento(Alojamiento alojamiento) {
        AppDataBase.executorDB.execute(() -> {
            if ((alojamiento instanceof Habitacion)) {
                alojamientoDAO.insertar((Habitacion) alojamiento);
            } else {
                alojamientoDAO.insertar((Departamento) alojamiento);
            }
        });
    }

    public void recuperarAlojamientos(Boolean hoteles, Boolean departamentos) {
        //TODO sortear segun precio (para que se mezclen los resultados)
        if (hoteles) {
            //TODO buscar habitaciones
//            alojamientoDAO.recuperarHabitaciones();
        }
        if (departamentos) {
            //TODO buscar departamentos
//            alojamientoDAO.recuperarDepartamentos();
        }
    }
}
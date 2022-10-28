package com.mdgz.dam.labdam2022.repo;

import android.content.Context;

import com.mdgz.dam.labdam2022.database.AppDataBase;
import com.mdgz.dam.labdam2022.database.modelDAO.CiudadDAO;
import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.ArrayList;
import java.util.List;

public class CiudadRepository {

    private static CiudadRepository ciudadRepository = null;
    private final CiudadDAO ciudadDAO;

    private CiudadRepository(Context context) {
        AppDataBase db = AppDataBase.getInstance(context);
        ciudadDAO = db.ciudadDAO();
    }

    public static CiudadRepository getInstance(Context context) {
        if (ciudadRepository == null) {
            ciudadRepository = new CiudadRepository(context);
        }
        return ciudadRepository;
    }

    public static ArrayList<Ciudad> populateData() {
        ArrayList<Ciudad> listaCiudades = new ArrayList<>();
        listaCiudades.add(new Ciudad(null,"Ciudad 1","ABC1"));
        listaCiudades.add(new Ciudad(null,"Ciudad 2","ABC2"));
        listaCiudades.add(new Ciudad(null,"Ciudad 3","ABC3"));
        listaCiudades.add(new Ciudad(null,"Ciudad 4","ABC4"));
        return listaCiudades;
    }





    public static final List<Ciudad> _CIUDADES = List.of(
            new Ciudad(1,"Ciudad 1","ABC1"),
                new Ciudad(2,"Ciudad 2","ABC2"),
                new Ciudad(3,"Ciudad 3","ABC3"),
                new Ciudad(4,"Ciudad 4","ABC4")
        );

    public List<Ciudad> listaCiudades(){
        return  _CIUDADES;
    }
}

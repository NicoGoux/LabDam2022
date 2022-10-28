package com.mdgz.dam.labdam2022.database.modelDAO;

import androidx.room.Dao;
import androidx.room.Insert;

import com.mdgz.dam.labdam2022.model.Ubicacion;


@Dao
public interface UbicacionDAO {

    @Insert()
    void insert(Ubicacion ubicacion);
}

package com.mdgz.dam.labdam2022.data.datasource.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.data.datasource.room.entities.CiudadEntity;

@Dao
public interface CiudadDAO {
    @Insert()
    void insertar(CiudadEntity ciudad);

    @Query("SELECT * FROM ciudadentity")
    CiudadEntity[] recuperarCiudades();

    @Query("SELECT * FROM ciudadentity WHERE id=:id_ciudad")
    CiudadEntity recuperarCiudadPorId(Integer id_ciudad);
}

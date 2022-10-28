package com.mdgz.dam.labdam2022.database.modelDAO;

import androidx.room.Dao;
import androidx.room.Insert;

import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.List;

@Dao
public interface CiudadDAO {
    @Insert()
    void insert(List<Ciudad> ciudades);
}

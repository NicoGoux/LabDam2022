package com.mdgz.dam.labdam2022.data.datasource.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.mdgz.dam.labdam2022.data.datasource.room.entities.ReservaEntity;

@Dao
public interface ReservaDAO {
    @Insert
    void insertar(ReservaEntity reserva);
}

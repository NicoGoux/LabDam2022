package com.mdgz.dam.labdam2022.model.modelDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;

@Dao
public interface AlojamientoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertar(Departamento departamento);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertar(Habitacion habitacion);

//    @Query("SELECT * FROM alojamiento")
//    public Alojamiento[] obtenerAlojamientos();
}

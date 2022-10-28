package com.mdgz.dam.labdam2022.database.modelDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;

@Dao
public interface AlojamientoDAO {
    @Insert()
    void insertar(Departamento departamento);

    @Insert()
    void insertar(Habitacion habitacion);

    @Insert()
    void insertar(Alojamiento alojamiento);

    @Query("SELECT * FROM alojamiento JOIN habitacion WHERE id=id_alojamiento")
    Habitacion[] recuperarAlojamientos();
}

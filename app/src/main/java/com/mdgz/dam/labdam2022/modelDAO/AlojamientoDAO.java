package com.mdgz.dam.labdam2022.modelDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Departamento;

@Dao
public interface AlojamientoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertarDepartamento(Departamento departamento);

    @Query("SELECT * FROM departamento")
    public Departamento[] cargarDepartamentos();
}

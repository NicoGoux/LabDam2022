package com.mdgz.dam.labdam2022.data.datasource.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.data.datasource.room.entities.UbicacionEntity;

import java.util.UUID;


@Dao
public interface UbicacionDAO {

    @Insert()
    void insertar(UbicacionEntity ubicacion);

    @Query("SELECT * FROM ubicacionentity WHERE id=:ubicacion_id")
    UbicacionEntity recuperarUbicacionPorId(UUID ubicacion_id);

    @Query("SELECT id FROM ubicacionentity WHERE lat=:lat AND lng=:lng")
    UUID recuperarUbicacionPorLatLng(Double lat, Double lng);
}

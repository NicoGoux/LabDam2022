package com.mdgz.dam.labdam2022.data.datasource.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.data.datasource.room.entities.HotelEntity;

import java.util.UUID;

@Dao
public interface HotelDAO {
    @Insert
    void insertar(HotelEntity hotel);

    @Query("SELECT * FROM hotelentity WHERE id=:id")
    HotelEntity recuperarHotelPorId(UUID id);

    @Query("SELECT id FROM hotelentity WHERE id=:id")
    UUID existeIdHotel(UUID id);
}

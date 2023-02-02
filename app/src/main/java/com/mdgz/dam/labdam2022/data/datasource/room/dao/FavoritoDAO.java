package com.mdgz.dam.labdam2022.data.datasource.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.data.datasource.room.entities.FavoritoEntity;

import java.util.UUID;

@Dao
public interface FavoritoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertar(FavoritoEntity favorito);

    @Delete
    void eliminar(FavoritoEntity favorito);

    @Query("SELECT alojamiento_id FROM favoritoentity WHERE alojamiento_id=:alojamiento_id AND usuario_id=:usuario_id")
    UUID pertenece(UUID alojamiento_id, UUID usuario_id);

    @Query("DELETE FROM favoritoentity")
    void clearTable();
}

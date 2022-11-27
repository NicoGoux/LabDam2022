package com.mdgz.dam.labdam2022.data.datasource.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.data.datasource.room.entities.FavoritoEntity;

import java.util.UUID;

@Dao
public interface FavoritoDAO {

    @Insert
    void insertar(FavoritoEntity favorito);

    @Query("SELECT * FROM favoritoentity")
    FavoritoEntity[] recuperarFavoritos();

    @Delete
    void eliminar(FavoritoEntity favorito);
}

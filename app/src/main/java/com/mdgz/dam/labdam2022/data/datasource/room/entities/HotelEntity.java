package com.mdgz.dam.labdam2022.data.datasource.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(foreignKeys = @ForeignKey(entity = UbicacionEntity.class, parentColumns = "id", childColumns = "ubicacion_id",
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE), indices = @Index({"ubicacion_id"}))
public class HotelEntity {

    @NonNull
    @PrimaryKey
    private UUID id;

    private String nombre;
    private Integer categoria;

    @ColumnInfo(name = "ubicacion_id")
    private UUID ubicacionId;

    @Ignore
    public HotelEntity(final String nombre, final Integer categoria,
                 final UUID ubicacion_id) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacionId = ubicacion_id;
    }

    public HotelEntity(@NonNull final UUID id, final String nombre, final Integer categoria, final UUID ubicacionId) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacionId = ubicacionId;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public UUID getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(UUID ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
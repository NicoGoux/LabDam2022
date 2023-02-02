package com.mdgz.dam.labdam2022.data.datasource.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import java.util.UUID;

@Entity(
        indices = @Index(value = { "alojamiento_id" }),
        foreignKeys = @ForeignKey(entity = AlojamientoEntity.class, parentColumns = "id", childColumns = "alojamiento_id",
                onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        primaryKeys = {"alojamiento_id", "usuario_id"})
public class FavoritoEntity {

    @NonNull
    @ColumnInfo(name = "alojamiento_id")
    private UUID alojamientoId;

    @NonNull
    @ColumnInfo(name = "usuario_id")
    private UUID usuarioId;

    public FavoritoEntity(UUID alojamientoId, UUID usuarioId) {
        this.alojamientoId = alojamientoId;
        this.usuarioId = usuarioId;
    }

    public UUID getAlojamientoId() {
        return alojamientoId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setAlojamientoId(UUID alojamientoId) {
        this.alojamientoId = alojamientoId;
    }

    public void setUsuarioID(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }
}

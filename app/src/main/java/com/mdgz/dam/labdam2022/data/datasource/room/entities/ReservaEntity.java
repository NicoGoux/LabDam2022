package com.mdgz.dam.labdam2022.data.datasource.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import java.util.Date;
import java.util.UUID;
@Entity(
        indices = @Index(value = { "alojamiento_id" }),
        foreignKeys = @ForeignKey(entity = AlojamientoEntity.class, parentColumns = "id", childColumns = "alojamiento_id",
                onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        primaryKeys = {"alojamiento_id", "usuario_id", "fecha_ingreso", "fecha_egreso"})
public class ReservaEntity {

    @NonNull
    @ColumnInfo(name = "alojamiento_id")
    private UUID alojamientoId;

    @NonNull
    @ColumnInfo(name = "usuario_id")
    private UUID usuarioId;

    @NonNull
    @ColumnInfo(name = "fecha_ingreso")
    private Date fechaIngreso;

    @NonNull
    @ColumnInfo(name = "fecha_egreso")
    private Date fechaSalida;

    public ReservaEntity(@NonNull UUID alojamientoId, @NonNull UUID usuarioId, @NonNull Date fechaIngreso, @NonNull Date fechaSalida) {
        this.alojamientoId = alojamientoId;
        this.usuarioId = usuarioId;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    @NonNull
    public UUID getAlojamientoId() {
        return alojamientoId;
    }

    @NonNull
    public UUID getUsuarioId() {
        return usuarioId;
    }

    @NonNull
    public Date getFechaIngreso(){ return fechaIngreso;}

    @NonNull
    public Date getFechaSalida(){ return fechaSalida;}

    public void setAlojamientoId(@NonNull UUID alojamientoId) {
        this.alojamientoId = alojamientoId;
    }

    public void setUsuarioID(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setFechaIngreso(@NonNull Date fechaIngreso){ this.fechaIngreso = fechaIngreso;}

    public void setFechaSalida(@NonNull Date fechaSalida){ this.fechaSalida = fechaSalida;}
}

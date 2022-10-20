package com.mdgz.dam.labdam2022.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Instant;

@Entity (tableName = "reserva")
public class Reserva {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "fecha_ingreso")
    private Instant fechaIngreso;
    @ColumnInfo(name = "fecha_egreso")
    private Instant fechaEgreso;

    private Boolean cancelada;
    private Double monto;

    @NonNull
    public Integer getId() {
        return id;
    }

    public Instant getFechaIngreso() {
        return fechaIngreso;
    }

    public Instant getFechaEgreso() {
        return fechaEgreso;
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public Double getMonto() {
        return monto;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setFechaIngreso(Instant fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaEgreso(Instant fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}

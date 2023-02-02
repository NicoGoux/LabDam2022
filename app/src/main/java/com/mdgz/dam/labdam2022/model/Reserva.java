package com.mdgz.dam.labdam2022.model;

import java.util.Date;
import java.util.UUID;

public class Reserva {

    private UUID alojamientoId;
    private UUID usuarioId;
    private Date fechaIngreso;
    private Date fechaSalida;

    public Reserva(UUID alojamientoID, UUID usuarioID, Date fechaIngreso, Date fechaSalida) {
        this.alojamientoId = alojamientoID;
        this.usuarioId = usuarioID;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public UUID getAlojamientoID() {
        return alojamientoId;
    }

    public UUID getUsuarioID() {
        return usuarioId;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setAlojamientoID(UUID alojamientoID) {
        this.alojamientoId = alojamientoID;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioId = usuarioID;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "alojamientoID=" + alojamientoId +
                ", usuarioID=" + usuarioId +
                ", fechaIngreso=" + fechaIngreso +
                ", fechaSalida=" + fechaSalida +
                '}';
    }
}

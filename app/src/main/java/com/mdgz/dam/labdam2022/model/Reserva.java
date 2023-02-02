package com.mdgz.dam.labdam2022.model;

import java.util.Date;
import java.util.UUID;

public class Reserva {

    UUID alojamientoID;
    UUID usuarioID;
    Date fechaIngreso;
    Date fechaSalida;

    public Reserva(UUID alojamientoID, UUID usuarioID, Date fechaIngreso, Date fechaSalida) {
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public UUID getAlojamientoID() {
        return alojamientoID;
    }

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setAlojamientoID(UUID alojamientoID) {
        this.alojamientoID = alojamientoID;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}

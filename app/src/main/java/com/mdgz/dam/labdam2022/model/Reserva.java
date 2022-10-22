package com.mdgz.dam.labdam2022.model;

import java.time.Instant;

public class Reserva {

    private Integer id;

    private Instant fechaIngreso;

    private Instant fechaEgreso;

    private Boolean cancelada;
    private Double monto;

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

    public void setId(Integer id) {
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

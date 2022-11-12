package com.mdgz.dam.labdam2022.model;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.UUID;

public abstract class Alojamiento implements Parcelable {
    protected UUID id;
    protected String titulo;
    protected String descripcion;
    protected Integer capacidad;
    protected Double precioBase;

    protected Alojamiento() {
    }

    protected Alojamiento(final String titulo, final String descripcion, final Integer capacidad,
                          final Double precioBase) {
        this(null, titulo, descripcion, capacidad, precioBase);
    }

    protected Alojamiento(final UUID id, final String titulo, final String descripcion, final Integer capacidad,
                          final Double precioBase) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
    }

    public abstract Ubicacion getUbicacion();
    public Double costoDia(){
        return precioBase;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }
}

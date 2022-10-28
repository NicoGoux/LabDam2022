package com.mdgz.dam.labdam2022.model;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;


@Entity
public abstract class Alojamiento implements Parcelable {
    @PrimaryKey
    @NonNull
    protected UUID id;

    protected String titulo;
    protected String descripcion;
    protected Integer capacidad;

    @ColumnInfo(name = "precio_base")
    protected Double precioBase;

    @Ignore  public Alojamiento(String titulo, String descripcion, Integer capacidad, Double precioBase) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
    }

    @Ignore public Alojamiento(){
        super();
    }

    public Alojamiento(UUID id, String titulo, String descripcion, Integer capacidad, Double precioBase) {
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
}

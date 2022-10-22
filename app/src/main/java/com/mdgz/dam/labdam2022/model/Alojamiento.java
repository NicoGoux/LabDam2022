package com.mdgz.dam.labdam2022.model;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public abstract class Alojamiento implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    protected Integer id;

    protected String titulo;
    protected String descripcion;
    protected Integer capacidad;

    @ColumnInfo(name = "precio_base")
    protected Double precioBase;

    public abstract Ubicacion getUbicacion();
    public Double costoDia(){
        return precioBase;
    }

    public Alojamiento(){
        super();
    }

    @Ignore public Alojamiento(Integer id, String titulo, String descripcion, Integer capacidad, Double precioBase) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
    }

    @NonNull
    public Integer getId() {
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

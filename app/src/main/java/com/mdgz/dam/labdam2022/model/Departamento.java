package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Departamento extends Alojamiento implements Parcelable {

    private Boolean tieneWifi;
    private Double costoLimpieza;
    private Integer cantidadHabitaciones;
    private Ubicacion ubicacion;

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Departamento(){
        super();
    }

    public Departamento(Integer id, String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean tieneWifi, Double costoLimpieza, Integer cantidadHabitaciones,Ubicacion ubicacion) {
        super(id, titulo, descripcion, capacidad, precioBase);
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ubicacion = ubicacion;
    }

    public Boolean getTieneWifi() {
        return tieneWifi;
    }

    public void setTieneWifi(Boolean tieneWifi) {
        this.tieneWifi = tieneWifi;
    }

    public Double getCostoLimpieza() {
        return costoLimpieza;
    }

    public void setCostoLimpieza(Double costoLimpieza) {
        this.costoLimpieza = costoLimpieza;
    }

    public Integer getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    @Override
    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.tieneWifi);
        dest.writeValue(this.costoLimpieza);
        dest.writeValue(this.cantidadHabitaciones);
        dest.writeParcelable(this.ubicacion, flags);
        dest.writeValue(this.id);
        dest.writeString(this.titulo);
        dest.writeString(this.descripcion);
        dest.writeValue(this.capacidad);
        dest.writeValue(this.precioBase);
    }

    public void readFromParcel(Parcel source) {
        this.tieneWifi = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.costoLimpieza = (Double) source.readValue(Double.class.getClassLoader());
        this.cantidadHabitaciones = (Integer) source.readValue(Integer.class.getClassLoader());
        this.ubicacion = source.readParcelable(Ubicacion.class.getClassLoader());
        this.id = (Integer) source.readValue(Integer.class.getClassLoader());
        this.titulo = source.readString();
        this.descripcion = source.readString();
        this.capacidad = (Integer) source.readValue(Integer.class.getClassLoader());
        this.precioBase = (Double) source.readValue(Double.class.getClassLoader());
    }

    protected Departamento(Parcel in) {
        this.tieneWifi = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.costoLimpieza = (Double) in.readValue(Double.class.getClassLoader());
        this.cantidadHabitaciones = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ubicacion = in.readParcelable(Ubicacion.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.titulo = in.readString();
        this.descripcion = in.readString();
        this.capacidad = (Integer) in.readValue(Integer.class.getClassLoader());
        this.precioBase = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Departamento> CREATOR = new Parcelable.Creator<Departamento>() {
        @Override
        public Departamento createFromParcel(Parcel source) {
            return new Departamento(source);
        }

        @Override
        public Departamento[] newArray(int size) {
            return new Departamento[size];
        }
    };
}

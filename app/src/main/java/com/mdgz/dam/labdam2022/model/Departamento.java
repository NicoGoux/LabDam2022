package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Departamento extends Alojamiento implements Parcelable {

    private Boolean tieneWifi;
    private Double costoLimpieza;
    private Integer cantidadHabitaciones;
    private Ubicacion ubicacion;
    public Departamento(final String titulo, final String descripcion, final Integer capacidad,
                        final Double precioBase, final Boolean tieneWifi, final Double costoLimpieza,
                        final Integer cantidadHabitaciones,
                        final Ubicacion ubicacion) {
        this(null, titulo, descripcion, capacidad, precioBase, tieneWifi, costoLimpieza, cantidadHabitaciones,
                ubicacion);
    }

    public Departamento(final UUID id, final String titulo, final String descripcion, final Integer capacidad,
                        final Double precioBase, final Boolean tieneWifi, final Double costoLimpieza,
                        final Integer cantidadHabitaciones,
                        final Ubicacion ubicacion) {
        super(id, titulo, descripcion, capacidad, precioBase);
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ubicacion = ubicacion;
    }

    @Override
    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }

    public Boolean getTieneWifi() {
        return tieneWifi;
    }

    public Double getCostoLimpieza() {
        return costoLimpieza;
    }

    public Integer getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setTieneWifi(Boolean tieneWifi) {
        this.tieneWifi = tieneWifi;
    }

    public void setCostoLimpieza(Double costoLimpieza) {
        this.costoLimpieza = costoLimpieza;
    }

    public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
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
        dest.writeSerializable(this.id);
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
        this.id = (UUID) source.readSerializable();
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
        this.id = (UUID) in.readSerializable();
        this.titulo = in.readString();
        this.descripcion = in.readString();
        this.capacidad = (Integer) in.readValue(Integer.class.getClassLoader());
        this.precioBase = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<Departamento> CREATOR = new Creator<Departamento>() {
        @Override
        public Departamento createFromParcel(Parcel source) {
            return new Departamento(source);
        }

        @Override
        public Departamento[] newArray(int size) {
            return new Departamento[size];
        }
    };

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", capacidad=" + capacidad +
                ", precioBase=" + precioBase +
                ", tieneWifi=" + tieneWifi +
                ", costoLimpieza=" + costoLimpieza +
                ", cantidadHabitaciones=" + cantidadHabitaciones +
                ", ubicacion=" + ubicacion +
                '}';
    }
}

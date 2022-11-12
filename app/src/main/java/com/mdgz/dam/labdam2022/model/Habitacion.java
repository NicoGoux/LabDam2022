package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.UUID;

public class Habitacion extends Alojamiento implements Parcelable {

    private Integer camasIndividuales;
    private Integer camasMatrimoniales;
    private Boolean tieneEstacionamiento;
    private Hotel hotel;

    public Habitacion(final String titulo, final String descripcion, final Integer capacidad,
                      final Double precioBase, final Integer camasIndividuales, final Integer camasMatrimoniales,
                      final Boolean tieneEstacionamiento, final Hotel hotel) {
        this(null, titulo, descripcion, capacidad, precioBase, camasIndividuales, camasMatrimoniales,
                tieneEstacionamiento, hotel);
    }

    public Habitacion(final UUID id, final String titulo, final String descripcion, final Integer capacidad,
                      final Double precioBase, final Integer camasIndividuales, final Integer camasMatrimoniales,
                      final Boolean tieneEstacionamiento, final Hotel hotel) {
        super(id, titulo, descripcion, capacidad, precioBase);
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.hotel = hotel;
    }

    @Override
    public Ubicacion getUbicacion() {
        return this.hotel.getUbicacion();
    }

    public Integer getCamasIndividuales() {
        return camasIndividuales;
    }

    public Integer getCamasMatrimoniales() {
        return camasMatrimoniales;
    }

    public Boolean getTieneEstacionamiento() {
        return tieneEstacionamiento;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setTieneEstacionamiento(Boolean tieneEstacionamiento) {
        this.tieneEstacionamiento = tieneEstacionamiento;
    }

    public void setCamasMatrimoniales(Integer camasMatrimoniales) {
        this.camasMatrimoniales = camasMatrimoniales;
    }

    public void setCamasIndividuales(Integer camasIndividuales) {
        this.camasIndividuales = camasIndividuales;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @NonNull
    @Override
    public String toString() {
        return "Habitacion{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", capacidad=" + capacidad +
                ", precioBase=" + precioBase +
                ", camasIndividuales=" + camasIndividuales +
                ", camasMatrimoniales=" + camasMatrimoniales +
                ", tieneEstacionamiento=" + tieneEstacionamiento +
                ", hotel=" + hotel +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.camasIndividuales);
        dest.writeValue(this.camasMatrimoniales);
        dest.writeValue(this.tieneEstacionamiento);
        dest.writeParcelable(this.hotel, flags);
        dest.writeSerializable(this.id);
        dest.writeString(this.titulo);
        dest.writeString(this.descripcion);
        dest.writeValue(this.capacidad);
        dest.writeValue(this.precioBase);
    }

    public void readFromParcel(Parcel source) {
        this.camasIndividuales = (Integer) source.readValue(Integer.class.getClassLoader());
        this.camasMatrimoniales = (Integer) source.readValue(Integer.class.getClassLoader());
        this.tieneEstacionamiento = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.hotel = source.readParcelable(Hotel.class.getClassLoader());
        this.id = (UUID) source.readSerializable();
        this.titulo = source.readString();
        this.descripcion = source.readString();
        this.capacidad = (Integer) source.readValue(Integer.class.getClassLoader());
        this.precioBase = (Double) source.readValue(Double.class.getClassLoader());
    }

    protected Habitacion(Parcel in) {
        this.camasIndividuales = (Integer) in.readValue(Integer.class.getClassLoader());
        this.camasMatrimoniales = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tieneEstacionamiento = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.hotel = in.readParcelable(Hotel.class.getClassLoader());
        this.id = (UUID) in.readSerializable();
        this.titulo = in.readString();
        this.descripcion = in.readString();
        this.capacidad = (Integer) in.readValue(Integer.class.getClassLoader());
        this.precioBase = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<Habitacion> CREATOR = new Creator<Habitacion>() {
        @Override
        public Habitacion createFromParcel(Parcel source) {
            return new Habitacion(source);
        }

        @Override
        public Habitacion[] newArray(int size) {
            return new Habitacion[size];
        }
    };
}

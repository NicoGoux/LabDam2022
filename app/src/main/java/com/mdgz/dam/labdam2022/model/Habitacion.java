package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Habitacion  extends Alojamiento implements Parcelable {

    private int camasIndividuales;
    private int camasMatrimoniales;
    private Boolean tieneEstacionamiento;
    private Hotel hotel;

    public Habitacion() {
        super();
    }

    public Habitacion(Integer id, String titulo, String descripcion, Integer capacidad, Double precioBase, int camasIndividuales, int camasMatrimoniales, Boolean tieneEstacionamiento, Hotel hotel) {
        super(id, titulo, descripcion, capacidad, precioBase);
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.hotel = hotel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCamasIndividuales() {
        return camasIndividuales;
    }

    public void setCamasIndividuales(int camasIndividuales) {
        this.camasIndividuales = camasIndividuales;
    }

    public int getCamasMatrimoniales() {
        return camasMatrimoniales;
    }

    public void setCamasMatrimoniales(int camasMatrimoniales) {
        this.camasMatrimoniales = camasMatrimoniales;
    }

    public Boolean getTieneEstacionamiento() {
        return tieneEstacionamiento;
    }

    public void setTieneEstacionamiento(Boolean tieneEstacionamiento) {
        this.tieneEstacionamiento = tieneEstacionamiento;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public Ubicacion getUbicacion() {
        return hotel.getUbicacion();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.camasIndividuales);
        dest.writeInt(this.camasMatrimoniales);
        dest.writeValue(this.tieneEstacionamiento);
        dest.writeParcelable(this.hotel, flags);
        dest.writeValue(this.id);
        dest.writeString(this.titulo);
        dest.writeString(this.descripcion);
        dest.writeValue(this.capacidad);
        dest.writeValue(this.precioBase);
    }

    public void readFromParcel(Parcel source) {
        this.camasIndividuales = source.readInt();
        this.camasMatrimoniales = source.readInt();
        this.tieneEstacionamiento = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.hotel = source.readParcelable(Hotel.class.getClassLoader());
        this.id = (Integer) source.readValue(Integer.class.getClassLoader());
        this.titulo = source.readString();
        this.descripcion = source.readString();
        this.capacidad = (Integer) source.readValue(Integer.class.getClassLoader());
        this.precioBase = (Double) source.readValue(Double.class.getClassLoader());
    }

    protected Habitacion(Parcel in) {
        this.camasIndividuales = in.readInt();
        this.camasMatrimoniales = in.readInt();
        this.tieneEstacionamiento = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.hotel = in.readParcelable(Hotel.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.titulo = in.readString();
        this.descripcion = in.readString();
        this.capacidad = (Integer) in.readValue(Integer.class.getClassLoader());
        this.precioBase = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Habitacion> CREATOR = new Parcelable.Creator<Habitacion>() {
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

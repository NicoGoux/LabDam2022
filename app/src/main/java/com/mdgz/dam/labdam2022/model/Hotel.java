package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Hotel implements Parcelable {

    private UUID id;

    private String nombre;
    private Integer categoria;

    Ubicacion ubicacion;

    public Hotel(final UUID id, final String nombre, final Integer categoria,
                 final Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(final Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.id);
        dest.writeString(this.nombre);
        dest.writeValue(this.categoria);
        dest.writeParcelable(this.ubicacion, flags);
    }

    public void readFromParcel(Parcel source) {
        this.id = (UUID) source.readSerializable();
        this.nombre = source.readString();
        this.categoria = (Integer) source.readValue(Integer.class.getClassLoader());
        this.ubicacion = source.readParcelable(Ubicacion.class.getClassLoader());
    }

    protected Hotel(Parcel in) {
        this.id = (UUID) in.readSerializable();
        this.nombre = in.readString();
        this.categoria = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ubicacion = in.readParcelable(Ubicacion.class.getClassLoader());
    }

    public static final Creator<Hotel> CREATOR = new Creator<Hotel>() {
        @Override
        public Hotel createFromParcel(Parcel source) {
            return new Hotel(source);
        }

        @Override
        public Hotel[] newArray(int size) {
            return new Hotel[size];
        }
    };
}

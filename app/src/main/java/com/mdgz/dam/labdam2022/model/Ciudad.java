package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Ciudad implements Parcelable {
    private Integer id;
    private String nombre;
    private String abreviatura;

    public Ciudad(Integer id, String nombre, String abreviatura) {
        this.id = id;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }


    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return nombre;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.abreviatura);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Integer) source.readValue(Integer.class.getClassLoader());
        this.nombre = source.readString();
        this.abreviatura = source.readString();
    }

    protected Ciudad(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nombre = in.readString();
        this.abreviatura = in.readString();
    }

    public static final Parcelable.Creator<Ciudad> CREATOR = new Parcelable.Creator<>() {
        public Ciudad createFromParcel(Parcel source) {
            return new Ciudad(source);
        }

        public Ciudad[] newArray(int size) {
            return new Ciudad[size];
        }
    };
}

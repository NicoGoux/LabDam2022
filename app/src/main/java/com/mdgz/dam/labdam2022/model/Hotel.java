package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "hotel", foreignKeys = @ForeignKey(entity = Ubicacion.class,
        parentColumns = {"lat", "lng"},
        childColumns = {"ubicacion_lat", "ubicacion_lng"},
        onDelete = ForeignKey.RESTRICT,
        onUpdate = ForeignKey.CASCADE),
        indices = {@Index({"ubicacion_lat","ubicacion_lng"})})
public class Hotel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    private String nombre;
    private Integer categoria;

    @Ignore Ubicacion ubicacion;

    @ColumnInfo(name = "ubicacion_lat")
    private Double ubicacionLat;

    @ColumnInfo(name = "ubicacion_lng")
    private Double ubicacionLng;

    public Hotel(){
        super();
    }

    public Hotel(Integer id, String nombre, Integer categoria, Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
    }

    public Integer getId() {
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

    public Double getUbicacionLat() {
        return ubicacionLat;
    }

    public Double getUbicacionLng() {
        return ubicacionLng;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUbicacionLat(Double ubicacionLat) {
        this.ubicacionLat = ubicacionLat;
    }

    public void setUbicacionLng(Double ubicacionLng) {
        this.ubicacionLng = ubicacionLng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.nombre);
        dest.writeValue(this.categoria);
        dest.writeParcelable(this.ubicacion, flags);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Integer) source.readValue(Integer.class.getClassLoader());
        this.nombre = source.readString();
        this.categoria = (Integer) source.readValue(Integer.class.getClassLoader());
        this.ubicacion = source.readParcelable(Ubicacion.class.getClassLoader());
    }

    protected Hotel(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nombre = in.readString();
        this.categoria = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ubicacion = in.readParcelable(Ubicacion.class.getClassLoader());
    }

    public static final Parcelable.Creator<Hotel> CREATOR = new Parcelable.Creator<Hotel>() {
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

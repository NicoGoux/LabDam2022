package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "ubicacion", foreignKeys = @ForeignKey(entity = Ciudad.class,
        parentColumns = "id",
        childColumns = "ciudad_id"), primaryKeys = {"lat","lng"})
public class Ubicacion implements Parcelable {

    @NonNull
    private Double lat;
    @NonNull
    private Double lng;

    private String calle;
    private String numero;

    @Ignore private Ciudad ciudad;

    @ColumnInfo(name = "ciudad_id")
    private Integer ciudadId;

    public Ubicacion(){

    }

    public Ubicacion(double lat, double lng, String calle, String numero, Ciudad ciudad) {
        this.lat = lat;
        this.lng = lng;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
        dest.writeString(this.calle);
        dest.writeString(this.numero);
        dest.writeParcelable(this.ciudad, flags);
    }

    public void readFromParcel(Parcel source) {
        this.lat = source.readDouble();
        this.lng = source.readDouble();
        this.calle = source.readString();
        this.numero = source.readString();
        this.ciudad = source.readParcelable(Ciudad.class.getClassLoader());
    }

    protected Ubicacion(Parcel in) {
        this.lat = in.readDouble();
        this.lng = in.readDouble();
        this.calle = in.readString();
        this.numero = in.readString();
        this.ciudad = in.readParcelable(Ciudad.class.getClassLoader());
    }

    public static final Parcelable.Creator<Ubicacion> CREATOR = new Parcelable.Creator<Ubicacion>() {
        @Override
        public Ubicacion createFromParcel(Parcel source) {
            return new Ubicacion(source);
        }

        @Override
        public Ubicacion[] newArray(int size) {
            return new Ubicacion[size];
        }
    };
}

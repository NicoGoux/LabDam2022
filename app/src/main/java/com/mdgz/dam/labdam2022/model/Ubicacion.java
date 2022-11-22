package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ubicacion implements Parcelable {

    private Double lat;
    private Double lng;
    private String calle;
    private String numero;
    private Ciudad ciudad;

    public Ubicacion(final double lat, final double lng, final String calle, final String numero, final Ciudad ciudad) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.lat);
        dest.writeValue(this.lng);
        dest.writeString(this.calle);
        dest.writeString(this.numero);
        dest.writeParcelable(this.ciudad, flags);
    }

    public void readFromParcel(Parcel source) {
        this.lat = (Double) source.readValue(Double.class.getClassLoader());
        this.lng = (Double) source.readValue(Double.class.getClassLoader());
        this.calle = source.readString();
        this.numero = source.readString();
        this.ciudad = source.readParcelable(Ciudad.class.getClassLoader());
    }

    protected Ubicacion(Parcel in) {
        this.lat = (Double) in.readValue(Double.class.getClassLoader());
        this.lng = (Double) in.readValue(Double.class.getClassLoader());
        this.calle = in.readString();
        this.numero = in.readString();
        this.ciudad = in.readParcelable(Ciudad.class.getClassLoader());
    }

    public static final Creator<Ubicacion> CREATOR = new Creator<Ubicacion>() {
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

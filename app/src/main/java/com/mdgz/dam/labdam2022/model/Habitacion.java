package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(ignoredColumns = {"id", "titulo","descripcion","capacidad","precio_base"},
        foreignKeys = @ForeignKey(entity = Alojamiento.class,parentColumns = "id",childColumns = "id_alojamiento",onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE))
public class Habitacion  extends Alojamiento implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_alojamiento")
    private UUID idAlojamiento;

    private Integer camasIndividuales;

    private Integer camasMatrimoniales;

    private Boolean tieneEstacionamiento;

    @Ignore private Hotel hotel;

    public Habitacion() {
        super();
    }

    @Ignore public Habitacion(String titulo, String descripcion, Integer capacidad, Double precioBase, Integer camasIndividuales, Integer camasMatrimoniales, Boolean tieneEstacionamiento, Hotel hotel) {
        super(titulo, descripcion, capacidad, precioBase);
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.hotel = hotel;
    }

    @Ignore public Habitacion(UUID id, String titulo, String descripcion, Integer capacidad, Double precioBase, Integer camasIndividuales, Integer camasMatrimoniales, Boolean tieneEstacionamiento, Hotel hotel) {
        super(id, titulo, descripcion, capacidad, precioBase);
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.hotel = hotel;
    }

    @Override
    public Ubicacion getUbicacion() {
        return hotel.getUbicacion();
    }

    @NonNull
    public UUID getId() {
        return id;
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

    @NonNull
    public UUID getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(@NonNull UUID idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
        this.id = (UUID) source.readValue(UUID.class.getClassLoader());
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
        this.id = (UUID) in.readValue(UUID.class.getClassLoader());
        this.titulo = in.readString();
        this.descripcion = in.readString();
        this.capacidad = (Integer) in.readValue(Integer.class.getClassLoader());
        this.precioBase = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Habitacion> CREATOR = new Parcelable.Creator<>() {
        @Override
        public Habitacion createFromParcel(Parcel source) {
            return new Habitacion(source);
        }

        @Override
        public Habitacion[] newArray(int size) {
            return new Habitacion[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "Habitacion{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", capacidad=" + capacidad +
                ", precioBase=" + precioBase +
                ", idAlojamiento=" + idAlojamiento +
                ", camasIndividuales=" + camasIndividuales +
                ", camasMatrimoniales=" + camasMatrimoniales +
                ", tieneEstacionamiento=" + tieneEstacionamiento +
                ", hotel=" + hotel +
                '}';
    }
}

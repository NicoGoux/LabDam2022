package com.mdgz.dam.labdam2022.data.datasource.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(foreignKeys = @ForeignKey(entity = CiudadEntity.class,
        parentColumns = "id",
        childColumns = "ciudad_id"),
        indices = @Index("ciudad_id"))
public class UbicacionEntity {

    @PrimaryKey
    @NonNull
    private UUID id;

    @NonNull
    private Double lat;
    @NonNull
    private Double lng;

    private String calle;
    private String numero;

    @ColumnInfo(name = "ciudad_id")
    private Integer ciudadId;

    @Ignore
    public UbicacionEntity(double lat, double lng, String calle, String numero, Integer ciudadId) {
        this.id = UUID.randomUUID();
        this.lat = lat;
        this.lng = lng;
        this.calle = calle;
        this.numero = numero;
        this.ciudadId = ciudadId;
    }

    public UbicacionEntity(@NonNull UUID id, double lat, double lng, String calle, String numero, Integer ciudadId) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.calle = calle;
        this.numero = numero;
        this.ciudadId = ciudadId;
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

    public Integer getCiudadId() {
        return ciudadId;
    }

    public UUID getId() {
        return id;
    }


    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
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
}
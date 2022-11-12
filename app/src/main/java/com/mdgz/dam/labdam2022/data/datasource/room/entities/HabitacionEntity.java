package com.mdgz.dam.labdam2022.data.datasource.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.UUID;

@Entity(
    indices = {@Index(value = { "alojamiento_id" }), @Index(value = { "hotel_id" })},
    foreignKeys = {
            @ForeignKey(entity = AlojamientoEntity.class, parentColumns = "id", childColumns = "alojamiento_id",
                    onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
            @ForeignKey(entity = HotelEntity.class, parentColumns = "id", childColumns = "hotel_id",
                    onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class HabitacionEntity {
    @PrimaryKey
    @NonNull
    private UUID id;
    private Integer camasIndividuales;
    private Integer camasMatrimoniales;
    private Boolean tieneEstacionamiento;
    @ColumnInfo(name = "alojamiento_id")
    private UUID alojamientoId;

    @ColumnInfo(name = "hotel_id")
    private UUID hotelId;

    @Ignore
    public HabitacionEntity(final Integer camasIndividuales, final Integer camasMatrimoniales,
        final Boolean tieneEstacionamiento, final UUID alojamientoId, final UUID hotelId) {
        this(UUID.randomUUID(), camasIndividuales, camasMatrimoniales, tieneEstacionamiento, alojamientoId, hotelId);
    }

    public HabitacionEntity(@NonNull final UUID id, final Integer camasIndividuales, final Integer camasMatrimoniales,
        final Boolean tieneEstacionamiento, final UUID alojamientoId, final UUID hotelId) {
        this.id = id;
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.alojamientoId = alojamientoId;
        this.hotelId = hotelId;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull final UUID id) {
        this.id = id;
    }

    public Integer getCamasIndividuales() {
        return camasIndividuales;
    }

    public void setCamasIndividuales(final Integer camasIndividuales) {
        this.camasIndividuales = camasIndividuales;
    }

    public Integer getCamasMatrimoniales() {
        return camasMatrimoniales;
    }

    public void setCamasMatrimoniales(final Integer camasMatrimoniales) {
        this.camasMatrimoniales = camasMatrimoniales;
    }

    public Boolean getTieneEstacionamiento() {
        return tieneEstacionamiento;
    }

    public void setTieneEstacionamiento(final Boolean tieneEstacionamiento) {
        this.tieneEstacionamiento = tieneEstacionamiento;
    }

    public UUID getAlojamientoId() {
        return alojamientoId;
    }

    public void setAlojamientoId(final UUID alojamientoId) {
        this.alojamientoId = alojamientoId;
    }

    public UUID getHotelId() {
        return hotelId;
    }

    public void setHotelId(UUID hotelId) {
        this.hotelId = hotelId;
    }
}

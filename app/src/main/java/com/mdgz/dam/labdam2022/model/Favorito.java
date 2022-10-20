package com.mdgz.dam.labdam2022.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorito", foreignKeys = @ForeignKey(entity = Alojamiento.class,
        parentColumns = "id",
        childColumns = "id_alojamiento",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),
        indices = {@Index("id_alojamiento")})
public class Favorito {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @Ignore private Alojamiento alojamiento;

    @ColumnInfo(name = "id_alojamiento")
    private Integer idAlojamiento;

    @NonNull
    public Integer getId() {
        return id;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public Integer getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public void setIdAlojamiento(Integer idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }
}

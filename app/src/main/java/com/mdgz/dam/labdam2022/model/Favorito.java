package com.mdgz.dam.labdam2022.model;

import java.util.UUID;

public class Favorito {
    private UUID alojamientoId;

    private UUID usuarioId;

    public Favorito(UUID alojamientoId, UUID usuarioID) {
        this.alojamientoId = alojamientoId;
        this.usuarioId = usuarioID;
    }

    public UUID getAlojamientoId() {
        return alojamientoId;
    }

    public UUID getUsuarioID() {
        return usuarioId;
    }

    public void setAlojamientoId(UUID alojamientoId) {
        this.alojamientoId = alojamientoId;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioId = usuarioID;
    }

    @Override
    public String toString() {
        return "Favorito{" +
                "alojamientoId=" + alojamientoId +
                ", usuarioId=" + usuarioId +
                '}';
    }
}

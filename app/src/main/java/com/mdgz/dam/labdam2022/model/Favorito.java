package com.mdgz.dam.labdam2022.model;

import java.util.UUID;

public class Favorito {
    private UUID alojamientoId;

    private UUID usuarioID;


    public UUID getAlojamientoId() {
        return alojamientoId;
    }

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setAlojamientoId(UUID alojamientoId) {
        this.alojamientoId = alojamientoId;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }
}

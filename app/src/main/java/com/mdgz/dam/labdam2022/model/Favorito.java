package com.mdgz.dam.labdam2022.model;

public class Favorito {
    private Integer id;

    private Alojamiento alojamiento;

    private Integer idAlojamiento;

    public Integer getId() {
        return id;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public Integer getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public void setIdAlojamiento(Integer idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }
}

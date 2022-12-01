package com.mdgz.dam.labdam2022.data.datasource.retrofit.entities;

import java.io.Serializable;
import java.lang.String;

public class ReservaRest implements Serializable {
  private String fechaIngreso;

  private String alojamientoId;

  private String fechaSalida;

  private String usuarioId;

  public String getFechaIngreso() {
    return this.fechaIngreso;
  }

  public void setFechaIngreso(String fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
  }

  public String getAlojamientoId() {
    return this.alojamientoId;
  }

  public void setAlojamientoId(String alojamientoId) {
    this.alojamientoId = alojamientoId;
  }

  public String getFechaSalida() {
    return this.fechaSalida;
  }

  public void setFechaSalida(String fechaSalida) {
    this.fechaSalida = fechaSalida;
  }

  public String getUsuarioId() {
    return this.usuarioId;
  }

  public void setUsuarioId(String usuarioId) {
    this.usuarioId = usuarioId;
  }
}

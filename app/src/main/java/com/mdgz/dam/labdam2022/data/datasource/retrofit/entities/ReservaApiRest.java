package com.mdgz.dam.labdam2022.data.datasource.retrofit.entities;

import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReservaApiRest {
    @Headers("Authorization: Basic UkFORzoxMjM0NQ==")
    @GET("reserva")
    Call<List<Reserva>> listarReservas(@Query("usuarioId") UUID usuarioId);

    @Headers("Authorization: Basic UkFORzoxMjM0NQ==")
    @POST("reserva")
    Call<Reserva> crearReserva(@Body Reserva r);
}

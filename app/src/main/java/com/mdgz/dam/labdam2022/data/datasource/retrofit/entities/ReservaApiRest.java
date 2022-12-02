package com.mdgz.dam.labdam2022.data.datasource.retrofit.entities;

import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ReservaApiRest {
    @Headers("Authorization: Basic UkFORzoxMjM0NQ==")
    @GET("reserva")
    Call<List<Reserva>> listarReservas();

    @Headers("Authorization: Basic UkFORzoxMjM0NQ==")
    @POST("reserva")
    Call<Reserva> crearReserva(@Body Reserva r);
}

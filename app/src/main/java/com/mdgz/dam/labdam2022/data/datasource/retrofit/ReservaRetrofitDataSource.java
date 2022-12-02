package com.mdgz.dam.labdam2022.data.datasource.retrofit;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.ReservaDataSource;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.entities.ReservaApiRest;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.interfaces.IsSuccessful;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.retrofit.AppRetrofit;
import com.mdgz.dam.labdam2022.model.Reserva;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import retrofit2.Response;

public class ReservaRetrofitDataSource implements ReservaDataSource {

    private final ReservaApiRest reservaApiRest;

    public ReservaRetrofitDataSource() {
        this(AppRetrofit.getInstance());
    }

    public ReservaRetrofitDataSource(AppRetrofit retrofit) {
        reservaApiRest = retrofit.reservaApiRest;
    }

    @Override
    public void guardarReserva(Reserva reserva, OnResult<Reserva> callback) {
        try {
            Response<Reserva> response = reservaApiRest.crearReserva(reserva).execute();
            IsSuccessful<Reserva> responseStatus = new IsSuccessful<Reserva>() {
                @Override
                public void isSuccessful(Response<Reserva> response, OnResult<Reserva> callback) throws IOException {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    }
                    else {
                        throw new IOException("No pudo completarse la solicitud a la API, Codigo de error: "+response.code());
                    }
                }
            };
            responseStatus.isSuccessful(response, callback);
        } catch (IOException e) {
            callback.onError(e);
        }
    }

    public void consultarReservas(UUID userId, OnResult<List<Reserva>> callback) {
        try {
            Response<List<Reserva>> response = reservaApiRest.listarReservas().execute();
            IsSuccessful<List<Reserva>> responseStatus = new IsSuccessful<List<Reserva>>() {
                @Override
                public void isSuccessful(Response<List<Reserva>> response, OnResult<List<Reserva>> callback) throws IOException {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    }
                    else {
                        throw new IOException("No pudo completarse la solicitud a la API, Codigo de error: "+response.code());
                    }
                }
            };
            responseStatus.isSuccessful(response, callback);
        } catch (IOException e) {
            callback.onError(e);
        }
    }
}

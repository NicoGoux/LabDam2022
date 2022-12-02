package com.mdgz.dam.labdam2022.data.datasource.retrofit;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.FavoritoDataSource;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.entities.FavoritoApiRest;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.interfaces.IsSuccessful;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.retrofit.AppRetrofit;
import com.mdgz.dam.labdam2022.model.Favorito;

import java.io.IOException;

import retrofit2.Response;

public class FavoritoRetrofitDataSource implements FavoritoDataSource {

    private final FavoritoApiRest favoritoApiRest;

    public FavoritoRetrofitDataSource() {
        this(AppRetrofit.getInstance());
    }

    public FavoritoRetrofitDataSource(AppRetrofit retrofit) {
        favoritoApiRest = retrofit.favoritoApiRest;
    }

    @Override
    public void guardarFavorito(Favorito favorito, OnResult<Favorito> callback) {
        try {
            Response<Favorito> response = favoritoApiRest.crearFavorito(favorito).execute();
            IsSuccessful<Favorito> responseStatus = new IsSuccessful<Favorito>() {
                @Override
                public void isSuccessful(Response<Favorito> response, OnResult<Favorito> callback) throws IOException {
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

    @Override
    public void eliminarFavorito(Favorito favorito, OnResult<Favorito> callback) {
        try {
            Response<String> response = favoritoApiRest.eliminarFavorito(favorito.getAlojamientoId()).execute();

            // Solo se usa para entrar al isSuccessful
            OnResult<String> callbackIsSuccessful = new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    callback.onSuccess(favorito);
                }

                @Override
                public void onError(Throwable exception) {

                }
            };

            IsSuccessful<String> responseStatus = new IsSuccessful<String>() {
                @Override
                public void isSuccessful(Response<String> response, OnResult<String> callback) throws IOException {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    }
                    else {
                        throw new IOException("No pudo completarse la solicitud a la API, Codigo de error: "+response.code());
                    }
                }
            };
            responseStatus.isSuccessful(response, callbackIsSuccessful);

        } catch (IOException e) {
            callback.onError(e);
        }
    }

    @Override
    public void perteneceFavorito(Favorito favorito, OnResult<Boolean> callback) {

    }
}

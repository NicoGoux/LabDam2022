package com.mdgz.dam.labdam2022.data.datasource.retrofit;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.FavoritoDataSource;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.entities.FavoritoApiRest;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.interfaces.IsSuccessful;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.retrofit.AppRetrofit;
import com.mdgz.dam.labdam2022.model.Favorito;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
            IsSuccessful<Favorito> responseStatus = new IsSuccessful<>() {
                @Override
                public void isSuccessful(Response<Favorito> response) throws IOException {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        throw new IOException("No pudo completarse la solicitud a la API, Codigo de error: " + response.code());
                    }
                }
            };
            responseStatus.isSuccessful(response);
        } catch (IOException e) {
            callback.onError(e);
        }
    }

    @Override
    public void eliminarFavorito(Favorito favorito, OnResult<Favorito> callback) {
        try {
            Response<String> response = favoritoApiRest.eliminarFavorito(favorito.getAlojamientoId()).execute();

            IsSuccessful<String> responseStatus = new IsSuccessful<>() {
                @Override
                public void isSuccessful(Response<String> response) throws IOException {
                    if (response.isSuccessful()) {
                        callback.onSuccess(favorito);
                    } else {
                        throw new IOException("No pudo completarse la solicitud a la API, Codigo de error: " + response.code());
                    }
                }
            };
            responseStatus.isSuccessful(response);

        } catch (IOException e) {
            callback.onError(e);
        }
    }

    @Override
    public void consultarFavoritos(UUID userId, OnResult<List<Favorito>> callback) {
        try {
            Response<List<Favorito>> response = favoritoApiRest.listarFavoritos(userId).execute();

            IsSuccessful<List<Favorito>> responseStatus = new IsSuccessful<>() {
                @Override
                public void isSuccessful(Response<List<Favorito>> response) throws IOException {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        throw new IOException("No pudo completarse la solicitud a la API, Codigo de error: " + response.code());
                    }
                }
            };
            responseStatus.isSuccessful(response);
        } catch (IOException e) {
            callback.onError(e);
        }
    }

    @Override
    public void limpiarFavoritos(OnResult<Boolean> callback) {}

    @Override
    public void perteneceFavorito(Favorito favorito, OnResult<Boolean> callback) {}
}

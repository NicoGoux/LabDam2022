package com.mdgz.dam.labdam2022.data.datasource.retrofit.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.entities.FavoritoApiRest;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.entities.ReservaApiRest;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.adapter.GsonIsoDateAdapter;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AppRetrofit {

    public static final ExecutorService EXECUTOR_API = Executors.newSingleThreadExecutor();
    private static AppRetrofit INSTANCE;

    public ReservaApiRest reservaApiRest;
    public FavoritoApiRest favoritoApiRest;

    public static  AppRetrofit getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppRetrofit();
        }
        return INSTANCE;
    }

    private AppRetrofit() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new GsonIsoDateAdapter())
                .setPrettyPrinting()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dam-recordatorio-favoritos-api.duckdns.org/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        reservaApiRest = retrofit.create(ReservaApiRest.class);
        favoritoApiRest = retrofit.create(FavoritoApiRest.class);
    }
}

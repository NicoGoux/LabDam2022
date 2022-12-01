package com.mdgz.dam.labdam2022.data.datasource.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.adapter.GsonIsoDateAdapter;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRetrofit {

    public static final ExecutorService EXECUTOR_DB = Executors.newSingleThreadExecutor();
    private static AppRetrofit INSTANCE;

    public AppApiRest appApiRest;

    public static  AppRetrofit getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppRetrofit();
        }
        return INSTANCE;
    }

    private AppRetrofit() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new GsonIsoDateAdapter())
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dam-recordatorio-favoritos-api.duckdns.org/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        appApiRest = retrofit.create(AppApiRest.class);
    }
}

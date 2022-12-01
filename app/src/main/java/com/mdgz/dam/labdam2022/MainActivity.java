package com.mdgz.dam.labdam2022;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.AppApiRest;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.AppRetrofit;
import com.mdgz.dam.labdam2022.data.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.databinding.ActivityMainBinding;
import com.mdgz.dam.labdam2022.model.Reserva;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private AppApiRest apiRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.materialToolbar);
        setContentView(binding.getRoot());

        AppDataBase.getInstance(this);
        AppRetrofit.getInstance();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSettings:
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
                NavHostFragment.findNavController(currentFragment).navigate(R.id.action_global_settingsFragment);

            case R.id.menuBuscar:
                apiRest = AppRetrofit.getInstance().appApiRest;
                AppRetrofit.EXECUTOR_DB.execute(() -> {
                    try {

                        Response<List<Reserva>> response = apiRest.listarReservas().execute();

                        Log.i("TEST", ""+ response.code());

                        for (Reserva r : response.body()) {
                            Log.i("RESERVA",r.toString());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case R.id.menuMisReservas:
                Gson gson = new Gson();
                Reserva reserva = new Reserva(
                        UUID.fromString("47bb6cac-7a15-43b6-aa6f-611958063fb1"),
                        UUID.fromString("ba6dbe60-387b-412e-8fbb-0971f6f0c21a"),
                        Date.from(Instant.ofEpochMilli(1668384000000l)),
                        Date.from(Instant.ofEpochMilli(1669766400000l)));

                Log.i("Reserva", reserva.toString());
                AppRetrofit.EXECUTOR_DB.execute(() -> {
                    try {
                        Response<Reserva> response = AppRetrofit.getInstance().appApiRest.crearReserva(reserva).execute();
                        if (response.isSuccessful()) {
                            Log.i("Reserva", reserva.toString());
                        }
                        else {
                            Log.i("ERROR", "("+ response.code()+ ")");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        }
        return super.onOptionsItemSelected(item);
    }
}


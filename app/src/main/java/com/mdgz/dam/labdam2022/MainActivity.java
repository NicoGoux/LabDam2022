package com.mdgz.dam.labdam2022;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.mdgz.dam.labdam2022.databinding.ActivityMainBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repo.AppDataBase;
import com.mdgz.dam.labdam2022.repo.CiudadRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.materialToolbar);
        setContentView(binding.getRoot());

        dataBase = AppDataBase.getInstance(this);

        dataBase.clearTables();

        AlojamientoRepository alojamientoRepository = AlojamientoRepository.getInstance(this);



        final Ubicacion ubicacion1 = new Ubicacion(-42.6,-38.3,"San Martin","1989", CiudadRepository._CIUDADES.get(0));
        final Ubicacion ubicacion2 = new Ubicacion(-42.25,-38.2,"Lopez y Planes","2007",CiudadRepository._CIUDADES.get(1));

        final Departamento departamento = new Departamento(
                null,
                "Depto 1",
                "luminoso y amplio",
                6,
                300.0,
                true,
                1500.0,
                3,
                ubicacion1);

        final Habitacion habitacion = new Habitacion(
                null,
                "Habitacion 2",
                "Espectacular suite",
                4,
                1200.0,
                2,
                1,
                false,
                new Hotel(1,"Hotel 1",3,ubicacion2) );

        ArrayList<Alojamiento> listaAlojamientos = new ArrayList<>();
        listaAlojamientos.add(departamento);
        listaAlojamientos.add(habitacion);


        alojamientoRepository.insertarListaAlojamientos(listaAlojamientos);


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
        }
        return super.onOptionsItemSelected(item);
    }
}


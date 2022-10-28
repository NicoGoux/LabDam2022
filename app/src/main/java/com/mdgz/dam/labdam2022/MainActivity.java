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

import com.mdgz.dam.labdam2022.database.AppDataBase;
import com.mdgz.dam.labdam2022.databinding.ActivityMainBinding;

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

        Runnable r = () -> {
            dataBase.alojamientoDAO().recuperarAlojamientos();
        };
        dataBase.getQueryExecutor().execute(r);
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

                // TODO borrar. Usado para probar
                Runnable r = () -> {
                    Log.i("Objeto habitacion", (dataBase.alojamientoDAO().recuperarAlojamientos())[0].toString());
                };
                dataBase.getQueryExecutor().execute(r);

        }
        return super.onOptionsItemSelected(item);
    }

    /*

    * TODO Herencia en Room. Como tratarla y como generar objetos completos a partir de la relacion?.
    * TODO Utilizacion de Embedded. Es mejor realizar Embedded o ingresar tablas individuales?.
    * TODO RoomDataSource. Como se implementan?
    * TODO Inserts complementarios (primero alojamiento y despues habitacion/departamento). Que pasa si uno falla?

     */
}


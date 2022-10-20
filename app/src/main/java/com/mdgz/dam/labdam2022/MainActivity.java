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
import com.mdgz.dam.labdam2022.modelDAO.AlojamientoDAO;
import com.mdgz.dam.labdam2022.repo.AppDataBase;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.materialToolbar);
        setContentView(binding.getRoot());

        dataBase = AppDataBase.getinstance(this);
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

    //TODO el recycler del log no funciona [: no entiendo bien como se maneja.
    // revisa la funcion asignarDatos de la clase AdapterLog y el comentario.
    // revisa el log_item_list.xml
    // en la clase BusquedaFragment no pude agregar las ciudades al spinner en la linea 94
}


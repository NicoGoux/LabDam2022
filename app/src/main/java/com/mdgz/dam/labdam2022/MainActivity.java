package com.mdgz.dam.labdam2022;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.retrofit.retrofit.AppRetrofit;
import com.mdgz.dam.labdam2022.data.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.data.factory.AlojamientoRepositoryFactory;
import com.mdgz.dam.labdam2022.data.factory.FavoritoRepositoryFactory;
import com.mdgz.dam.labdam2022.data.repository.AlojamientoRepository;
import com.mdgz.dam.labdam2022.data.repository.FavoritoRepository;
import com.mdgz.dam.labdam2022.databinding.ActivityMainBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.materialToolbar);
        setContentView(binding.getRoot());

        AppDataBase.getInstance(this);
        AppRetrofit.getInstance();

        // TODO hardcodeado porque no sabiamos como obtener un UUID de la aplicacion
        UUID user_id = UUID.fromString("ba6dbe60-387b-412e-8fbb-0971f6f0c21a");

        // Precarga de la lista de favoritos y reservas guardada en la nube a la base de datos local

        // Se crean ambos repository (uno para API y otro para DB)
        FavoritoRepository frDb = FavoritoRepositoryFactory.create(this);
        FavoritoRepository frApi = FavoritoRepositoryFactory.create();

        OnResult<Favorito> guardarFavoritoCallback = new OnResult<Favorito>() {
            @Override
            public void onSuccess(Favorito result) {

            }

            @Override
            public void onError(Throwable exception) {
                exception.printStackTrace();
            }
        };
        OnResult<List<Favorito>> ListaFavoritosCallback = new OnResult<List<Favorito>>() {
            @Override
            public void onSuccess(List<Favorito> result) {

                for (Favorito favorito : result) {

                    Log.i("FAVORITOS", favorito.toString());
                    AppDataBase.EXECUTOR_DB.execute(() -> frDb.guardarFavorito(favorito, guardarFavoritoCallback));
                }
            }
            @Override
            public void onError(Throwable exception) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "No pudieron cargarse los datos de la nube", Toast.LENGTH_SHORT).show());
                exception.printStackTrace();
            }
        };

        AppRetrofit.EXECUTOR_API.execute(() -> frApi.listarFavoritos(user_id, ListaFavoritosCallback));


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


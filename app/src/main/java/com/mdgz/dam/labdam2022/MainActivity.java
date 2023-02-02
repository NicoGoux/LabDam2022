package com.mdgz.dam.labdam2022;

import android.os.Bundle;
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
import com.mdgz.dam.labdam2022.data.factory.FavoritoRepositoryFactory;
import com.mdgz.dam.labdam2022.data.factory.ReservaRepositoryFactory;
import com.mdgz.dam.labdam2022.data.repository.FavoritoRepository;
import com.mdgz.dam.labdam2022.data.repository.ReservaRepository;
import com.mdgz.dam.labdam2022.databinding.ActivityMainBinding;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Reserva;

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

        sincronizarFavYRes();

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

    private void sincronizarFavYRes() {
        // TODO hardcodeado porque no sabiamos como obtener un UUID de la aplicacion
        UUID user_id = UUID.fromString("ba6dbe60-387b-412e-8fbb-0971f6f0c21a");

        // Precarga de la lista de favoritos y reservas guardada en la nube a la base de datos local

        // Se crean ambos repository de cada entidad (uno para API y otro para DB)
        FavoritoRepository frDb = FavoritoRepositoryFactory.create(this);
        FavoritoRepository frApi = FavoritoRepositoryFactory.create();

        ReservaRepository resDb = ReservaRepositoryFactory.create(this);
        ReservaRepository resApi = ReservaRepositoryFactory.create();


        // Proceso para la tabla Favoritos
        OnResult<List<Favorito>> ListaFavoritosCallback = new OnResult<>() {
            @Override
            public void onSuccess(List<Favorito> result) {

                OnResult<Boolean> limpiarTablaCallback = new OnResult<>() {
                    @Override
                    public void onSuccess(Boolean exito) {
                        OnResult<Favorito> guardarFavoritoCallback = new OnResult<>() {
                            @Override
                            public void onSuccess(Favorito result) {
                            }

                            @Override
                            public void onError(Throwable exception) {
                                exception.printStackTrace();
                            }
                        };

                        // Se cargan la lista de favoritos que persiste en la nube
                        AppDataBase.EXECUTOR_DB.execute(() -> {
                            for (Favorito favorito : result) {
                                frDb.guardarFavorito(favorito, guardarFavoritoCallback);
                            }
                            runOnUiThread(() -> Toast.makeText(MainActivity.this, "Se sincronizaron los favoritos", Toast.LENGTH_SHORT).show());
                        });
                    }

                    @Override
                    public void onError(Throwable exception) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "No se pudo sincronizar los datos de la nube", Toast.LENGTH_SHORT).show());
                    }
                };

                //Se limpia la tabla que se encuentra en local
                AppDataBase.EXECUTOR_DB.execute(() -> frDb.limpiarFavoritos(limpiarTablaCallback));
            }

            @Override
            public void onError(Throwable exception) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "No se pudo sincronizar los datos de la nube", Toast.LENGTH_SHORT).show());
                exception.printStackTrace();
            }
        };
        AppRetrofit.EXECUTOR_API.execute(() -> frApi.consultarFavoritos(user_id, ListaFavoritosCallback));

        // Proceso para la tabla Reserva
        OnResult<List<Reserva>> ListaReservasCallback = new OnResult<>() {
            @Override
            public void onSuccess(List<Reserva> result) {
                OnResult<Boolean> limpiarTablaCallback = new OnResult<>() {
                    @Override
                    public void onSuccess(Boolean exito) {
                        OnResult<Reserva> guardarReservaCallback = new OnResult<>() {
                            @Override
                            public void onSuccess(Reserva result) {
                            }

                            @Override
                            public void onError(Throwable exception) {
                                exception.printStackTrace();
                            }
                        };

                        // Se cargan la lista de favoritos que persiste en la nube
                        AppDataBase.EXECUTOR_DB.execute(() -> {
                            for (Reserva reserva : result) {
                                resDb.guardarReserva(reserva, guardarReservaCallback);
                            }
                            runOnUiThread(() -> Toast.makeText(MainActivity.this, "Se sincronizaron las reservas", Toast.LENGTH_SHORT).show());
                        });
                    }

                    @Override
                    public void onError(Throwable exception) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "No se pudo sincronizar los datos de la nube", Toast.LENGTH_SHORT).show());
                    }
                };

                //Se limpia la tabla que se encuentra en local
                AppDataBase.EXECUTOR_DB.execute(() -> resDb.limpiarReservas(limpiarTablaCallback));
            }

            @Override
            public void onError(Throwable exception) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "No se pudo sincronizar los datos de la nube", Toast.LENGTH_SHORT).show());
                exception.printStackTrace();
            }
        };
        AppRetrofit.EXECUTOR_API.execute(() -> resApi.consultarReservas(user_id, ListaReservasCallback));
    }
}


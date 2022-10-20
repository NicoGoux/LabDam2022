package com.mdgz.dam.labdam2022;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repo.CiudadRepository;

import java.time.Instant;
import java.util.ArrayList;

public class BusquedaFragment extends Fragment {

    private NavController navHost;
    private FragmentBusquedaBinding binding;

    public BusquedaFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBusquedaBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navHost = NavHostFragment.findNavController(this);

        ArrayList<Ciudad> ciudades = new ArrayList<>();

        // Se crea una ciudad con nombre vacio y atributos null para utilizarlo como primer elemento del spinner
        ciudades.add(0,new Ciudad(null,"Seleccione una ciudad", null));
        ciudades.addAll(new CiudadRepository().listaCiudades());

        ArrayAdapter<Ciudad> ciudadAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item,ciudades);
        binding.ciudadId.setAdapter(ciudadAdapter);


        binding.resetButtonId.setOnClickListener((View view1) -> {

                binding.hotelCheckboxId.setChecked(false);
                binding.departamentoCheckboxId.setChecked(false);
                binding.cantidadPersonasId.setText("");
                binding.wifiCheckBoxId.setChecked(false);
                binding.minimoPrecioId.setText("");
                binding.maximoPrecioId.setText("");
                binding.ciudadId.setSelection(0);
        });

        binding.searchButtonId.setOnClickListener( (View view1) -> {

            // Deberia obtenerse de base de datos

            ArrayList<Alojamiento> listaDatos = new ArrayList<Alojamiento>();
            listaDatos.addAll(new AlojamientoRepository().listaCiudades());

            Bundle args = new Bundle();
            args.putParcelableArrayList("resultados_busqueda", listaDatos);

            navHost.navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment,args);

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
            boolean autorizacion = sharedPreferences.getBoolean("autorizacion_recopilacion",false);

            if(autorizacion){
                String registro = "";
                String ts = String.valueOf(Instant.now().getEpochSecond());

                registro += ts + ",";
                if(binding.hotelCheckboxId.isChecked()) registro += "buscar:" + binding.hotelCheckboxId.getText().toString() + ",";
                if(binding.departamentoCheckboxId.isChecked()) registro += "buscar:" + binding.departamentoCheckboxId.getText().toString() + ",";
                if(!binding.cantidadPersonasId.getText().toString().isEmpty()) registro += "personas:" + binding.cantidadPersonasId.getText().toString() + ",";
                if(binding.wifiCheckBoxId.isChecked()) registro += binding.wifiCheckBoxId.getText().toString() + ",";
                if(!binding.minimoPrecioId.getText().toString().isEmpty()) registro += "precio min.:" + binding.minimoPrecioId.getText().toString() + ",";
                if(!binding.maximoPrecioId.getText().toString().isEmpty()) registro += "precio max.:" + binding.maximoPrecioId.getText().toString() + ",";

                //TODO agregar la ciudad en base a la seleccion del spiner
                if(!((Ciudad) binding.ciudadId.getSelectedItem()).getNombre().equals("Seleccione una ciudad")) registro += "ciudad:" + ((Ciudad) binding.ciudadId.getSelectedItem()).getNombre() + ",";


                //TODO agregar la cantidad de resultados, es decir, el tamaño de la lista de los alojamientos filtrados
                registro += "resultados:" + listaDatos.size();

                //TODO agregar tiempo que tardó la busqueda (quizá en base a la busqueda en base de datos) Instant2.now() - Instant1.now()

               //Se escribe el archivo
                FileManager.saveLogFile(registro,requireContext());
            }
        });
    }
}
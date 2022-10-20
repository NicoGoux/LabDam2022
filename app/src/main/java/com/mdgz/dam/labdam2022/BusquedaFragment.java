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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusquedaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusquedaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BusquedaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusquedaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusquedaFragment newInstance(String param1, String param2) {
        BusquedaFragment fragment = new BusquedaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private NavController navHost;
    private FragmentBusquedaBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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

        // TODO -> Lo intente de mil formas y no anda <-
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        ciudades.addAll(new CiudadRepository().listaCiudades());
        //ArrayAdapter<Ciudad> ciudadAdapter = new ArrayAdapter<Ciudad>(this, android.R.layout.simple_spinner_item,ciudades);

        binding.resetButtonId.setOnClickListener((View view1) -> {

                binding.hotelCheckboxId.setChecked(false);
                binding.departamentoCheckboxId.setChecked(false);
                binding.cantidadPersonasId.setText("");
                binding.wifiCheckBoxId.setChecked(false);
                binding.minimoPrecioId.setText("");
                binding.maximoPrecioId.setText("");
                binding.ciudadId.setSelected(false);

        });

        binding.searchButtonId.setOnClickListener( (View view1) -> {

            //Se pasarian los alojamientos filtrados
            navHost.navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment);

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
            boolean autorizacion = sharedPreferences.getBoolean("autorizacion_recopilacion",false);

            if(autorizacion){

            String registro = "";
            String ts = String.valueOf(Instant.now().getEpochSecond());

            registro += ts + " ";
            if(binding.hotelCheckboxId.isChecked()) registro += binding.hotelCheckboxId.getText().toString() + " ";
            if(binding.departamentoCheckboxId.isChecked()) registro += binding.departamentoCheckboxId.getText().toString() + " ";
            if(binding.cantidadPersonasId.getText() != null) registro += binding.cantidadPersonasId.getText().toString() + " ";
            if(binding.wifiCheckBoxId.isChecked()) registro += binding.wifiCheckBoxId.getText().toString() + " ";
            if(binding.minimoPrecioId.getText() != null) registro += binding.minimoPrecioId.getText().toString() + " ";
            if(binding.maximoPrecioId.getText() != null) registro += binding.maximoPrecioId.getText().toString() + " ";

            //TODO agregar la ciudad en base a la seleccion del spiner
                if(binding.ciudadId.getSelectedItem() != null) registro += ((Ciudad) binding.ciudadId.getSelectedItem()).getNombre() + " ";


            //TODO agregar la cantidad de resultados, es decir, el tamaño de la lista de los alojamientos filtrados
            ArrayList<Alojamiento> listaDatos = new ArrayList<>();
            listaDatos.addAll(new AlojamientoRepository().listaCiudades());
            registro += listaDatos.size() + " ";

            //TODO agregar tiempo que tardó la busqueda (quizá en base a la busqueda en base de datos) Instant2.now() - Instant1.now()


           //Se escribe el archivo
            FileManager.saveLogFile(registro,requireContext());

            //TODO BORRAR, muestra los strings que hay en el archivo
                FileManager.readLogFile("search_register.txt",requireContext());

            }
            else{
                String fileName = "search_register.txt";
                //Se elimina el archivo
                FileManager.deleteLogFile(fileName,requireContext());
            }


        });
    }
}
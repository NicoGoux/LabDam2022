package com.mdgz.dam.labdam2022;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;

import java.util.ArrayList;

public class ResultadoBusquedaFragment extends Fragment {

    private NavController navHost;
    private FragmentResultadoBusquedaBinding binding;

    public ResultadoBusquedaFragment() {
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
        binding = FragmentResultadoBusquedaBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Se obtiene el navHost
        navHost = NavHostFragment.findNavController(this);

        ArrayList<Alojamiento> listaResultados = new ArrayList<>();

        // Se obtienen los reusltados de la busqueda
        if (getArguments() != null) {
            listaResultados = getArguments().getParcelableArrayList("resultados_busqueda");
        }

        // Seteo del recycler
        RecyclerView recycler = binding.recycler;
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));

        AdapterAlojamiento adapterAlojamiento = new AdapterAlojamiento(listaResultados);
        recycler.setAdapter(adapterAlojamiento);

        // Action listener nueva busqueda
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navHost.navigate(R.id.action_resultadoBusquedaFragment_to_busquedaFragment);
            }
        });


    }


}
package com.mdgz.dam.labdam2022;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleAlojamientoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleAlojamientoFragment extends Fragment {

    private FragmentDetalleAlojamientoBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleAlojamientoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleAlojamientoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleAlojamientoFragment newInstance(String param1, String param2) {
        DetalleAlojamientoFragment fragment = new DetalleAlojamientoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        binding = FragmentDetalleAlojamientoBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Alojamiento alojamiento = null;
        if (getArguments() != null) {
            alojamiento = new AlojamientoRepository().getAlojamiento(getArguments().getInt("id_alojamiento"));
        }
        if (alojamiento != null) {
            binding.descripcionDetalle.setText("Descripcion: " + alojamiento.getDescripcion());
            binding.capacidadDetalle.setText("Capacidad: "+ alojamiento.getCapacidad());
            binding.precioBaseDetalle.setText("Precio por noche: " + alojamiento.getPrecioBase());

            if(alojamiento instanceof Departamento){
                binding.tituloDetalle.setText(alojamiento.getTitulo());
                String tieneWifi = "NO";
                if(((Departamento) alojamiento).getTieneWifi()) tieneWifi = "SI";
                binding.tieneWifiDetalle.setText("Tiene Wifi: " + tieneWifi);
                binding.costoLimpiezaDetalle.setText("Costo limpieza por dia: " + ((Departamento) alojamiento).getCostoLimpieza());
                binding.cantidadHabitacionesDetalle.setText("Cantidad habitaciones: " + ((Departamento) alojamiento).getCantidadHabitaciones());
                Ubicacion ubicacion = alojamiento.getUbicacion();
                binding.ubicacionDetalle.setText("Ubicacion: " + ubicacion.getCiudad().getNombre() + " " + ubicacion.getCalle() + " " + ubicacion.getNumero());

                binding.tieneWifiDetalle.setVisibility(View.VISIBLE);
                binding.costoLimpiezaDetalle.setVisibility(View.VISIBLE);
                binding.cantidadHabitacionesDetalle.setVisibility(View.VISIBLE);
                binding.ubicacionDetalle.setVisibility(View.VISIBLE);
            }
            else if(alojamiento instanceof Habitacion){
                binding.tituloDetalle.setText(((Habitacion) alojamiento).getHotel().getNombre());
                binding.camasIndividualesDetalle.setText("Cantidad de camas individuales: " + ((Habitacion)alojamiento).getCamasIndividuales());
                binding.camasMatrimonialesDetalle.setText("Cantidad de camas matrimoniales: " + ((Habitacion)alojamiento).getCamasMatrimoniales());
                String tieneEstacionamiento = "NO";
                if(((Habitacion) alojamiento).getTieneEstacionamiento()) tieneEstacionamiento = "SI";
                binding.tieneEstacionamientoDetalle.setText("Tiene estacionamiento: " + tieneEstacionamiento);
                Ubicacion ubicacion = alojamiento.getUbicacion();
                binding.ubicacionDetalle.setText("Ubicacion: " + ubicacion.getCiudad().getNombre() + " " + ubicacion.getCalle() + " " + ubicacion.getNumero());

                binding.camasIndividualesDetalle.setVisibility(View.VISIBLE);
                binding.camasMatrimonialesDetalle.setVisibility(View.VISIBLE);
                binding.tieneEstacionamientoDetalle.setVisibility(View.VISIBLE);
                binding.ubicacionDetalle.setVisibility(View.VISIBLE);

            }

            binding.fechaIngresoId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            // +1 because January is zero
                            final String selectedDate = day + " / " + (month+1) + " / " + year;
                            binding.fechaIngresoId.setText(selectedDate);
                        }
                    });
                    newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
                }
            });
            binding.fechaEgresoId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            // +1 because January is zero
                            final String selectedDate = day + " / " + (month+1) + " / " + year;
                            binding.fechaEgresoId.setText(selectedDate);
                        }
                    });
                    newFragment.show(requireActivity().getSupportFragmentManager(), "datePicker");
                }
            });

            binding.favoriteButton.setOnClickListener((View view1) -> {
                binding.favoriteButton.setVisibility(View.GONE);
                binding.redFavoriteButton.setVisibility(View.VISIBLE);
                Toast.makeText(view1.getContext(), "Añadido a favoritos",Toast.LENGTH_SHORT).show();
            });

            binding.redFavoriteButton.setOnClickListener((View view1) -> {
                binding.redFavoriteButton.setVisibility(View.GONE);
                binding.favoriteButton.setVisibility(View.VISIBLE);
                Toast.makeText(view1.getContext(), "Eliminado de favoritos",Toast.LENGTH_SHORT).show();
            });

            binding.reservarButtonId.setOnClickListener((View view1) -> { Toast.makeText(view1.getContext(), "Reserva creada con exito",Toast.LENGTH_SHORT).show(); });
        }
    }
}
package com.mdgz.dam.labdam2022.fragments;

import static java.lang.Math.abs;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.utilities.DatePickerFragment;
import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DetalleAlojamientoFragment extends Fragment {

    private FragmentDetalleAlojamientoBinding binding;

    public DetalleAlojamientoFragment() {
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
        binding = FragmentDetalleAlojamientoBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Alojamiento alojamiento = null;
        if (getArguments() != null) {
            alojamiento = getArguments().getParcelable("alojamiento_seleccionado");
        }

        // Se completan y muestran los campos dependiendo si
        // se selecciono un departamento o una habitacion

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
            // Se copia el contenido de alojamiento ya que no es null en este punto
            Alojamiento finalAlojamiento = alojamiento;
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

            // Se crea un observador para actualizar el precio dinamicamente
            TextWatcher fechaWatcher = new TextWatcher(){
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { actualizarPrecioTotal(finalAlojamiento); }

                @Override
                public void afterTextChanged(Editable editable) {}
            };

            binding.fechaIngresoId.addTextChangedListener(fechaWatcher);
            binding.fechaEgresoId.addTextChangedListener(fechaWatcher);

            // Se simula el boton favorito encendido
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

            // Se muestra un Toast de que la reserva se creo con exito
            binding.reservarButtonId.setOnClickListener((View view1) -> { Toast.makeText(view1.getContext(), "Reserva creada con exito",Toast.LENGTH_SHORT).show(); });
        }
    }

    // Este metodo obtiene la cantidad de dias de hospedaje y muestra el precio total
    private void actualizarPrecioTotal(Alojamiento alojamiento){

        if(!binding.fechaIngresoId.getText().toString().equals("") && !binding.fechaEgresoId.getText().toString().equals("")){

            String fechaIngreso = binding.fechaIngresoId.getText().toString();
            String fechaEgreso = binding.fechaEgresoId.getText().toString();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d / M / yyyy");
            LocalDate dateIngreso = LocalDate.parse(fechaIngreso,formatter);
            LocalDate dateEgreso = LocalDate.parse(fechaEgreso,formatter);

            Log.i("fechaIngreso: ",dateIngreso.toString());
            Log.i("fechaEgreso: ",dateEgreso.toString());

            Duration duration = Duration.between(dateIngreso.atStartOfDay(),dateEgreso.atStartOfDay());
            // Se obtienen los dias en valor absoluto
            Long days = abs(duration.toDays());
            Double costoTotal = alojamiento.obtenerCosto(days,alojamiento.getPrecioBase());
            binding.precioDetalleId.setText("Costo total: $" + costoTotal.toString());

        }

    }
}
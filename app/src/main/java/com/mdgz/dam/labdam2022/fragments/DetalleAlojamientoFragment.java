package com.mdgz.dam.labdam2022.fragments;

import static java.lang.Math.abs;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.data.factory.FavoritoRepositoryFactory;
import com.mdgz.dam.labdam2022.data.factory.ReservaRepositoryFactory;
import com.mdgz.dam.labdam2022.data.repository.FavoritoRepository;
import com.mdgz.dam.labdam2022.data.repository.ReservaRepository;
import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.utilities.DatePickerFragment;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

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

        UUID user_id = UUID.fromString("ba6dbe60-387b-412e-8fbb-0971f6f0c21a");

        // Se instancian los repositorios
        FavoritoRepository favoritoRepository = FavoritoRepositoryFactory.create(requireContext());
        ReservaRepository reservaRepository = ReservaRepositoryFactory.create(requireContext());

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

            // Se setea el color del boton de favorito si pertenece o no a favorito
            OnResult<Boolean> perteneceCallback = new OnResult<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    if (result) {
                        binding.favoriteButton.setColorFilter(Color.RED);
                    }
                    else {
                        binding.favoriteButton.setColorFilter(null);
                    }
                }
                @Override
                public void onError(Throwable exception) {
                    exception.printStackTrace();
                }
            };
            AppDataBase.EXECUTOR_DB.execute(() -> favoritoRepository.perteneceFavorito(new Favorito(finalAlojamiento.getId(), user_id),perteneceCallback));

            binding.favoriteButton.setOnClickListener((View view1) -> {
                if(binding.favoriteButton.getColorFilter() == null){
                    binding.favoriteButton.setColorFilter(Color.RED);
                    OnResult<Favorito> favoritoCallback = new OnResult<Favorito>() {
                        @Override
                        public void onSuccess(Favorito result) {
//                        Toast.makeText(view1.getContext(), "Añadido a favoritos",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable exception) {
//                        Toast.makeText(view1.getContext(), "No pudo añadirse a favoritos",Toast.LENGTH_SHORT).show();
                            exception.printStackTrace();
                        }
                    };
                    AppDataBase.EXECUTOR_DB.execute(() -> favoritoRepository.guardarFavorito(new Favorito(finalAlojamiento.getId(), user_id), favoritoCallback));
                }
                else{
                    binding.favoriteButton.setColorFilter(null);
                    OnResult<Favorito> favoritoCallback = new OnResult<Favorito>() {
                        @Override
                        public void onSuccess(Favorito result) {
                        }

                        @Override
                        public void onError(Throwable exception) {
                            exception.printStackTrace();
                        }
                    };
                    AppDataBase.EXECUTOR_DB.execute(() -> favoritoRepository.eliminarFavorito(new Favorito(finalAlojamiento.getId(), user_id), favoritoCallback));
                }
            });

            // Se muestra un Toast de que la reserva se creo con exito
            binding.reservarButtonId.setOnClickListener((View view1) -> {
                String fechaIngresoString = binding.fechaIngresoId.getText().toString();
                String fechaEgresoString = binding.fechaEgresoId.getText().toString();

                if (fechaEgresoString.isEmpty() || fechaEgresoString.isEmpty()) {
                    Toast.makeText(view1.getContext(), "Complete las fechas de entrada y salida", Toast.LENGTH_SHORT).show();
                    return;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d / M / yyyy");
                LocalDate fechaIngreso = LocalDate.parse(fechaIngresoString,formatter);
                LocalDate fechaEgreso = LocalDate.parse(fechaEgresoString,formatter);

                // Se convierten las fechas LocalDate a Date
                Date fechaIngresoDate = Date.from(fechaIngreso.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date fechaEgresoDate = Date.from(fechaEgreso.atStartOfDay(ZoneId.systemDefault()).toInstant());

                OnResult<Reserva> guardarReservaCallback = new OnResult<Reserva>() {
                    @Override
                    public void onSuccess(Reserva result) {
                        requireActivity().runOnUiThread(() -> Toast.makeText(view1.getContext(), "Reserva guardada", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onError(Throwable exception) {
                        requireActivity().runOnUiThread(() -> Toast.makeText(view1.getContext(), "No pudo guardarse la reserva", Toast.LENGTH_SHORT).show());
                        exception.printStackTrace();
                    }
                };

                AppDataBase.EXECUTOR_DB.execute(() -> reservaRepository.guardarReserva(new Reserva(
                        finalAlojamiento.getId(),
                        user_id,
                        fechaIngresoDate,
                        fechaEgresoDate
                ), guardarReservaCallback));
            });
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

            Duration duration = Duration.between(dateIngreso.atStartOfDay(),dateEgreso.atStartOfDay());
            // Se obtienen los dias en valor absoluto
            Long days = abs(duration.toDays());
            Double costoTotal = alojamiento.obtenerCosto(days,alojamiento.getPrecioBase());
            binding.precioDetalleId.setText("Costo total: $" + costoTotal.toString());
        }

    }
}
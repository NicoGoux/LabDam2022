package com.mdgz.dam.labdam2022.data.datasource.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.data.OnResult;
import com.mdgz.dam.labdam2022.data.datasource.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.AlojamientoDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.CiudadDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.DepartamentoDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.HabitacionDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.HotelDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.dao.UbicacionDAO;
import com.mdgz.dam.labdam2022.data.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.AlojamientoEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.CiudadEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.DepartamentoEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.HabitacionEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.HotelEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.entities.UbicacionEntity;
import com.mdgz.dam.labdam2022.data.datasource.room.mapper.AlojamientoMapper;
import com.mdgz.dam.labdam2022.data.datasource.room.mapper.CiudadMapper;
import com.mdgz.dam.labdam2022.data.datasource.room.mapper.DepartamentoMapper;
import com.mdgz.dam.labdam2022.data.datasource.room.mapper.HabitacionMapper;
import com.mdgz.dam.labdam2022.data.datasource.room.mapper.HotelMapper;
import com.mdgz.dam.labdam2022.data.datasource.room.mapper.UbicacionMapper;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AlojamientoRoomDataSource implements AlojamientoDataSource {
    private final AlojamientoDAO alojamientoDAO;
    private final HabitacionDAO habitacionDAO;
    private final DepartamentoDAO departamentoDAO;
    private final UbicacionDAO ubicacionDAO;
    private final CiudadDAO ciudadDAO;
    private final HotelDAO hotelDAO;

    public AlojamientoRoomDataSource(final Context context) {
        this(AppDataBase.getInstance(context));
    }

    public AlojamientoRoomDataSource(final AppDataBase db) {
        alojamientoDAO = db.alojamientoDAO();
        habitacionDAO = db.habitacionDAO();
        departamentoDAO = db.departamentoDAO();
        ubicacionDAO = db.ubicacionDAO();
        ciudadDAO = db.ciudadDAO();
        hotelDAO = db.hotelDAO();
    }

    @Override
    public void guardarHabitacion(final Habitacion habitacion, final OnResult<Habitacion> callback) {
        try {
            final AlojamientoEntity ae = AlojamientoMapper.toEntity(habitacion);
            alojamientoDAO.insertar(ae);


            final Hotel hotel = habitacion.getHotel();
            final Ubicacion ubicacion = hotel.getUbicacion();

            // Si no existe la ubicacion se inserta
            UUID ubicacionId = ubicacionDAO.recuperarUbicacionPorLatLng(ubicacion.getLat(),ubicacion.getLng());
            if (ubicacionId == null) {
                final UbicacionEntity ue = UbicacionMapper.toEntity(ubicacion);
                ubicacionDAO.insertar(ue);
                ubicacionId = ue.getId();
            }

            // Si no existe el hotel se inserta
            UUID hotelId = hotelDAO.existeIdHotel(hotel.getId());
            if (hotelId == null) {
                final HotelEntity hotelEntity = HotelMapper.toEntity(habitacion.getHotel(), ubicacionId);
                hotelDAO.insertar(hotelEntity);
                hotelId = hotelEntity.getId();
            }

            final HabitacionEntity he = HabitacionMapper.toEntity(habitacion, ae.getId(), hotelId);
            habitacionDAO.insertar(he);
            habitacion.setId(he.getId());
            callback.onSuccess(habitacion);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void guardarDepartamento(final Departamento departamento, final OnResult<Departamento> callback) {
        try {
            final AlojamientoEntity ae = AlojamientoMapper.toEntity(departamento);
            alojamientoDAO.insertar(ae);

            // Si no existe la ubicacion se inserta
            Ubicacion ubicacion = departamento.getUbicacion();
            UUID ubicacionId = ubicacionDAO.recuperarUbicacionPorLatLng(ubicacion.getLat(),ubicacion.getLng());
            if (ubicacionId == null) {
                final UbicacionEntity ue = UbicacionMapper.toEntity(ubicacion);
                ubicacionDAO.insertar(ue);
                ubicacionId = ue.getId();
            }

            final DepartamentoEntity de = DepartamentoMapper.toEntity(departamento, ae.getId(), ubicacionId);
            departamentoDAO.insertar(de);

            departamento.setId(de.getId());
            callback.onSuccess(departamento);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarHabitaciones(final OnResult<List<Habitacion>> callback) {
        try {
            final HabitacionEntity[] heCollection = habitacionDAO.recuperarHabitaciones();
            final List<Habitacion> habitaciones = new ArrayList<>();

            Habitacion habitacion;
            AlojamientoEntity ae;
            HotelEntity hotelEntity;
            UbicacionEntity ue;
            CiudadEntity ce;
            for (final HabitacionEntity he : heCollection) {
                ae = alojamientoDAO.recuperarAlojamientos(he.getAlojamientoId());
                habitacion = HabitacionMapper.fromEntity(he, ae);

                hotelEntity = hotelDAO.recuperarHotelPorId(he.getHotelId());
                ue = ubicacionDAO.recuperarUbicacionPorId(hotelEntity.getUbicacionId());
                ce = ciudadDAO.recuperarCiudadPorId(ue.getCiudadId());
                Ciudad ciudadHotel = CiudadMapper.fromEntity(ce);
                Ubicacion ubicacionHotel = UbicacionMapper.fromEntity(ue, ciudadHotel);

                habitacion.setHotel(HotelMapper.fromEntity(hotelEntity,ubicacionHotel));
                habitaciones.add(habitacion);
            }
            callback.onSuccess(habitaciones);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarDepartamentos(final OnResult<List<Departamento>> callback) {
        try {
            final DepartamentoEntity[] deCollection = departamentoDAO.recuperarDepartamentos();
            final List<Departamento> departamentos = new ArrayList<>();

            Departamento departamento;
            AlojamientoEntity ae;
            UbicacionEntity ue;
            CiudadEntity ce;

            // Se arma el objeto completo buscando todos los objetos asociados
            for (final DepartamentoEntity de : deCollection) {
                ae = alojamientoDAO.recuperarAlojamientos(de.getAlojamientoId());
                departamento = DepartamentoMapper.fromEntity(de, ae);

                ue = ubicacionDAO.recuperarUbicacionPorId(de.getUbicacionId());
                ce = ciudadDAO.recuperarCiudadPorId(ue.getCiudadId());
                Ciudad ciudadDepartamento = CiudadMapper.fromEntity(ce);
                departamento.setUbicacion(UbicacionMapper.fromEntity(ue,ciudadDepartamento));
                departamentos.add(departamento);
            }
            callback.onSuccess(departamentos);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarAlojamientos(final OnResult<List<Alojamiento>> callback) {
        try {
            final AlojamientoEntity[] aeCollection = alojamientoDAO.recuperarAlojamientos();
            final List<Alojamiento> alojamientos = new ArrayList<>();

            DepartamentoEntity de;
            HabitacionEntity he;

            HotelEntity hotelEntity;
            UbicacionEntity ue;
            CiudadEntity ce;

            for (final AlojamientoEntity ae : aeCollection) {
                if (Objects.equals(ae.getTipo(), AlojamientoEntity.TIPO_DEPARTAMENTO)) {
                    de = departamentoDAO.recuperarDepartamentoConAlojamiento(ae.getId());
                    Departamento departamento = DepartamentoMapper.fromEntity(de, ae);

                    ue = ubicacionDAO.recuperarUbicacionPorId(de.getUbicacionId());
                    ce = ciudadDAO.recuperarCiudadPorId(ue.getCiudadId());
                    Ciudad ciudadDepartamento = CiudadMapper.fromEntity(ce);

                    departamento.setUbicacion(UbicacionMapper.fromEntity(ue,ciudadDepartamento));
                    alojamientos.add(departamento);

                } else {
                    he = habitacionDAO.recuperarHabitacionConAlojamiento(ae.getId());
                    Habitacion habitacion = HabitacionMapper.fromEntity(he, ae);

                    hotelEntity = hotelDAO.recuperarHotelPorId(he.getHotelId());
                    ue = ubicacionDAO.recuperarUbicacionPorId(hotelEntity.getUbicacionId());
                    ce = ciudadDAO.recuperarCiudadPorId(ue.getCiudadId());
                    Ciudad ciudadHotel = CiudadMapper.fromEntity(ce);
                    Ubicacion ubicacionHotel = UbicacionMapper.fromEntity(ue, ciudadHotel);

                    habitacion.setHotel(HotelMapper.fromEntity(hotelEntity,ubicacionHotel));
                    alojamientos.add(habitacion);
                }
            }
            callback.onSuccess(alojamientos);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }
}

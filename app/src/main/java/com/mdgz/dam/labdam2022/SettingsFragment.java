package com.mdgz.dam.labdam2022;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

public class SettingsFragment extends PreferenceFragmentCompat {

    private NavController navHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.root_preferences);

        // Definimos el NavController
        navHost = NavHostFragment.findNavController(this);

        // Dependencia de metodo de pago y moneda
        final ListPreference metodoPagoPreferenceList = (ListPreference)findPreference("metodo_de_pago_list");
        final ListPreference monedaPreferenceList = (ListPreference)findPreference("moneda_list");
        metodoPagoPreferenceList.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
           public boolean onPreferenceChange(Preference preference, Object newValue) {
               final String val = newValue.toString();
               int index = metodoPagoPreferenceList.findIndexOfValue(val);
               if(index==0) {
                   monedaPreferenceList.setEnabled(true);
               }
               else {
                   monedaPreferenceList.setEnabled(false);
                   monedaPreferenceList.setValue("");
               }
               return true;
           }
        });

        // deshabilitacion o habilitacion de lista de monedas
        if (metodoPagoPreferenceList.findIndexOfValue(metodoPagoPreferenceList.getValue()) == 0) {
            monedaPreferenceList.setEnabled(true);
        }
        else {
            monedaPreferenceList.setEnabled(false);
        }

        final SwitchPreference notificaciones = (SwitchPreference) findPreference("notificaciones");
        notificaciones.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                final Boolean value = (Boolean) newValue;
                if (value) {
                    Toast.makeText(getContext(),"Notificaciones activadas", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(),"Notificaciones desactivadas", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        final CheckBoxPreference recopilacionAutorizada = (CheckBoxPreference) findPreference("autorizacion_recopilacion");
        recopilacionAutorizada.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                final Boolean value = (Boolean) newValue;
                if (value) {
                    Toast.makeText(getContext(),"Se recopilaran datos", Toast.LENGTH_SHORT).show();
                }
                else {
                    //TODO borrar log
                    Toast.makeText(getContext(),"Datos recopilados eliminados", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        final Preference irDetallesRecopilados = findPreference("detalles_recopilacion");
        irDetallesRecopilados.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(@NonNull Preference preference) {
                navHost.navigate(R.id.busquedaFragment);
                return true;
            }
        });
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    public void setPreferencesFromResource(int root_preferences, String rootKey) {
    }
}
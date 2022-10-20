package com.mdgz.dam.labdam2022;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.databinding.FragmentLogBinding;

import java.util.ArrayList;

public class LogFragment extends Fragment {

    private FragmentLogBinding binding;

    public LogFragment() {
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
        binding = FragmentLogBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String fileName = "search_register.txt";
        ArrayList<String> logList = FileManager.readLogFile(fileName,requireContext());

        if (logList == null) {
            Toast.makeText(requireContext(),"No existe LogFile", Toast.LENGTH_SHORT).show();
            return;
        }

        RecyclerView recyclerLog = binding.recyclerLog;
        recyclerLog.setLayoutManager(new LinearLayoutManager(this.getContext()));

        int i = 0;
        for (String log : logList) {
            Log.i("Element #"+i+": ", log);
            i++;
        }

        AdapterLog adapterLog = new AdapterLog(logList);
        recyclerLog.setAdapter(adapterLog);

    }
}
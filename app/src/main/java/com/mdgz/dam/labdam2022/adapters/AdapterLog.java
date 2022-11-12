package com.mdgz.dam.labdam2022.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.mdgz.dam.labdam2022.R;

import java.util.ArrayList;

public class AdapterLog extends RecyclerView.Adapter<AdapterLog.ViewHolder> {

    private final ArrayList<String> logList;

    public AdapterLog(ArrayList<String> logList) {
        this.logList = logList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_item_list,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.asignarDatos(logList.get(position));
    }

    @Override
    public int getItemCount() {
        return logList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView timeStamp;
        TextView campo1;
        TextView campo2;
        TextView campo3;
        TextView campo4;
        TextView campo5;
        TextView campo6;
        TextView campo7;
        TextView campo8;
        TextView campo9;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeStamp = itemView.findViewById(R.id.timeStampId);
            campo1 = itemView.findViewById(R.id.logText1);
            campo2 = itemView.findViewById(R.id.logText2);
            campo3 = itemView.findViewById(R.id.logText3);
            campo4 = itemView.findViewById(R.id.logText4);
            campo5 = itemView.findViewById(R.id.logText5);
            campo6 = itemView.findViewById(R.id.logText6);
            campo7 = itemView.findViewById(R.id.logText7);
            campo8 = itemView.findViewById(R.id.logText8);
            campo9 = itemView.findViewById(R.id.logText9);
        }

        public void asignarDatos(String line) {
            //Se usan campos genericos porque sino se tendria que saber que campos estan completos
            // para asignar los datos a dichos campos
            for (String logItem : line.split(",")) {
                if (timeStamp.getText() == "") timeStamp.setText(logItem);
                else if (campo1.getText() == "") campo1.setText(logItem);
                else if (campo2.getText() == "") campo2.setText(logItem);
                else if (campo3.getText() == "") campo3.setText(logItem);
                else if (campo4.getText() == "") campo4.setText(logItem);
                else if (campo5.getText() == "") campo5.setText(logItem);
                else if (campo6.getText() == "") campo6.setText(logItem);
                else if (campo7.getText() == "") campo7.setText(logItem);
                else if (campo8.getText() == "") campo8.setText(logItem);
                else if (campo9.getText() == "") campo9.setText(logItem);
            }
        }
    }
}

package com.mdgz.dam.labdam2022;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


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

        for (String line: logList) { holder.asignarDatos(line); }




    }

    @Override
    public int getItemCount() {
        return 0;
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
            campo1 = itemView.findViewById(R.id.hotelCheckboxId);
            campo2 = itemView.findViewById(R.id.departamentoCheckboxId);
            campo3 = itemView.findViewById(R.id.cantidadPersonasId);
            campo4 = itemView.findViewById(R.id.wifiCheckBoxId);
            campo5 = itemView.findViewById(R.id.minimoPrecioId);
            campo6 = itemView.findViewById(R.id.maximoPrecioId);
            campo7 = itemView.findViewById(R.id.ciudadId);
            campo8 = itemView.findViewById(R.id.cantResultadosId);
            campo9 = itemView.findViewById(R.id.tiempoBusquedaId);

        }

        public void asignarDatos(String line) {
            String aux = "";
            int position = 0;

            //TODO uso campos genericos porque sino tendria que saber que campos estan completos
            // para asignar los datos a esos campos

            while(position < line.length()){

                aux = recorrerString(line,position);
                position += aux.length();
                if(timeStamp.getText() == "") timeStamp.setText(aux);
                else if (campo1.getText() == "") campo1.setText(aux);
                else if (campo2.getText() == "") campo2.setText(aux);
                else if (campo3.getText() == "") campo3.setText(aux);
                else if (campo4.getText() == "") campo4.setText(aux);
                else if (campo5.getText() == "") campo5.setText(aux);
                else if (campo6.getText() == "") campo6.setText(aux);
                else if (campo7.getText() == "") campo7.setText(aux);
                else if (campo8.getText() == "") campo8.setText(aux);
                else if (campo9.getText() == "") campo9.setText(aux);

            }

        }

        private String recorrerString(String text, int initialPosition){

            String res = "";
            char c = text.charAt(initialPosition);
            while(c != ' '){
                res += c;
                initialPosition++;
                c = text.charAt(initialPosition);
            }

            return res;
        }

    }
}

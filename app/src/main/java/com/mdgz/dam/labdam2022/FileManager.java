package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileManager {

    public static void saveLogFile(String registro, Context context){

        //Se escribe el archivo con los datos de las busquedas
        String fileName = "search_register.txt";
        try {
            FileOutputStream output = context.openFileOutput(fileName, Context.MODE_APPEND);
            output.write(registro.getBytes());
            output.write("\n".getBytes());
            output.flush();
            output.close();

        }   catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e){ e.printStackTrace(); }

    }

    public static ArrayList<String> readLogFile(String fileName, Context context){

        ArrayList<String> lines = new ArrayList<>();
        //Se lee el archivo
        FileInputStream input = null;
        try {
            input = context.openFileInput(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = new InputStreamReader(input, StandardCharsets.UTF_8);
        String line;
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            line = reader.readLine();
            lines.add(line);
            while (line != null) {
                line = reader.readLine();
                if(line != null) lines.add(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO borrar, mensaje por consola para ver el contenido del archivo
        for (String aLine:lines) {
            Log.i("msg: ",aLine);
        }

        return lines;

    }

    public static void deleteLogFile(String fileName, Context context){
        context.deleteFile(fileName);
    }

}

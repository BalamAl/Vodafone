/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telcel.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.telcel.remedyREST.Verificar_INC;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;

/**
 *
 * @author CONSULTOR
 */
public class Validar_INC {
    public static void main(String[] args)throws JSONException, CsvValidationException {
         String carpetaCSV = "C:\\Users\\CONSULTOR\\Downloads\\csv"; 

        File carpeta = new File(carpetaCSV);
        File[] archivosCSV = carpeta.listFiles();
        //verifica si carpeta de csv existe
        if (archivosCSV != null) {
            //itera para leer archivo x archivo
            for (File archivo : archivosCSV) {
                if (archivo.isFile() && archivo.getName().endsWith(".csv")) {
                    //llamada al metodo para leer el archivo
                    leerArchivoCSV(archivo);
                }
            }
        } else {
            System.out.println("No se encontraron archivos CSV en la carpeta.");
        }
    }
        //la exception de CSV es por la libreria
    private static void leerArchivoCSV(File archivo) throws CsvValidationException, JSONException {
       
        // crea un lector para el archivo CSV es
        try (CSVReader csvReader = new CSVReader(new FileReader(archivo))) {
            String[] fila;


            while ((fila = csvReader.readNext()) != null) {
                    String columna1 = fila[0];
                    String columna3 = fila[2];
                    System.out.println("Archivo: " + archivo.getName() +
                            ", Columna 1: " + columna1 + ", Columna 3: " + columna3);
                    String id= columna1;

                Conteo_Horas horas = new Conteo_Horas();
                horas.Conteo(columna3);
                if(horas.resultado){
                    System.out.println("8 horas trancurridas");
                    Verificar_INC ver = new Verificar_INC();
                    ver.verificar(id);
                }
                
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
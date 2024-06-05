/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telcel.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

/**
 *
 * @author CONSULTOR
 */
public class BorrarArchivo {
    public static String NombreArchivo="INC000023990477";
    public static String NombreCarpeta = "C:\\Users\\CONSULTOR\\Downloads\\csv";
    
 public static void main(String[] args) throws CsvValidationException, IOException {
        String nombreArchivo = "INC000023990477"; // Nombre del archivo a eliminar (sin extensión)
        String carpeta = "C:\\Users\\CONSULTOR\\Downloads\\csv"; // Ruta de la carpeta donde buscar el archivo
        eliminarArchivoCSV(carpeta, nombreArchivo);
    }

    public static void eliminarArchivoCSV(String carpeta, String nombreArchivo) throws IOException, CsvValidationException {
  String nombreArchivoCSV = nombreArchivo + ".csv"; // Agregar la extensión .csv al nombre del archivo
        File carpetaFile = new File(carpeta); // Crear el objeto File para la carpeta

        if (carpetaFile.exists() && carpetaFile.isDirectory()) {
            File[] archivos = carpetaFile.listFiles(); // Listar archivos en la carpeta

            for (File archivo : archivos) {
                if (archivo.getName().equals(nombreArchivoCSV)) { // Verificar si el archivo coincide con el nombre y la extensión
                    if (archivo.delete()) { // Intentar eliminar el archivo
                        System.out.println("El archivo " + nombreArchivoCSV + " ha sido eliminado exitosamente.");
                    } else {
                        System.out.println("No se pudo eliminar el archivo " + nombreArchivoCSV + ".");
                    }
                    break; // Detener el bucle después de eliminar el archivo
                }
            }
        } else {
            System.out.println("La carpeta especificada no existe o no es una carpeta válida.");
        }
    }
}
     


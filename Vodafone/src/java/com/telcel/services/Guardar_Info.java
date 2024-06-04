/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telcel.services;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.Paths;
import java.io.IOException;
/**
 *
 * @author CONSULTOR
 */
public class Guardar_Info {
    public static void verificar(String ruta) {
        File directorio = new File(ruta);
        File[] listaDeArchivos = directorio.listFiles();

        if (listaDeArchivos != null && listaDeArchivos.length > 0) {
            System.out.println("Fechas archivos:");

            for (File archivo : listaDeArchivos) {
                try {
                    BasicFileAttributes attrs = Files.readAttributes(Paths.get(archivo.getAbsolutePath()), BasicFileAttributes.class);
                    System.out.println("Archivo: " + archivo.getName() + " - Fecha de creación: " + attrs.creationTime());
                } catch (IOException e) {
                    System.out.println("Hubo un error al obtener la fecha de creación del archivo: " + archivo.getName());
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("El directorio está vacío o no se pudo leer.");
        }
    }
}

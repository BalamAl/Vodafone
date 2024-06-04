/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telcel.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author CONSULTOR
 */
public class Fecha_actual {
    String fechaHoraStr = "10/01/2024 10:15";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime fechaHoraEntrada = LocalDateTime.parse(fechaHoraStr, formatter);
        
        // Fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now(); 
}

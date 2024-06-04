/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telcel.services;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author CONSULTOR
 */
public class Conteo_Horas {
     private static final LocalTime INICIO_JORNADA = LocalTime.of(8, 0);
    private static final LocalTime FIN_JORNADA = LocalTime.of(17, 0);

    public static void main(String[] args) {
        String fechaStr = "03/06/2024 10:15";
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        boolean resultado = haPasadoMasDe8HorasLaborables(fechaInicio, LocalDateTime.now());
        System.out.println("¿Han pasado más de 8 horas laborables? " + resultado);
    }

    public static boolean haPasadoMasDe8HorasLaborables(LocalDateTime inicio, LocalDateTime fin) {
        LocalDateTime actual = inicio;

        long horasLaborables = 0;

        while (actual.isBefore(fin)) {
            if (esDiaLaborable(actual.toLocalDate()) && estaDentroDeJornadaLaboral(actual.toLocalTime())) {
                horasLaborables++;
            }
            actual = actual.plusHours(1);
        }

        return horasLaborables > 8;
    }

    private static boolean esDiaLaborable(LocalDate date) {
        DayOfWeek dia = date.getDayOfWeek();
        return dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY;
    }

    private static boolean estaDentroDeJornadaLaboral(LocalTime time) {
        return !time.isBefore(INICIO_JORNADA) && !time.isAfter(FIN_JORNADA);
    }

}

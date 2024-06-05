/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.telcel.webService;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.opencsv.CSVWriter;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author CONSULTOR
 */
@WebService(serviceName = "Vodafone")
public class Vodafone {

    /**
     * This is a sample web service operation
     */
   @WebMethod(operationName = "guardarDatos")
    public String guardarDatos(@WebParam(name = "Id_Request") String id_Request, @WebParam(name = "Id_INC") String id_INC, @WebParam(name = "Fecha_Modificacion") String fecha) {
        
        String incidente1 = id_Request;
        String incidente2 = id_INC;
        
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse(fecha, inputFormatter);
        String formattedDate = dateTime.format(outputFormatter);
        
        String filePath = "/home/remedy/Vodafone/INC_Pendientes/"+incidente2+".csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Encabezados
            /*String[] header = {"Incident Number 1", "Incident Number 2", "Date"};
            writer.writeNext(header);
*/
            // Datos
            String[] data = {incidente1, incidente2, formattedDate};
            writer.writeNext(data);

            System.out.println("Datos guardados en incidentes.csv exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo CSV: " + e);
        }
        
       return null;
        
    }


}

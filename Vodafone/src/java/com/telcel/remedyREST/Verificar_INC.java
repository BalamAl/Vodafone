/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telcel.remedyREST;
import com.telcel.services.Registro_logs;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author CONSULTOR
 */
public class Verificar_INC {
    public void verificar(String inc) throws JSONException{
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5aVQxanRRbXdmWFM1bEw4Z09sSmw3ajdRS3hjdkN5Q3NWUjE3NVA1V0F5enZHTTlYdlQ1eFNHZVFlbVlFWDU0Uk9yVWE2RGhCNWFXNGpVanVvSUFmY2M1Q3ZcLzNQV3ZTbFVYY2FTcFhjdEtRTUxsOE9Rd3Jodz09IiwibmJmIjoxNzE3NjAzMDQ4LCJpc3MiOiJpdGhocHJlbTFkYXIwMyIsImV4cCI6MTcxNzYwNjc2OCwiX2NhY2hlSWQiOjEwODAxMDYsImlhdCI6MTcxNzYwMzE2OCwianRpIjoiSURHQUE1VjBIM1VJM0FTNE1IREVTM0w3SzRFUldDIn0.7335BLg1lq_dlZoPOr0vcKnEynIjFfxVUEXRhRJ2GEE";
        try {
                OkHttpClient client = new OkHttpClient().newBuilder().build();
                Request request = new Request.Builder()
                        .url("http://100.127.4.48:8008/api/arsys/v1/entry/HPD:Help Desk/?q='1'=\""+inc+"\"and'7'=\"Pending\"&fields=values(1,6)")
                        .method("GET", null)
                        .addHeader("Authorization", "AR-JWT " + token)
                        .build();
                Response response = client.newCall(request).execute();
                
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();

                    // Convertir la cadena JSON a un objeto JSONObject
                    JSONObject jsonResponse = new JSONObject(responseBody);

                    // Obtener la matriz de entradas
                    JSONArray entriesArray = jsonResponse.getJSONArray("entries");

                    // Verificar si la matriz de entradas no está vacía
                    if (entriesArray.length() > 0) {
                        // Obtener el primer elemento de la matriz
                        JSONObject entryObject = entriesArray.getJSONObject(0);

                        // Obtener los valores del objeto de entrada
                        JSONObject valuesObject = entryObject.getJSONObject("values");

                        // Obtener el valor de "Last Modified Date"
                        String lastModifiedDateStr = valuesObject.getString("Last Modified Date");

                        // Imprimir el valor de "Last Modified Date"
                        
                        
                        
                        // Parsear la fecha de Last Modified Date a LocalDateTime
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                        LocalDateTime lastModifiedDate = LocalDateTime.parse(lastModifiedDateStr, formatter);
                        System.out.println("Last Modified Date: " + lastModifiedDate);
                        // Obtener la fecha y hora actual
                        LocalDateTime now = LocalDateTime.now();

                        // Comparar las fechas
                        if (lastModifiedDate.compareTo(now) > 0) {
                            System.out.println("La fecha de Last Modified Date es posterior a la fecha actual.");
                        } else if (lastModifiedDate.compareTo(now) < 0) {
                            System.out.println("La fecha de Last Modified Date es anterior a la fecha actual.");
                        } else {
                            System.out.println("La fecha de Last Modified Date es igual a la fecha actual.");
                        }
                    } else {
                        // No se encontraron entradas
                        System.out.println("No se encontraron entradas");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al realizar la solicitud GET "+ e);
            }
    }    

}

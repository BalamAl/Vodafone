/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telcel.remedyREST;
import com.telcel.services.Registro_logs;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
    public static void main(String[] args) throws JSONException {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSVWppSytXMUlCS3hEZlhxQmNzTVVKd09tQkIxTXBpRGJOZHpCU1N5MHNTSVJNVVJUTkFqSlp5RWtUVHIxOFBMZUd6Mm40Y0VCS0ROSE1lbUhIS1NHWFBuXC9LMEF6dnQyYTU1Snl2bTNMNk5HMGlwa1ViSlpqQT09IiwibmJmIjoxNzE3NTM1MzMzLCJpc3MiOiJpdGhocHJlbTFkYXIwMyIsImV4cCI6MTcxNzUzOTA1MywiX2NhY2hlSWQiOjEwNjUzMzMsImlhdCI6MTcxNzUzNTQ1MywianRpIjoiSURHQUE1VjBIM1VJM0FTNEtRVUZTM0tCMTVEOVlMIn0.XlDjEKsCiakC93drYosZV3WIKUzqKLrrdhppt1X1JEk";
        try {
                OkHttpClient client = new OkHttpClient().newBuilder().build();
                Request request = new Request.Builder()
                        .url("http://100.127.4.48:8008/api/arsys/v1/entry/HPD:Help Desk/?q='1000000161'=\"INC000023984199\"and'7'=\"Pending\"&fields=values(1)")
                        .method("GET", null)
                        .addHeader("Authorization", "AR-JWT " + token)
                        .build();
                Response response = client.newCall(request).execute();
                
                if (response.isSuccessful()) {
                    // Convertir la respuesta JSON a un objeto JSONObject
                    String responseBody = response.body().string();
                    JSONObject jsonResponse = new JSONObject(responseBody);

                    // Obtener la matriz de entradas
                    JSONArray entriesArray = jsonResponse.getJSONArray("entries");

                    // Verificar si la matriz de entradas está vacía
                    if (entriesArray.length() == 0) {
                        // No se encontraron resultados
                        System.out.println("No se encontraron resultados");

                    } else {
                        // Se encontraron resultados
                        System.out.println("Se encontraron resultados");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al realizar la solicitud GET "+ e);
            }
    }
}

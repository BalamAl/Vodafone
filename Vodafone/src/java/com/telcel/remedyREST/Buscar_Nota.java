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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author CONSULTOR
 */
public class Buscar_Nota {
    public static void main(String[] args) throws JSONException{
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSVWppSytXMUlCS3hEZlhxQmNzTVVKd09tQkIxTXBpRGJOZHpCU1N5MHNTSVJNVVJUTkFqSlp5RWtUVHIxOFBMZUd6Mm40Y0VCS0ROSE1lbUhIS1NHWFBuXC9LMEF6dnQyYTU1Snl2bTNMNk5HMGlwa1ViSlpqQT09IiwibmJmIjoxNzE3NTM1MzMzLCJpc3MiOiJpdGhocHJlbTFkYXIwMyIsImV4cCI6MTcxNzUzOTA1MywiX2NhY2hlSWQiOjEwNjUzMzMsImlhdCI6MTcxNzUzNTQ1MywianRpIjoiSURHQUE1VjBIM1VJM0FTNEtRVUZTM0tCMTVEOVlMIn0.XlDjEKsCiakC93drYosZV3WIKUzqKLrrdhppt1X1JEk";
        try {
                OkHttpClient client = new OkHttpClient().newBuilder().build();
                Request request = new Request.Builder()
                        .url("http://100.127.4.48:8008/api/arsys/v1/entry/HPD:WorkLog/?q='1000000161'=\"INC000023984196\"&fields=values(1000000157)")
                        .method("GET", null)
                        .addHeader("Authorization", "AR-JWT " + token)
                        .build();
                Response response = client.newCall(request).execute();
                
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                JSONArray entries = jsonResponse.getJSONArray("entries");

                if (entries.length() > 0) {
                    JSONObject firstEntry = entries.getJSONObject(0);
                    JSONObject values = firstEntry.getJSONObject("values");

                    String dateString = values.getString("Work Log Submit Date");

                     DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                    LocalDateTime dateTime = LocalDateTime.parse(dateString, inputFormatter);
                    String formattedDate = dateTime.format(outputFormatter);

                    System.out.println("Formatted Date: " + formattedDate);
                } else {
                    System.out.println("No entries found.");
                }
            }
            } catch (IOException e) {
                System.out.println("Error al realizar la solicitud GET "+ e);
            }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telcel.config;
import com.telcel.services.Registro_logs;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
/**
 *
 * @author CONSULTOR
 */
public class AmbienteDeploy {
     public String url;
    public String user;
    public String pass;
    public String port;

    private Properties prop = new Properties();

    public AmbienteDeploy() {
        cargarPropiedades();
    }

    private void cargarPropiedades() {
        try (InputStream input = AmbienteDeploy.class.getClassLoader().getResourceAsStream("Apagado_servicios_ant.properties")) {
            if (input == null) {
                throw new IOException("No se pudo cargar el archivo de propiedades");
            }
            // Carga el archivo de propiedades
            prop.load(input);
            user = prop.getProperty("REMEDY_USER");
            port = prop.getProperty("ARS_PORT");
        } catch (IOException ex) {
            Registro_logs.logException("Error al cargar el archivo de propiedades", ex);
        }
    }

    public void peticionIp() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ip = localHost.getHostAddress();

            // Determina el entorno (QA, PROD o DEV) basado en la dirección IP de cada ambiente
            if (ip.equals(prop.getProperty("QA_RC_IP"))) {
                url = prop.getProperty("QA_ARS_IP");
                pass = prop.getProperty("DEV_REMEDY_PASS");
            } else if (ip.equals(prop.getProperty("PROD_RC_IP"))) {
                url = prop.getProperty("PROD_ARS_IP");
                pass = prop.getProperty("PROD_REMEDY_PASS");
            } else {
                url = prop.getProperty("DEV_ARS_IP");
                pass = prop.getProperty("DEV_REMEDY_PASS");
            }

        } catch (UnknownHostException e) {
            Registro_logs.logException("Error al obtener la IP del Host", e);
        }
    }
}


import com.telcel.webService.Vodafone;
import org.json.JSONException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author CONSULTOR
 */
public class Test {
    public static void main(String[] args) throws JSONException {
        String idINC = "INC000023990476";
        String fecha = "2024-06-04T22:16:42.000+0000";
        Vodafone von = new Vodafone();
        von.guardarDatos(idINC, idINC,fecha);
    }
}

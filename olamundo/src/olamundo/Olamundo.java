/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olamundo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zenner
 */
public class Olamundo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String hora = getDateTime();
        System.out.println(hora);
    }

public static String getDateTime() { 
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    return dateFormat.format(date).toString(); 
}
}

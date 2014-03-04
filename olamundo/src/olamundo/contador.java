/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olamundo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zenner
 */
public class contador extends Thread{
    public void run(){
        int x,i=0;
        long tempoInicial = System.currentTimeMillis();
        for (x = 0; x < 1000000; x++) {
            System.out.println(String.valueOf(x));
        };
        long tempoFinal = System.currentTimeMillis();
        System.out.println("tempo total:" + String.valueOf(tempoFinal - tempoInicial));

    }
    
}

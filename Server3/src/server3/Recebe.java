/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server3;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zenner
 */
public class Recebe extends Thread {

    public InputStream cliente;
    public Conexao con;
    public String[] cmd = new String[10];
    public int nCmd = 0, cont = 0;

    public Recebe(InputStream in, Conexao con) {
        this.cliente = in;
        this.con = con;
    }

    public void run() {
        Scanner s = new Scanner(this.cliente);
        while (s.hasNextLine() && !con.socket.isClosed()) {
            String m = s.nextLine();
            System.out.println(con.getHostAddress() + ":" + m);
            con.trat.tratar_valores(m);
        }
        try {
              con.mc.mudar_status(con.trat.idUser, 0);
        } catch (Exception e) {
        }
      
        System.out.println("saiu");
        con.close();
    }

    public String receber() {
        Scanner s = new Scanner(this.cliente);
        while (s.hasNextLine()) {
            String m = s.nextLine();
        }
        return "";
    }
    public void close(){
                try {
                    this.interrupt();
            this.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(Envia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

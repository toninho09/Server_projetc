/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server3;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zenner
 */
public class Conexao extends Thread {

    public Socket socket;
    public PrintStream out;
    public Envia env;
    public Recebe rec;
    public Tratamento trat;
    public MysqlComand mc;

    public Conexao(Socket s, MysqlComand mc) {
        this.socket = s;
        this.mc = mc;
    }

    public void run() {
        System.out.println(Server3.pegarHora() + ":Conectou :" + this.getHostAddress());
        try {
            rec = new Recebe(socket.getInputStream(), this);
            rec.start();
            out = new PrintStream(socket.getOutputStream());
            env = new Envia(out, this);
            trat = new Tratamento(this, mc);
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public String getHostAddress() {
        return socket.getInetAddress().getHostAddress();
    }
}

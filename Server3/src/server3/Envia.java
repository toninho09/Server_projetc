/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server3;

import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Zenner
 */
public class Envia extends Thread {

    public PrintStream cliente;
    public Conexao con;
    public String men;

    public Envia(PrintStream out, Conexao con) {
        this.cliente = out;
        this.con = con;
    }

    public void run(String mensagem) {
        mensagem(mensagem);
    }

    public void mensagem(String mensagem) {
        cliente.println(mensagem);
    }
}
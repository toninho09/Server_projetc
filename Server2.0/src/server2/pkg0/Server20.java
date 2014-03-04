/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server2.pkg0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zenner
 */
public class Server20 {

    /**
     * @param args the command line arguments
     */
    static int i = 0;

    public static void main(String[] args) {


        ServerSocket socket = null;
        try {
            socket = new ServerSocket(1234);
            while (!socket.isClosed()) {
                new Conexao(socket.accept()).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server20.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void teste() {
    }
}

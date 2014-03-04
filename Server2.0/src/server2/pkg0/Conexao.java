/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server2.pkg0;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zenner
 */
public class Conexao extends Thread {

    private static Socket connectionSocket;
    private String clientCmd, cont;

    Conexao(Socket socket) {
        try {
            this.connectionSocket = socket;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        try {
            System.out.println("abriu conexao ");
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));//variavel de entrado do socket
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());//variavel de saida do socket
         //   clientCmd = inFromClient.readLine();
           // System.out.println(clientCmd);
                    Scanner s = new Scanner(this.connectionSocket.getInputStream());
        System.out.println("recebeu");
         while (s.hasNextLine()) {
             System.out.println(s.nextLine()+"1");
         }
            outToClient.writeBytes("Enviado");
            //sleep(1);
            inFromClient.close();
            outToClient.flush();
            outToClient.close();
            connectionSocket.close();
            System.out.println("fechado ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }

    public boolean isClosed() {
        return connectionSocket.isClosed();

    }
}

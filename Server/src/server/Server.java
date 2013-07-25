/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Zenner
 */
public class Server {

  public static void main(String[] args) {

       String clientSentence;
       String capitalized;
  
       try {
 
           // Cria um SocketServer (Socket característico de um servidor)
           ServerSocket socket = new ServerSocket(5432);
   
           while(true) {    
    
               /* Cria um objeto Socket, mas passando informações características de um servidor,
                *no qual somente abre uma porta e aguarda a conexão de um cliente 
                */
               Socket connectionSocket = socket.accept();
    
               // Cria uma buffer que irá armazenar as informações enviadas pelo cliente
               BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
    
               // Cria uma stream de sáida para retorno das informações ao cliente
               DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
    
               // Faz a leitura das informações enviadas pelo cliente as amazenam na variável "clientSentence"
               clientSentence = inFromClient.readLine();
    
               /* Faz uma modificação na String enviada pelo cliente, simulando um processamento em "back-end"
                * antes de retorná-la ao cliente
                */
               capitalized = clientSentence.toUpperCase() + "\n";
    
               // Imprime a a String modificada no console do servidor
               System.out.println(capitalized);
    
               // Retorna as informações modificadas, ao cliente
               outToClient.writeBytes(capitalized);  
               
                              // Faz a leitura das informações enviadas pelo cliente as amazenam na variável "clientSentence"
               clientSentence = inFromClient.readLine();
    
               /* Faz uma modificação na String enviada pelo cliente, simulando um processamento em "back-end"
                * antes de retorná-la ao cliente
                */
               capitalized = clientSentence.toUpperCase() + "\n";
    
               // Imprime a a String modificada no console do servidor
               System.out.println(capitalized);
    
               // Retorna as informações modificadas, ao cliente
               outToClient.writeBytes(capitalized);  
            //   socket.close();
           } 
      
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }  
   }
}

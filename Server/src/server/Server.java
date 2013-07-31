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
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zenner
 */
public class Server {

    private static Socket[] connectionSocket;
    private static ResultSet rs;
    private static Thread[] conect;

    public static void main(String[] args) {
        int x = 0, number;
        String clientCmd,Campo_key,Campo_de,Campo_para,Campo_mensagem;
        String serverName = "localhost", mydatabase = "mensageiro", username = "root", password = "290891", port = "3306";
        Conexao_mysql msql = new Conexao_mysql();
        if (msql.configurar(serverName, mydatabase, username, password, port) == true) {
            System.out.println("conexao com mysql configurada");
        };
        if (msql.conectar() == true) {
            System.out.println("Conectado ao mysql");

        };
        try {

            // Cria um SocketServer (Socket característico de um servidor)
            ServerSocket socket = new ServerSocket(5432);
            for (int i=0 ;i<10;i++){
             conect[i] = new Thread(new Runnable() {

                @Override
                public void run() {
              
                }
            });
            }
           
            while (true) {

                connectionSocket[1] = socket.accept();  //abre o socket
                System.out.println("abriu conexao");

                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket[1].getInputStream()));//variavel de entrado do socket
                DataOutputStream outToClient = new DataOutputStream(connectionSocket[1].getOutputStream());//variavel de saida do socket

                clientCmd = inFromClient.readLine();
                System.out.println(clientCmd);
                String[] s = clientCmd.split(";");
                switch (s[0]) {
                    case "env": { // recebe mensagem
                        msql.escrever_mensagem(s[1], s[2], s[3]);
                        outToClient.writeBytes("enviado");
                    }
                    break;
                    case "ver": {//verifica se tem mensagem
                        if (msql.pesquisar_existe("SELECT * FROM Mensageiro.Mensagem where lida =0 and para = '" + s[1] + "';") == true) {
                         rs= msql.pesquisa_tabela("SELECT * FROM Mensageiro.Mensagem where lida =0 and para = '" + s[1] + "';");
                         Campo_key = rs.getString("KEY");
                         Campo_de = rs.getString("De");
                         Campo_para = rs.getString("Para");
                         Campo_mensagem = rs.getString("Mensagem");
                         outToClient.writeBytes("new_m;"+Campo_de+";"+Campo_para+";"+Campo_mensagem);
                         msql.Marcar_lida(Campo_key);
                        }else{
                         outToClient.writeBytes("no_new");
                        };
                   
                    }
                    break;
                    default: {
                        try {
                            System.out.println(s[0]);
                            outToClient.writeBytes("erro");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                outToClient.writeBytes("Enviado");
                connectionSocket[1].close();
                System.out.println("fechou conexao");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void funcao(String[] s, DataOutputStream outToClient, Conexao_mysql msql) {
        switch (s[0]) {
            case "env": {
                msql.escrever_mensagem(s[1], s[2], s[3]);
            }
            ;
            break;
            default: {
                try {
                    outToClient.writeBytes("erro");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            ;
        }
    }
    
}

/*198.23.136.132
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zenner
 */
public class conexao extends Thread {

    private static Connection connection;
    private static Statement sql;
    private static int x;
    private static ResultSet rs;
    private static Socket s;
    private static PrintStream ps;
    private BufferedReader inFromClient;

    public void run() {
        int x = 0;

        try {
            int i, j;
            s = new Socket("198.23.136.132", 1234);
            System.out.println("conectou");
            ps = new PrintStream(s.getOutputStream());
            inFromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));//variavel de entrado do socket
            //login();
            sleep(10000);
            //verificar_pedidos_amizade();
           // att_amigo(); 
            //att_amigo(); 
            //aceitar_pedido("test8");
            //att_amigo();
            //aceitar_pedido("test8");
            //verificar_online("admin");
            //del_amigo("user");
            //verificar_mensagem();
         //   pegar_mensagem();
            // enviar_mensagem("user", "testando se manda mensagem1");
            s.close();
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
            x++;
        }
        System.out.println("erros:" + String.valueOf(x));


    }

    public void login() {

        try {
            ps.println("//login");
            ps.println("admin");
            ps.println("admin");
            System.out.println(inFromClient.readLine());
        } catch (IOException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verificar_mensagem() {

        try {
            ps.println("//verificarMensagem");
            System.out.println(inFromClient.readLine());
        } catch (IOException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pegar_mensagem() {
        String cmd;
        try {
            ps.println("//acceptMensagem");
            cmd = inFromClient.readLine();
            while (cmd.equals("//envMensagem")) {
                System.out.println(inFromClient.readLine());
                System.out.println(inFromClient.readLine());
                System.out.println(inFromClient.readLine());
                cmd = inFromClient.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviar_mensagem(String para, String mensagem) {

        try {
            ps.println("//Sendmensagem");
            ps.println(para);
            ps.println(mensagem);
            System.out.println(inFromClient.readLine());
        } catch (IOException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add_amigo(String amigoID) {

        try {
            ps.println("//adicionarAmigo");
            ps.println(amigoID);
            System.out.println(inFromClient.readLine());
        } catch (IOException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void att_amigo() {
        String cmd;
        try {
            ps.println("//carregarAmigo");
            cmd = inFromClient.readLine();
            while (cmd.equals("//envAmigo")) {
                System.out.println(inFromClient.readLine());
                cmd = inFromClient.readLine();
                //   System.out.println(cmd);
            }

        } catch (IOException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verificar_online(String login) {

        try {
            ps.println("//checkOnline");
            ps.println(login);
            System.out.println(inFromClient.readLine());
        } catch (IOException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void del_amigo(String login) {

        try {
            ps.println("//delAmigo");
            ps.println(login);
            System.out.println(inFromClient.readLine());
        } catch (IOException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verificar_pedidos_amizade() {

        String cmd;
        try {
            ps.println("//checkAmigo");
            cmd = inFromClient.readLine();
            System.out.println(cmd);
            if (cmd.equals("//Sconvite")) {
                cmd = inFromClient.readLine();
                    System.out.println(cmd);
                while (!cmd.equals("//endconvite")) {
                    System.out.println(inFromClient.readLine());
                    cmd = inFromClient.readLine();
                    System.out.println(cmd);

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void aceitar_pedido(String login) {

        try {
            ps.println("//aceitarAmigo");
            ps.println(login);
            System.out.println(inFromClient.readLine());
        } catch (IOException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

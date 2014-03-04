/*198.23.136.132
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
public class Cliente {

    private static Connection connection;
    private static Statement sql;
    private  int x;
    private static ResultSet rs;
    private static Socket s;
    private static PrintStream ps;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int i;
        for (i=1 ; i<=200;i++){
        new conexao().start();
        }
    }
}

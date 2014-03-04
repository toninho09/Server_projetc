package server3;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server3 {

    public static void main(String[] args) {
        MysqlComand mc = new MysqlComand();
        mc.configurar("198.23.136.132", "Mensageiro", "root", "aeiouaoA12", "3306");
        while (!mc.conectar()) {
            System.out.println(pegarHora() + ":Erro ao tentar conectar na base de dados");
        }
        System.out.println(pegarHora() + ":Conectado na base de dados");
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(1234);
            while (!socket.isClosed()) {
                new Conexao(socket.accept()).start();
                System.out.println( Thread.activeCount());
                System.gc();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String pegarHora() {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(new Date());
    }
}

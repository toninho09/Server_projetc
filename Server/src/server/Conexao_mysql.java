package server;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zenner
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao_mysql {

    private String serverName, mydatabase, url, username, password;
    private Connection connection;
    private Statement sql;

    public boolean configurar(String servername, String database, String username, String password, String port) {
        boolean retorno = false;
        try {
            setMydatabase(database);
            setPassword(password);
            setServerName(servername + ":" + port);
            setUsername(username);
            retorno = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }

    public boolean conectar() {
        boolean retorno = false;
        setUrl("jdbc:mysql://" + getServerName() + "/" + getMydatabase());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            sql = connection.createStatement();
            retorno = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }

    /**
     * @return the serverName
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @param serverName the serverName to set
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * @return the mydatabase
     */
    public String getMydatabase() {
        return mydatabase;
    }

    /**
     * @param mydatabase the mydatabase to set
     */
    public void setMydatabase(String mydatabase) {
        this.mydatabase = mydatabase;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

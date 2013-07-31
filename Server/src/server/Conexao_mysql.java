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
    private ResultSet rs;

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
    public boolean escrever_mensagem(String numero_de,String numero_para,String Mensagem){
         boolean retorno = false;
        try {
            retorno = sql.execute("INSERT INTO `Mensageiro`.`Mensagem` (`De`, `Para`, `Mensagem`, `Lida`) VALUES ('"+numero_de+"','"+numero_para+"','"+Mensagem+"', 0);");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    return false;
    }
    
    public ResultSet pesquisa_tabela(String cod_sql){
        try {
            rs = sql.executeQuery(cod_sql);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
    
    public boolean pesquisar_existe(String cod_sql){
        boolean retorno =false;
        
        try {
            retorno =sql.execute(cod_sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }
    public boolean Marcar_lida(String Key){
        boolean retorno=false;
        try {
            sql.execute("UPDATE `Mensageiro`.`Mensagem` SET `Lida`=1 WHERE `Key`='"+Key+"';");
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

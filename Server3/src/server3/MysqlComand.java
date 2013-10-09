/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zenner
 */
public class MysqlComand extends Thread {

    public MysqlComand() {
    }
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

    public boolean escrever_mensagem(String numero_de, String numero_para, String Mensagem) {
        boolean retorno = false;
        try {
            retorno = sql.execute("INSERT INTO `Mensageiro`.`Mensagem` (`to`, `from`, `mensagem`, `date`,`view`) VALUES ('" + numero_para + "', '" + numero_de + "', '" + Mensagem + "','" + getDateTime() + "',0);");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int pegar_quantidade(String sql) {
        int retorno = 0;
        ResultSet rs = pesquisa_tabela(sql);
        try {
            while (rs.next()) {
                retorno++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlComand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean verificar_existe(String sql, int numero) {
        boolean resultado = false;
        int i;
        ResultSet rs = pesquisa_tabela(sql);
        try {
            resultado = rs.first();
            for (i = 1; i < numero; i++) {
                resultado = rs.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlComand.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }
    public String pegar_Valor(String sql, int numero, String campo) {
        String retorno = null;
        ResultSet rs = pesquisa_tabela(sql);
        int i;
        try {
            rs.first();
            for (i = 1; i < numero; i++) {
                rs.next();
            }
            retorno = rs.getString(campo);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlComand.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public ResultSet pesquisa_tabela(String cod_sql) {
        try {
            rs = sql.executeQuery(cod_sql);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public boolean pesquisar_existe(String cod_sql) {
        boolean retorno = false;

        try {
            retorno = sql.executeQuery(cod_sql).first();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }

    public boolean marcar_lida(String Key) {
        boolean retorno = false;
        try {
            sql.execute("UPDATE `Mensageiro`.`Mensagem` SET `view`=1 WHERE `idMensagem`='"+Key+"';");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }

    public boolean verificar_login(String name, String pass) {
        return pesquisar_existe("Select * from Mensageiro.User where userName = '" + name + "' and userPass ='" + pass + "';");
    }

    public boolean verificar_login_create(String name) {
        return pesquisar_existe("Select * from Mensageiro.User where userName = '" + name + "';");
    }

    public boolean crear_user(String name, String pass) {
        boolean retorno = false;
        try {
            if (!verificar_login_create(name)) {
                sql.execute("INSERT INTO `Mensageiro`.`User` (`userName`, `userPass`) VALUES ('" + name + "', '" + pass + "');");
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlComand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date).toString();
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

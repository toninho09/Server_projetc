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
            String hora = getDateTime();
            sql.execute("INSERT INTO `Mensageiro`.`Mensagem` (`to`, `from`, `mensagem`, `date`,`view`) VALUES ('" + numero_para + "', '" + numero_de + "', '" + Mensagem + "','" + hora + "',0);");
            retorno = pesquisar_existe("SELECT * FROM Mensageiro.Mensagem where Mensagem.from = '" + numero_de + "'and Mensagem.to = '" + numero_para + "'and Mensagem.date = '" + hora + "'and Mensagem.view = '" + Mensagem + "' and Mensagem.view = 0 ;");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;
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
    public boolean verificar_online(String login){
        boolean retorno = false;
        retorno = verificar_existe("SELECT * FROM Mensageiro.User where userName = '"+login+"' and status = 1;", 1);
        return retorno;
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
            sql.execute("UPDATE `Mensageiro`.`Mensagem` SET `view`=1 WHERE `idMensagem`='" + Key + "';");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }

    public boolean add_amigo(String login, String loginAmigo) {
        boolean retorno = false;
        String user, userAmigo, cmd, cmd2;
        if (verificar_login_used(login)) {
            cmd = "Select * from Mensageiro.User where userName = '" + login + "';";
            user = pegar_Valor(cmd, 1, "idUser");
            if (verificar_login_used(loginAmigo)) {
                cmd = "Select * from Mensageiro.User where userName = '" + loginAmigo + "';";
                userAmigo = pegar_Valor(cmd, 1, "idUser");
                cmd = "Select * from Mensageiro.Amigo where idAmigo1 ='" + user + "' and idAmigo2 = '" + userAmigo + "'";
                cmd2 = "Select * from Mensageiro.Amigo where idAmigo2 ='" + user + "' and idAmigo1 = '" + userAmigo + "'";
                if (!verificar_existe(cmd, 1) && !verificar_existe(cmd2, 1)) {
                    cmd = "INSERT INTO `Mensageiro`.`Amigo` (`idAmigo1`, `idAmigo2`, `Aceito1`, `Aceito2`) VALUES (" + user + ", " + userAmigo + ", 1, 0)";
                    try {
                        sql.execute(cmd);
                    } catch (SQLException ex) {
                        Logger.getLogger(MysqlComand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cmd = "SELECT * FROM Mensageiro.Amigo where idAmigo1 = " + user + " and idAmigo2=" + userAmigo + " ;";
                    retorno = pesquisar_existe(cmd);
                }
            }
        };
        return retorno;
    }

    public boolean verificar_login(String name, String pass) {
        return pesquisar_existe("Select * from Mensageiro.User where userName = '" + name + "' and userPass ='" + pass + "';");
    }

    public boolean verificar_login_used(String name) {
        return pesquisar_existe("Select * from Mensageiro.User where userName = '" + name + "';");
    }

    public boolean verificar_email_used(String email) {
        return pesquisar_existe("Select * from Mensageiro.User where email = '" + email + "';");
    }

    public boolean crear_user(String name, String pass, String email) {
        boolean retorno = false;
        try {
            if (!verificar_login_used(name) && !verificar_email_used(email)) {
                sql.execute("INSERT INTO `Mensageiro`.`User` (`userName`, `userPass`,`email`) VALUES ('" + name + "', '" + pass + "', '" + email + "');");
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlComand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean mudar_status(String idUser, int status) {
        boolean retorno = false;
        try {
            retorno = sql.execute("UPDATE `Mensageiro`.`User` SET `status`='" + status + "' WHERE `idUser`='" + idUser + "';");
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

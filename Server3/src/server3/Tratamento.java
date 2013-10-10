/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server3;

import com.sun.jndi.ldap.PersistentSearchControl;

/**
 *
 * @author Zenner
 */
public class Tratamento extends Thread {

    public String login, pass, idUser;
    private Conexao con;
    private MysqlComand mc;
    public String[] cmd = new String[10];
    public int nCmd = 0, cont = 0;

    public Tratamento(Conexao con, MysqlComand mc) {
        this.con = con;
        this.mc = mc;

    }

    public void run() {
    }

    public void tratar_valores(String comando) {
        String m = comando;
        if (m.equals("//login")) {//funcionando 
            nCmd = 1;
        } else if (m.equals("//createlogin")) {// funcionando
            nCmd = 2;
        } else if (m.equals("//sendmensagem")) { // funcionando
            nCmd = 3;
        } else if (m.equals("//acceptMensagem")) { // funcionando
            nCmd = 4;
        } else if (m.equals("//verificarMensagem")) { // funcionando
            nCmd = 5;
        } else if (m.equals("//carregarAmigo")) { // funcionando
            nCmd = 6;
        } else if (m.equals("//adicionarAmigo")) { // funcionando
            nCmd = 7;
        } else if (m.equals("//delAmigo")) {
            nCmd = 8;
        } else if (m.equals("//checkAmigo")) {
            nCmd = 9;
        } else if (m.equals("//checkOnline")) { // funcionando
            nCmd = 10;
        }

        switch (nCmd) {
            case 1: {
                cont++;
                cmd[cont] = m;
                if (cont == 3) {
                    this.tratar(cmd);
                    cont = 0;
                    nCmd = 0;

                }
            }
            break;
            case 2: {
                cont++;
                cmd[cont] = m;
                if (cont == 3) {
                    this.tratar(cmd);
                    cont = 0;
                    nCmd = 0;
                }
            }
            break;
            case 3: {
                cont++;
                cmd[cont] = m;
                if (cont == 3) {
                    this.tratar(cmd);
                    cont = 0;
                    nCmd = 0;
                }

            }
            break;
            case 4: {
                int i, n_mensagem;
                String sql = "SELECT * FROM Mensageiro.Mensagem where Mensagem.to = '" + this.login + "' and Mensagem.view = false;";
                n_mensagem = this.numero_mensagem();
                for (i = 1; i <= n_mensagem; i++) {
                    con.env.mensagem("//envMensagem");
                    con.env.mensagem(con.mc.pegar_Valor(sql, 1, "from"));
                    con.env.mensagem(con.mc.pegar_Valor(sql, 1, "mensagem"));
                    con.env.mensagem(con.mc.pegar_Valor(sql, 1, "date"));
                    con.mc.marcar_lida(con.mc.pegar_Valor(sql, 1, "idMensagem"));
                }
                con.env.mensagem("//endMensagem");
            }
            break;
            case 5: {
                cont++;
                cmd[cont] = m;
                this.tratar(cmd);
                cont = 0;
                nCmd = 0;

            }
            break;
            case 6: {
                int i, n;
                String sql1 = "SELECT * FROM Mensageiro.Amigo where idAmigo1 = '" + this.idUser + "' and Aceito1 = 1 and Aceito2 =1 ;";
                n = mc.pegar_quantidade(sql1);
                for (i = 1; i <= n; i++) {

                    con.env.mensagem("//envAmigo");
                    con.env.mensagem(mc.pegar_Valor("SELECT * FROM Mensageiro.User where idUser = '" + mc.pegar_Valor(sql1, i, "idAmigo2") + "';", 1, "userName"));
                }
                sql1 = "SELECT * FROM Mensageiro.Amigo where idAmigo2 = '" + this.idUser + "' and Aceito1 = 1 and Aceito2 =1 ;";
                n = mc.pegar_quantidade(sql1);
                for (i = 1; i <= n; i++) {

                    con.env.mensagem("//envAmigo");
                    con.env.mensagem(mc.pegar_Valor("SELECT * FROM Mensageiro.User where idUser = '" + mc.pegar_Valor(sql1, i, "idAmigo1") + "';", 1, "userName"));
                }
                con.env.mensagem("//endAmigo");

            }
            break;
            case 7: {
                cont++;
                cmd[cont] = m;
                if (cont == 2) {
                    this.tratar(cmd);
                    cont = 0;
                    nCmd = 0;
                }
            }
            break;
            case 8: {
                cont++;
                cmd[cont] = m;
                if (cont == 2) {
                    this.tratar(cmd);
                    cont = 0;
                    nCmd = 0;
                }
            }
            break;
            case 9: {
                cont++;
                cmd[cont] = m;
                this.tratar(cmd);
                cont = 0;
                nCmd = 0;
            }
            break;
            case 10: {
                cont++;
                cmd[cont] = m;
                if (cont == 2) {
                    this.tratar(cmd);
                    cont = 0;
                    nCmd = 0;
                }
            }
        }
    }

    public void tratar(String[] cmd) {
        if (cmd[1].equals("//login")) {//cmd[2] = name , cmd[3] = pass
            if (mc.verificar_login(cmd[2], cmd[3]) == true) {
                login = cmd[2];
                pass = cmd[3];
                idUser = mc.pegar_Valor("Select * from Mensageiro.User where userName = '" + login + "' and userPass ='" + pass + "';", 1, "idUser");
                System.out.println(idUser);
                mc.mudar_status(idUser, 1);
                con.env.mensagem("//logado");
            } else {
                con.env.mensagem("//Nlogado");
            }
        } else if (cmd[1].equals("//createlogin")) {//cmd[2] = name , cmd[3] = pass , cmd[4] = email
            if (mc.crear_user(cmd[2], cmd[3], cmd[4])) {
                con.env.mensagem("//create");

            } else {
                con.env.mensagem("//Ncreate");
            }
        } else if (cmd[1].equals("//sendmensagem")) { //cmd[2] = to ,cmd[3] = from ,cmd[4] = mensagem,
            if (mc.verificar_login_used(cmd[2])) {
                if (mc.escrever_mensagem(login, cmd[2], cmd[3])) {
                    con.env.mensagem("//Smensagem");
                } else {
                    con.env.mensagem("//NSmensagem");
                }
            } else {
                con.env.mensagem("//NUmensagem");
            }

        } else if (cmd[1].equals("//verificarMensagem")) {
            String sql = "SELECT * FROM Mensageiro.Mensagem where Mensagem.to = '" + login + "' and Mensagem.view = false;";
            if (mc.pegar_quantidade(sql) >= 1) {
                con.env.mensagem("//Tmensagem");
            } else {
                con.env.mensagem("//NTmensagem");
            }

        } else if (cmd[1].equals("//adicionarAmigo")) {
            if (mc.add_amigo(login, cmd[2])) { //cmd[2] = login amigo
                con.env.mensagem("//SAddAmigo");
            } else {
                con.env.mensagem("//NAddAmigo");
            }
        } else if (cmd[1].equals("//delAmigo")) {
            // ainda a implementar
        } else if (cmd[1].equals("//checkAmigo")) {
            String sql = "SELECT * FROM Mensageiro.Amigo where  Aceito1 = 1 and Aceito2 = 1 and idAmigo1 = '" + idUser + "' or idAmigo2 = '" + idUser + "';";
            mc.pegar_quantidade(pass);
        } else if (cmd[1].equals("//checkOnline")) {
            if (mc.verificar_online(cmd[2])) {
                con.env.mensagem("//Sonline");
            } else {
                con.env.mensagem("//Nonline");
            }
        };
    }

    public int numero_mensagem() {
        String sql = "SELECT * FROM Mensageiro.Mensagem where Mensagem.to = '" + login + "' and Mensagem.view = false;";
        return mc.pegar_quantidade(sql);
    }
}

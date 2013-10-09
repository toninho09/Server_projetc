/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server3;

/**
 *
 * @author Zenner
 */
public class Tratamento extends Thread {

    public String login, pass;
    private Conexao con;
    private MysqlComand mc;

    public Tratamento(Conexao con, MysqlComand mc) {
        this.con = con;
        this.mc = mc;

    }

    public void run() {
    }

    public void tratar(String[] cmd) {
        if (cmd[1].equals("//login")) {//cmd[2] = name , cmd[3] = pass
            if (mc.verificar_login(cmd[2], cmd[3]) == true) {
                login = cmd[2];
                pass = cmd[3];
                con.env.mensagem("//logado");
            } else {
                con.env.mensagem("//Nlogado");
            }
        } else if (cmd[1].equals("//createlogin")) {//cmd[2] = name , cmd[3] = pass
            if (mc.crear_user(cmd[2], cmd[3])) {
                con.env.mensagem("//create");
            } else {
                con.env.mensagem("//Ncreate");
            }
        } else if (cmd[1].equals("//Sendmensagem")) { //cmd[2] = to ,cmd[3] = from ,cmd[4] = mensagem,
            if (mc.verificar_login_create(cmd[2])) {
                if (mc.escrever_mensagem(cmd[2], login, cmd[3])) {
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

        } else if (cmd[1].equals("//CarregarAmigo")) {
            // ainda a implementar
        } else if (cmd[1].equals("//adicionarAmigo")) {
            // ainda a implementar
        } else if (cmd[1].equals("//delAmigo")) {
            // ainda a implementar
        } else if (cmd[1].equals("//checkAmigo")) {
            // ainda a implementar
        } else if (cmd[1].equals("//checkOnline")) {
            // ainda a implementar
        }
    }

    public int numero_mensagem() {
        String sql = "SELECT * FROM Mensageiro.Mensagem where Mensagem.from = '" + login + "' and Mensagem.view = false;";
        return mc.pegar_quantidade(sql);
    }
}

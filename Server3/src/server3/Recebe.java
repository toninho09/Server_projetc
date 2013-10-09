/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server3;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zenner
 */
public class Recebe extends Thread {

    public InputStream cliente;
    public Conexao con;
    public String[] cmd = new String[10];
    public int nCmd = 0, cont = 0;

    public Recebe(InputStream in, Conexao con) {
        this.cliente = in;
        this.con = con;
    }

    public void run() {
        Scanner s = new Scanner(this.cliente);
        while (s.hasNextLine()) {
            String m = s.nextLine();
            System.out.println(con.getHostAddress() + ":" + m);
            if (m.equals("//login")) {
                nCmd = 1;
            } else if (m.equals("//createlogin")) {
                nCmd = 2;
            } else if (m.equals("//Sendmessagem")) {
                nCmd = 3;
            } else if (m.equals("//acceptMensagem")) {
                nCmd = 4;
            } else if (m.equals("//verificarMensagem")) {
                nCmd = 5;
            } else if (m.equals("//CarregarAmigo")) {
                nCmd = 6;
            } else if (m.equals("//adicionarAmigo")) {
                nCmd = 7;
            } else if (m.equals("//delAmigo")) {
                nCmd = 8;
            } else if (m.equals("//checkAmigo")) {
                nCmd = 9;
            } else if (m.equals("//checkOnline")) {
                nCmd = 10;
            }

            switch (nCmd) {
                case 1: {
                    cont++;
                    cmd[cont] = m;
                    if (cont == 3) {
                        con.trat.tratar(cmd);
                        cont = 0;
                        nCmd = 0;

                    }
                }
                break;
                case 2: {
                    cont++;
                    cmd[cont] = m;
                    if (cont == 3) {
                        con.trat.tratar(cmd);
                        cont = 0;
                        nCmd = 0;
                    }
                }
                break;
                case 3: {
                    cont++;
                    cmd[cont] = m;
                    if (cont == 3) {
                        con.trat.tratar(cmd);
                        cont = 0;
                        nCmd = 0;
                    }

                }
                break;
                case 4: {
                    int i, id;
                    String sql = "SELECT * FROM Mensageiro.Mensagem where Mensagem.from = '" + con.trat.login + "' and Mensagem.view = false;";
                    for (i = 1; i < con.trat.numero_mensagem(); i++) {
                        con.env.mensagem("//envMensagem");
                        con.env.mensagem(con.mc.pegar_Valor(sql, 1, "from"));
                        con.env.mensagem(con.mc.pegar_Valor(sql, 1, "mensagem"));
                        con.env.mensagem(con.mc.pegar_Valor(sql, 1, "date"));
                        con.mc.marcar_lida(con.mc.pegar_Valor(sql, 1, "idMensagem"));
                    }
                }
                break;
                case 5: {
                    cont++;
                    cmd[cont] = m;
                    con.trat.tratar(cmd);
                    cont = 0;
                    nCmd = 0;

                }
                break;
                case 6: {
                    cont++;
                    cmd[cont] = m;
                    con.trat.tratar(cmd);
                    cont = 0;
                    nCmd = 0;
                }
                break;
                case 7: {
                    cont++;
                    cmd[cont] = m;
                    if (cont == 2) {
                        con.trat.tratar(cmd);
                        cont = 0;
                        nCmd = 0;
                    }
                }
                break;
                case 8: {
                    cont++;
                    cmd[cont] = m;
                    if (cont == 2) {
                        con.trat.tratar(cmd);
                        cont = 0;
                        nCmd = 0;
                    }
                }
                break;
                case 9: {
                    cont++;
                    cmd[cont] = m;
                    con.trat.tratar(cmd);
                    cont = 0;
                    nCmd = 0;
                }
                break;
                case 10: {
                    cont++;
                    cmd[cont] = m;
                    con.trat.tratar(cmd);
                    cont = 0;
                    nCmd = 0;
                }
            }

        }
        System.out.println("saiu");
        s.close();
    }

    public String receber() {
        Scanner s = new Scanner(this.cliente);
        while (s.hasNextLine()) {
            String m = s.nextLine();
        }
        ;
        return "";
    }
}

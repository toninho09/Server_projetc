/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Zenner
 */
public class Main {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException{
        mensageiro gerente =(mensageiro) Naming.lookup("rmi://localhost/msg");
        System.out.print(gerente.sayhey());
    }
}

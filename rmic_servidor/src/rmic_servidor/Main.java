/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmic_servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Zenner
 */
public class Main {
    public static void main(String[] args) throws RemoteException, MalformedURLException{
        gestor g = new gestor();
        LocateRegistry.createRegistry(1099);
        Registry r = LocateRegistry.getRegistry();
        r.rebind("msg", g);
        System.out.println("Server on");
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmic_servidor;

import cliente.mensageiro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Zenner
 */
public class gestor extends UnicastRemoteObject implements mensageiro{

    @Override
    public String sayhey() throws RemoteException {
        int i,j;
        for(i=0 ;i<1000 ; i++){
            for (j=0;j<1000;j++){
                System.out.println("teste "+i+" teste "+j);
            }
            
        }
        return "testando mais uma vez";
    }
    protected gestor() throws RemoteException{
        super();
    }

    @Override
    public String teste() throws RemoteException {
        return "testando mais um metodo";
    }
}

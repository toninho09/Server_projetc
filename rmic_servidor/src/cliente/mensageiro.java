/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Zenner
 */
public interface mensageiro extends Remote{
    public String sayhey()throws RemoteException;
    public String teste()throws RemoteException;
    
}

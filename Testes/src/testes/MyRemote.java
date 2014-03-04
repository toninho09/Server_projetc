/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;
import java.rmi.*;
/**
 *
 * @author Zenner
 */
public interface MyRemote extends Remote{
    public String SayHello() throws RemoteException;
}

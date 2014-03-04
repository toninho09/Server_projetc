/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;
import java.rmi.*;
import java.rmi.server.*;
/**
 *
 * @author Zenner
 */

public class Testes extends UnicastRemoteObject implements MyRemote{

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        try {
            MyRemote service = new Testes();
            Naming.rebind("rmi://localhost/sms", service);
        } catch (Exception ex) {
           ex.printStackTrace();
           
       }
    }

    @Override
    public String SayHello() throws RemoteException {
       
        return "Server say,'Hey'";
        
    }
    
    public Testes() throws RemoteException{}
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketrmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Denilson
 */

public interface Hello extends Remote{
    public String benvindo(String n) throws RemoteException;
    public String guardar(String n) throws RemoteException;
    //acrescentar métodos
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketrmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Denilson
 */

public class ImplHello extends UnicastRemoteObject implements Hello{
    
    ImplHello() throws RemoteException{
        super();
    }
    
    //@Override
    @Override
    public String benvindo(String n) throws RemoteException{
        return "Olá! "+n;
    }   
    
    public String guardar(String n) throws RemoteException{
        //conectar e registar na abse de dados;
        return "Registado com sucesso";
    }  
    
     //concretizar os métodos da interface
    
}

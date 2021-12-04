/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketrmi;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
/**
 *
 * @author Denilson
 */

public class ClienteRMI {
    
    public static void main(String []args) throws NotBoundException, MalformedURLException, RemoteException{
        Scanner s = new Scanner(System.in);
        System.out.println("Insira um nome");
        String n= s.next();
        Hello hor = (Hello) Naming.lookup("testeHello");
        
        //String resposta = hor.benvindo(n);
        String resposta = hor.benvindo(n);
        System.out.println(resposta);
    }
}

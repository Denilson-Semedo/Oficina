/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketrmi;

import java.sql.*;
import java.io.DataOutputStream;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Denilson
 */
public class ServidorRMI {

    public static void entrada_veiculo(String matricula, String dono, String data, String problema) {

        //Apanhar as credencias de acesso a base de dados nas variaveis de ambiente;
        String db_user = System.getenv("BD_USER");
        String db_pass = System.getenv("BD_PASS");

        try {
            // create a mysql database connection
            String myUrl = "jdbc:mysql://localhost:3306/teste?useTimezone=true&serverTimezone=UTC";
            //Class.forName(myDriver);
            Connection connect = DriverManager.getConnection(myUrl, db_user, db_pass);

            // the mysql insert statement
            String query = "insert into entrada_veiculoID (matricula,dono,data_entrada,problema) values (?,?,?,?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connect.prepareStatement(query);
            preparedStmt.setString(1, matricula);
            preparedStmt.setString(2, dono);
            preparedStmt.setString(3, data);
            preparedStmt.setString(4, problema);

            // execute the preparedstatement
            preparedStmt.execute();

            connect.close();
            String resposta = "\nEntrada de veiculo Feito com sucesso!!";
            System.err.println(resposta);

        } catch (Exception e) {
            System.err.println("\nGot an exception!" + e.getMessage());
        }
    }

    public static String consultar_pecas(int codigo) {

        //Apanhar as credencias de acesso a base de dados nas variaveis de ambiente;
        String db_user = System.getenv("BD_USER");
        String db_pass = System.getenv("BD_PASS");

        String pecaQtd = "";

        try {
            // create a mysql database connection
            String myUrl = "jdbc:mysql://localhost:3306/teste?useTimezone=true&serverTimezone=UTC";
            //Class.forName(myDriver);
            Connection connect = DriverManager.getConnection(myUrl, db_user, db_pass);

            // the mysql select statement
            String sql = "SELECT * FROM pecas WHERE codigo = ? ";
            PreparedStatement pstm = connect.prepareStatement(sql);
            pstm.setInt(1, codigo);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                pecaQtd = rs.getString("quantidade");
            }
            rs.close();
            pstm.close();

            connect.close();

        } catch (Exception e) {
            System.err.println("\nGot an exception!" + e.getMessage());
        }
        return pecaQtd;
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        System.setProperty("java.rmi.server.hostname", "localhost");
        //Naming.rebind("rmi://localhost:1099/testeHello", new ImplHello());
        Naming.rebind("testeHello", new ImplHello());
        System.out.println("Serviço disponivel....");

    }
}

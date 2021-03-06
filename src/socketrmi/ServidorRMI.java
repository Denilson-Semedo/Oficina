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
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Denilson
 */
public class ServidorRMI {

    private static Connection BD_Conexao() throws SQLException {
        //Apanhar as credencias de acesso a base de dados nas variaveis de ambiente;
        String db_user = System.getenv("BD_USER");
        String db_pass = System.getenv("BD_PASS");

        // create a mysql database connection
        String myUrl = "jdbc:mysql://localhost:3306/teste?useTimezone=true&serverTimezone=UTC";
        //Class.forName(myDriver);
        Connection connect = DriverManager.getConnection(myUrl, db_user, db_pass);

        return connect;
    };

    private static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void entrada_veiculo(String matricula, String dono, String problema) {

        String data = getDate();

        try {

            Connection connect = BD_Conexao();

            // the mysql insert statement
            String query = "insert into entrada_veiculo (matricula,dono,data_entrada,problema) values (?,?,?,?)";

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

    public static String consultar_peca(int codigo) {

        String pecaQtd = "";

        try {
            Connection connect = BD_Conexao();

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

    public static void saida_veiculo(int entrada_veiculoID, String solucao) {

        String data = getDate();

        try {

            Connection connect = BD_Conexao();

            // the mysql insert statement
            String query = "insert into saida_veiculo (entrada_veiculoID,data_saida,solucao) values (?,?,?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connect.prepareStatement(query);
            preparedStmt.setInt(1, entrada_veiculoID);
            preparedStmt.setString(2, data);
            preparedStmt.setString(3, solucao);

            // execute the preparedstatement
            preparedStmt.execute();

            connect.close();
            String resposta = "\nSa??da de veiculo Feita com sucesso!!";
            System.err.println(resposta);

        } catch (Exception e) {
            System.err.println("\nGot an exception!" + e.getMessage());
        }

    }

    public static String consultar_pecasALL() {

        String pecaQtd = "";

        try {
            Connection connect = BD_Conexao();

            // the mysql select statement
            String sql = "SELECT * FROM pecas;";
            PreparedStatement pstm = connect.prepareStatement(sql);
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

        return "";
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        System.setProperty("java.rmi.server.hostname", "localhost");
        //Naming.rebind("rmi://localhost:1099/testeHello", new ImplHello());
        Naming.rebind("testeHello", new ImplHello());
        System.out.println("Servi??o disponivel....");
        
        String matricula = "so-43-st";
        String dono = "Denilon";
        String problema = "Pneu Furada";
        int codigo = 1;
        int entrada_veiculoID = 1;
        
        //entrada_veiculo(, );

    }
}



/**
 *
 * @author ROH
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateDatabaseDynamic {

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Statement statement = null;
        String db;
        try {
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/mysql";
            connection = DriverManager.getConnection(url, "root", "");

            statement = connection.createStatement();
            System.out.println("Enter the name of database");
           Scanner scanner = new Scanner(System.in);  
   String datab = scanner.next(); 
            String hrappSQL = "CREATE DATABASE "+datab;
            statement.executeUpdate(hrappSQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                } 
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                } 
            }
        }
    }

}/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roh
 */

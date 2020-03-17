/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateDynamicDBAndTable {

    public static void main(String[] args) {
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
   ;  try{
   String datab = scanner.next(); 
            String hrappSQL = "CREATE DATABASE "+datab;
            statement.executeUpdate(hrappSQL);
            System.out.println("Database"+datab+" has been created");
   }catch(Exception e) {
       System.out.println(e);
   }
            
            DatabaseMetaData dbm = connection.getMetaData();
// check if "employee" table is there
Scanner s = new Scanner(System.in);
System.out.println("Enter the tabel name");
String tabl= s.next();
ResultSet tables = dbm.getTables(null, null, ""+tabl, null);//Static table creation is created within "  "
if (tables.next()) {
  // Table exists
  System.out.println("Table exist");
  
}
else {
  // Table does not exist
}



         statement = connection.createStatement();
         String sql = "CREATE TABLE "+tabl+" " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           TEXT    NOT NULL, " + 
                        " AGE            INT     NOT NULL, " + 
                        " ADDRESS        CHAR(50), " + 
                        " SALARY         REAL)"; 
         statement.executeUpdate(sql);
         System.out.println("Table is created");
         statement.close();
         connection.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                } // nothing we can do
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                } // nothing we can do
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roh
 */


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCDatabaseMetaDataTablesExample {

  public static void main(String[] args)  throws SQLException {
   // String database_url   = "jdbc:mysql://localhost:3306/sqdl","root","";
    String username     = "root";
    String password     = "";
    Connection connection   = null;
    
    try {
      Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sqdl","root","");
         System.out.println("Opened database successfully");
         
    } catch (ClassNotFoundException e) {
      System.out.println("ERROR: Unable to load SQLServer JDBC Driver");
      e.printStackTrace();
      return;
    }
   
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqdl", username, password);
    } catch (SQLException e) {
      System.out.println("ERROR:  Unable to establish a connection with the database!");
      e.printStackTrace();
      return;
    }
   
    try {
      if (connection != null) {
        DatabaseMetaData metadata = connection.getMetaData();
          System.out.println("nList of Tables Available for 'dbo'...n");
          System.out.format("%-15s %-10s %-20s %-10sn", "DATABASE", "SCHEMA", "TABLE NAME", "TYPE");
          System.out.format("%-15s %-10s %-20s %-10sn", "-------------", "--------", "----------------", "-----");
          ResultSet rs = metadata.getTables("sqdl", "aa", "%", null);
          while (rs.next()) {
            System.out.format("%-15s %-10s %-20s %-10sn", rs.getString(1), rs.getString(2), 
                      rs.getString(3), rs.getString(4));
          }
          
      } else {
        System.out.println("ERROR: Unable to make a database connection!");
      }
      
      
    } catch (SQLException e) {
      e.printStackTrace();
      return;
    } finally {
        if (connection != null) connection.close();
    }
  }
}


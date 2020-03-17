/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UpdatableResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author imssbora
 */
public class ResultSetBackandForth {
   public static void main(String[] args) {
      String jdbcUrl = "jdbc:mysql://localhost:3306/dd";
      String username = "root";
      String password = "";
      String sql = "select id, name from employee";

      try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql,
                  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery()) {

         // Move cursor to 1st row
         rs.absolute(2);
         System.out.println("ID : " + rs.getInt("ID") + " \tNAME : " + rs.getString("NAME"));
         
         // Move cursor to 4th row
         rs.absolute(4);
         System.out.println("ID : " + rs.getInt("ID") + " \tNAME : " + rs.getString("NAME"));

         // Move cursor to last row
         rs.first();
         System.out.println("ID : " + rs.getInt("ID") + " \tNAME : " + rs.getString("NAME"));
         
         // Move cursor to last row
         rs.last();
         System.out.println("ID : " + rs.getInt("ID") + " \tNAME : " + rs.getString("NAME"));
        
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}


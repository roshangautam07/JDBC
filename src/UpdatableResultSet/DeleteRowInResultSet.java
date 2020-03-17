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
public class DeleteRowInResultSet {
   public static void main(String[] args) {
      String jdbcUrl = "jdbc:mysql://localhost:3306/dd";
      String username = "root";
      String password = "";
      String sql = "select id, name from employee";

      try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement stmt = conn.prepareStatement(sql,
                  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery()) {

         // Move cursor to last row
         // Delete last row
         rs.last();
         rs.deleteRow();

         // Move cursor to 2nd row
         // Delete 2nd row
         rs.absolute(5);
         rs.deleteRow();

         // Move cursor to before the first row. 
         rs.absolute(0);

         while (rs.next()) {
            System.out.println(
                  "ID : " + rs.getInt("ID") + " \tNAME : " + rs.getString("NAME"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
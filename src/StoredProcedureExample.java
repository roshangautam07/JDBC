/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author imssbora
 */
public class StoredProcedureExample {
   public static void main(String[] args) {
       
      String jdbcUrl = "jdbc:mysql://localhost:3306/dd";
      String username = "root";
      String password = "";
      String sql = "{call PRODUCT_PROC(?,?,?)}";

      try{ 
              Connection conn = DriverManager.getConnection(jdbcUrl, username, password); 
            CallableStatement stmt=conn.prepareCall(sql);
         
         //Set IN parameter
         stmt.setInt(1, 1);  
         
         //Set OUT parameter
         stmt.registerOutParameter(2, Types.VARCHAR);
         
         //Set INOUT parameter
         stmt.setDouble(3, 15.15);
         stmt.registerOutParameter(3, Types.DOUBLE);
         
         //Execute stored procedure
         stmt.execute();
         
         // Get Out and InOut parameters
         System.out.println("Product Name = "+stmt.getString(2));
         System.out.println("Product Old Price = "+stmt.getDouble(3));
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
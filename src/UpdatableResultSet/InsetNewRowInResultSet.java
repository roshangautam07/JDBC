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
public class InsetNewRowInResultSet {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/dd";
        String username = "root";
        String password = "";
        String sql = "select id, name from employee";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
                PreparedStatement stmt = conn.prepareStatement(sql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery()) {

            // Save the current cursor position, and move cursor to the insert row, 
            rs.moveToInsertRow();
            //Set columns values
            rs.updateInt("ID", 8);
            rs.updateString("NAME", "Tom Hardy");
            System.out.println("ID : " + rs.getInt("ID") + " \tNAME : " + rs.getString("NAME"));

            //Insert new row
            rs.insertRow();

            // Move cursor back to saved position
            rs.moveToCurrentRow();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

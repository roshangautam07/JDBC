/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UpdatableResultSet;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author imssbora
 */
public class UpdateRowInResultSet {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/dd";
        String username = "root";
        String password = "";
        String sql = "select id, name from employee";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
                PreparedStatement stmt = conn.prepareStatement(sql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery()) {

            // Update 2nd row
            // Move cursor to 2nd row
            /* Scanner sc =new Scanner(System.in);
         out.println("Which row you want to update");
         int n =sc.nextInt();
         String s =sc.next();*/
            rs.absolute(2);
            rs.updateString("NAME", "Roshan");
            rs.updateRow();

            System.out.println("ID : " + rs.getInt("ID") + " \tNAME : " + rs.getString("NAME"));

        } catch (SQLException e) {
            out.println(e);
        }
    }
}

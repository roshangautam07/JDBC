/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ROH
 */
import java.sql.*;
import java.io.*;

public class updaterecord {

    public static void main(String args[]) throws Exception {                // standard 3 JDBC statements
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please Enter the ID no:");
        String str = br.readLine();
        int id = Integer.parseInt(str);
        // System.out.println("Enter new salary:");
        //int sal = Integer.parseInt(br.readLine());

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqdl", "root", "");
        Statement stmt = con.createStatement();
        System.out.println("Opened database successfully");

        ResultSet res;
        res = stmt.executeQuery("select * from COMPANY where id = " + id);

        res.next();
        String name = res.getString(1);
        double oldSal = res.getDouble(4);

        System.out.println("Please New Salary:");
        String str2 = br.readLine();
        int sal = Integer.parseInt(str2);
        //  update the salary in the database by Rs.500
        stmt.executeUpdate("Update COMPANY set salary = " + sal + " where id = " + id);
        //  to get the new salary from database
        res = stmt.executeQuery("select * from COMPANY where id = " + id);
        res.next();

        System.out.println("successfully updated");

    }
}

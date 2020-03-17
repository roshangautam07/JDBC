/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roh
 */
import java.sql.*;
import java.util.*;

public class Prepared {

    private static final String URL = "jdbc:mysql://localhost:3306/dd";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static void main(String args[]) throws Exception {

        try {
            int id, age;
            String name;

            Connection c = null;
            PreparedStatement stm = null;
            Class.forName(DRIVER);
            c = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Connection is opened");
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter id");
            id = sc.nextInt();
            System.out.println("Enter name");
            name = sc.next();
            System.out.println("Enter age");
            age = sc.nextInt();
            String sql="insert into INFO values(?,?,?)";

            stm=c.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, name);
            stm.setInt(3, age);

           stm.executeUpdate();
            System.out.println("Record is inserted");
            c.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

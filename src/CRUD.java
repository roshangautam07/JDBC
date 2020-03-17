/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roh
 */
import static JDBC.App.Insert;
import static JDBC.App.delete;
import static JDBC.App.retrive;
import static JDBC.App.update;
import java.sql.*;
import java.util.*;

public class CRUD {

    //Database Connection
    private static final String URL = "jdbc:mysql://localhost:3306/facebook";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static Connection conection() throws Exception {

        Class.forName(DRIVER);
        System.out.println("Driver is successfully loaded");
        Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        System.out.println("Connection is successfully established");
        return con;

    }

    public static void insert(int id, String name, int age) {
        //Insert operation
        try {
            Connection con = CRUD.conection();
            PreparedStatement pst = null;
            String sql = "insert into  students values(?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setInt(3, age);
            pst.executeUpdate();
            System.out.println("Inserted successfully");
            System.out.println("Do you want to go back to main menu  then press 1  else press anythings");
            Scanner scs = new Scanner(System.in);
            int b = scs.nextInt();
            if (b == 1) {
                CRUD.main(new String[]{});
            }
        } catch (Exception e) {

            System.out.println(e);

        }
    }

    public static void retrive(int id) {
        //retrive operation
        try {

            Connection con = CRUD.conection();
            PreparedStatement pt = null;
            String sql = "select * from students where id=?";
            pt = con.prepareStatement(sql);
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Name=" + rs.getString("name") + "Age=" + rs.getInt("age"));
                System.out.println("Do you want to go back to main menu  then press 1  else press anythings");
                Scanner scs = new Scanner(System.in);
                int b = scs.nextInt();
                if (b == 1) {
                    CRUD.main(new String[]{});
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void update(int id) {
        //update operation
        try {
            Connection conn = CRUD.conection();
            //statetment
            Scanner scs = new Scanner(System.in);
            System.out.println("Enter Your name");
            String names = scs.next();
            System.out.println("Enter age");
            int ages = scs.nextInt();
            String sql = "UPDATE students SET name=?, age=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, names);
            stmt.setInt(2, ages);
            stmt.setInt(3, id);
            //execute
            stmt.executeUpdate();
            System.out.println("Record Updated Successfully");
            //close connection
            conn.close();
            System.out.println("Do you want to go back to main menu  then press 1  else press anythings");

            int b = scs.nextInt();
            if (b == 1) {
                CRUD.main(new String[]{});
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private static void delete(int id) {
        //delete operation
        try {
            //establsh Connectiton
            Connection conn = CRUD.conection();
            //statetment
            String sql = "DELETE FROM students WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            //execute
            stmt.executeUpdate();
            System.out.println("Record Deleted Successfully");
            //close connection
            conn.close();
            System.out.println("Do you want to go back to main menu  then press 1  else press anythings");
            Scanner scs = new Scanner(System.in);
            int b = scs.nextInt();
            if (b == 1) {
                CRUD.main(new String[]{});
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        int ids, ages;
        String names;

        System.out.println("A.ADD\n B.Update\n C.Retrive\n D.Delete\n E.Exit");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice");
        String alpha = sc.nextLine();
        switch (alpha) {
            case "A":
                Scanner scs = new Scanner(System.in);
                System.out.println("Enter id");

                ids = scs.nextInt();
                System.out.println("Enter Your name");
                names = scs.next();
                System.out.println("Enter age");

                ages = scs.nextInt();
                insert(ids, names, ages);
                break;
            case "B":
                Scanner scss = new Scanner(System.in);
                System.out.println("Enter id");
                ids = scss.nextInt();
                update(ids);
                break;
            case "C":
                Scanner scssc = new Scanner(System.in);
                System.out.println("Enter id you want to retrive");
                ids = scssc.nextInt();
                retrive(ids);
                break;
            case "D":

                Scanner scsss = new Scanner(System.in);
                System.out.println("Enter id you want to delete");
                ids = scsss.nextInt();
                delete(ids);
                break;

            case "E":
                System.out.println("Exit successfully");
                System.exit(0);

            default:
                System.out.println("Wrong input");
                System.exit(0);
                break;

        }
    }

}
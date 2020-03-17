/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.*;
import java.util.Scanner;
public class App {
    private static final String URL = "jdbc:mysql://localhost:3306/dd";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
    public static void main(String args[]) throws Exception{
       // int sel = 0;
        System.out.println("1.ADD\n 2.Update\n 3.Retrive\n 4.Delete");
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter your choice");
        String alpha = sc.nextLine();
        switch(alpha){
            case "A":
                Insert();
                break;
            case "B":
                   update(1);
                   break;
            case "C"   :
                retrive(2);
                break;
            case "D" :
                delete(1);
                break;
                default: 
                    System.out.println("Wrpng input");
    break;  
            
        }
        
    }
    public static Connection connect() throws Exception{
        Connection c=null;
        Statement stm=null;
        Class.forName(DRIVER);
        System.out.println("Driver is Loaded");
       
            c=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            
            System.out.println("Connectio is established");
            return c;
        }
    
    public static void Insert() throws Exception{
       try{ 
        Connection c =connect();
        Statement stm =c.createStatement();
        String sql="INSERT INTO INFO (ID,NAME,AGE) VALUES(2,'ROJ',36)";
        stm.executeUpdate(sql);
        			System.out.println("Record Saved Successfully");
                                c.close();
    }catch(Exception e){
       System.out.println(e.getMessage()); 
    }
       
    }
    
    public static void update(int id) throws Exception{
        try{
            Connection c =connect();
            PreparedStatement smt=null;
            String sql="update INFO set NAME=?, AGE=? WHERE ID=?";
            smt=c.prepareStatement(sql);
            smt.setString(1, "ROH");
            smt.setInt(2, 88);
            smt.setInt(3, id);
            smt.executeUpdate();
            System.out.println("Record Successfully updated");
            c.close();
        }catch(Exception e){
       System.out.println(e.getMessage()); 
    }
    }
    
    public static void retrive(int id){
        try{
            Connection c = connect();
            PreparedStatement smt=c.prepareStatement("select * from INFO WHERE ID=?");
            smt.setInt(1, id);
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
                System.out.println("NAME:"+rs.getString("NAME")+"AGE :"+rs.getInt("AGE"));
            }
            c.close();
        }catch(Exception e){
       System.out.println(e.getMessage()); 
    }
    }
    
    public static void delete(int id)throws Exception{
        
        try{
            Connection c=connect();
            PreparedStatement stm=null;
        String sql="delete from INFO where ID=?";
            stm=c.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            System.out.println("Record Successfully Deleted");
            c.close();
        }catch(Exception e){
       System.out.println(e.getMessage()); 
    }
    }
    
    
}

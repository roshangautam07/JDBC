
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roh
 */
public class DynamicTable {
    
    
  
  public static void main(String args[])
  { 
    Connection con = null;
    Statement stmt = null;

    try
    {
        
        
        Class.forName("com.mysql.jdbc.Driver");  

   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrapp", "root", "");  
   Statement st = con.createStatement();  

   System.out.println("connection has been made ");  

   Scanner scanner = new Scanner(System.in);  
   System.out.println("please enter table name ");  
   String tableName = scanner.next();  

   String strr = null;  

   System.out.println("Enter no of columns you like to use: ");  
   int noc = scanner.nextInt();  

   for (int i = 1; i <= noc; i++) {  
    BufferedReader bf = new BufferedReader(new InputStreamReader(  
      System.in));  
    System.out.println("Enter column name with type ");  
    String s1 = bf.readLine();  
    if (strr == null) {  
     strr = s1;  

    } else {  
     strr = strr + s1;  

    }  

   }  

   st.executeUpdate("create table " + tableName + "(" + strr + ")");  
   System.out.println("your table " + tableName  
     + " has been created!!!");  

   ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);  
   ResultSetMetaData rsmd = rs.getMetaData();  
   int NumOfCol = rsmd.getColumnCount();  
   System.out.println("Number of Columns of your table =" + tableName  
     + " " + NumOfCol);  
   System.out.println("Column names are ");  
   for (int i = 1; i <= NumOfCol; i++) {  
    System.out.println(rsmd.getColumnName(i));  
   }  
   String strn = null;  
   System.out.println("please enter values you need to insert");  

   for (int i = 1; i <= NumOfCol; i++) {  
    String s5 = scanner.next();  
    if (strn == null) {  
     strn = s5;  

    } else  
     strn = strn + s5;  

   }  
   System.out.println(strn);  

   st.executeUpdate("insert into " + tableName + " values" + "("  
     + strn + ")");  
   System.out.println("your table " + tableName  
     + " has been filled!!!");  

   con.close();  
   System.out.println("connection has been colsed ");  

  }  

  catch (Exception e) {  

   System.out.println(e.getMessage());  
  }  
    }	
    
}
  


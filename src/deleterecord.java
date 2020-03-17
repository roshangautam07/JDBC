import java.sql.*;
import java.io.*;

public class deleterecord
{
  public static void main(String args[]) throws Exception
  {                                               // to take input from the user
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Please Enter the ID no:");
    String str=br.readLine();
    int id = Integer.parseInt(str);
		                                  // 3 standard JDBC statements
    Class.forName("com.mysql.jdbc.Driver");
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sql","root","");
    Statement stmt=con.createStatement();
                                                 // send SQL command
    int affectedRecords = stmt.executeUpdate("delete from CM where id=" + id);
						// printing message for the user
    System.out.println("Number " + id + " record deleted");
    System.out.println("Number of records deleted: " + affectedRecords);
    br.close();
    stmt.close();
    con.close();
  }
} 
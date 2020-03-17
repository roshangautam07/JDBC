import java.sql.*;
public class CreateTables
{
  public static void main(String args[])
  { 
    Connection con = null;
    Statement stmt = null;

    try
    {			                             // load the driver
      Class.forName("com.mysql.jdbc.Driver");
                                                     // establish the connection
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqdl","root","");
                                                     // create Statement object
      stmt = con.createStatement();
                                                     // send sql command
      String sql = "CREATE TABLE CMP " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           VARCHAR(20)    NOT NULL, " + 
                        " AGE            INT(3)     NOT NULL, " + 
                        " ADDRESS        CHAR(50), " + 
                        " SALARY         REAL)"; 
         stmt.executeUpdate(sql);
      System.out.println("Table created");
    }
    catch(ClassNotFoundException e)
    {
      System.err.println("Driver is not found. " + e);
    }
    catch(SQLException e)
    {
      System.err.println("Some problem in creating connection. " + e);
    }
    finally
    {                                                // close the database connection
      try
      {
        stmt.close();
        con.close();
      }
      catch(SQLException e)
      {
        System.err.println("Unable to close. " + e);
      }
    }
  }
} 
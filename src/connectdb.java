import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class connectdb {
  public static void main( String args[] ) {
      Connection c = null;
      
      try {
         Class.forName("com.mysql.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/sql","root","");
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Opened database successfully");
   }
}
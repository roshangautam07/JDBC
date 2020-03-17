import java.sql.*;
import java.util.Scanner;

public class Createtable {

   public static void main( String args[] ) {
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("com.mysql.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/sqdl","root","");
         System.out.println("Opened database successfully");
         
         

DatabaseMetaData dbm = c.getMetaData();
// check if "employee" table is there
Scanner s = new Scanner(System.in);
System.out.println("Enter the tabel name");
String tabl= s.next();
ResultSet tables = dbm.getTables(null, null, ""+tabl, null);//Static table creation is created within "  "
if (tables.next()) {
  // Table exists
  System.out.println("Table exist");
}
else {
  // Table does not exist


            

         stmt = c.createStatement();
         String sql = "CREATE TABLE "+tabl+" " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           TEXT    NOT NULL, " + 
                        " AGE            INT     NOT NULL, " + 
                        " ADDRESS        CHAR(50), " + 
                        " SALARY         REAL)"; 
         stmt.executeUpdate(sql);
         System.out.println("Table is created");
         stmt.close();
         c.close();
 }} catch ( Exception e ) {
         /*System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);*/
      }
      
      //System.out.println("Table created successfully");
   }
}
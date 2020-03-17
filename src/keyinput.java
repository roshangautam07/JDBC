import java.sql.*;                                     // for JDBC classes
import java.io.*;                                      // for keyboard reading
 
public class keyinput
{
  public static void main(String args[]) throws Exception
  {                                                    // standard database connections
    Class.forName("com.mysql.jdbc.Driver");
      // establish the connection
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
    Statement stmt = con.createStatement();
    
    
    
    

DatabaseMetaData dbm = con.getMetaData();
// check if "employee" table is there
ResultSet tables = dbm.getTables(null, null, "CM", null);
if (tables.next()) {
  // Table exists
  
  System.out.println("Table Exist");
}
else {
  // Table does not exist



    
   stmt = con.createStatement();
                                                     // send sql command
      String sql = "CREATE TABLE CM " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           VARCHAR(20)    NOT NULL, " + 
                        " AGE            INT(3)     NOT NULL, " + 
                        " ADDRESS        CHAR(50), " + 
                        " SALARY         REAL)"; 
         stmt.executeUpdate(sql);
      System.out.println("Table created");
}
                                                       // keyboard input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    System.out.println("Enter Employee ID:");
    int id = Integer.parseInt(br.readLine());
 
    System.out.println("Enter Employee Name:");
    String name = br.readLine();
 
    System.out.println("Enter Employee age:");
    int age = Integer.parseInt(br.readLine());
    
     System.out.println("Enter Employee Address:");
    String address = br.readLine();
    
    System.out.println("Enter Employee Salary:");
    double salary = Double.parseDouble(br.readLine());
 
    stmt.executeUpdate("insert into CM values("+id+ ", '"+name+"',"+age+",'"+address+"',"+salary+")");
                                                      // now record inserted (of Insert Record JDBC Keyboard Input)
    System.out.println(name + " record inserted");
 
    stmt.close();
    con.close();
   }
 } 
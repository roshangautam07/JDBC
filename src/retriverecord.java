import java.sql.*;
public class retriverecord
{
  public static void main(String args[]) throws Exception
  {                                            // standard 3 JDBC statements
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
    Statement stmt = con.createStatement(); //Default method for ResultSet.TYPE_FORWARD_ONLY
    
                                               // send a SQL query to retrieve records
    ResultSet res = stmt.executeQuery("select *from CM");
                                               // process the data
    while(res.next())
    {
      System.out.println(res.getInt("id") //pass the name of the column to get the value
              + "\t" + res.getString(2) + //passing column index instead of a column name
              "\t" +res.getInt("age") + 
              "\t" + res.getString("address") + 
              "\t"+ res.getDouble("salary"));
    }
                                               // close the database connection
    res.close();
    stmt.close();
    con.close();
   }
}  
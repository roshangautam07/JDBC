import java.sql.*;
public class retriverecordbyfind
{
  public static void main(String args[]) throws Exception
  {                                            // standard 3 JDBC statements
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql","root","");
    Statement stmt = con.createStatement(); //Default method for ResultSet.TYPE_FORWARD_ONLY
    
                                               // send a SQL query to retrieve records
    ResultSet res = stmt.executeQuery("select *from CM");
                                               // process the data
                                               
    int idIndex = res.findColumn("id");
    int nameIndex   = res.findColumn("name");
int ageIndex    = res.findColumn("age");
int addressIndex  = res.findColumn("address");
int salaryIndex=res.findColumn("salary");

while(res.next()) {
    int id =res.getInt(idIndex);
    String     name        = res.getString     (nameIndex);
    int        age         = res.getInt        (ageIndex);
    String address = res.getString (addressIndex);
    String salary =res.getString(salaryIndex);
    
    System.out.println(res.getInt(idIndex) 
              + "\t" + res.getString(nameIndex) + 
              "\t" +res.getInt(ageIndex) + 
              "\t" + res.getString(addressIndex) + 
              "\t"+ res.getDouble(salaryIndex));
}
                                               // close the database connection
    res.close();
    stmt.close();
    con.close();
   }
}  
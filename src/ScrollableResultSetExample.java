/* Theory

In this example we shall show you how to create and use a scrollable ResultSet. To use a scrollable ResultSet one should perform the following steps:

    *Load the JDBC driver, using the forName(String className) API method of the Class. 
In this example we use the MySQL JDBC driver.
    *Create a Connection to the database. Invoke the getConnection(String url, String user, String password) API method of the DriverManager to create the connection.
    *Create a Statement, using the createStatement() API method of the Connection. 
The Statement must have the type ResultSet.TYPE_SCROLL_INSENSITIVE or ResultSet.TYPE_SCROLL_SENSITIVE and the concurrency ResultSet.CONCUR_UPDATABLE, in order to return scrollable result sets.
   * Execute the query to the database, using the executeQuery(String sql) API method. 
The data produced by the given query is a ResultSet.
    *Get the cursor position, with the getRow() API method and check if it is before the first row, with the isBeforeFirst() API method.
    *Invoke the next() API method to move the cursor to next row, and last() API method to move cursor to the last row. 
In order to check if it is in the last row, we can call the isLast() API method.
   * Move the cursor to the end of this ResultSet object, just after the last row, with the afterLast() API method and use the isAfterLast() API method to check if it is after the last row.
    *Move cursor to other rows, with the absolute(int row) API method and check again its position.
    *Invoke the relative(int rows) API method to move the cursor,

as described in the code snippet below.
*/



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableResultSetExample {
 
  public static void main(String[] args) {

    Connection connection = null;
    try {

  // Load the MySQL JDBC driver

  String driverName = "com.mysql.jdbc.Driver";

  Class.forName(driverName);


  // Create a connection to the database

  String serverName = "localhost";

  String schema = "dd";

  String url = "jdbc:mysql://" + serverName +  "/" + schema;

  String username = "root";

  String password = "";

  connection = DriverManager.getConnection(url, username, password);

  

  System.out.println("Successfully Connected to the database!");

  
    } catch (ClassNotFoundException e) {

  System.out.println("Could not find the database driver " + e.getMessage());
    } catch (SQLException e) {

  System.out.println("Could not connect to the database " + e.getMessage());
    }

    try {


  /*

    * An insensitive scrollable result set is one where the values captured in the 

    * result set never change, even if changes are made to the table from which the 

    * data was retrieved.

    * A sensitive scrollable result set is one where the current values in the table 

    * are reflected in the result set. So if a change is made to a row in the table, 

    * the result set will show the new data when the cursor is moved to that row

    */


  // Create an insensitive scrollable result set (for sensitive scrollable result sets use ResultSet.TYPE_SCROLL_SENSITIVE directive)

  Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

  ResultSet results = statement.executeQuery("SELECT * FROM INFO");


  // Get cursor position

  System.out.println("Cursor position " + results.getRow() + ", is before first ? " + 
          results.isBeforeFirst());


  // Every call to next() moves cursor to the next row - in this case the first row

  results.next();


  // Get cursor position

  System.out.println("Cursor position " + results.getRow() + ", is first ? " + results.isFirst());


  // A call to last() moves cursor to the last row; the row number is also the row count

  results.last();


  // Get cursor position

  System.out.println("Cursor position " + results.getRow() + ", is last ? " + results.isLast());


  // A call to after last moves cursor past last row (before first row)

  results.afterLast();


  // Get cursor position

  System.out.println("Cursor position " + results.getRow() + ", is after last ? " + results.isAfterLast());


  // Move cursor to the third row

  results.absolute(3);


  // Get cursor position

  System.out.println("Cursor position " + results.getRow());


  // Move cursor to the last row

  results.absolute(-1);


  // Get cursor position

  System.out.println("Cursor position " + results.getRow() + ", is last ? " + results.isLast());


  // Move cursor to the forth last row

  results.absolute(-4);


  // Get cursor position

  System.out.println("Cursor position " + results.getRow());


  // Move cursor down 5 rows from the current row.  If this moves

  // cursor beyond the last row, cursor is put after the last row

  results.relative(5);


  // Get cursor position

  System.out.println("Cursor position " + results.getRow() + ", is after last ? " + results.isAfterLast());


  // Move cursor up 13 rows from the current row.  If this moves

  // cursor beyond the first row, cursor is put before the first row

  results.relative(-13);


  // Get cursor position

  System.out.println("Cursor position " + results.getRow() + ", is before first ? " + results.isBeforeFirst());


} catch (SQLException e) {

    System.out.println("Could not retrieve data from the database " + e.getMessage());

}

  }
}

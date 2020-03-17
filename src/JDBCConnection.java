import java.sql.*;

public class JDBCConnection {

    final static String DATABASE_URL = "jdbc:mysql://localhost:3306/";
    static Connection connection = null;
    static Statement statement = null;

    private static void createDatabase() throws SQLException {
        String sql_stmt = "CREATE DATABASE IF NOT EXISTS  `customers_db`;";

        statement = connection.createStatement();

        statement.executeUpdate(sql_stmt);

        System.out.println("customers_db has successfully been created");
    }

    public static void main(String[] args) {
        try {
            DriverManager.setLoginTimeout(23);
            connection = DriverManager.getConnection(DATABASE_URL, "root", "");

            createDatabase();
        } catch (SQLException ex) {
            System.out.println("The following exception has occured: " + ex.getMessage());
        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("The following exception has occured: " + ex.getMessage());
            }
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/db";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	public static void main(String[] args) {
		update(1);
	}

	private static void readAll() {
		//establish connection
		try{
			Connection conn = connect();

			//Create Statement
			String sql="SELECT * FROM tbl_students";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//Execute Statement
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println("ID: "+rs.getInt("id") +
						" NAME: "+rs.getString("first_name")+ 
						" "+rs.getString("last_name")+ " EMAIL:"
								+ " "+rs.getString("email"));
			}
			//Close Connection
		}catch(Exception e){
			System.out.println("Read Error: "+e.getMessage());
		}
	}
	private static void readById(int id) {
		//establish connection
		try{
			Connection conn = connect();

			//Create Statement
			String sql="SELECT * FROM tbl_students WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			//Execute Statement
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println("ID: "+rs.getInt("id") +
						" NAME: "+rs.getString("first_name")+ 
						" "+rs.getString("last_name")+ " EMAIL: "+rs.getString("email"));
			}
			//Close Connection
		}catch(Exception e){
			System.out.println("Read Error: "+e.getMessage());
		}
	}

	public static Connection connect() throws Exception{		
		//register database driver
		Class.forName(DRIVER);
		System.out.println("Driver Loaded Successfully...");
		//establish database connection		
		Connection conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
		System.out.println("Connection Established Successfully....");
		return conn;	
	}

	private static void save() {
		try{
			//establsh Connectiton
			Connection conn =  connect();
			//statetment
			Statement stmt = conn.createStatement();
			String sql="INSERT INTO tbl_students "
					+ "(first_name,last_name,email,password) "
					+ "VALUES('dummy text','dummy cast','dummy@dummy.com','dummy')";

			//execute
			Integer id = stmt.executeUpdate(sql);
			System.out.println("Record Saved Successfully at"+id);
			//close connection
			conn.close();
		}catch(Exception e){
			System.out.println("Error: "+e.getMessage());
		}
	}

	private static void update(int id) {
		try{
			//establsh Connectiton
			Connection conn =  connect();
			//statetment
			String sql ="UPDATE tbl_students SET first_name=?, last_name=? WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "ROH");
			stmt.setString(2, "GAUT");
			stmt.setInt(3, id);
			//execute
			stmt.executeUpdate();
			System.out.println("Record Updated Successfully");
			//close connection
			conn.close();
		}catch(Exception e){
			System.out.println("Error: "+e.getMessage());
		}
	}

	private static void delete(int id) {
		try{
			//establsh Connectiton
			Connection conn =  connect();
			//statetment
			String sql="DELETE FROM tbl_students WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			//execute
			stmt.executeUpdate();
			System.out.println("Record Deleted Successfully");
			//close connection
			conn.close();
		}catch(Exception e){
			System.out.println("Error: "+e.getMessage());
		}
	}


}
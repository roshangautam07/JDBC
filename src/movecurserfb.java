    import java.sql.*;  
import java.util.Scanner;
    class FetchRecord{  
    public static void main(String args[])throws Exception{  
        try{
            
        
      
    Class.forName("com.mysql.jdbc.Driver");  
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sqdl","root","");  
    Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); //move cursor forward and backward and updatable using ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE
    Scanner s = new Scanner(System.in);
    System.out.println("set");
    int ss =s.nextInt();
    ResultSet rs=stmt.executeQuery("select * from COMPANY where ID='"+ss+"'");  
       while(rs.next()){
        String a = rs.getString(1); 
        if(a.equals(ss)){
           
            System.out.println("set");
        }
            rs.updateString("NAME", "Alex");
            System.out.println("set");
       }
    //getting the record of 3rd row  
    /*rs.absolute(1);  
    System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)); */
    con.close(); 
        }catch(Exception e){System.out.println(e);}
      
     
    }}
    
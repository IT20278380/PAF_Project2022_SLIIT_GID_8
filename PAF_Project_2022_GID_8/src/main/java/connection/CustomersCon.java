package connection;

import java.sql.*;

public class CustomersCon {
	
	public Connection connect() { 
		 
		Connection con = null; 
 
		try { 

			Class.forName("com.mysql.jdbc.Driver");   
 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/PAF_Project2022_SLIIT", "root", "15263"); 
			System.out.println("----Connection Succsuss !----");
			return con;
		} 
 
		catch (Exception e) {
			
			e.printStackTrace();	
			System.out.println("----Connection Error !----");
			return con; 		
		} 

	}
	
	public Connection bank() { 
		 
		Connection con = null; 
 
		try { 

			Class.forName("com.mysql.jdbc.Driver");   
 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Payment_Server", "root", "15263"); 
			System.out.println("----Connection Succsuss !----");
			return con;
		} 
 
		catch (Exception e) {
			
			e.printStackTrace();	
			System.out.println("----Connection Error !----");
			return con; 		
		} 

	}
}

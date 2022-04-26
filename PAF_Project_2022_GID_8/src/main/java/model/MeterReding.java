package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.CustomersCon;
import connection.MeterRederCon;
import connection.UserAssignCon;

public class MeterReding {
	
	//Read All Users
			public String readAllUsers() { 
				 
				String output = ""; 
		 
				try { 
		 
					MeterRederCon meterRederCon = new MeterRederCon();
					Connection con = meterRederCon.connect();  
		 
					if (con == null) {
						System.err.println("----- Connection Error for Read ! -----");
						return "Error while connecting to the database for reading."; 
					} 
		 
					// Prepare the html table to be displayed
		 
					output = "<table border='1'><tr><th>ID</th><th>User ID</th><th>Full Name</th><th>Address</th>" + "<th>City</th>" + "<th>Mobile Number</th><th>Email</th><th>Power Plant</th><th>Remark</th>" + "<th>Update</th></tr>"; 
		 
					String query = "select * from powerplant"; 
					Statement stmt = con.createStatement(); 
					ResultSet rs = stmt.executeQuery(query); 
		 
					// iterate through the rows in the result set
					while (rs.next()) { 
		 
						String ID = Integer.toString(rs.getInt("id"));
						String UserID = rs.getString("UserID");; 
						String FullName = rs.getString("FullName"); 
						String Address = rs.getString("Address"); 
						String City = rs.getString("City"); 
						String MobileNumber = rs.getString("MobileNumber"); 				
						String Email = rs.getString("Email");
						String PowerPlant = rs.getString("PowerPlant");
						String Remark = rs.getString("Remark");
		 
						// Add into the html table
						output += "<tr><td>" + ID + "</td>";
						output += "<td>" + UserID + "</td>"; 
						output += "<td>" + FullName + "</td>"; 
						output += "<td>" + Address + "</td>"; 
						output += "<td>" + City + "</td>";  
						output += "<td>" + MobileNumber + "</td>";
						output += "<td>" + Email + "</td>";
						output += "<td>" + PowerPlant + "</td>";
						output += "<td>" + Remark + "</td>";
		 
						// buttons
						output += "<td><input name='btnUp' type='submit' value='View Details' class='btn btn-danger'></td></tr>"; 
		 
					} 
		 
					con.close(); 
					// Complete the html table
					output += "</table>"; 
				} 
		 
				catch (Exception e) { 
					output = "Error while reading the items."; 
					System.err.println(e.getMessage()); 
					System.err.println("----- Error for Read ! -----");
		 
				} 
		 
				return output;  
			}
			
		//Read All Users
			public String readOneUsers(String UserID) { 
				 
				String output = ""; 
		 
				try { 
		 
					MeterRederCon meterRederCon = new MeterRederCon();
					Connection con = meterRederCon.connect();  
		 
					if (con == null) {
						System.err.println("----- Connection Error for Read ! -----");
						return "Error while connecting to the database for reading."; 
					} 
		 
					// Prepare the html table to be displayed
		 
					output = "<table border='1'><tr><th>ID</th><th>User ID</th><th>Full Name</th><th>Address</th>" + "<th>City</th>" + "<th>Mobile Number</th><th>Email</th><th>Power Plant</th><th>Remark</th>" + "<th>Update</th></tr>"; 
		 
					String query = "select * from powerplant where UserID like ?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setString(1, UserID); 
					ResultSet rs = preparedStmt.executeQuery(); 
		 
					// iterate through the rows in the result set
					while (rs.next()) { 
		 
						String ID = Integer.toString(rs.getInt("id"));
						String UID = rs.getString("UserID");; 
						String FullName = rs.getString("FullName"); 
						String Address = rs.getString("Address"); 
						String City = rs.getString("City"); 
						String MobileNumber = rs.getString("MobileNumber"); 				
						String Email = rs.getString("Email");
						String PowerPlant = rs.getString("PowerPlant");
						String Remark = rs.getString("Remark");
		 
						// Add into the html table
						output += "<tr><td>" + ID + "</td>";
						output += "<td>" + UID + "</td>"; 
						output += "<td>" + FullName + "</td>"; 
						output += "<td>" + Address + "</td>"; 
						output += "<td>" + City + "</td>";  
						output += "<td>" + MobileNumber + "</td>";
						output += "<td>" + Email + "</td>";
						output += "<td>" + PowerPlant + "</td>";
						output += "<td>" + Remark + "</td>";
		 
						// buttons
						output += "<td><input name='btnUp' type='submit' value='Read Meter' class='btn btn-danger'></td></tr>"; 
		 
					} 
		 
					con.close(); 
					// Complete the html table
					output += "</table>"; 
				} 
		 
				catch (Exception e) { 
					output = "Error while reading the items."; 
					System.err.println(e.getMessage()); 
					System.err.println("----- Error for Read ! -----");
		 
				} 
		 
				return output;  
			}
			
		//Insert Unit
			public String insertUnit(String UserID, String FullName, String City, String MobileNumber, String Unit, String Remark) { 
				 
				String output = ""; 
		 
				try { 
					
					MeterRederCon meterRederCon = new MeterRederCon();
					Connection con = meterRederCon.connect(); 
		 
					if (con == null) {			
						return "Error while connecting to the database for inserting."; 	
					} 
		 
						// create a prepared statement
						String query = " insert into meterunit (`id`,`UserID`,`FullName`,`City`,`MobileNumber`,`Unit`,`Remark`)" + " values (?, ?, ?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, UserID); 
						preparedStmt.setString(3, FullName); 
						preparedStmt.setString(4, City); 
						preparedStmt.setString(5, MobileNumber); 
						preparedStmt.setString(6, Unit); 
						preparedStmt.setString(7, Remark); 
		 
						// execute the statement
						preparedStmt.execute();  
						con.close(); 
		 
						output = "Inserted successfully"; 
				} 
		 
				catch (Exception e) { 
		 
					output = "Error while inserting the item."; 
		 
					System.err.println(e.getMessage());
					System.err.println("----- Insert Error ! -----");
		 
				} 
				
		 		return output; 
			} 
			
		//Read Old Bills
			public String readAllBills() { 
				 
				String output = ""; 
		 
				try { 
		 
					MeterRederCon meterRederCon = new MeterRederCon();
					Connection con = meterRederCon.connect();  
		 
					if (con == null) {
						System.err.println("----- Connection Error for Read ! -----");
						return "Error while connecting to the database for reading."; 
					} 
		 
					// Prepare the html table to be displayed
		 
					output = "<table border='1'><tr><th>User ID</th><th>Full Name</th>" + "<th>City</th>" + "<th>Mobile Number</th><th>Unit</th>" + "<th>Price</th><th>Payment</th><th>Payment Date</th><th>Delete Bill</th><th>Update User</th><th>Insert Unit</th></tr>"; 

					String query = "select * from bill"; 
					Statement stmt = con.createStatement(); 
					ResultSet rs = stmt.executeQuery(query); 
		 
					// iterate through the rows in the result set
					while (rs.next()) { 
		 
						String UserID = rs.getString("UserID");; 
						String FullName = rs.getString("FullName"); 
						String City = rs.getString("City"); 
						String MobileNumber = rs.getString("MobileNumber"); 				
						String Unit = rs.getString("Unit");
						String Price = rs.getString("Price");
						String Payment = rs.getString("Payment");
						String Date = rs.getString("Date");
		 
						// Add into the html table
						output += "<tr><td>" + UserID + "</td>";
						output += "<td>" + FullName + "</td>"; 
						output += "<td>" + City + "</td>";  
						output += "<td>" + MobileNumber + "</td>";
						output += "<td>" + Unit + "</td>";
						output += "<td>" + Price + "</td>";
						output += "<td>" + Payment + "</td>";
						output += "<td>" + Date + "</td>";
		 
						// buttons
						output += "<td><input name='btnDel' type='submit' value='Delete' class='btn btn-danger'></td>"+
						"<td><input name='btnUp' type='submit' value='Update User' class='btn btn-danger'></td>"+
						"<td><input name='btnIn' type='submit' value='Insert Unit' class='btn btn-danger'></td></tr>"; 
		 
					} 
		 
					con.close(); 
					// Complete the html table
					output += "</table>"; 
				} 
		 
				catch (Exception e) { 
					output = "Error while reading the items."; 
					System.err.println(e.getMessage()); 
					System.err.println("----- Error for Read ! -----");
		 
				} 
		 
				return output;  
			}
			
		//Update Customer Profile
			public String UpdateUser(String UserID, String Remark) { 

				String output = ""; 
		 
				try { 
		 
					MeterRederCon meterRederCon = new MeterRederCon();
					Connection con = meterRederCon.connect();  
					
					if (con == null) {
						
						return "Error while connecting to the database for updating."; 
					} 
		 
					// create a prepared statement
					String query = "UPDATE PowerPlant SET Remark=? WHERE UserID=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
					// binding values
					preparedStmt.setString(1, Remark); 
					preparedStmt.setString(2, UserID); 
		 
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					output = "Updated successfully"; 
				} 
		 
				catch (Exception e) { 
					
					output = "Error while updating the item."; 
					System.err.println(e.getMessage()); 
				} 
		 
				return output; 
		 
			}

		//Delete Old Bill
			public String DeleteOldBill(String UserID) { 
				 
				String output = ""; 
		 
				try { 
		 
					MeterRederCon meterRederCon = new MeterRederCon();
					Connection con = meterRederCon.connect();  
		 
					if (con == null) {
						
						return "Error while connecting to the database for deleting."; 
					} 
		 
					// create a prepared statement
					String query = "delete from Bill where UserID=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
					// binding values
					preparedStmt.setString(1, UserID); 
		 
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					output = "Deleted successfully"; 
		 
				} 
		 
				catch (Exception e) { 
					output = "Error while deleting the item."; 
					System.err.println(e.getMessage()); 
		 
				} 
		 
				return output; 
		 
			}}

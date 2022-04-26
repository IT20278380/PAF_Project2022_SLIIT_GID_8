package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.BillCreationCon;
import connection.CustomersCon;
import connection.MeterRederCon;

public class BillCreation {

	//View MeterUnit
		public String ViewMeterunit() { 
				 
				String output = ""; 
		 
				try { 
		 
					BillCreationCon billCreationCon = new BillCreationCon();
					Connection con = billCreationCon.connect(); 
		 
					if (con == null) {
						System.err.println("----- Connection Error for Read ! -----");
						return "Error while connecting to the database for reading."; 
					} 
		 
					// Prepare the html table to be displayed
		 
					output = "<table border='1'><tr><th>User ID</th><th>Full Name</th>" + "<th>City</th>" + "<th>Mobile Number</th><th>Unit</th><th>Remark</th>" + "<th>Create Bill</th></tr>"; 

					// create a prepared statement
					String query = "select * from meterunit"; 
					Statement stmt = con.createStatement(); 
					ResultSet rs = stmt.executeQuery(query);
		 
					// iterate through the rows in the result set
					while (rs.next()) { 
						String UID = rs.getString("UserID"); 
						String FullName = rs.getString("FullName"); 
						String City = rs.getString("City"); 
						String MobileNumber = rs.getString("MobileNumber"); 				
						String Unit = rs.getString("Unit");
						String Remark = rs.getString("Remark");
		 
						// Add into the html table 
						output += "<tr><td>" + UID + "</td>"; 
						output += "<td>" + FullName + "</td>";  
						output += "<td>" + City + "</td>";  
						output += "<td>" + MobileNumber + "</td>";
						output += "<td>" + Unit + "</td>"; 
						output += "<td>" + Remark + "</td>";
		 
						// buttons
						output += "<td><form method='post' action='#'>" + "<input name='btnCB' type='submit' value='Create Bill' class='btn btn-danger'>" + "<input name='CustomersID' type='hidden' value='" + UID  + "'>" + "</form></td></tr>"; 
		 
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
		
		private String Price;
		//Insert Cal Bill
		public String CalBill(String UserID, String FullName, String City, String MobileNumber, String Unit, String Remark, String Price1_30, String Price30_60, String Price60_90, String Price90) { 
	 
			String output = ""; 
	 
			try { 
				
				BillCreationCon billCreationCon = new BillCreationCon();
				Connection con = billCreationCon.connect();  
	 
				if (con == null) {			
					return "Error while connecting to the database for inserting."; 	
				} 
				double value1 = 0;
				double value2 = 0;
				double value3 = 0;
				
				double price1 = 0;
				double price2 = 0;
				double price3 = 0;
				double price4 = 0;
			        try{
			            double value = Double.parseDouble(Unit);
			            if(value >= 0) {
			            	value1 = value-30;
			            		if(value <= 30) {
			            			price1 = Double.parseDouble(Price1_30)*value;
			            		}else {
			            			price1 = Double.parseDouble(Price1_30)*30;
			            		}
			            } if(value1 > 0) {	
			            	value2 = value1-30;
				            	if(value1 <= 30) {
			            			price2 = Double.parseDouble(Price30_60)*value1;
			            		}else {
			            			price2 = Double.parseDouble(Price30_60)*30;
			            		}
			            
			            } if(value2 > 0) {
			            	value3 = value2-30;
				            	if(value2 <= 30) {
			            			price3 = Double.parseDouble(Price60_90)*value2;
			            		}else {
			            			price3 = Double.parseDouble(Price60_90)*30;
			            		}
				            	
				            	if(value3 >= 0) {
				            		price4 = Double.parseDouble(Price90)*value3;
			            		}
			      
			            }
			            try {
			            	double remark = Double.parseDouble(Remark);
			            	double price = price1 + price2 + price3 + price4 + remark;
			            	Price = Double.toString(price);
			            }catch(NumberFormatException ex) {
			            	double price = price1 + price2 + price3 + price4;
			            	Price = Double.toString(price);
			            }
			             
			           System.out.println("-----  "+Price+"------------------"+price1+"-------"+price2+"-------"+price3+"------"+price4+"---------");
			        }
			        catch (NumberFormatException ex){
			            ex.printStackTrace();
			            System.out.println("------------Error catch Cal-----------");
			        }

					// create a prepared statement
					String query = " insert into Bill (`id`,`UserID`,`FullName`,`City`,`MobileNumber`,`Unit`,`Remark`,`Price`,`Payment`,`Date`)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
					// binding values
					preparedStmt.setInt(1, 0); 
					preparedStmt.setString(2, UserID); 
					preparedStmt.setString(3, FullName); 
					preparedStmt.setString(4, City); 
					preparedStmt.setString(5, MobileNumber); 
					preparedStmt.setString(6, Unit); 
					preparedStmt.setString(7, Remark); 
					preparedStmt.setString(8, Price);
					preparedStmt.setString(9, Price); 
					preparedStmt.setString(10, Price); 
	 
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
		
		//Update Bill
		public String UpdateBill(String UserID, String Price, String Remark) { 

			String output = ""; 
	 
			try { 
	 
				BillCreationCon billCreationCon = new BillCreationCon();
				Connection con = billCreationCon.connect(); 
				
				if (con == null) {
					
					return "Error while connecting to the database for updating."; 
				} 
	 
				// create a prepared statement
				String query = "UPDATE Bill SET Price=?, Remark=? WHERE UserID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
				// binding values
				preparedStmt.setString(1, Price); 
				preparedStmt.setString(2, Remark); 
				preparedStmt.setString(3, UserID); 
	 
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
		
		//Read No Payed User
		public String LostUser(String Remark) { 
			 
			String output = ""; 
			System.out.println(Remark);
			try { 
	 
				MeterRederCon meterRederCon = new MeterRederCon();
				Connection con = meterRederCon.connect();  
	 
				if (con == null) {
					System.err.println("----- Connection Error for Read ! -----");
					return "Error while connecting to the database for reading."; 
				} 
	 
				// Prepare the html table to be displayed
	 
				output = "<table border='1'><tr><th>ID</th><th>User ID</th><th>Full Name</th><th>Address</th>" + "<th>City</th>" + "<th>Mobile Number</th><th>Email</th><th>Power Plant</th><th>Remark</th>" + "<th>Update</th></tr>"; 
	 
				String query = "select * from powerplant where Remark like ?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, Remark); 
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
					String Rm = rs.getString("Remark");
	 
					// Add into the html table
					output += "<tr><td>" + ID + "</td>";
					output += "<td>" + UID + "</td>"; 
					output += "<td>" + FullName + "</td>"; 
					output += "<td>" + Address + "</td>"; 
					output += "<td>" + City + "</td>";  
					output += "<td>" + MobileNumber + "</td>";
					output += "<td>" + Email + "</td>";
					output += "<td>" + PowerPlant + "</td>";
					output += "<td>" + Rm + "</td>";
	 
					// buttons
					output += "<td><input name='btnUp' type='submit' value='Lost Connection' class='btn btn-danger'></td></tr>"; 
	 
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

		//Lost Connection(Delete)
		public String LostConnection(String UserID) { 
			 
			String output = ""; 
	 
			try { 
	 
				BillCreationCon billCreationCon = new BillCreationCon();
				Connection con = billCreationCon.connect(); 
	 
				if (con == null) {
					
					return "Error while connecting to the database for deleting."; 
				} 
	 
				// create a prepared statement
				String query1 = "select * from PowerPlant where UserID like '"+UserID+"'"; 
				String query2 = "delete from PowerPlant where UserID like '"+UserID+"'"; 
				
				Statement stmt = con.createStatement();
				PreparedStatement preparedStmt2 = con.prepareStatement(query2); 
	 
				// execute the statement
				ResultSet rs = stmt.executeQuery(query1);
				//preparedStmt2.execute();  
				
				try {
					// iterate through the rows in the result set
					while (rs.next()) { 
						String UID = rs.getString("UserID"); 
						String FullName = rs.getString("FullName"); 
						String Address = rs.getString("Address"); 
						String City = rs.getString("City"); 
						String MobileNumber = rs.getString("MobileNumber"); 				
						String Email = rs.getString("Email");
						String PowerPlant = rs.getString("PowerPlant");
		 
						String query3 = " insert into LostConnection (`id`,`UserID`,`FullName`,`Address`,`City`,`MobileNumber`,`Email`,`PowerPlant`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query3); 
						
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, UID); 
						preparedStmt.setString(3, FullName); 
						preparedStmt.setString(4, Address); 
						preparedStmt.setString(5, City); 
						preparedStmt.setString(6, MobileNumber);
						preparedStmt.setString(7, Email);
						preparedStmt.setString(8, PowerPlant);
		 
						// execute the statement
						preparedStmt.execute(); 
						output += "Insert successfully";
					}
					}catch(Exception e) { 
						output = "Error while deleting the item."; 
						System.err.println(e.getMessage()); 
			 
					} 
				output += "----Deleted successfully"; 
			}
			catch (Exception e) { 
				output = "Error while deleting the item."; 
				System.err.println(e.getMessage()); 
	 
			} 
	 
			return output; 
	 
		}
}

package com;

//For REST Services
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

import model.Customers;

@Path("/Customers") 
public class CustomersService {
	
	Customers customers = new Customers();
	
	//Add Customers
	@POST
	@Path("/Add") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertCustomers(@FormParam("FullName") String FullName, 
	 @FormParam("Address") String Address, 
	 @FormParam("City") String City, 
	 @FormParam("MobileNumber") String MobileNumber,
	 @FormParam("Email") String Email) { 
		
	 String output = customers.insertCustomers(FullName, Address, City, MobileNumber, Email); 
		return output; 
	}
	
	// Read Users Profile
	@GET
	@Path("/One") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String ViewUsersProfile(String ProfileData) {
				
	 //Convert the input string to an XML document
	 Document doc = Jsoup.parse(ProfileData, "", Parser.xmlParser()); 
				 
	 //Read the value from the element <itemID>
	 String UserID = doc.select("UserID").text(); 
	 String output = customers.ViewUsersProfile(UserID); 
			
		return output; 
	} 
		
	
	//Update Users
	@PUT
	@Path("/Update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String UpdateProfile(String UsersData) {
				
	 //Convert the input string to a JSON object 
	 JsonObject jsonobj = new JsonParser().parse(UsersData).getAsJsonObject(); 
			 
	 //Read the values from the JSON object
	 String UserID = jsonobj.get("UserID").getAsString(); 
	 String FullName = jsonobj.get("FullName").getAsString();
	 String Address = jsonobj.get("Address").getAsString();
	 String City = jsonobj.get("City").getAsString();
	 String MobileNumber = jsonobj.get("MobileNumber").getAsString();
	 String Email = jsonobj.get("Email").getAsString();
			 
	 String output = customers.UpdateProfile(UserID, FullName, Address, City, MobileNumber, Email); 
		 
		 return output; 
	}

	//Delete Users
	@DELETE
	@Path("/Delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String DeleteProfiles(String ProfileData) {
			
	 //Convert the input string to an XML document
	 Document doc = Jsoup.parse(ProfileData, "", Parser.xmlParser()); 
			 
	 //Read the value from the element <itemID>
	 String UserID = doc.select("UserID").text(); 
	 String output = customers.DeleteProfiles(UserID); 
		
		return output; 
	}
	
	// Read Bill 
	@GET
	@Path("/Bill") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String ViewUsersBill(String ProfileData) {
				
	 //Convert the input string to an XML document
	 Document doc = Jsoup.parse(ProfileData, "", Parser.xmlParser()); 
				 
	 //Read the value from the element <itemID>
	 String UserID = doc.select("UserID").text(); 
	 String output = customers.ViewUsersBill(UserID); 
			
		return output; 
	}
	
	//Pay bill
		@POST
		@Path("/Payment") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String Payment(@FormParam("UserID") String UserID,
				@FormParam("FullName") String FullName,
				@FormParam("BankName") String BankName,
				@FormParam("AccName") String AccName,
				@FormParam("AccNo") String AccNo,
				@FormParam("BrachName") String BrachName,
				@FormParam("BillPrice") String BillPrice,
				@FormParam("Date") String Date) {
					
		/* //Convert the input string to a JSON object 
		 JsonObject jsonobj = new JsonParser().parse(UsersData).getAsJsonObject(); 
				 
		 //Read the values from the JSON object
		 String UserID = jsonobj.get("UserID").getAsString();
		 String FullName = jsonobj.get("FullName").getAsString();
		 String BackName = jsonobj.get("BackName").getAsString(); 
		 String AccName = jsonobj.get("AccName").getAsString();
		 String AccNo = jsonobj.get("AccNo").getAsString();
		 String BrachName = jsonobj.get("BrachName").getAsString();
		 String BillPrice = jsonobj.get("BillPrice").getAsString();
		 String Date = jsonobj.get("Date").getAsString();*/
				 
		 String output = customers.Payment(UserID, FullName, BankName, AccName, AccNo, BrachName, BillPrice, Date); 
			 
			 return output; 
		}
}

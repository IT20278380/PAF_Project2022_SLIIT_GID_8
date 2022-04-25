package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

import model.UserAssign;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 



@Path("/UserAssing") 
public class UserAssignService {
	
	UserAssign userAssing = new UserAssign(); 

	// Read Pending Users 
	@GET
	@Path("/Pending") 
	@Produces(MediaType.TEXT_HTML) 
	public String readPendingUsers() { 

		return userAssing.readPendingUsers(); 
	} 
	
	// Read Pending One Users 
	@GET
	@Path("/Pending/One") 
	@Produces(MediaType.TEXT_HTML) 
	public String readPendingOneUsers(String CustomersData) { 
			
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(CustomersData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		String CustomersID = doc.select("CustomersID").text();
		String output = userAssing.readPendingOneUsers(CustomersID);
		
			return output; 
	} 
	
	//Add User
	@POST
	@Path("/Add") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String UserAssign(@FormParam("UserID") String UserID, 
	 @FormParam("FullName") String FullName, 
	 @FormParam("Address") String Address, 
	 @FormParam("City") String City, 
	 @FormParam("MobileNumber") String MobileNumber,
	 @FormParam("Email") String Email,
	 @FormParam("PowerPalnt") String PowerPalnt) { 
		
	String output = userAssing.UserAssign(UserID, FullName, Address, City, MobileNumber, Email, PowerPalnt); 
	
		return output; 
	}
	
	// Read All Users 
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readUsers() { 

			return userAssing.readUsers(); 
		} 
	
	//Update Users
	@PUT
	@Path("/Update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String UpdatePowerPlant(String UsersData) {
			
	//Convert the input string to a JSON object 
	 JsonObject jsonobj = new JsonParser().parse(UsersData).getAsJsonObject(); 
		 
	//Read the values from the JSON object
	 String UserID = jsonobj.get("UserID").getAsString(); 
	 String PowerPalnt = jsonobj.get("PowerPalnt").getAsString(); 
		 
	 String output = userAssing.UpdatePowerPlant(UserID, PowerPalnt); 
		 
		 return output; 
	}
		
	//Delete Users
	@DELETE
	@Path("/Pending/Delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String DeletePendigUsers(String CustomersData) {
		
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(CustomersData, "", Parser.xmlParser()); 
		 
	//Read the value from the element <itemID>
	String itemID = doc.select("CustomersID").text(); 
	String output = userAssing.DeletePendigUsers(itemID); 
	
		return output; 
	}

}

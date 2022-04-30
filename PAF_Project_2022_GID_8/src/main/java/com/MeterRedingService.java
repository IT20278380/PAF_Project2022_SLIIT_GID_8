package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.MeterReding;

@Path("/MeterReader") 
public class MeterRedingService {

	MeterReding meterReading = new MeterReding();
	
	// Read All Users 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readAllUsers() { 

		return meterReading.readAllUsers(); 
	} 
	
	// Read One Users 
	@GET
	@Path("/One") 
	@Produces(MediaType.TEXT_HTML) 
	public String readOneUsers(String UserData) { 
				
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(UserData, "", Parser.xmlParser()); 
			 
	//Read the value from the element <itemID>
	String UserID = doc.select("UserID").text();
	String output = meterReading.readOneUsers(UserID);
			
		return output; 
	} 
	
	//Add Meter Unit
	@POST
	@Path("/Add") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUnit(@FormParam("UserID") String UserID, 
	 @FormParam("FullName") String FullName,  
	 @FormParam("City") String City, 
	 @FormParam("MobileNumber") String MobileNumber,
	 @FormParam("Unit") String Unit,
	 @FormParam("Remark") String Remark) { 
		
	 String output = meterReading.insertUnit(UserID, FullName, City, MobileNumber, Unit, Remark); 
		return output; 
	}
	
	// Read Bill 
	@GET
	@Path("/Bill") 
	@Produces(MediaType.TEXT_HTML) 
	public String readAllBills() { 

		return meterReading.readAllBills(); 
	}
	
	//Update Users
	@PUT
	@Path("/Update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String UpdateUser(String UsersData) {
				
	 //Convert the input string to a JSON object 
	 JsonObject jsonobj = new JsonParser().parse(UsersData).getAsJsonObject(); 
			 
	 //Read the values from the JSON object
	 String UserID = jsonobj.get("UserID").getAsString(); 
	 String Remark = jsonobj.get("Remark").getAsString();
			 
	 String output = meterReading.UpdateUser(UserID, Remark); 
		 
		 return output; 
	}
	
	//Delete Users
	@DELETE
	@Path("/Delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String DeleteOldBill(String UserData) {
			
	 //Convert the input string to an XML document
	 Document doc = Jsoup.parse(UserData, "", Parser.xmlParser()); 
			 
	 //Read the value from the element <itemID>
	 String UserID = doc.select("UserID").text(); 
	 String output = meterReading.DeleteOldBill(UserID); 
		
		return output; 
	}
}

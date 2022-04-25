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

import model.BillCreation;

@Path("/BillCreation") 
public class BillCreationService {
	
	BillCreation billCreation = new BillCreation();
	
	// Read All Meter 
	@GET
	@Path("/meterReader") 
	@Produces(MediaType.TEXT_HTML) 
	public String ViewMeterunit() { 

		return billCreation.ViewMeterunit(); 
	}
	
	//Create Bill
	@POST
	@Path("/Add") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String CalBill(@FormParam("UserID") String UserID, 
	 @FormParam("FullName") String FullName, 
	 @FormParam("City") String City, 
	 @FormParam("MobileNumber") String MobileNumber,
	 @FormParam("Unit") String Unit,
	 @FormParam("Remark") String Remark, 
	 @FormParam("Price1_30") String Price1_30, 
	 @FormParam("Price30_60") String Price30_60, 
	 @FormParam("Price60_90") String Price60_90, 
	 @FormParam("Price90") String Price90) { 
		
	 String output = billCreation.CalBill(UserID, FullName, City, MobileNumber, Unit, Remark, Price1_30, Price30_60, Price60_90, Price90); 
		return output; 
	}
	
	//UpdateBill
	@PUT
	@Path("/Update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String UpdateBill(String UsersData) {
				
	 //Convert the input string to a JSON object 
	 JsonObject jsonobj = new JsonParser().parse(UsersData).getAsJsonObject(); 
			 
	 //Read the values from the JSON object
	 String UserID = jsonobj.get("UserID").getAsString(); 
	 String Price = jsonobj.get("Price").getAsString();
	 String Remark = jsonobj.get("Remark").getAsString();
			 
	 String output = billCreation.UpdateBill(UserID, Price, Remark); 
		 
		 return output; 
	}
	
	//Lost User
	@GET
	@Path("/LostUser") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String LostUser(String UserData) {
			
	 //Convert the input string to an XML document
	 Document doc = Jsoup.parse(UserData, "", Parser.xmlParser()); 
			 
	 //Read the value from the element <itemID>
	 String Remark = doc.select("Remark").text(); 
	 String output = billCreation.LostUser(Remark); 
		
		return output; 
	}
	
	//Delete Users
	@DELETE
	@Path("/Delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String LostConnection(String UserData) {
			
	 //Convert the input string to an XML document
	 Document doc = Jsoup.parse(UserData, "", Parser.xmlParser()); 
			 
	 //Read the value from the element <itemID>
	 String UserID = doc.select("UserID").text(); 
	 String output = billCreation.LostConnection(UserID); 
		
		return output; 
	}
}

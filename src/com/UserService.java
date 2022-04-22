//To implement the RESTful API
package com;

import model.User;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
*/
@Path("/Users")
public class UserService {
	
	User userObj = new User();
	
	//view customer details
	/*
	 * @GET
	 * 
	 * @Path("/{userId}")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public String readCustomer() { return
	 * userObj.readCustomer(); }
	 */
	
	//Add customer (registration)
	@POST
	@Path("/")
	// To specify the input type as form data
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	// To specify the form elements as the parameters to the register() method
	public String register(@FormParam("firstName") String firstName, 
							@FormParam("lastName") String lastName,
							@FormParam("NIC") String NIC, 
							@FormParam("address") String address,
							@FormParam("phone") int phone,
							@FormParam("email") String email)
	
	{
		// pass these values to the register() method, return into a String variable
		// named output, which can be returned to the client as the response.
		String output = userObj.register(firstName, lastName, NIC, address, phone, email);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String customerData) {
		
		// Convert the input string to a JSON object
		JsonObject UserObject = new JsonParser().parse(customerData).getAsJsonObject();

		// Read the values from the JSON object
		String userId = UserObject.get("userId").getAsString();
		String firstName = UserObject.get("firstName").getAsString();
		String lastName = UserObject.get("lastName").getAsString();
		String NIC = UserObject.get("NIC").getAsString();
		String address = UserObject.get("address").getAsString();
		String phone = UserObject.get("phone").getAsString();
		String email = UserObject.get("email").getAsString();


		String output = userObj.updateCustomer(userId, firstName, lastName, NIC, address, phone, email);
		return output;
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteCustomer(String customerData) { 
	
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <itemID>
		String userId = doc.select("userId").text(); 
		
		//Pass this itemID can call the deleteItem() method in the modeland
		String output = userObj.deleteCustomer(userId); 
		return output; 
	}
}

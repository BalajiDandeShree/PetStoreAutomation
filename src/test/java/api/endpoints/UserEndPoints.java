package api.endpoints;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// This class is used for CRUD methods of implementation
public class UserEndPoints {
	
	

	public static Response createUser(User payload) {
		
		Response response = given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)// this is for header
		   .body(payload)
		.when()
		 .post(Routes.post_url);
		System.out.println(Routes.post_url);
		
		return response;
		
		
		
	}
	
	
public static Response rearUser(String userName) {
		
		Response response = given()
				 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		   .pathParam("username", userName)
		   
		.when()
		 .get(Routes.get_url);
		
		
		return response;
		
	}
public static Response updateUser(User payload,String userName) {
	
	Response response = given()
	   .contentType(ContentType.JSON)
	   .accept(ContentType.JSON)
	   .body(payload)
	   .pathParam("username", userName)
	.when()
	 .put(Routes.update_url);
	
	return response;
	
}

public static Response deleteUser(String userName) {
	
	Response response = given()
	   
	//   .accept(ContentType.JSON)
	   .pathParam("username", userName)
	   
	.when()
	 .delete(Routes.delete_url);
	
	return response;
	
}


}

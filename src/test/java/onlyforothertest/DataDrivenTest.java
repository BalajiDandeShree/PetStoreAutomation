package onlyforothertest;

import java.util.Hashtable;


import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;

import io.restassured.response.Response;

public class DataDrivenTest {

	
@Test(priority = 1,dataProvider = "Data", dataProviderClass = DataProviders.class)
public void test_post_User(Hashtable<String , String> hashTable) {

		 User userPayload = new User();
		 
		 userPayload.setId(Integer.parseInt(hashTable.get("UserId")));
		 System.out.println(userPayload.getId());
		 userPayload.setUserName(hashTable.get("UserName"));
		 userPayload.setFirstName(hashTable.get("FirstName"));
		 userPayload.setLastName(hashTable.get("LastName"));
		 userPayload.setEmail(hashTable.get("Email"));
		 userPayload.setPassword(hashTable.get("Password"));
		 userPayload.setPhone(hashTable.get("Phone"));
		System.out.println(userPayload);
		 Response response = UserEndPoints.createUser(userPayload);
			response.then().log().body();
			Assert.assertEquals(response.getStatusCode(), 200);	
		
}

@Test(priority = 2,dataProvider = "UserNames", dataProviderClass = DataProviders.class)
public void getUserByName(String userName) {
	
	Response response = UserEndPoints.rearUser(userName);
	response.then().log().body();
	Assert.assertEquals(response.getStatusCode(), 200);
	System.out.println("User name "+response.body().jsonPath().getString("username"));
}

//@Test(priority = 3,dataProvider = "UserNames", dataProviderClass = DataProviders.class)
public void test_Delete_by_UserName(String userName) {
	
	Response response = UserEndPoints.deleteUser(userName);
	Assert.assertEquals(response.getStatusCode(), 200);
}



}



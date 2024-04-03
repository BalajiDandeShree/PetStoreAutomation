package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	
@Test(priority = 1,dataProvider = "Data", dataProviderClass = DataProviders.class)
public void test_post_User(String userId,String userName,String firstName,String lastName,String email,String pass, String phone) {
		 User userPayload = new User();
		 
		 userPayload.setId(Integer.parseInt(userId));
		 System.out.println(userPayload.getId());
		 userPayload.setUserName(userName);
		 userPayload.setFirstName(firstName);
		 userPayload.setLastName(lastName);
		 userPayload.setEmail(email);
		 userPayload.setPassword(pass);
		 userPayload.setPhone(phone);
		
		 Response response = UserEndPoints.createUser(userPayload);
			response.then().log().body();
			Assert.assertEquals(response.getStatusCode(), 200);	
}

@Test(priority = 2,dataProvider = "UserNames", dataProviderClass = DataProviders.class)
public void getUserByName(String userName) {
	
	Response response = UserEndPoints.rearUser(userName);
	response.then().log().body();
	Assert.assertEquals(response.getStatusCode(), 200);
}

@Test(priority = 3,dataProvider = "UserNames", dataProviderClass = DataProviders.class)
public void test_Delete_by_UserName(String userName) {
	
	Response response = UserEndPoints.deleteUser(userName);
	Assert.assertEquals(response.getStatusCode(), 200);
}



}



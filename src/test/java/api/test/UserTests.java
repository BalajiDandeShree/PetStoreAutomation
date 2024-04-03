package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
/*{
"id": 784251202,
"username": "dil.kua7",
"firstName": "aswq",
"lastName": "dfd",
"email": "strinaswas.coasa@asaa.comg",
"password": "ramjane1233",
"phone": "99568985895",
"userStatus": 0
}
*/


public class UserTests {
	
	
	Faker faker;
	User userPayload;
	JSONObject js;
	public Logger logger;
	@BeforeClass
	public void setUp() {
		/*
		 * Double randon = Math.random(); String s = randon.toString().substring(5,8);
		 * int a= Integer.parseInt(s);
		 */
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUserName(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		//userPayload.setEmail("asa"+s+"@gmail.com");
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password(5, 10));
		
		//Logs
		logger = LogManager.getLogger(this.getClass());
		
		
		
	//System.out.println(userPayload.toString());
		 js = new JSONObject(userPayload);
		 System.out.println(js);
	}
	
	@Test(priority = 1)
	public void test_post_User() {
		
		logger.info("******Creating user ***************" + userPayload );
		
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		//System.out.println(response.body().jsonPath().getString("message"));
		logger.info("******User is Created  ***************");
	}
	
	@Test(priority = 2)
	public void getUserByName() {
		
		logger.info("******Reading  user Info  ***************");
		Response response = UserEndPoints.rearUser(userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("****** User Info displayed ***************");
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		
		
		logger.info("******Updating  user ***************");
		// update pay load using some data
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
	
		Response response = UserEndPoints.updateUser(userPayload,this.userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("****** user info Updated ***************");
	}
	
	@Test(priority = 4)
	public void testDeleteUserByUserName() {
		
		logger.info("******Deletig user ***************");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("****** user  deleated ***************");
	}
	
}

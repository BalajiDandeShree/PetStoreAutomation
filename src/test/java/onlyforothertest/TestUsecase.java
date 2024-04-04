package onlyforothertest;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TestUsecase {
	
	
	@Test
	public void testStripe() {
		
		Response response = given()
		.auth().basic("sk_test_51P1mwQSD5Fx3LCy6WEP3FRugou4G6XJSZ0kdHq6rbaB2PN7Tn1Ftu4YMLUf0SKWMptBc5P1JXgfTrwh7E3Ze5IU400hqBbaHrc","")
		.formParam("name", "Balaji")
		.formParam("email", "askas@aswqs.com")
		.formParam("address[city]", "Pune ")
		.formParam("address[line1]", "mu aloak ")
		.formParam("preferred_locales[0]", "syesas")
		.formParam("invoice_settings[footer]", "online")
		.formParam("invoice_settings.custom_fields[name]", "abc")
		.when()
		.post("https://api.stripe.com/v1/customers");
		
		response.prettyPrint();
	
	}

}

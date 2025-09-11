package api.ChainingEndToEnd;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	

//	{
//	    "name":"{{name_env}}", 
//	    "email":"{{email_env}}", 
//	    "status":"active"
//	}
	
	@Test
	void test_updateUser(ITestContext context) {
		
		Faker faker=new Faker();
		JSONObject data= new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken = "6bfc8b531c3ba6219ca170279e878e71b388cd8ebbfbec1d311b453f886cf8de";
		int id = (Integer) context.getSuite().getAttribute("user_id"); //this should come from createUser request
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("id", id)
			.body(data.toString())
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200).log().all();
		
		
	}

}

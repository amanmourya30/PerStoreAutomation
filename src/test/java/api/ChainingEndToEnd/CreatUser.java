package api.ChainingEndToEnd;

import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;


public class CreatUser {
	
//	{
//	    "name":"{{name_env}}", 
//	    "gender":"male", 
//	    "email":"{{email_env}}", 
//	    "status":"inactive"
//	}
	
	@Test
	void test_createUser(ITestContext context) {
		
		Faker faker=new Faker();
		JSONObject data= new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken = "6bfc8b531c3ba6219ca170279e878e71b388cd8ebbfbec1d311b453f886cf8de";
		
		int id =given()
					.headers("Authorization","Bearer "+bearerToken)
					.contentType("application/json")
					.body(data.toString())
				.when().post("https://gorest.co.in/public/v2/users")
				.jsonPath().get("id");
		
		System.out.println("Generate ID is: "+ id);
		context.getSuite().setAttribute("user_id", id);
		
	}
	
	

}

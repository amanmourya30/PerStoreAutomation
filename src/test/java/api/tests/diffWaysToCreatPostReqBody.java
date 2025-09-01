package api.tests;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import api.endpoints.StudentsRoutes;
import io.restassured.response.Response;

//start local server: json-server --watch db.json --port 3000
//creating request body using HashMap
//using ORG.JSON library

public class diffWaysToCreatPostReqBody {
	
	String createdStudentId;

	//@Test(priority = 1)
	public void testUsingHashMap() {
		Map<String, Object> data = new HashMap<>();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456789");
		String[] courseArr = { "C", "C++" };
		data.put("courses", courseArr);

		Response response = given().contentType("application/json")
				.body(data)
				.when().post(StudentsRoutes.post_url);
		
		response.then().statusCode(201).log().all();

		createdStudentId = response.jsonPath().getString("id");
		System.out.println("Created student with ID: " + createdStudentId);

	}
	
	@Test
	public void testUsingOrgJsonLibrary() {
		JSONObject data = new JSONObject();
		
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456789");
		String[] courseArr = { "C", "C++" };
		data.put("courses", courseArr);
		
		Response response = given().contentType("application/json")
				.body(data.toString())
				.when().post(StudentsRoutes.post_url);
		
		response.then().statusCode(201).log().all();

		createdStudentId = response.jsonPath().getString("id");
		System.out.println("Created student with ID: " + createdStudentId);		
	}

	@Test(priority = 2, dependsOnMethods = "testUsingOrgJsonLibrary")
	public void testDeleteStudent() {
		given().pathParam("id", createdStudentId)
		.when().delete(StudentsRoutes.delete_url)
		.then().statusCode(200).log().all();
	}

	@BeforeMethod
	public void delay() throws InterruptedException {
		Thread.sleep(500);
	}
}

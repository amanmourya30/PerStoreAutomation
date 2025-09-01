package api.tests;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.endpoints.StudentsRoutes;
import api.payload.Student;
import io.restassured.response.Response;

//start local server: json-server --watch db.json --port 3000
//creating request body using HashMap
//using ORG.JSON library

public class diffWaysToCreatPostReqBody {

	String createdStudentId;

	// @Test(priority = 1)
	public void testUsingHashMap() {
		Map<String, Object> data = new HashMap<>();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456789");
		String[] courseArr = { "C", "C++" };
		data.put("courses", courseArr);

		Response response = given().contentType("application/json").body(data).when().post(StudentsRoutes.post_url);

		response.then().statusCode(201).log().all();

		createdStudentId = response.jsonPath().getString("id");
		System.out.println("Created student with ID: " + createdStudentId);

	}

	// @Test
	public void testUsingOrgJsonLibrary() {
		JSONObject data = new JSONObject();

		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456789");
		String[] courseArr = { "C", "C++" };
		data.put("courses", courseArr);

		Response response = given().contentType("application/json").body(data.toString()).when()
				.post(StudentsRoutes.post_url);

		response.then().statusCode(201).log().all();

		createdStudentId = response.jsonPath().getString("id");
		System.out.println("Created student with ID: " + createdStudentId);
	}

	// using POJO class
	// @Test(priority=1)
	void testUsingPOJOClass() {
		Student data = new Student();
		data.setName("aman");
		data.setLocation("New york");
		data.setPhone("123456789");
		String[] coursesarr = { "selenium", "java" };
		data.setCourses(coursesarr);

		Response response = given().contentType("application/json").body(data).when().post(StudentsRoutes.post_url);

		response.then().statusCode(201).log().all();

		createdStudentId = response.jsonPath().getString("id");
		System.out.println("Created student with ID: " + createdStudentId);
	}

	// using External json file
	@Test(priority = 1)
	void testUsingExternalJsonFile() throws FileNotFoundException {

		File f = new File(".//body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);

		Response response = given().contentType("application/json").body(data.toString()).when()
				.post(StudentsRoutes.post_url);

		response.then().statusCode(201).log().all();

		createdStudentId = response.jsonPath().getString("id");
		System.out.println("Created student with ID: " + createdStudentId);
	}

	@Test(priority = 2)
	public void testDeleteStudent() {
		given().pathParam("id", createdStudentId).when().delete(StudentsRoutes.delete_url).then().statusCode(200).log()
				.all();
	}

	@BeforeMethod
	public void delay() throws InterruptedException {
		Thread.sleep(500);
	}
}

package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StudentsEndPoints;
import api.payload.Student;
import io.restassured.response.Response;

public class StudentsTest {

	Faker faker;
	Student studentPayload;
	String studentId; // store ID for later tests

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		studentPayload = new Student();
		studentPayload.setName(faker.name().fullName());
		studentPayload.setLocation(faker.address().city());
		studentPayload.setPhone(faker.phoneNumber().cellPhone());
		studentPayload.setCourses(new String[] { "Mathematics", "Science", "History" });
	}

	@Test(priority = 1)
	public void testCreateStudent() {
		Response response = StudentsEndPoints.createStudent(studentPayload);
		response.then().log().all();

		System.out.println("Create Status Code: " + response.getStatusCode());

		// Accept either 200 or 201
		Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 201,
				"Expected status code 200 or 201 but got " + response.getStatusCode());

		// save id for later tests
		studentId = response.jsonPath().getString("id");
		System.out.println(studentId);
	}

	@Test(priority = 2, dependsOnMethods = "testCreateStudent")
	public void testGetStudentById() {
		Response response = StudentsEndPoints.readStudent(studentId);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("id"), studentId); // use getString
	}

	@Test(priority = 3, dependsOnMethods = "testCreateStudent")
	public void testUpdateStudent() {
		// update some fields
		studentPayload.setName("Updated " + faker.name().firstName());
		studentPayload.setLocation("UpdatedCity");

		Response response = StudentsEndPoints.updateStudent(studentId, studentPayload);
		response.then().log().all();

		System.out.println("Update Status Code: " + response.getStatusCode());
		// json-server returns 200 for PUT
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 4, dependsOnMethods = "testCreateStudent")
	public void testDeleteStudent() {
		Response response = StudentsEndPoints.deleteStudent(studentId);
		response.then().log().all();

		System.out.println("Delete Status Code: " + response.getStatusCode());
		// json-server returns 200 for DELETE
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@BeforeMethod
	public void delay() throws InterruptedException {
		Thread.sleep(1000);
	}
}

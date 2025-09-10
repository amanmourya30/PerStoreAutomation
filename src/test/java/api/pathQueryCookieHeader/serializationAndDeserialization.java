package api.pathQueryCookieHeader;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import api.payload.Student;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class serializationAndDeserialization {

	//@Test
	void testSerialization() {
		String [] courses= {"python","c#"};
		Student stu= new Student("amansingh", "newYork", "987654321", courses );
		
		
		given().contentType(ContentType.JSON)
				.body(stu)
		.when().post("http://localhost:3000/students")
		.then().statusCode(201).log().body();
		
		
	}
	
	@Test
	void testDeserialization() {
		Student stuResponse =
			    RestAssured.given()
			        .when()
			            .get("http://localhost:3000/students/6d65")
			        .then()
			            .extract()
			            .as(Student.class); // <-- JSON -> POJO

			System.out.println(stuResponse.getName()); // amansingh
			System.out.println(stuResponse.getLocation()); // newYork

	}
}

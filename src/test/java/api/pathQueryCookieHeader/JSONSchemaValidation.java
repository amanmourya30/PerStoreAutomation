package api.pathQueryCookieHeader;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class JSONSchemaValidation {

	@Test
	void jsonSchemaValidationTest() {
		given().when().get("http://localhost:3000/students").then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("studentsSchema.json"));
	}
}

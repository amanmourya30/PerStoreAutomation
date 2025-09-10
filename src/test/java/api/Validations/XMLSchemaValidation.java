package api.Validations;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers;

public class XMLSchemaValidation {

	@Test
	void jsonSchemaValidationTest() {
		given()
		.when().get("https://www.w3schools.com/xml/plant_catalog.xml")
		.then()
		.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("plantsxmlSchema.xsd"));
	}
}

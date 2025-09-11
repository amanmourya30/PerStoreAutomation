package api.ChainingEndToEnd;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {

	@Test
	void test_deleteUser(ITestContext context) {
		
		int id = (Integer) context.getSuite().getAttribute("user_id"); //this should come from createUser request
		String bearerToken = "6bfc8b531c3ba6219ca170279e878e71b388cd8ebbfbec1d311b453f886cf8de";

		given()
				.headers("Authorization","Bearer "+bearerToken)
				.pathParam("id", id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			.log().all();
	}
}

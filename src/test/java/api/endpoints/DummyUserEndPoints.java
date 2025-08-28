package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.DummyUser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DummyUserEndPoints {

	// CREATE employee
	public static Response createUser(DummyUser payload) {
		return given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).log().body() // log request
																											// JSON
				.when().post(DummyRoutes.post_url);
	}

	// READ employee by ID
	public static Response readUser(int userId) {
		return given().pathParam("id", userId).when().get(DummyRoutes.get_url);
	}

	// UPDATE employee by ID
	public static Response updateUser(int userId, DummyUser payload) {
		return given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("id", userId).body(payload)
				.when().put(DummyRoutes.update_url);
	}

	// DELETE employee by ID
	public static Response deleteUser(int userId) {
		return given().pathParam("id", userId).when().delete(DummyRoutes.delete_url);
	}
}

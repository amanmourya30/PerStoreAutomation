package api.pathQueryCookieHeader;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class PathAndQueryParam {

	// https://reqres.in/api/users?page=2&id=5

	@Test(priority = 1)
	void testPathAndQueryParams() {
		given().header("x-api-key", "reqres-free-v1")
				.pathParam("mypath", "users")
				.queryParam("page", 2).queryParam("id", 5)

				.when()
				.get("https://reqres.in/api/{mypath}")
				.then()
				.statusCode(200).log().all();
	}

}

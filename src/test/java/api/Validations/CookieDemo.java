package api.Validations;

import static io.restassured.RestAssured.given;

import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class CookieDemo {

	//https://www.google.com/

	//@Test(priority = 1)
	void testCookie() {
		given()
		.when().get("https://www.google.com/")
		.then().cookie("AEC")
		.log().all();
	}
	
	@Test(priority = 2)
	void testCookiesInfo() {
		Response res = given()
		.when().get("https://www.google.com/");
		
		String cookie_value =res.getCookie("AEC");
		System.out.println("AEC cookie value is: "+cookie_value);
		
		Map<String,String>cookies_values =res.cookies();
		
		for(String k:cookies_values.keySet()) {
			String cookie_val = res.getCookie(k);
			System.out.println(k+"       "+cookie_val);
		}
	}

}

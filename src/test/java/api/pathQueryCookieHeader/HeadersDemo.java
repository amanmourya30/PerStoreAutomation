package api.pathQueryCookieHeader;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {

	//https://www.google.com/

	//@Test(priority = 1)
	void testHeader() {
		given()
		.when().get("https://www.google.com/")
		.then().header("Content-Type", "text/html; charset=ISO-8859-1")
				.and()
				.header("Content-Encoding", "gzip")
				.and()
				.header("Server", "gws").log().all();
	}
	
	@Test(priority = 2)
	void testHeadersInfo() {
		Response res= given()
		.when().get("https://www.google.com/");
		
		String contentHeader = res.getHeader("Content-Type");
		System.out.println(contentHeader);
		
		Headers myHeaders =res.getHeaders();
		for(Header hd:myHeaders) {
			System.out.println(hd.getName()+"  =====>  "+hd.getValue());
		}	
	}
	
	

}

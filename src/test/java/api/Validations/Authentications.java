package api.Validations;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class Authentications {


    @Test(priority = 1)
    public void testBasicAuth() {
    	// Basic Auth with Postman Echo
    	given()
    	  .auth().basic("postman","password")
    	.when()
    	  .get("https://postman-echo.com/basic-auth")
    	.then()
    	  .statusCode(200);
    }
    
    @Test(priority = 2)
    public void testDigestAuth() {
    	// digest Auth with Postman Echo
    	given()
    	  .auth().digest("postman","password")
    	.when()
    	  .get("https://postman-echo.com/basic-auth")
    	.then()
    	  .statusCode(200);
    }
    
    @Test(priority = 3)
    public void testPreemptiveAuth() {
    	// preemptive Auth with Postman Echo
    	given()
    	  .auth().preemptive().basic("postman","password")
    	.when()
    	  .get("https://postman-echo.com/basic-auth")
    	.then()
    	  .statusCode(200);
    }
}

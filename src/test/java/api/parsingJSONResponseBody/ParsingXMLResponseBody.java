package api.parsingJSONResponseBody;

import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;


import java.util.List;
import io.restassured.specification.RequestSpecification;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponseBody {

	// https://www.w3schools.com/xml/plant_catalog.xml

	//@Test(priority = 1)
	void testParsingResponseBody() {
		//Approach 1
//		given().contentType(ContentType.XML)
//		.when()
//		.get("https://www.w3schools.com/xml/plant_catalog.xml")
//		.then().statusCode(200)
//		.header("Content-Type", "text/xml")
//		.body("CATALOG.PLANT[0].COMMON", equalTo("Bloodroot"))
//		.log().all();
		
		//Approach 2
		Response res = given().contentType(ContentType.XML)
		.when()
		.get("https://www.w3schools.com/xml/plant_catalog.xml");
//		Assert.assertEquals(res.statusCode(), 200);
//		Assert.assertEquals(res.header("Content-Type"), "text/xml");
		String firstPlantName= res.xmlPath().get("CATALOG.PLANT[0].COMMON").toString();
		Assert.assertEquals(firstPlantName, "Bloodroot");
		
		

	}
	
	@Test(priority = 1)
	void testXMLparsing() {
		
		Response res = given().contentType(ContentType.XML)
		.when()
		.get("https://www.w3schools.com/xml/plant_catalog.xml");		
		
		
		XmlPath xmlObj =new XmlPath(res.asString());
		
		List<String> plants= xmlObj.getList("CATALOG.PLANT");
		System.out.println(plants.size());
		Assert.assertEquals(plants.size(), 36);
		
		boolean found=false;
		List<String> plantNames=xmlObj.getList("CATALOG.PLANT.COMMON");
		for(String plantName: plantNames) {
//			System.out.println(plantName);
			if(plantName.equals("Gentian")) {
				found=true;
				System.out.println("**************found the plant!!!**************");
				break;
			}
		}
		Assert.assertEquals(found, true);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


}

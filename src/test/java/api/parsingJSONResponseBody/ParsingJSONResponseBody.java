package api.parsingJSONResponseBody;

import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponseBody {

    // http://localhost:3000/students

    @Test(priority = 1)
    void testParsingResponseBody() {
        // given().contentType("ContentType.JSON")
        //        .when().get("http://localhost:3000/students")
        //        .then().body("[3].name", equalTo("Deepak"));

        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/students");

        // Assert.assertEquals(res.getStatusCode(), 200);
        // Assert.assertEquals(res.header("Content-Type"), "application/json");
        // String userName = res.jsonPath().get("[3].name");
        // Assert.assertEquals(userName, "Deepak");

        boolean found = false;
        JSONArray students = new JSONArray(res.asString());

        for (int i = 0; i < students.length(); i++) {
            JSONObject student = students.getJSONObject(i);
            String stuName = student.get("name").toString();
            System.out.println(stuName);

            if (stuName.equals("Alice")) {
                found = true;
                break;
            }
        }

        Assert.assertEquals(found, true);
    }
}

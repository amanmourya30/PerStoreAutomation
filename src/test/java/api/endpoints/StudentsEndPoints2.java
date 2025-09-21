package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.Student;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StudentsEndPoints2 {
	
	static ResourceBundle getURL() {
		return ResourceBundle.getBundle("routes");
	}
	
	
    // Create Student
    public static Response createStudent(Student payload) {
    	
    	String post_url =getURL().getString("post_url");
    	
        return given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(post_url);
    }

    // Read Student by ID
    public static Response readStudent(String studentId) {
    	String get_url =getURL().getString("get_url");

        Response responce = given()
                    .pathParam("id", studentId)
                .when()
                    .get(get_url); // /students/{id}
        return responce;
    }

    // Update Student by ID
    public static Response updateStudent(String studentId, Student payload) {
    	String update_url =getURL().getString("update_url");

        return given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .pathParam("id", studentId)
                    .body(payload)
                .when()
                    .put(update_url); // /students/{id}
    }

    // Delete Student by ID
    public static Response deleteStudent(String studentId) {
    	String delete_url =getURL().getString("delete_url");

        return given()
                    .pathParam("id", studentId)
                .when()
                    .delete(delete_url); // /students/{id}
    }
}

package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import api.payload.Student;

public class StudentsEndPoints {

	// Create student
	public static Response createStudent(Student payload) {
		return given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).log().body() // log request
																											// JSON
				.when().post(StudentsRoutes.post_url);
	}

	// Read student by ID
	public static Response readStudent(int studentId) {
		return given().pathParam("id", studentId).when().get(StudentsRoutes.get_url);
	}

	// Update student
	public static Response updateStudent(int studentId, Student payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", studentId)
                .body(payload)
        .when()
                .put(StudentsRoutes.update_url);
    }
	
	// Delete student
    public static Response deleteStudent(int studentId) {
        return given()
                .pathParam("id", studentId)
        .when()
                .delete(StudentsRoutes.delete_url);
    }
}

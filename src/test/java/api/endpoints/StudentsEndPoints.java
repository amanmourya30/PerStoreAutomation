package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.Student;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StudentsEndPoints {

    // Create Student
    public static Response createStudent(Student payload) {
        return given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(StudentsRoutes.post_url);
    }

    // Read Student by ID
    public static Response readStudent(String studentId) {
        Response responce = given()
                    .pathParam("id", studentId)
                .when()
                    .get(StudentsRoutes.get_url); // /students/{id}
        return responce;
    }

    // Update Student by ID
    public static Response updateStudent(String studentId, Student payload) {
        return given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .pathParam("id", studentId)
                    .body(payload)
                .when()
                    .put(StudentsRoutes.update_url); // /students/{id}
    }

    // Delete Student by ID
    public static Response deleteStudent(String studentId) {
        return given()
                    .pathParam("id", studentId)
                .when()
                    .delete(StudentsRoutes.delete_url); // /students/{id}
    }
}

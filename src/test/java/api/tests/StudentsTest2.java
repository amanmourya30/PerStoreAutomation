package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StudentsEndPoints2;
import api.payload.Student;
import io.restassured.response.Response;

public class StudentsTest2 {

    Faker faker;
    Student studentPayload;
    String studentId; // store ID for later tests
    public Logger logger;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        studentPayload = new Student();
        studentPayload.setName(faker.name().fullName());
        studentPayload.setLocation(faker.address().city());
        studentPayload.setPhone(faker.phoneNumber().cellPhone());
        studentPayload.setCourses(new String[] { "Mathematics", "Science", "History" });

        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testCreateStudent() {
        logger.info("***** Starting testCreateStudent *****");

        Response response = StudentsEndPoints2.createStudent(studentPayload);
        response.then().log().all();

        logger.info("Create Status Code: " + response.getStatusCode());

        // Accept either 200 or 201
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 201,
                "Expected status code 200 or 201 but got " + response.getStatusCode());

        // save id for later tests
        studentId = response.jsonPath().getString("id");
        logger.info("Student created with ID: " + studentId);

        logger.info("***** Finished testCreateStudent *****");
    }

    @Test(priority = 2, dependsOnMethods = "testCreateStudent")
    public void testGetStudentById() {
        logger.info("***** Starting testGetStudentById *****");

        Response response = StudentsEndPoints2.readStudent(studentId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("id"), studentId);

        logger.info("Successfully fetched student with ID: " + studentId);
        logger.info("***** Finished testGetStudentById *****");
    }

    @Test(priority = 3, dependsOnMethods = "testCreateStudent")
    public void testUpdateStudent() {
        logger.info("***** Starting testUpdateStudent *****");

        // update some fields
        studentPayload.setName("Updated " + faker.name().firstName());
        studentPayload.setLocation("UpdatedCity");

        Response response = StudentsEndPoints2.updateStudent(studentId, studentPayload);
        response.then().log().all();

        logger.info("Update Status Code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("Student updated successfully with ID: " + studentId);
        logger.info("***** Finished testUpdateStudent *****");
    }

    @Test(priority = 4, dependsOnMethods = "testCreateStudent")
    public void testDeleteStudent() {
        logger.info("***** Starting testDeleteStudent *****");

        Response response = StudentsEndPoints2.deleteStudent(studentId);
        response.then().log().all();

        logger.info("Delete Status Code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("Student deleted successfully with ID: " + studentId);
        logger.info("***** Finished testDeleteStudent *****");
    }

    @BeforeMethod
    public void delay() throws InterruptedException {
        Thread.sleep(1000);
    }
}

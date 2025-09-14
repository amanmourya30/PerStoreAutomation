package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.StudentsEndPoints;
import api.payload.Student;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDtests {

    @Test(priority = 1, dataProvider = "AllStudentData", dataProviderClass = DataProviders.class)
    public void testPostStudent(String name, String location, String phone, String courses) {

    	Student studentPayload = new Student();
    	
    	studentPayload = new Student();
    	studentPayload.setName(name);
		studentPayload.setLocation(location);
		studentPayload.setPhone(phone);
		studentPayload.setCourses(new String [] {courses,"English"});
        
        Response response = StudentsEndPoints.createStudent(studentPayload);
		response.then().log().all();

		// Accept either 200 or 201
		Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 201,
				"Expected status code 200 or 201 but got " + response.getStatusCode());

		// save id for later tests
		String studentId = response.jsonPath().getString("id");
		System.out.println(studentId);

        
    }
}

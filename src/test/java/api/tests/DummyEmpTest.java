package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import api.endpoints.DummyUserEndPoints;
import api.payload.DummyUser;
import io.restassured.response.Response;

public class DummyEmpTest {

    Faker faker;
    DummyUser empPayload;
    int empId; // store ID returned from create

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        empPayload = new DummyUser();

        empPayload.setName(faker.name().fullName());
        empPayload.setSalary(String.valueOf(faker.number().numberBetween(3000, 9000)));
        empPayload.setAge(String.valueOf(faker.number().numberBetween(20, 50)));
    }

    @Test(priority = 1)
    public void testCreateEmployee() {
        Response response = DummyUserEndPoints.createUser(this.empPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        // capture the employee id returned in response
        empId = response.jsonPath().getInt("data.id");
        System.out.println("Created Employee ID: " + empId);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateEmployee")
    public void testGetEmployeeById() {
        Response response = DummyUserEndPoints.readUser(empId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("data.id"), empId);
    }

    @Test(priority = 3, dependsOnMethods = "testCreateEmployee")
    public void testUpdateEmployee() {
        // update some fields
        empPayload.setName("Updated " + faker.name().firstName());
        empPayload.setSalary("7500");
        empPayload.setAge("40");

        Response response = DummyUserEndPoints.updateUser(empId, this.empPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4, dependsOnMethods = "testCreateEmployee")
    public void testDeleteEmployee() {
        Response response = DummyUserEndPoints.deleteUser(empId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }
    
    @BeforeMethod
    public void waitDelay() throws InterruptedException {
    	Thread.sleep(2000); 
    }
}

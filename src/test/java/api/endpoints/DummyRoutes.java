package api.endpoints;

//Dummy REST API Example (Employee CRUD)
//Base URL: https://dummy.restapiexample.com/api/v1
//Create user (employee)  POST    /create
//Get user by ID          GET     /employee/{id}
//Update user             PUT     /update/{id}
//Delete user             DELETE  /delete/{id}	
	
public class DummyRoutes {
public static String base_url = "https://dummy.restapiexample.com/api/v1";
	
	// Employee model (similar to user)
	public static String post_url   = base_url + "/create";        // Create employee (POST)
	public static String get_url    = base_url + "/employee/{id}"; // Get employee by ID (GET)
	public static String update_url = base_url + "/update/{id}";   // Update employee (PUT)
	public static String delete_url = base_url + "/delete/{id}";   // Delete employee (DELETE)
	
}

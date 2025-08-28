package api.endpoints;

//Create user post	https://petstore.swagger.io/v2/user
//Get user by username	https://petstore.swagger.io/v2/user/{username}
//Update user	https://petstore.swagger.io/v2/user/{username}
//Delete user	https://petstore.swagger.io/v2/user/{username}

	
	
public class Routes {
	public static String base_url="https://petstore.swagger.io/v2";
	
	//user model
	public static String post_url=base_url+"/user";
	public static String get_url = base_url + "/user/{username}"; 			  // Get user by username (GET)
	public static String update_url = base_url + "/user/{username}";          // Update user (PUT)
	public static String delete_url = base_url + "/user/{username}";          // Delete user (DELETE)
}

package api.fileUploadAndDownload;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.File;
import org.testng.annotations.Test;

public class fileUploadAndDownload {

	@Test(priority = 1)
	void testSingleFileUpload() {
		
		File myFile = new File("./testData/TextFile1.txt");

		given()
			.multiPart("file" ,myFile)
			.contentType("multipart/form-data")

		.when()
		.post("http://localhost:8080/uploadFile")
		.then().statusCode(200)
			.body("fileName", equalTo("TextFile1.txt"))
			.log().all();
		
	}
	
	//@Test(priority = 2)
	void testMultipleFileUpload() {
		
		File myFile1 = new File("./testData/TextFile1.txt");
		File myFile2 = new File("./testData/TextFile2.txt");

		given()
			.multiPart("files" ,myFile1)
			.multiPart("files",myFile2)
			.contentType("multipart/form-data")

		.when()
		.post("http://localhost:8080/uploadMultipleFiles")
		.then().statusCode(200)
			.body("[0].fileName", equalTo("TextFile1.txt"))
			.body("[1].fileName", equalTo("TextFile2.txt"))
			.log().all();
		
	}
	
	@Test(priority = 2)
	void testFileDownload() {
		
		given()
		.when().get("http://localhost:8080/downloadFile/TextFile1.txt")
		.then().statusCode(200).log().body();
		
	}
	

}

package genericUtility;
import static io.restassured.RestAssured.*;

import io.restassured.response.Response;


/**
 * Consist of method of Restassured
 * @author User
 *
 */

public class RestAssuredLibrary {
	/**
	 * This method will return json data from the corresponding response body
	 * 
	 * 
	 */
	
	public String getJsonData(Response response,String path) {
		String jsonData=response.jsonPath().get(path);
		return jsonData;
		
	}

}

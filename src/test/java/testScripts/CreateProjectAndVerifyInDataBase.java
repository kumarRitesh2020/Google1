package testScripts;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.sql.SQLException;

import genericUtility.BaseClass;
import genericUtility.CreateProject;
import genericUtility.EndPointsLibrary;
import genericUtility.JavaLibrary;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateProjectAndVerifyInDataBase extends BaseClass {
	
	
	@Test
	public void createProj() throws SQLException {
		
		
		//Step 1:create pre requisites
		CreateProject cp= new CreateProject("ritesh","TYSS"+jLib.randomNum(),"createdBy",21);
		
		//Step 2:send the request
		Response resp=given().body(cp).contentType(ContentType.JSON).when().post(EndPointsLibrary.createProject);
		
		//Step 3:capture the project ID
		
		String expdata=rLib.getJsonData(resp, "projectId");
		System.out.println(expdata);
		
		//Step 4:validate the id in database
		
		String query="select * from project;";
		String actData=dLib.readDataFromDBAndValidate(query, 5, expdata);
		System.out.println("TC PASS");
		
		resp.then().log().all();
				
		 
			
		}
	}



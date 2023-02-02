package genericUtility;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.sql.SQLException;
public class BaseClass {
	
	public DatabaseLibrary dLib=new DatabaseLibrary();
	public JavaLibrary jLib=new JavaLibrary();
	public RestAssuredLibrary rLib=new RestAssuredLibrary();
	public EndPointsLibrary elb=new EndPointsLibrary();
	
	@BeforeSuite
	public void bsConfig() throws SQLException
	{
		dLib.connectToDB();
		baseURI="http://rmgtestingserver";
		port=8084;
		
	}
	
	@AfterSuite
	public void asConfig() throws SQLException
	{
		dLib.closeDB();
	}

}

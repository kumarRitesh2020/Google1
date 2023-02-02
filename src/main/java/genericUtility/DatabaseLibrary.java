package genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DatabaseLibrary {
	Driver driverRef;
	Connection con;
	
	/**
	 * This method is used to connect with mysql db
	 * @throws SQLException 
	 * 
	 */
	
	public void connectToDB() throws SQLException {
		driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		con=DriverManager.getConnection(IConstants.dbURL,IConstants.dbUserName,IConstants.dbPassword);
	}
	
	/**
	 * This method will close database connection
	 * @throws SQLException 
	 * 
	 */
	
	public void closeDB() throws SQLException {
		con.close();
	}
	
	/**
	 * This method will execute the query and return the value only if the validation is
	 * @throws SQLException 
	 * 
	 * 
	 */
	
	public String readDataFromDBAndValidate(String query,int columnIndex,String expData) throws SQLException {
		boolean flag=false;
		ResultSet result=con.createStatement().executeQuery(query);
		while(result.next()) {
			if(result.getString(columnIndex).equalsIgnoreCase(expData)) {
				flag=true;
				break;
			}
		}
		
		if(flag) {
			System.out.println("verified");
			return expData;
		}
		else {
			System.out.println("data not verified");
			return "";
		}
		
		
		
	}

}

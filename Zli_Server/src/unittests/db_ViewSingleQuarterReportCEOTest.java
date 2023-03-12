package unittests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import dbControllers.db_ViewSingleQuarterReportCEO;
import entities.MyFile;
import entities.enumE.DB_Message;
import entities.users.User;
import entities.users.ceo.CEO;
import entities.users.customer.Customer;
import serverManager.ServerHandler;
import serverManager.mysqlConnection;

public class db_ViewSingleQuarterReportCEOTest {
	Connection conn;
	CEO ceo;
	User user;
	DB_Message msg;
	boolean result;

	@Before
	public void setup() {
	mysqlConnection sql = mysqlConnection.Connect("root", "Amasha10@", "zli");
	conn =sql.getConncetion();
	ServerHandler.conn = conn;
	db_ViewSingleQuarterReportCEO.conn = conn;
	user=new User(null,null);
	ceo=new CEO(user);
	}
	
	/**
	 * Test funcionality: testing if the method imports from the dataBase THE CORRECT FOLDER WITH CORRECT ELEMENTS.
	 * input: file details: (year:2015,quarter:2,branch:EAST)
	 * EXPECTED RESULT: file content: 1,56,2,30,3,120,4,330,5,130,6,556,7,759,8,103,9,922,10,222.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void TestSuccessfullImport() throws SQLException {
		ceo.setYear1("2015");
		ceo.setQuarter1("2");
		ceo.setB_type1("EAST");
	    MyFile str=db_ViewSingleQuarterReportCEO.ViewQuarterReport(conn, ceo, user);
		String SS=new String(str.mybytearray);
		String[] data=SS.split(",");
		String[] res= {"1","56","2","30","3","120","4","330","5","130","6","556","7","759","8","103","9","922","10","222"};
		assertEquals(data,res);
	}
	
	/**
	 * Test funcionality: testing if the method imports from the dataBase corect folder but with differnet content
	 * input: file details: (year:2015,quarter:2,branch:EAST)
	 * EXPECTED RESULT: not equal files. 
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void TestUnsuccessfullImport_differnetContent() throws SQLException {
		ceo.setYear1("2015");
		ceo.setQuarter1("2");
		ceo.setB_type1("EAST");
	    MyFile str=db_ViewSingleQuarterReportCEO.ViewQuarterReport(conn, ceo, user);
		String SS=new String(str.mybytearray);
		String[] data=SS.split(",");
		String[] res= {"1","56","2","30","3","120","4","330","5","130","6","556","7","759","8","103","9","922"};
		assertNotEquals(data,res);
	}
	
	/**
	 * Test funcionality: testing if the method returns null when trying to import unexsiting report
	 * input: file details: (year:2030,quarter:2,branch:EAST)
	 * EXPECTED RESULT: return null
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void TestUnsuccessfullImport_NotExistingReport() throws SQLException {
		ceo.setYear1("2030");
		ceo.setQuarter1("2");
		ceo.setB_type1("EAST");
	    MyFile str=db_ViewSingleQuarterReportCEO.ViewQuarterReport(conn, ceo, user);
		assertNull(str);
	}
		
	}
		
		
		




package dbControllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import entities.MyFile;
import entities.users.User;
import entities.users.ceo.CEO;

public class db_ViewMonthlyReportCEO {

	/**
	 * @param conn1
	 * @param msg
	 * @param usertmp
	 * @return
	 * @throws Exception
	 */
	public static  MyFile ViewMonthlyReport(Connection conn1, Object msg, User usertmp) throws Exception {
		//// this function for viewing monthly reports .
		// TODO Auto-generated method stub
		CEO ceo=(CEO)msg;
 		ResultSet res;
 		String txt = ".txt";
 		String fileName=ceo.getMonth()+'-'+ceo.getYear1()+'-'+ceo.getB_type1();
 	
 		MyFile myFile = new MyFile(fileName+txt);
 		byte[] mybytearray;
 		try {
 			 String sql = "SELECT reportDetails FROM zli.reports WHERE month=? AND year=? AND branchType=? AND reportType=? AND reportDuration='monthly';";
 	         PreparedStatement stmt = conn1.prepareStatement(sql); /// query to bring report details 
 	         stmt.setString(1, ceo.getMonth());
 	         stmt.setString(2, ceo.getYear1());
 	         stmt.setString(3,ceo.getB_type1());
 	         stmt.setString(4, ceo.getRepType());
 	        stmt.execute();
 			res = stmt.getResultSet();
 			if (!res.next()) {// if file not exist
 			System.out.println("no file");
 			myFile=null;
 			}else { // if the file exist 
 		
 			mybytearray = new byte[(int) res.getBlob("reportDetails").length()];
 			mybytearray = res.getBytes("reportDetails");
 			myFile.initArray(mybytearray.length);
 			myFile.setSize(mybytearray.length);
 			myFile.setMybytearray(mybytearray);
 			}
 		 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		return myFile; 
 		
 }
	

}



package dbControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.MyFile;
import entities.enumE.OpType;
import entities.users.User;
import entities.users.ceo.CEO;

public class db_ViewSingleQuarterReportCEO {

	public static Connection conn;

	/**
	 * @param conn1
	 * @param msg
	 * @param usertmp
	 * @return
	 */
	public static MyFile ViewQuarterReport(Connection conn1, Object msg, User usertmp) {
		// TODO Auto-generated method stub
		// this function for viewing quarter reports.
		CEO ceo=(CEO)msg;
		
        
 		ResultSet res;
 		String txt = ".txt";
 		String fileName=ceo.getQuarter1()+'-'+ceo.getYear1()+'-'+ceo.getB_type1();
 	
 		MyFile myFile = new MyFile(fileName+txt);
 		byte[] mybytearray;
 		try {
 			 String sql = "SELECT reportDetails FROM zli.quartley_reports WHERE year=? AND quarter=? AND branchType=? ;"; //query to bring the report details 
 	         PreparedStatement stmt = conn1.prepareStatement(sql);
 	         
 	         stmt.setString(1, ceo.getYear1());
 	         stmt.setString(2,ceo.getQuarter1());
 	         stmt.setString(3, ceo.getB_type1());
 	        stmt.execute();
 			res = stmt.getResultSet();
 			if (!res.next()) {// if file not exist
 			System.out.println("no file");
 			myFile=null;
 			}
 			else { // if the file exist 
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

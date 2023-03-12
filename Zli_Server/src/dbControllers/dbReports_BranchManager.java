package dbControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import entities.AccountCustomer;
import entities.MyFile;
import entities.Orders;
import entities.Report;
import entities.enumE.AccountStatus;
import entities.enumE.BranchType;
import entities.enumE.OpType;
import entities.enumE.ReportType;
import entities.enumE.userType;
import entities.users.User;
import entities.users.branchmanager.BranchManager;
import entities.users.ceo.CEO;

public class dbReports_BranchManager {

//	private String selected_Month;
//    private String selected_year;
 //   private ReportType reportType;
 //   private BranchType branchType;

	
	/**
	 * @param conn1
	 * @param msg
	 * @param user
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static  MyFile ViewReports(Connection conn1,Object msg, User user) throws SQLException, IOException {
	 ///this function for viewing the report 
		BranchManager br=(BranchManager)msg;
 		ResultSet res;
 		String txt = ".txt";
 		String fileName=br.getMonth()+'-'+br.getYear()+'-'+br.getBr_type();
 		MyFile myFile = new MyFile(fileName+txt);
 		byte[] mybytearray;
 		try {
 			try {
 				String query="SELECT branchType FROM zli.branchmanager WHERE userID =?;";
 					PreparedStatement stmt2=conn1.prepareStatement(query);
 					stmt2.setInt(1,user.getId());
 					stmt2.executeQuery();
 					ResultSet resSET=stmt2.getResultSet();
 					while(resSET.next()) {
 						br.setBr_type(resSET.getString("branchType"));
 					}
 					//System.out.println(br.getBr_type()+" branchtype");
 				}catch(SQLException e) {e.printStackTrace();}
 		//	System.out.println(br.getBr_type()+" raaaaaaaaaaaaamiiiiiiiii");
 			 String sql = "SELECT reportDetails FROM zli.reports WHERE month=? AND year=? AND branchType=? AND reportType=? AND reportDuration='monthly';";
 	         PreparedStatement stmt = conn1.prepareStatement(sql);
 	         stmt.setString(1, br.getMonth());
 	         stmt.setString(2, br.getYear());
 	         stmt.setString(3,br.getBr_type());
 	         stmt.setString(4, br.getRepTYPE());
 	   //      System.out.println( br.getMonth()+ " "+ br.getYear()+" "+br.getBr_type()+" "+br.getRepTYPE());
 	        stmt.execute();
 			res = stmt.getResultSet();
 			if (!res.next()) {// if file not exist
 			System.out.println("no file");
 			myFile=null;
 			}
 			else {
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

              
	


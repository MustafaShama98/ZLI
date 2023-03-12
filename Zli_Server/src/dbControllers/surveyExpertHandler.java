package dbControllers;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import entities.Message;
import entities.MyFile;
import entities.enumE.MessageType;
import server.ConnectionToClient;

public class surveyExpertHandler {
	private static MyFile report;
	private static Connection conn;
	/**
	 * A method that handles the PDF file that is being sent by the expert.
	 * @param msg : Message from client. Datatype is MyFile
	 * @param conn:  SQL Connection
	 * @param client: Client reference 
	 */
	public static void handleFileReceived(Object msg,Connection conn,  ConnectionToClient client) {
		 report = ((MyFile)msg);
		 surveyExpertHandler.conn = conn;
		String LocalfilePath = report.getFileName();
		String[] a = LocalfilePath.split("\\\\");
		report.setFileName(a[a.length - 1]);
		System.out.println(report.getFileName());
		//MyFile new_file = new MyFile(a[a.length - 1]);
		try {
			ByteArrayInputStream is = new ByteArrayInputStream(report.getMybytearray());
			sendReportToDB(conn,is);// send file to database
			client.sendToClient(new Message(MessageType.Success));
			//paraseFileBytes(requestFile(report.getFileName()));// bring from database and put it folder
		}
		catch(Exception e) {
			System.out.println("Error!");
		}
	}
	/**
	 * Functionality: this method saves the created income file as a blob in the database 
	 * @param conn : SQL connection
	 * @param is : ByteArrayInputStream, the requested file as bytes
	 */
	private static void sendReportToDB(Connection conn, ByteArrayInputStream is) {
		String query = "INSERT INTO zli.surveyreports (surveyPDF,datee,fileName) VALUES (?,?,?)";
		
		try {	
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBlob(1, is); 
			
			pstmt.setString(2, LocalDate.now().toString());
			pstmt.setString(3, report.getFileName());
			pstmt.execute();
			pstmt.executeUpdate();
			is.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}
	private static MyFile requestFile( String fileName) {
		PreparedStatement ps;
		ResultSet res;
		String pdf = ".pdf";
		MyFile myFile = new MyFile(fileName);
		byte[] mybytearray;
		try {
			ps = conn.prepareStatement("SELECT * FROM zli.surveyreports WHERE fileName =?;");
			ps.setString(1, fileName);
			ps.execute();
			res = ps.getResultSet();
			if (!res.next()) {// if file not exist
			System.out.println("no file");
			}
			
			mybytearray = new byte[(int) res.getBlob("surveyPDF").length()];
			mybytearray = res.getBytes("surveyPDF");
			myFile.initArray(mybytearray.length);
			myFile.setSize(mybytearray.length);
			myFile.setMybytearray(mybytearray);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myFile;
	}
	private static void paraseFileBytes(MyFile file) {
		System.out.println("here");
		String LocalfilePath = file.getFileName();
		int size = file.getSize();
		//String[] a = LocalfilePath.split("\\\\");
		//MyFile new_file = new MyFile(a[a.length - 1]);
		try {
				File newFile = new File("Survey Reports\\"+LocalfilePath);
				FileOutputStream fis = new FileOutputStream(newFile);
			//	FileInputStream fis = new FileInputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fis);	
				bos.write(file.getMybytearray(), 0, size);
				fis.close();
				bos.close(); 
			}
			catch(Exception e) {
				System.out.println("Error!");
			}
	}

}

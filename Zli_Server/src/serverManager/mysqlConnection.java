package serverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javafx.scene.control.TextArea;

public class mysqlConnection {
	   static mysqlConnection conn = null;
	   static Connection connectionReference = null;
	private static TextArea server_messages;
	   //mysqlConnection constructor to connect with DB
	   ////singleton constructor - need only 1 connection to DB
	String password;
	public void setPassword(String s) {
		password = s;
	}
	  private mysqlConnection(String user, String password, String name) {
		
			if (connectionReference == null) {
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				} catch (Exception ex) {/* handle the error*/}
			  
				try 
			  	{
					Properties properties = new Properties();
					properties.setProperty("user", user);
					properties.setProperty("password", password);
					properties.setProperty("useSSL", "false");
					connectionReference = DriverManager.getConnection("jdbc:mysql://localhost/"+name+"?serverTimezone=IST", properties);
			     System.out.println("- SQL connection succeed.");
			     // server_messages.appendText("- SQL connection succeed.");
			      //server_messages.appendText("\n");
				} catch (SQLException ex) {/* handle any errors*/
			      System.out.println("SQLException: " + ex.getMessage());
			      System.out.println("SQLState: " + ex.getSQLState());
			      System.out.println("VendorError: " + ex.getErrorCode());
			      }
			  	}
	
	  }
	  
		public static mysqlConnection Connect(String user, String password, String name)  {
			if (conn == null) {
			//	System.out.println("Sql Started from running the server.\n");
				conn = new mysqlConnection(user,password,name);
			   
			}
			return conn;
		}
		
	//get the connectionReference with DB  
		public static Connection getConncetion() {
			if(connectionReference != null) {
			return connectionReference;}
			return null;
		}
		
		public void closeConnection() throws SQLException {//close the connection with DB 
			if (conn != null) 
			{
				conn = null;
			}
			connectionReference.close();
			
			System.out.println("SQL connection closed.");
		}
		public static void set_ServerMessages(TextArea serverMessages) {
			server_messages = serverMessages;
		}
}

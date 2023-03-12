package dbControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Orders;
import entities.users.User;

public class dbMessage {
	
	
	
	
	 public static Double  LoadCompensation(Connection conn1,User user) throws SQLException
		{ 
	    	Double Price=0.0,compensation=0.0;
	    	boolean found = false;
				String query  = "SELECT compensation FROM messages WHERE CustomerID=?;";
				PreparedStatement statment = conn1.prepareStatement(query);
				statment.setInt(1,user.getId());
	
				ResultSet result = statment.executeQuery();
					if (found = result.next())
					{
						do {	
							
								Price=result.getDouble("compensation");		
								compensation+=Price;
						}while(result.next());
					}
					
				return compensation;
		}

	 
	 public static ArrayList<String> LoadMessages(Connection conn1,User user) throws SQLException
	 {
		 
		 ArrayList<String> Message=new ArrayList<String>();
    		 boolean found = false;
			String query  = "SELECT * FROM messages WHERE CustomerID=?;";
			PreparedStatement statment = conn1.prepareStatement(query);
			statment.setInt(1,user.getId());
			ResultSet result = statment.executeQuery();
			if (found = result.next())
			{
				do {	
						Message.add(result.getInt("OrderNumber")+" "+result.getString("result"));		
				}while(result.next());
			}
			
	 return Message;
}

	 
	 
}
package dbControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entities.Orders;
import entities.enumE.OpType;
import entities.users.Sales;
import entities.users.User;

public class dbSales {
	static Map<String,String> SalesMap= new HashMap<String,String>(); 
	static ArrayList<String> str2; 
	static String Branch;
	static String Sale;
	public static ArrayList<Sales> BranchList;
    public static Map<String,String> Sales(Connection conn1) throws SQLException
    {
    	
    	boolean found = false;
			String query  = "SELECT * FROM sales;";
			PreparedStatement statment = conn1.prepareStatement(query);
			ResultSet result = statment.executeQuery();
			if (found = result.next()) {
				do {	
					if(result.getString("Activation").equals("ACTIVE"))
					{
						SalesMap.put(result.getString("BranchName"),result.getString("Sales"));
					}
					
				}while(result.next());	
			}

		
			return SalesMap;	
    }
    public static ArrayList<Sales> LoadSalesTable(Connection conn1) throws SQLException
   	{ 
    	
       	BranchList = new ArrayList<Sales>();
       	
       	
       	boolean found = false;
   			String query  = "SELECT * FROM sales ;";
   			PreparedStatement statment = conn1.prepareStatement(query);
   			
   			
   			ResultSet result = statment.executeQuery();
   			if (found = result.next()) {
   				do {	
   					BranchList.add(new Sales(result.getString("BranchName"),result.getString("Sales"),result.getString("Activation")));
   				}while(result.next());	
   			}
   			else
   			{
   				return null;
   			}
   			
   			return BranchList;
   	}
   		

   
	 public static boolean UpdateSales(Connection conn1,Object object) 
	    {
		ArrayList<String>myList=(ArrayList<String>)object;
	   Branch=myList.get(0);
	   Sale=myList.get(1);
	   PreparedStatement st;
	   Boolean found=true;
	   if(Branch.equals("ALL")) 
	   {
			try {
				st = conn1.prepareStatement("UPDATE sales SET Sales = ?; "); 
				st.setString(1, Sale);
		       st.executeUpdate();
		     
			} catch (SQLException e) {
				
				found=false;
				e.printStackTrace();
			}
		   
	   }
	   else
	   {
			    try {
			       st = conn1.prepareStatement("UPDATE sales SET Sales = ? WHERE BranchName = ?");
				   st.setString(1, Sale);
				   st.setString(2,Branch); 
				   st.executeUpdate(); 
				} catch (SQLException e) {
					found=false;
					e.printStackTrace();
				}
			   
	   
	   }
	return found;
	  
}
	public static Boolean SalesActivity(Connection conn, Object object) throws SQLException {
		PreparedStatement st;
		String Name=(String)object;
		String Activity=" ";
		Boolean found=false;
		String query  = "SELECT Activation FROM sales WHERE BranchName=?;";
		st = conn.prepareStatement(query);
		st.setString(1,Name);
		ResultSet result = st.executeQuery();
		if (found = result.next()) {
			do {	
				if(result.getString("Activation").equals("ACTIVE"))
				{
			Activity="FROZEN";
		}
		else
		{
			Activity="ACTIVE";
		}
		st = conn.prepareStatement("UPDATE sales SET Activation = ? WHERE BranchName = ?;");
		st.setString(1, Activity);
	    st.setString(2,Name);
	    st.executeUpdate();
			}while(result.next());
		}
		
		
	    		   
	    return found; 
	   	
	}
	
}
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    


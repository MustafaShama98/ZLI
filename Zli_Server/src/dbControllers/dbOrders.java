package dbControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import entities.Orders;
import entities.users.User;
import entities.users.customer.Customer;
import serverManager.mysqlConnection;

public class dbOrders {
	
	public static ArrayList<Orders> OrdersList;
	public  mysqlConnection sql;
	public static ArrayList<Orders> str,str1;
	public static ArrayList<String> str2;
   // private static Connection conn1 = null;

    public static ArrayList<Orders> LoadOrdersTable(Connection conn1,User user) throws SQLException
	{ 
    	OrdersList = new ArrayList<Orders>();
    	
    	
    	boolean found = false;
			String query  = "SELECT OrderNumber,Details,TotalPrice,Activity,Status,OrderDate,ReceivedDate,SupplyType FROM orders WHERE CustomerId=?;";
			PreparedStatement statment = conn1.prepareStatement(query);
			statment.setString(1,String.valueOf(user.getId()));
			
			ResultSet result = statment.executeQuery();
			if (found = result.next()) {
				do {	
				 OrdersList.add(new Orders(result.getInt("OrderNumber"), result.getString("Details"),result.getDouble("TotalPrice"),result.getString("Activity"),result.getString("Status"),result.getString("OrderDate"),result.getString("ReceivedDate"),result.getString("SupplyType")));
				}while(result.next());	
			}
			else
			{
				return null;
			}
			
			return OrdersList;
	}
		
	
    
    public static boolean Cancel(Connection conn1,Object MyObject,User user) throws  SQLException
    {
    OrdersList = new ArrayList<Orders>();
    boolean found = false;	
    int OrderNum =(int)MyObject ;
    Boolean flag=false;
    Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date2=new Date();
	
	SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    String query  = "SELECT * FROM Orders WHERE OrderNumber=?;";
	PreparedStatement statment = conn1.prepareStatement(query);
	statment.setInt(1,OrderNum);
	ResultSet result = statment.executeQuery(); 
	if (found = result.next()) {
		do {
			
			
			
				if((result.getString("Activity").equals("Purchased") && result.getString("Status").equals("Confirm" )) && result.getDate("ReceivedDate").before(date) )
				{
			PreparedStatement st = conn1.prepareStatement("UPDATE Orders SET Activity = ?, Status = ? ,CancelDateTime =? WHERE OrderNumber = ?");
		    st.setString(1,"Cancel");
		    st.setString(2,"Waiting");
		    st.setString(3, formatter.format(date));
		    st.setInt(4,OrderNum);
		    st.executeUpdate();
		    flag=true;
			}
		}while(result.next());
	}
		return flag;
    }
 
    public static Boolean details(Connection conn1,Object object) throws SQLException
   	{
    	ArrayList<String> Details=(ArrayList<String>) object;
    	boolean done=false;
    	String query  = "INSERT INTO Orders(CustomerId,Details,Branch,Activity,Status,SupplyType,receiverName,receiverAddress,receiverPhoneNumber,DeliveryFee,Price,TotalPrice,OrderDate,ReceivedDate) VALUES (?, ?, ?,?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement statment = conn1.prepareStatement(query);
		statment.setString(1,Details.get(0));
		statment.setString(2,Details.get(1));
		statment.setString(3,Details.get(2));
		statment.setString(4,Details.get(3));
		statment.setString(5,Details.get(4));
		statment.setString(6,Details.get(5));
		statment.setString(7,Details.get(6));
		statment.setString(8,Details.get(7));
		statment.setString(9,Details.get(8));
		statment.setString(10,Details.get(9));
		statment.setString(11,Details.get(10));
		statment.setString(12,Details.get(11));
		statment.setString(13,Details.get(12));
		statment.setString(14,Details.get(13));
		statment.executeUpdate();
		done=true;
		return done;
   	}
    /////////////////////////////////////////////

//   public  boolean FirstOrserOrNot(Connection conn1,User user)
//   {
//	    boolean isFound= false;
//		String query  = "SELECT OrderNumber FROM orders WHERE CustomerId=?;";
//		PreparedStatement statment = conn1.prepareStatement(query);
//		statment.setString(1,user.getId());
//		ResultSet result = statment.executeQuery();
//	
//		return isFound;
//   }
}

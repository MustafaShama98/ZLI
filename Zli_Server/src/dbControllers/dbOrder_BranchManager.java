package dbControllers;

import java.sql.Connection;

import entities.enumE.OpType;
import entities.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entities.Orders;
import entities.users.User;
import serverManager.mysqlConnection;
/**
 * 
 *
 */
public class dbOrder_BranchManager {

	public static ArrayList<Orders> OrdersList;
	public  mysqlConnection sql;
	
	
	/**
	 * @param conn1
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Orders> LoadOrdersTableB_M(Connection conn1, User user) throws SQLException {
		// TODO Auto-generated method stub
    	OrdersList = new ArrayList<Orders>();
    	////// the following query is for getting the branch type of the branch manager
    	String brType = null;
    	String sql="SELECT branchType FROM zli.branchmanager WHERE userID=?;";
    	PreparedStatement statment2 = conn1.prepareStatement(sql);
		statment2.setInt(1, user.getId());
		 statment2.execute();
		 ResultSet result1 = statment2.getResultSet();
	
		if(result1.next())
		{
		brType=result1.getString("branchType");
	
		}/////// we got the branch type of the branch manager in the brType variable;
		if(brType==null) return null;
		boolean found = false;
		String query  = "SELECT OrderNumber,Details,TotalPrice,Activity,Status,OrderDate FROM zli.orders WHERE Branch=?;";
		PreparedStatement statment = conn1.prepareStatement(query);
		statment.setString(1,brType);
		ResultSet result = statment.executeQuery();
	
		
		if (found = result.next()) {
			do {		
				 OrdersList.add(new Orders(result.getInt("OrderNumber"), result.getString("Details"),
						 result.getDouble("TotalPrice"),result.getString("Activity"),result.getString("Status")));
			}while(result.next());
              return OrdersList;
              
		}else {
				return null;
				
			}
	

}

	/**
	 * @param conn1
	 * @param MyObject
	 * @return
	 * @throws SQLException
	 */
	/**
	 * @param conn1
	 * @param MyObject
	 * @return
	 * @throws SQLException
	 */
	public static boolean  ConfirmOrder(Connection conn1,Object MyObject) throws SQLException {
		// TODO Auto-generated method stub
		/// this function for confirming order of confirming cancellation of an order 
	    OrdersList = new ArrayList<Orders>();
	    boolean found = false;	
	    Boolean flag=false;
	     double Diff = 0;
	    double giveBack=0;
	    String answer=null;
	    int OrderNum =((Integer)MyObject).intValue() ;
	    Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2=formatter.format(date);
	    String query  = "SELECT CustomerId, OrderNumber,Details,TotalPrice,OrderDate,Activity,Status,CancelDateTime,ConfirmedDateTime,ReceivedDate FROM zli.orders WHERE OrderNumber=?;";//bring order details
		PreparedStatement statment = conn1.prepareStatement(query);
		statment.setInt(1,OrderNum);
		ResultSet result = statment.executeQuery(); 
		if (found = result.next()) {
			do {
					if(result.getString("Activity").equals("Purchased")) ///confirm order
					{
						PreparedStatement st = conn1.prepareStatement("UPDATE zli.orders SET  Status = ?,ConfirmedDateTime=? WHERE OrderNumber = ?");
						st.setString(1,"Confirm");
						st.setString(2,formatter.format(date));
						st.setInt(3,OrderNum);
						st.executeUpdate();
						flag=true;
						String query3= "INSERT INTO zli.messages (customerID, OrderNumber, result, compensation) VALUES (?, ?, ?, ?);";
						PreparedStatement st3 = conn1.prepareStatement(query3);
						st3.setInt(1,result.getInt("CustomerId"));
					    st3.setInt(2,result.getInt("OrderNumber"));
					    String msg = "Order Confirmed Successfully.";
						st3.setString(3,msg);
						st3.setDouble(4, 0);
						st3.executeUpdate();
						flag=true;
						
			    	}
					else {
						if(result.getString("Activity").equals("Cancel")&&result.getString("ConfirmedDateTime")!=null)  /// in the case that the customer asks to cancel the order
						{
							String status;
							flag=true;
//							Date ConfirmDate = new Date();
//							SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//							
//							PreparedStatement st = conn1.prepareStatement("UPDATE zli.orders SET  ConfirmedDateTime = ? WHERE OrderNumber = ?");
//							st.setString(1,formatter2.format(ConfirmDate));; /// putting the date of the cancellation confirmation
//							st.setInt(2,OrderNum);
//							st.executeUpdate();
							
							
							if(result.getDate("ReceivedDate").equals(result.getDate("CancelDateTime")))
							{
							Date ReceivedTime=result.getTime("ReceivedDate");
							Date CancelTime=result.getTime("CancelDateTime");
							double timeDiff=ReceivedTime.getTime()-CancelTime.getTime();
							Diff = (timeDiff / (1000*60*60)) % 24;   
							
							if(Diff<=1) /// checking which type of compensation the customer will get
							{
								answer="You won't get compensation.";
								 giveBack=0;}
							
							else if(Diff>1 && Diff<3)
							{answer="You got 50% compensation.";
							giveBack=result.getDouble("TotalPrice")*0.5;
							}
							
							else
							{
								answer="You got full compenastion.";
								giveBack=result.getDouble("TotalPrice");
							}
							}	
							else
							{
								answer="You got full compenastion.";
								giveBack=result.getDouble("TotalPrice");
							}
							}
						
							
							
							
			}
							
							String query1 = ("UPDATE zli.messages SET result=? , compensation=? WHERE customerID=? AND OrderNumber=?;");		
							PreparedStatement st1 = conn1.prepareStatement(query1);
							st1.setString(1, answer);
							st1.setDouble(2, giveBack);
							st1.setInt(3,result.getInt("CustomerId"));
							st1.setInt(4,result.getInt("OrderNumber"));
							st1.executeUpdate();
							flag=true;
							
							String query2 = ("UPDATE zli.orders SET Status=?  WHERE  OrderNumber=?;");		
							PreparedStatement st2 = conn1.prepareStatement(query2);
							st2.setString(1,"Confirm");
							st2.setDouble(2, result.getInt("OrderNumber"));
							st2.executeUpdate();
			
						
			
		
			}while(result.next());
		
		}
			return flag;
	    
}

	/**
	 * @param conn1
	 * @param MyObject
	 * @param usertmp
	 * @return
	 * @throws SQLException
	 */
	public static boolean UnConfirmOrder(Connection conn1, Object MyObject, User usertmp) throws SQLException {
		//this function for unconfirming
		// TODO Auto-generated method stub
	    OrdersList = new ArrayList<Orders>();
	    boolean found = false;	
	    Boolean flag=false;
	    int OrderNum =((Integer)MyObject).intValue() ;
	    String query  = "SELECT OrderNumber,Details,TotalPrice,OrderDate,Activity,Status FROM zli.orders WHERE OrderNumber=?;";//bring order details 
		PreparedStatement statment = conn1.prepareStatement(query);
		statment.setInt(1,OrderNum);
		ResultSet result = statment.executeQuery(); 
		if (found = result.next()) {
			do {
					if(result.getString("Activity").equals("Purchased"))
					{
						PreparedStatement st = conn1.prepareStatement("UPDATE zli.orders SET  Status = ? WHERE OrderNumber = ?");
						st.setString(1,"Rejected");
						st.setInt(2,OrderNum);
						st.executeUpdate();
						flag=true;
			    	}
			}while(result.next());
		}
			return flag;
	}
		

	}





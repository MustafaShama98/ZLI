package dbControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.AccountCustomer;
import entities.Orders;
import entities.enumE.AccountStatus;
import entities.enumE.OpType;
import entities.users.User;

public class dbMangeAccountsB_M {

	public static ArrayList<AccountCustomer> AccountList;

	/**
	 * @param conn1
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<AccountCustomer> LoadOrdersTableB_M(Connection conn1, User user) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// this function for loading the accounts data to the table
		AccountList = new ArrayList<AccountCustomer>();
    	boolean found = false;
		String query  = "SELECT FirstName,LastName,StatusInSystem FROM zli.customer;";
		PreparedStatement statment = conn1.prepareStatement(query);
		ResultSet result = statment.executeQuery();
		if (found = result.next()) {
			do {		
				AccountList.add(new AccountCustomer(result.getString("FirstName"), result.getString("LastName"),result.getString("StatusInSystem")));
			
			}while(result.next());
              return AccountList;
              
		}else {
				return null;
				
			}
	}

	/**
	 * @param conn1
	 * @param MyObject
	 * @param usertmp
	 * @return
	 * @throws SQLException
	 */
	public static boolean UnFreezeAccount(Connection conn1, Object MyObject, User usertmp) throws SQLException {
		// TODO Auto-generated method stub
		/// this function for unfreezing account and it changes the account status to CONFIRMED 
		AccountList = new ArrayList<AccountCustomer>();
		    boolean found = false;	
		    Boolean flag=false;
			
			
		String[] str=(String[])MyObject;
		     String first=str[0];
		     String last=str[1];

		    String query  = "SELECT FirstName,LastName,StatusInSystem FROM zli.customer WHERE FirstName=? AND LastName=?;";
			PreparedStatement statment = conn1.prepareStatement(query);
			statment.setString(1,first);
			statment.setString(2,last);
			ResultSet result = statment.executeQuery(); 
			if (found = result.next()) {
				do {
						if((result.getString("StatusInSystem")!="FROZEN"))
						{
							PreparedStatement st = conn1.prepareStatement("UPDATE zli.customer SET  StatusInSystem = ? WHERE FirstName=? AND LastName=?;"); // QUERY TO CHANGE THE STATUS
							st.setString(1,"UNFROZEN");
							st.setString(2,first);
							st.setString(3,last);
							st.executeUpdate();
							flag=true;
				    	}
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
	public static boolean ConfirmAccount(Connection conn1, Object MyObject, User usertmp) throws SQLException {
		// TODO Auto-generated method stub
		//this function confirms account status 
		AccountList = new ArrayList<AccountCustomer>();
	    boolean found = false;	
	    Boolean flag=false;
		
		
	String[] str=(String[])MyObject;
	     String first=str[0];
	     String last=str[1];

	    String query  = "SELECT FirstName,LastName,StatusInSystem FROM zli.customer WHERE FirstName=? AND LastName=?;"; // query to bring the account details
		PreparedStatement statment = conn1.prepareStatement(query);
		statment.setString(1,first);
		statment.setString(2,last);
		ResultSet result = statment.executeQuery(); 
		if (found = result.next()) {
			do {
					if((result.getString("StatusInSystem")!="PENDING_APPROVAL"))
					{
						PreparedStatement st = conn1.prepareStatement("UPDATE zli.customer SET  StatusInSystem = ? WHERE FirstName=? AND LastName=?;");
						st.setString(1,"CONFIRMED");
						st.setString(2,first);
						st.setString(3,last);
						st.executeUpdate();
						flag=true;
			    	}
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
	public static boolean FreezeAccount(Connection conn1, Object MyObject, User usertmp) throws SQLException {
		// TODO Auto-generated method stub
		/// this function for freezing account status, after that the customer will not be able to  enter the system
		
		
		AccountList = new ArrayList<AccountCustomer>();
	    boolean found = false;	
	    Boolean flag=false;
		
		
	String[] str=(String[])MyObject;
	     String first=str[0];
	     String last=str[1];

	    String query  = "SELECT FirstName,LastName,StatusInSystem FROM zli.customer WHERE FirstName=? AND LastName=?;";//query to bring account details
		PreparedStatement statment = conn1.prepareStatement(query);
		statment.setString(1,first);
		statment.setString(2,last);
		ResultSet result = statment.executeQuery(); 
		if (found = result.next()) {
			do {
					if((result.getString("StatusInSystem")!="CONFIRMED"))
					{
						PreparedStatement st = conn1.prepareStatement("UPDATE zli.customer SET  StatusInSystem = ? WHERE FirstName=? AND LastName=?;");
						st.setString(1,"FROZEN");
						st.setString(2,first);
						st.setString(3,last);
						st.executeUpdate();
						flag=true;
			    	}
			}while(result.next());
		}
			return flag;	}
			
}

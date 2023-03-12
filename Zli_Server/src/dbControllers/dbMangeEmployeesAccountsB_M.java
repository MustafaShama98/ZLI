package dbControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.AccountCustomer;
import entities.AccountEmployee;
import entities.enumE.AccountStatus;
import entities.enumE.OpType;
import entities.users.User;

public class dbMangeEmployeesAccountsB_M {
	public static ArrayList<AccountEmployee> AccountList;

	/**
	 * @param conn1
	 * @param MyObject
	 * @param usertmp
	 * @return
	 * @throws SQLException
	 */
	public static boolean ApproveAccount(Connection conn1, Object MyObject, User usertmp) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		/// this function for approving the account access permission 

				AccountList = new ArrayList<AccountEmployee>();
				    boolean found = false;	
				    Boolean flag=false;
					
					

				
				String[] str=(String[])MyObject;
				     String first=str[0];
				     String last=str[1];

				    String query  = "SELECT FirstName,LastName,permission FROM zli.branchemployee WHERE FirstName=? AND LastName=?;"; //query to bring the account details 
					PreparedStatement statment = conn1.prepareStatement(query);
					statment.setString(1,first);
					statment.setString(2,last);

					ResultSet result = statment.executeQuery(); 
					if (found = result.next()) {
						do {
								if((result.getString("permission")!="DENIED"))
								{
									PreparedStatement st = conn1.prepareStatement("UPDATE zli.branchemployee SET  permission = ? WHERE FirstName=? AND LastName=?");
									st.setString(1,"APPROVED");
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
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<AccountEmployee> LoadOrdersTableB_M(Connection conn1, User user) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// this function for loading the data into the table
		AccountList = new ArrayList<AccountEmployee>();
    	boolean found = false;
		String query  = "SELECT FirstName,LastName,permission FROM zli.branchemployee;";// query to bring account details
		PreparedStatement statment = conn1.prepareStatement(query);
		ResultSet result = statment.executeQuery();
		if (found = result.next()) {
			do {		
				AccountList.add(new AccountEmployee(result.getString("FirstName"), result.getString("LastName"),result.getString("permission")));
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
	public static boolean DenyAccount(Connection conn1, Object MyObject, User usertmp) throws SQLException {
		// TODO Auto-generated method stub
		//this function for denying the access permissions for the branchemployee, after that he will not be able to enter the system
		AccountList = new ArrayList<AccountEmployee>();
	    boolean found = false;	
	    Boolean flag=false;
		
		
		String[] str=(String[])MyObject;
		     String first=str[0];
		     String last=str[1];

	  

	    String query  = "SELECT FirstName,LastName,permission FROM zli.branchemployee WHERE FirstName=? AND LastName=?;"; //query to bring account details
		PreparedStatement statment = conn1.prepareStatement(query);
		statment.setString(1,first);
		statment.setString(2,last);

		ResultSet result = statment.executeQuery(); 
		if (found = result.next()) {
			do {
					if((result.getString("permission")!="APPROVED"))
					{
						PreparedStatement st = conn1.prepareStatement("UPDATE zli.branchemployee SET  permission = ? WHERE FirstName=? AND LastName=?;");
						st.setString(1,"DENIED");
						st.setString(2,first);
						st.setString(3,last);
						st.executeUpdate();
						flag=true;
			    	}
			}while(result.next());
		}
			return flag;
	}

}

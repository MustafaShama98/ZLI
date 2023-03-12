package dbControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import entities.enumE.MessageType;
import entities.enumE.OpType;
import entities.users.Complaints;
import entities.users.User;
import entities.users.branchemployee.BranchEmployee;
import serverManager.mysqlConnection;

/**
 * this class get and put data from and to the data base in the table complaints
 * @author Carol
 *
 */

public class dbComplaints {

	public static ArrayList<Complaints> ComplaintsList;//////////////////
	public  mysqlConnection sql;
   // private static Connection conn1 = null;

	/**
	 * the function take all the complaints data (CustomerId,Complaints) and put it in the array List ComplaintsList
	 * @param conn1  the connection
	 * @return the array list that have all the complaints we have put in the ComplaintsList
	 * @throws SQLException
	 */
    public static ArrayList<Complaints> LoadComplaintsTableType(Connection conn1) throws SQLException
	{ 
    	ComplaintsList = new ArrayList<Complaints>();
    	boolean found = false;
			String query  = "SELECT * FROM complaints;";
			PreparedStatement statment = conn1.prepareStatement(query);
			//statment.setString(1,"Product");
			//statment.setString(2,TableType.toString());
			ResultSet result = statment.executeQuery();
			if (found = result.next()) {
				do {	
				ComplaintsList.add(new Complaints(result.getInt("CustomerID"),result.getString("Complaint")));
				}while(result.next());

			}else {
					System.out.println("not found");
				}
		return ComplaintsList;
	}
    
    /**
     * this method get the complaint and put it in the data base in table complaints[CustomerID,Complaint]
     * @param conn the connection
     * @param complaint is Complaint type that have customerID and the complaint
     * @return
     */
    public static MessageType ParasingComplaintData(Connection conn, Complaints complaint) {
		Complaints comp = complaint  ;
		PreparedStatement st;
		int success_flag=0;
		try {
			st = conn.prepareStatement("INSERT INTO complaints (CustomerID,Complaint) VALUES (?,?);");
			//st.setInt(1, 1);

			st.setInt(1,complaint.getCustomerID());
			st.setString(2,comp.getComplaint());
			success_flag=st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(success_flag==1)
		return MessageType.Success;
		else return MessageType.Fail;
	
	    
	}
    
    /**
     * This method Check if the customer Id exist in the login table  
     * @param conn the connection
     * @param complaint in the complaint data (customerID,complaint)
     * @return string "Found" if exist "Not found" if not 
     */
   		public static String find_user(Connection conn, Complaints complaint) {
    	String query = "SELECT usertype,active_status,userID FROM login WHERE userID = ? AND usertype = ? ;";
    	boolean found = false;
    	//int found=0;
    	try {
    		PreparedStatement stmt = conn.prepareStatement(query);
    		stmt.setInt(1, complaint.getCustomerID() );
    		stmt.setString(2,"Customer");
    		ResultSet result = stmt.executeQuery();
    		//found=result.getInt("active_status");
    		//do {
    		if(found=result.next()) {
    		//if(found==1) {
    			System.out.println("found");
    			return "Found";
    		}//}while(result.next());
    		
    	}
    		
    	catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
		return "Not found";

    }

}

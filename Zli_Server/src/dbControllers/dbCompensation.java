package dbControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.enumE.MessageType;
/**
 * This class is to change in the data base in the table complaint
 * @author Carol
 *
 */
public class dbCompensation {
/**
 * This method delete complaint row that have the customer id customerId
 * @param conn
 * @param customerId is complaint's customer id  
 * @return true if we success to delete the row else false
 */
    public static MessageType DeleteComplaintData(Connection conn, int customerId) {
		PreparedStatement st;
		int success_flag=0;
		try {
			st = conn.prepareStatement("DELETE FROM complaints WHERE CustomerID=?;");
			//st.setInt(1, 1);
			st.setInt(1,customerId);
			//st.setString(2,"Customer");
			success_flag=st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(success_flag==1)
		return MessageType.Success;
		else return MessageType.Fail;
	
	}
}

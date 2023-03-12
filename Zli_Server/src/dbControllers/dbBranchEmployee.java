package dbControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import entities.enumE.MessageType;
import entities.users.User;
import entities.users.branchemployee.BranchEmployee;

public class dbBranchEmployee {

	
	public static MessageType ParasingSurveyData(Connection conn, BranchEmployee user) {
		BranchEmployee BE =  user;
		int ret=0, length = BE.getAnswers().size();
		ArrayList<Integer> list = BE.getAnswers();
		PreparedStatement st;
		try {
			st = conn.prepareStatement("INSERT INTO survey (Q1,Q2,Q3,Q4,Q5,Q6,Branch,Date) VALUES (?,?,?,?,?,?,?,?);");
			//st.setInt(1, 1);
			 
			for (int i = 0; i+1 < length+1; i++) {
				st.setInt(i+1, list.get(i));
			}
			st.setString(7, BE.getBranch().toString());
			st.setString(8, LocalDate.now().toString());
			ret=st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ret==1)
		return MessageType.Success;
		else return MessageType.Fail;
	
	    
	}
}

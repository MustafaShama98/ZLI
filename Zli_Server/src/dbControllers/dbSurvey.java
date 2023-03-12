package dbControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import entities.enumE.MessageType;
import entities.survey.Survey;
import entities.users.branchemployee.BranchEmployee;
import javafx.util.converter.DateTimeStringConverter;
/**
 * This class have functions that deal with the data in the data base table survey
 * @author Carol
 *
 */
public class dbSurvey {
	public static ArrayList<Survey> SurveysList;
/**
 * This method get survey that have the survey data and insert this data to the data base in the survey table 
 * @param conn connection
 * @param survey is from Survey type that have survey data (Q1,Q2,Q3,Q4,Q5,Q6,Branches,StartDate,EndDate,ProductID,DurationS,DurationE)
 * @return if the data insert success return true else return false
 */
    public static MessageType InsertSurveyData(Connection conn, Survey survey) {
		Survey sur=survey;
		PreparedStatement st;
		int success_flag=0;
		try {
			st = conn.prepareStatement("INSERT INTO survey (Q1,Q2,Q3,Q4,Q5,Q6,Branches,StartDate,EndDate,ProductID,DurationS,DurationE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
			//st.setInt(1, 1);

			for (int i = 1; i < 7; i++) {
			st.setString(i,sur.getQuestions()[i-1]);	
			}
			
			if(sur.getBranches()!=null) 
			{
			st.setString(7,sur.getBranches().toString());
			}else 
			{
				st.setString(7,"");
			}
			st.setString(8,sur.getStartDate().toString());
			st.setString(9,sur.getEndDate().toString());
			if(sur.getProduct()!=null) 
			{
			st.setString(10,sur.getProduct());
			}else 
			{
				st.setString(10,"");
			}
			if(sur.getStartD()!=null) 
			{
			st.setString(11,sur.getStartD().toString());
			}else 
			{
				st.setString(11,"");
			}
			if(sur.getEndD()!=null) { 
			st.setString(12,sur.getEndD().toString());
			}else 
			{
				st.setString(12,"");
			}
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
     * This method get brancheEmployee and return array list of all the surveys that created for his branch
     * @param conn1 the connection
     * @param branchEmployee is the account that we logged in from
     * @return Array List that have the surveys
     * @throws SQLException
     */
    public static ArrayList<Survey> LoadSurveysTable(Connection conn1,BranchEmployee branchEmployee) throws SQLException
	{ 
    	SurveysList = new ArrayList<Survey>();
    	boolean found = false;
    	String query  = "SELECT * FROM survey WHERE Branches LIKE ?;";
			PreparedStatement statment = conn1.prepareStatement(query);
			statment.setString(1,"%"+branchEmployee.getBranch().toString()+"%");

			ResultSet result = statment.executeQuery();
			if (found = result.next()) {
				do {	
					SurveysList.add(new Survey(((result.getString("DurationS")+" - "+result.getString("DurationE"))), result.getString("StartDate")+" - "+ result.getString("EndDate"), result.getString("ProductID"), result.getString("SurveyId")));
				}while(result.next());

			}else {
					System.out.println("not found");
				}
		return SurveysList;
	}
    
    /**
     * This method get the survey questions that have the survey id SurveyID from the data base table survey and put it in String[] 
     * @param conn1 the connection with the db
     * @param SurveyID
     * @return String[] that have the questions
     * @throws SQLException
     */
    public static String[] getSurveyQuestions(Connection conn1,String SurveyID) throws SQLException
  	{ 
      	String[] Questions=new String[6];
      	boolean found = false;
      	String query  = "SELECT * FROM survey WHERE SurveyId = ?;";
  			PreparedStatement statment = conn1.prepareStatement(query);
  			statment.setString(1,SurveyID);
  			ResultSet result = statment.executeQuery();
  			if (found = result.next()) {
  				do {	
  				Questions[0]=result.getString("Q1");
  				Questions[1]=result.getString("Q2");
  				Questions[2]=result.getString("Q3");
  				Questions[3]=result.getString("Q4");
  				Questions[4]=result.getString("Q5");
  				Questions[5]=result.getString("Q6");
  				
  				}while(result.next());

  			}else {
  					System.out.println("not found");
  				}
  		return Questions;
  	}
    
    
    
    
}

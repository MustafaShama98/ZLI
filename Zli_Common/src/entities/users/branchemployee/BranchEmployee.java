package entities.users.branchemployee;
import java.util.ArrayList;


/// BrancEmployee enters the results of the survey.
import entities.enumE.userType;
import entities.enumE.Catalog.BranchType;
import entities.users.User;

public class BranchEmployee extends User {

	private static final long serialVersionUID = -6067683660833775769L;
	private ArrayList<Integer> Answers;
	 private BranchType Branch;
	 private int Id;
		private String Firstname;
		private String Lastname;
		private String StatusInSystem;
		private int isLoggedIn;
		private String PhoneNumber;
		private String Email;
		private String surveyID;
		 private String permission;
			public String getPermission() {
				return permission;
			}
			public void setPermission(String permission) {
				this.permission = permission;
			}
	public BranchEmployee(User user) {
		super(userType.BranchEmployee,user.getUserName(),user.getPassword(),user.getId(),user.getisLoggedIn());
	}
	public String getEmail() {
		return Email;
	}

	public String getsurveyID() {
		return surveyID;
	}
	
	public void setSurveyId(String surveyid) {
		surveyID=surveyid;
	}	
	
	public void setEmail(String email) {
		Email = email;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		this.Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		this.Lastname = lastname;
	}

	public String getStatusInSystem() {
		return StatusInSystem;
	}

	public void setStatusInSystem(String statusInSystem) {
		StatusInSystem = statusInSystem;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.PhoneNumber = phoneNumber;
	}
	public void setAnswers(ArrayList<Integer> as) {
		Answers = as;
	}	
	public ArrayList<Integer> getAnswers(){
		return Answers;
	}
	public void setBranch(BranchType b) {
		Branch = b;
	}

	public BranchType getBranch() {
		return Branch;
	}
	
	public BranchType stringToBranchType(String branch) {
		if(branch.equals("NORTH")) 
		{
			return BranchType.NORTH;
		}
		if(branch.equals("EAST")) 
		{
			return BranchType.EAST;
		}
		if(branch.equals("SOUTH")) 
		{
			return BranchType.SOUTH;
		}
		if(branch.equals("WEST")) 
		{
			return BranchType.WEST;
		}
		return null;
		
	}
	
}
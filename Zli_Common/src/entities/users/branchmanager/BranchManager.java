package entities.users.branchmanager;
//Branch Manger confirms the orders and  view the reports related to his branch .
import entities.enumE.userType;
import entities.users.User;

public class BranchManager extends User{

	private int Id;


	private String Firstname;
	private String Lastname;
	private String StatusInSystem;

	private String PhoneNumber;
	private String Email;
	private String Br_type;
	private String RepTYPE;
	private String month;
	private String year;
	
public BranchManager(User user)
	{
		super(userType.BranchManager,user.getUserName(),user.getPassword(),user.getId(),user.getisLoggedIn());
		
	}


public String getBr_type() {
	return Br_type;
}
public void setBr_type(String br_type) {
	Br_type = br_type;
}
public String getRepTYPE() {
	return RepTYPE;
}
public void setRepTYPE(String repTYPE) {
	RepTYPE = repTYPE;
}
public String getMonth() {
	return month;
}
public void setMonth(String month) {
	this.month = month;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
	public String getEmail() {
		return Email;
	}


	public String getFirstname() {
		return Firstname;
	}


	public void setFirstname(String firstname) {
		Firstname = firstname;
	}


	public String getLastname() {
		return Lastname;
	}


	public void setLastname(String lastname) {
		Lastname = lastname;
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
		PhoneNumber = phoneNumber;
	}
}
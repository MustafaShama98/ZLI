package entities.users.serviceexpert;

import entities.enumE.BranchType;
import entities.enumE.userType;
import entities.users.User;

public class SurveyExpert extends User{
	
	private String Firstname;
	private String Lastname;
	private String StatusInSystem;
	private String PhoneNumber;
	private String Email;
	public SurveyExpert(User user) {
		super(userType.SurveyExpert,user.getUserName(),user.getPassword(),user.getId(),user.getisLoggedIn());
		
	}
	
	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		this.Email = email;
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

	
	
}

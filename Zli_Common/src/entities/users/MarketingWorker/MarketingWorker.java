package entities.users.MarketingWorker;

import entities.enumE.userType;
import entities.users.User;

public class MarketingWorker extends User {
	private String Id;
	private String Firstname;
	private String Lastname;
	private String StatusInSystem;
	private int isLoggedIn;
	private String PhoneNumber;
	private String Email;
	
	
public MarketingWorker(User user)
	{
		super(userType.MarketingWorker,user.getUserName(),user.getPassword(),user.getId(),user.getisLoggedIn());
		
	}
	public String getEmail() {
		return Email;
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

}


	
	
	
	
	
	
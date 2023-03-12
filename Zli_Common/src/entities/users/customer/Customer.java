package entities.users.customer;
/// Customer can view the catalog, view recent orders, cancel them , make order, also he can choose whether he
//wants delivery or to pick the order from the branch, and at the end he needs to pay  
import entities.enumE.userType;
import entities.users.User;

public class Customer extends User {
	
	private String Firstname;
	private String Lastname;
	private String StatusInSystem;
	private int isLoggedIn;
	private String PhoneNumber;
	private String CreditCardNumber;
	private String CreditCardCVVCode;
	private String CreditCardDateOfExpiration;
	private String Email;
	
	
public Customer(User user)
	{
		super(userType.Customer,user.getUserName(),user.getPassword(),user.getId(),user.getisLoggedIn());
		
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

	public String getCreditCardNumber() {
		return CreditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.CreditCardNumber = creditCardNumber;
	}

	public String getCreditCardCVVCode() {
		return CreditCardCVVCode;
	}

	public void setCreditCardCVVCode(String creditCardCVVCode) {
		this.CreditCardCVVCode = creditCardCVVCode;
	}

	public String getCreditCardDateOfExpiration() {
		return CreditCardDateOfExpiration;
	}

	public void setCreditCardDateOfExpiration(String creditCardDateOfExpiration) {
		this.CreditCardDateOfExpiration = creditCardDateOfExpiration;
	}

	
	
	
	
	
	
}

package entities.users;

import java.io.Serializable;
/**
 * class for complaint details that have the customer id and his complaints
 * @author Carol
 *
 */
public class Complaints implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5338875401403198315L;
	private int CustomerID;
	private String Complaint;
	
	private int ComplainID;
	/**
	 * Complaints constructor
	 * @param CustomerId the customer id number
	 * @param Complaint the complaint
	 */
	public Complaints(int CustomerId,String Complaint){
		this.Complaint=Complaint;
		this.CustomerID=CustomerId;
	}
	
	/**
	 * 
	 * @return  theComplaint
	 */
	public String getComplaint() {
		return Complaint;
	}
	
	/**
	 * set the complaint
	 * @param complaint customer complaint
	 */
	public void setComplaint(String complaint) {
		Complaint = complaint;
	}
	
	/**
	 * 
	 * @return the customer id
	 */
	public int getCustomerID() {
		return CustomerID;
	}
	
	/**
	 *set the customer id
	 * @param customerID
	 */
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public int getComplainID() {
		return ComplainID;
	}

	public void setComplainID(int complainID) {
		ComplainID = complainID;
	}

}

package entities;

import java.io.Serializable;
import java.sql.Blob;

public class Report implements Serializable{

	
	/**
	 * 
	 */
	private Blob reportDetails;

	public Report(Blob details) { super();this.reportDetails=details;} /// constructor
	
    /// getters and setters 
	public Blob getDetails() {
		return reportDetails;
	}

	public void setDetails(Blob details) {
		this.reportDetails = details;
	}
	
	
	
}

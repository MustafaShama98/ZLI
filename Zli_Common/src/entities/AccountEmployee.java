package entities;

import java.io.Serializable;

import entities.enumE.AccountStatus;

public class AccountEmployee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6499606941443831058L;
	private String FirstName;
	private String LastName;
	private String Permission;
	public AccountEmployee(String FirstName,String LastName,String permission) // this constructor used when the branch manager wants to manage customers accounts 
	{
		super();
		this.FirstName=FirstName;
		this.LastName=LastName;
		this.Permission=permission;
	
	}

	public String getPermission() {
		return Permission;
	}
	public void setPermission(String permission) {
		Permission = permission;
	}

	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
}
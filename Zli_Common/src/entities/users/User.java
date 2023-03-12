package entities.users;
// This is a superclass, that everyone who enters the system extends this class.
// we have 7 sub-classes that extends this one : 
// 1) Customer . 2) BranchEmployee. 3)BranchManger. 4)CEO.  5)NetWorker. 6)ServiceExpert  7)ServiceWorker.

import java.io.Serializable;

import entities.enumE.userType;

public class User implements Serializable{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 3752095027897027772L;

private userType UserType;
private String username;
private String password;
private int isLoggedIn;
private int Id;

public User(int Id ,int isLoggedIn)
{
	this.Id=Id;
	this.isLoggedIn=isLoggedIn;
}
	public User(userType userType, String username, String password,int Id) {
	
		this.UserType = userType;
		this.username = username;
		this.password = password;
		this.Id=Id;
	}

	public User(userType userType, String username, String password,int Id, int isLoggedIn) {
	
		this.UserType = userType;
		this.username = username;
		this.password = password;
		this.Id=Id;
		this.isLoggedIn = isLoggedIn;
	}
	public User(userType userType, String username, String password) {
		
		this.UserType = userType;
		this.username = username;
		this.password = password;
		
	}
	public int getId() {
		return Id;
	}
	public void setId(int Id) {
		this.Id = Id;
	}
	public User( String username, String password) {
		
		this.username = username;
		this.password = password;
	}
	public User(String string, String string2, int i) {
		// TODO Auto-generated constructor stub
	}
	public userType getUserType() {
		return UserType;
		
	}
	public String getUserName() {
		return username;
	}
	public String getPassword() {
		return password;
	}
//	public String getName() {
//		
//		return username;
//	}
	public int getisLoggedIn() {
		return isLoggedIn;
	}
	public void setUserType(userType t) {
		this.UserType = t;
	}
	public void setLoggedIn(int isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

		public void tostring() {
			// TODO Auto-generated method stub
			 System.out.println(getUserName()+" "+getPassword()+
					 " "+ getUserType() + " " + getId());
			
		}
		
}

package dbControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Orders;
import entities.enumE.AccountStatus;
import entities.enumE.OpType;
import entities.enumE.userType;
import entities.users.User;
import entities.users.customer.Customer;
import serverManager.mysqlConnection;

public class db_CreateAccountB_M {

	public  mysqlConnection sql;
	private static Customer customer;
	/**
	 * @param conn
	 * @param msg
	 * @param usertmp
	 * @return
	 * @throws SQLException
	 */
	public static boolean CreateAccount(Connection conn, Object msg, User usertmp) throws SQLException {
		/// this function for creating a new account in the system
		//the branch manager enters the details of the account
		// TODO Auto-generated method stub
		Customer customer=(Customer)msg;
		boolean flag=false;
		if(exist(conn,customer)) { /// if these data are already in the system 
			return false;
		}else {
			
			String query = "INSERT INTO `zli`.`customer` (`UserID`, `FirstName`, `LastName`, `StatusInSystem`, `IsLoggedIn`, `Email`, `PhoneNumber`, `CreditCardNumber`, `CreditCardCVVCode`, `CreditCardDateOfExpiration`) VALUES"
					+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, customer.getId());
			stmt.setString(2, customer.getFirstname());
			stmt.setString(3, customer.getLastname());
			stmt.setString(4,customer.getStatusInSystem());
			stmt.setInt(5, 0);
			stmt.setString(6, customer.getEmail());
			stmt.setString(7, customer.getPhoneNumber());
			stmt.setString(8, customer.getCreditCardNumber());
			stmt.setString(9, customer.getCreditCardCVVCode());
			stmt.setString(10, customer.getCreditCardDateOfExpiration());
			int result = stmt.executeUpdate();
			if(result==1)
			flag=true;
			}catch(SQLException e) {e.printStackTrace();}
		return flag;
	}
		}
	private static boolean exist(Connection conn,Customer customer2) { 
		/// this function for finding if we have customer in the system with the same data that the branch manager has entered  
		// TODO Auto-generated method stub
		String query = "SELECT userID,FirstName,LastName,Email,PhoneNumber,CreditCardNumber,CreditCardCVVCode,CreditCardDateOfExpiration FROM zli.customer "
				+ "WHERE userID = ? AND FirstName = ? "
				+ "AND LastName = ? AND Email = ? AND PhoneNumber = ? "
			   +"AND CreditCardNumber = ? AND CreditCardCVVCode = ? "
				+ "AND CreditCardDateOfExpiration = ? ;";
		boolean found = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, customer2.getId());
			stmt.setString(2, customer2.getFirstname());
			stmt.setString(3, customer2.getLastname());
			stmt.setString(4, customer2.getEmail());
			stmt.setString(5, customer2.getPhoneNumber());
			stmt.setString(6, customer2.getCreditCardNumber());
			stmt.setString(7, customer2.getCreditCardCVVCode());
			stmt.setString(8, customer2.getCreditCardDateOfExpiration());
			ResultSet result = stmt.executeQuery();
			if(result==null)/// there is customer in the system with these data
				return true;
		
			}catch(SQLException e) {e.printStackTrace();}
		return false;

		}
	}
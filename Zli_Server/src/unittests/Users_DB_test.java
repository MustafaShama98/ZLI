package unittests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dbControllers.dbLogin;
import entities.Message;
import entities.enumE.DB_Message;
import entities.enumE.OpType;
import entities.users.User;
import entities.users.branchemployee.BranchEmployee;
import entities.users.customer.Customer;
import serverManager.ServerHandler;
import serverManager.mysqlConnection;

public class Users_DB_test {

    Connection conn;
    Customer customer;
    User user;
    DB_Message msg;
    boolean result;
	 User user2;

	@Before
	public void setup() {
		user2=new User("Sam","Sam",10);
		 customer = new Customer(user2); 
		mysqlConnection sql = mysqlConnection.Connect("root", "Aa123456", "zli");
        conn =mysqlConnection.getConncetion();
  	    ServerHandler.conn = conn;
  	    dbLogin.conn = conn;
        user=new User(null,null);
        
	}
	/** checking function find_user when found and the status is confirmed 
	 * input : username=d , passward=d
	 * expected: return DB_message.found (login success)
	 */
 @Test
public void TestLoginFound() throws Exception {
	user.setUserName("d");
	user.setPassword("d");
	msg=dbLogin.find_user(conn, user);
	assertEquals(msg,DB_Message.found);
}
 /**checking function find_user when the user notFound
  * input : userName= k , password =k
  * expected : return DB_message.notfound (login failed)
  */
 
 @Test
 public void TestLoginNotFound() throws Exception {
 	user.setUserName("k");
 	user.setPassword("k");
 	msg=dbLogin.find_user(conn, user);
 	assertEquals(msg,DB_Message.notfound);
 }
 
  /**
   * checking functionality when the user is already connected
   * input : username=cu , password=cu
   * expected :return DB_Message isLogged (login failed)
   */
 @Test
 public void TestisisLogged() throws Exception {
 	user.setUserName("cu");
 	user.setPassword("cu");
 	PreparedStatement st = conn.prepareStatement("UPDATE login SET active_status = ? WHERE username = ? AND password=?;");
	st.setInt(1, 1);
	st.setString(2,"cu");
	st.setString(3,"cu");
	st.executeUpdate();
 	user.setLoggedIn(1);
 	msg=dbLogin.find_user(conn, user);
 	assertEquals(msg,DB_Message.isLogged);
 }
 /**
  * checking the function when user status is Frozen
  * input: username= Luna , password=Luna
  * expected: return DB_Message FROZEN(login failed)
  */
 
 @Test
public void testCustomerIsFrozen() throws Exception {
	 User user2=new User("Luna","Luna",9);
	 Customer customer = new Customer(user2); 
	 String str=dbLogin.setCustomer(customer);
	assertEquals(str,"FROZEN");
}
 /**
  * checking  the function when user Status is PENDING_APPROVAL
  * input : username=Sam ,password=Sam
  * expected : return is PENDING_APPROVAL(login failed)
  */
 
 @Test
 public void testCustomerIsPENDING_APPROVAL() throws Exception {
 	// User user2=new User("Sam","Sam",10);
 	// Customer customer = new Customer(user2); 
 	 String str=	dbLogin.setCustomer(customer);
 	assertEquals(str,"PENDING_APPROVAL");
 }
 /**
  * checking the permission for the branch employee (DENIED,APPROVED)
  * input: username=Olivia ,password=Olivia1
  * expected: return DENIED and (login failed)
  */
 
 @Test
 public void testBranchemployeeIsDENIED() throws Exception {
 	 User user2=new User("Olivia","Olivia1",1);
 	BranchEmployee branchemployee= new BranchEmployee(user2); 
 	 String str=dbLogin.setBranchEmployee(branchemployee);
 	assertEquals(str,"DENIED");
}
 /**
  * 
  * checking the permission for the branch employee (DENIED,APPROVED)
  * input : username=be, password=be
  * expected: return APPROVED and (login success)
  */
 @Test
 public void testBranchemployeeIsAPPROVED() throws Exception {
 	 User user2=new User("be","be",25);
 	BranchEmployee branchemployee= new BranchEmployee(user2); 
 	 String str=dbLogin.setBranchEmployee(branchemployee);
 	assertEquals(str,"APPROVED");
}

}

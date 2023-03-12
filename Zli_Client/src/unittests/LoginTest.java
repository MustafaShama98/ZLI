package unittests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controllers.LoginController;
import entities.Message;
import entities.enumE.MessageType;
import entities.enumE.userType;
import entities.users.User;
import entities.users.customer.Customer;

public class LoginTest {
	
	/**
	 * Fake login stub controller that we have control over what it returns
	 * 
	 */
	class LoginControllerStub implements I_LoginController{

		@Override
		public boolean input_empty() {
			// TODO Auto-generated method stub
			return isEmptyInput;
		}

		/**
		 * A function that sends a message to that server and receives back a message Object
		 */
		@Override
		public Message sendLoginToServer(User user) {
			// TODO Auto-generated method stub
			return msg;
		}

		@Override
		public boolean isAlreadyLogin() {
			// TODO Auto-generated method stub
			return islogged;
		}

		@Override
		public boolean isFrozenAcc() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isDeniedAcc() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean incorrectDetails() {
			// TODO Auto-generated method stub
			return true;
		}
 
	}
	User user;
	boolean islogged;
	boolean isEmptyInput=false;
	String loginMessage;
	LoginController loginController;
	I_LoginController loginStub;
	Message msg;
	@Before
	public void setUp() throws Exception {
		loginController = new LoginController();
		loginStub = new LoginControllerStub();
		loginController.setController(loginStub);
		user  = new User("cu","cu");
	}


	/**
	   * checking functionality if user input any detalis in the gui
	   * input : empty
	   * expected :return true
	   */
	@Test
	public void EmptyLoginFields() throws Exception {
		isEmptyInput= true;
	
		assertTrue(loginController.controller.input_empty());
	}
	
	@Test
	public void isNotEmptyLoginFields() throws Exception {
		isEmptyInput= false;
		assertFalse(	loginController.controller.input_empty());
	}
	/**
	   * Functionality: Checking whether the user is already connected to the server.
	   * Input : The username and passsword in the Object user
	   * expected :MessageType.IsLogin
	   */
	@Test
	public void testisLoggedin() throws Exception {
		msg = new Message(MessageType.IsLogin);
	islogged =true;
	loginController.controller.sendLoginToServer(user);
assertEquals(MessageType.IsLogin, loginController.controller.sendLoginToServer(user).getMessageType());
	
	}
	@Test
	public void test_isFrozen() throws Exception {
		msg = new Message(MessageType.IsFrozen);
	loginController.controller.sendLoginToServer(user);
assertEquals(MessageType.IsFrozen, loginController.controller.sendLoginToServer(user).getMessageType());
	
	}
	/**
	   * Functionality: Checking type of the user that has connected to the server.
	   * Input : The username and passsword in the Object user
	   * expected : Customer : test passed
	   */
	@Test
	public void test_ifCustomer() throws Exception {
		user.setUserType(userType.Customer);
		msg = new Message(MessageType.Success,user);
Message msg_fromServer=	loginController.controller.sendLoginToServer(user);
assertEquals(userType.Customer, msg_fromServer.getUser().getUserType());
	
	}

}

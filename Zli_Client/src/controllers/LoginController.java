package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import clientManager.OpenWindow;
import controllers.users.ServiceWorker.ServiceWorkerComplaintsController;
import entities.Message;
import entities.enumE.MessageType;
import entities.enumE.OpType;
import entities.enumE.TimeType;
import entities.enumE.userType;
import entities.users.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import unittests.I_LoginController;
import view.UserView.UserUI;
import view.UserView.BranchEmployee.BranchEmployeeUI;
import view.UserView.BranchManagerView.BranchManagerUI;
import view.UserView.CeoView.CeoUI;
import view.UserView.CustomerView.CustomerUI;
import view.UserView.MarketingWorkerView.MarketingWorkerUI;
import view.UserView.ServiceWorker.ServiceWorkerUI;
import view.UserView.surveyExpertView.SurveyExpertUI;

public class LoginController implements Runnable, Initializable{

	@FXML
	private TextField username_input;

	@FXML
	private Label wrong_detalis;

	@FXML
	private Label empty_input;

	@FXML
	private Button loginBtn;
	@FXML
	private Label Login;
	@FXML
	private Label frozenMSG;
	@FXML
	private Label denied_msg;
	@FXML
	private PasswordField password_input;
	ActionEvent event;
	ClientRunner clientRunner;
	ArrayList<String> login_detalis;
	Thread t;
	ServiceWorkerUI SWI;

	static UserUI window;
	protected Message msg;
	public I_LoginController controller;
	private OpenWindow newWin4;

	/**
	 *  A property injection 
	 * @param controller :  stub login controller
	 */
	public void setController(I_LoginController controller) {
		this.controller = controller;
	}
/**
 * Starting the real login controller
 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		controller = new OriginalLoginController();
	}

	/**
	 * when clicked, start the client connection
	 * 
	 */
	public void ClickLogin(ActionEvent event) {

		clientRunner = ClientRunner.getClientRunner(this.getClass().getName());

		if (controller.input_empty()) {
			empty_input.setVisible(true);
			wrong_detalis.setVisible(false);
			Login.setVisible(false);
		} else

		{
			empty_input.setVisible(false);
			wrong_detalis.setVisible(false);
			Login.setVisible(false);
			User user = new User(userType.Unknown, username_input.getText(), password_input.getText());

			Message msg = controller.sendLoginToServer(user);

			switch (msg.getMessageType())

			{
			case Success: {
				username_input.clear();
				password_input.clear();
				user = msg.getUser();
				ClientRunner.setUser(user);

				switch (user.getUserType()) {
				case Customer: {

					window = new CustomerUI(user, event);
					break;
				}
				case BranchManager: {
					window = new BranchManagerUI(user, event);
					break;
				}
				case BranchEmployee: {
					window = new BranchEmployeeUI(user, event);
					break;
				}
				case SurveyExpert: {
					window = new SurveyExpertUI(user, event);
					break;
				}
				case MarketingWorker: {
					window = new MarketingWorkerUI(user, event);
					break;
				}
				case ServiceWorker: {
					System.out.println("herere");

					SWI = new ServiceWorkerUI(msg.getUser(), event);
					window = SWI;
//					newWin4 = new OpenWindow(event,"/view/UserView/ServiceWorker/ServiceWorker.Complaints.fxml","User Portal - "+ user.getUserName());
//						newWin4.hideWindow();	

					break;
				}
				case CEO: {
					window = new CeoUI(msg.getUser(), event);
					break;

				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + msg.getUser());
				}
				break;
			}

			case Fail: {

				controller.incorrectDetails();
				break;

			}
			case IsLogin: {
				controller.isAlreadyLogin();
				break;
			}
			case IsFrozen: {
				controller.isFrozenAcc();
				break;
			}
			case IsDenied: {
				controller.isDeniedAcc();
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + msg.getMessageType());

			}
		}

	}
						
	class OriginalLoginController implements I_LoginController{

		
		@Override
		public boolean input_empty() {
			if (username_input.getText().trim().isEmpty() || password_input.getText().trim().isEmpty())
				return true;
			else
				return false;
		}
		/**
		 * A function that sends a message to that server and receives back a message Object
		 */
		@Override
		public Message sendLoginToServer(User user) {
			clientRunner.ClientConnect();// pass user reference for future uses in the system

			clientRunner.SendReqToServer(new Message(OpType.Login, user));

			msg = (Message) clientRunner.obj;
			return msg;
		}
		@Override
		public boolean incorrectDetails() {
			try {
		Login.setVisible(false);
		clientRunner.closeConnection();
		System.out.println("failed");
		wrong_detalis.setVisible(true);
	} catch (IOException e) {

		e.printStackTrace();
	}
			return true;
	}

		@Override
		public boolean isAlreadyLogin() {
			try {
				wrong_detalis.setVisible(false);
				clientRunner.closeConnection();
				System.out.println("is login!");
				Login.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
			
		}

		@Override
		public boolean isFrozenAcc() {
			try {
				wrong_detalis.setVisible(false);
				clientRunner.closeConnection();
				Login.setVisible(false);
				System.out.println("is frozen!");
				frozenMSG.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;

		}

		@Override
		public boolean isDeniedAcc() {
			try {
				wrong_detalis.setVisible(false);
				clientRunner.closeConnection();
				Login.setVisible(false);
				System.out.println("is denied!");
				frozenMSG.setVisible(false);
				denied_msg.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;

		}
		
	}
	
	
	@SuppressWarnings("unused")
	private ArrayList<String> loginDetails() {
		login_detalis = new ArrayList<>();
		login_detalis.add(username_input.getText());
		login_detalis.add(password_input.getText());
		return login_detalis;

	}
	

	

	public static UserUI getUserWindow() {
		return window;
	}

	public static void Logout() {

		OpenWindow w = LoginController.getUserWindow().getWin();
		w.getStage().close();
		ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.Logout, ClientRunner.getUser()));
		try {
			ClientRunner.getClientRunner().closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}


		((Stage) (((Node) w.getEvent().getSource()).getScene().getWindow())).show();
	}
	

//	@Override
//	public void run() {
//
//			if(clientRunner.obj instanceof MessageType
//				 &&((MessageType)clientRunner.obj)== MessageType.AllOut) {
//					System.out.println("Logging out. Overridden by server.");
//					OpenWindow w =LoginController.getUserWindow().getWin();
//			    	w.getStage().close();
//			    	((Stage) (((Node)w.getEvent().getSource()).getScene().getWindow())).show();
//			    	try {
//						ClientRunner.getClientRunner().closeConnection();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//
//	}

	public void run() {
		// TODO Auto-generated method stub
		Object msg = clientRunner.obj;
		Message message = (Message) msg;
		if (msg instanceof MessageType && ((MessageType) msg) == MessageType.AllOut) {
			System.out.println("Logging out. Overridden by server.");
			OpenWindow w = LoginController.getUserWindow().getWin();
			w.getStage().close();
			((Stage) (((Node) w.getEvent().getSource()).getScene().getWindow())).show();
			try {
				ClientRunner.getClientRunner().closeConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (message.TimeType != null && ((message.TimeType) == TimeType.Done)) {

			ServiceWorkerComplaintsController controller = (ServiceWorkerComplaintsController) (SWI.getWin()
					.getController());
			Platform.runLater(controller);

		}

	}



}

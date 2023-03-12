package controllers.users.branchManager;

import java.net.URL;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import entities.Message;
import entities.Orders;
import entities.enumE.AccountStatus;
import entities.enumE.OpType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import entities.users.User;
import entities.users.customer.Customer;

public class NewAccountController implements Initializable{

    @FXML
    private AnchorPane NewAccountTab;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button CreateAnotherBtn;

    @FXML
    private TextField FirstNameField;

    @FXML
    private TextField LastNameField;

    @FXML
    private TextField EmailField;

    @FXML
    private TextField PhoneField;

    @FXML
    private TextField CardNumberField;

    @FXML
    private TextField CVVfield;
    @FXML
    private TextField IDfield;
    @FXML
    private TextField DateOfExpirationField;

    @FXML
    private Label msg_missing_data;

    @FXML
    private Label msg_success;
    @FXML
    private Label msg_exist;

private User user=new User(0,0);
private Customer customer=new Customer(user);
    private Message msg;
  
    /**
	 * Functionality: clearing the fields to allow the branch manager to enter new details 
	 */
    @FXML
    void ClickCreateAnotherBtn(ActionEvent event) {
    	CVVfield.clear();
    	IDfield.clear();
    	DateOfExpirationField.clear();
    	EmailField.clear();
    	CardNumberField.clear();
    	FirstNameField.clear();
    	LastNameField.clear();
    	PhoneField.clear();
    	msg_success.setVisible(false);
		CreateAnotherBtn.setVisible(false);
    }

    /**
     * Functionality: confirming the account details and after that we send the details to server
     * @param event
     */
    @FXML
    void clickConfirm(ActionEvent event) {
    	this.customer.setCreditCardCVVCode(CVVfield.getText());
    	this.customer.setId(Integer.parseInt(IDfield.getText()));
    	this.customer.setCreditCardDateOfExpiration(DateOfExpirationField.getText());
    	this.customer.setEmail(EmailField.getText());
    	this.customer.setCreditCardNumber(CardNumberField.getText());
    	this.customer.setFirstname(FirstNameField.getText());
    	this.customer.setLastname(LastNameField.getText());
    	this.customer.setPhoneNumber(PhoneField.getText());
    	this.customer.setStatusInSystem("CONFIRMED");
    	/// the following for checking if we have missing data that the branch manager didn't enter.
    	if(this.customer.getCreditCardCVVCode()==null||this.customer.getCreditCardDateOfExpiration()==null
    			||this.customer.getCreditCardNumber()==null||this.customer.getEmail()==null||
    			this.customer.getFirstname()==null||this.customer.getId()==0||this.customer.getLastname()==null
    			||this.customer.getPhoneNumber()==null) { /// if the user didn't click on an item.
    		System.out.println(this.customer.getPhoneNumber());
    		//System.out.println(this.customer.getCreditCardDateOfExpiration());
    		
    	   msg_missing_data.setVisible(true);
    	}
    	else /// we have the complete data 
    	{ 
        	ClientRunner.getClientRunner().SendReqToServer(new Message((OpType.CreateAccountB_M),customer)); /// send to server 
        	msg=(Message)ClientRunner.getClientRunner().obj; // from server
        	Boolean result = ((Boolean) msg.getMsg()).booleanValue();
            ClientRunner.getClientRunner().obj = null;

            if(result==false)
        	{
            	msg_exist.setVisible(true);

        	}else
        	{	
        		msg_success.setVisible(true);
        		CreateAnotherBtn.setVisible(true);/// give him the option to create a new account
        	}

    	}

    }


  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		msg_missing_data.setVisible(false);
		msg_success.setVisible(false);
		msg_exist.setVisible(false);
		//customer.setLoggedIn(0);
		//customer.setStatusInSystem(AccountStatus.CONFIRMED.toString());
		
	}



}
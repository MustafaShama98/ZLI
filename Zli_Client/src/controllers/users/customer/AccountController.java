package controllers.users.customer;

import java.net.URL;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import entities.Message;
import entities.enumE.OpType;
import entities.users.customer.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * This class to view the  Customer Account details
 * 
 *
 */
public class AccountController implements Initializable {

    @FXML
    private Button Backbtn;

    @FXML
    private Label FirstName;

    @FXML
    private Label LastName;

    @FXML
    private Label Status;

    @FXML
    private Label CardNumber;

    @FXML
    private Label ExpirationDate;

    @FXML
    private Label CardName;

    @FXML
    private Label ID;

    @FXML
    private Label Email;

    @FXML
    private Label CVV;
    @FXML
    private Label PhoneNumber;
    @FXML
    private Label Com_Label;

	 private ActionEvent event;
	 private Customer customer;

    @FXML
    void Click_Back(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();;

    }
	public void setEvent(ActionEvent event) {
		this.event=event;
		
	}
	/**
	 * in initialize function we show what we want to see after open the previous window
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.compensation,ClientRunner.getUser()));// send request to server
		Message msg = (Message) ClientRunner.getClientRunner().obj; // from server
		
		Double compensation= (Double)msg.getMsg();
		Com_Label.setText(String.valueOf(compensation));
	
		customer = (Customer)ClientRunner.getUser();
		FirstName.setText(customer.getFirstname());
		LastName.setText(customer.getLastname());
		Status.setText(customer.getStatusInSystem());
		CardNumber.setText(customer.getCreditCardNumber());
		ExpirationDate.setText(customer.getCreditCardDateOfExpiration());
		CardName.setText(customer.getFirstname()+" "+ customer.getLastname());
		ID.setText(String.valueOf(customer.getId()));
		Email.setText(customer.getEmail());
		CVV.setText(customer.getCreditCardCVVCode());
		PhoneNumber.setText(customer.getPhoneNumber());
	}
	/**
	 * 
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * set the customer
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}

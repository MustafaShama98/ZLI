package view.UserView.CustomerView;
import clientManager.ClientRunner;
import clientManager.OpenWindow;
import entities.Message;
import entities.enumE.OpType;
import entities.users.User;
import entities.users.customer.Customer;
import javafx.event.ActionEvent;
import view.UserView.UserUI;

public class CustomerUI extends UserUI{
	Customer customer;
	public CustomerUI(User user,ActionEvent event) {
		super(user,event);
		initialize();
		
		 newWin = new OpenWindow(event,"/view/UserView/CustomerView/CustomerPortalUI.fxml", "User Portal - "+ customer.getFirstname() +" "+ customer.getLastname());
		 newWin.hideWindow();
		
		
	}
	void initialize () {
		 customer = (Customer)user;
		ClientRunner.setUser(customer);
	}

}

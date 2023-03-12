package view.UserView.BranchEmployee;
/**
 * This class  resposeble to open new windo
 */
import clientManager.ClientRunner;
import clientManager.OpenWindow;
import entities.users.User;
import entities.users.branchemployee.BranchEmployee;
import entities.users.branchmanager.BranchManager;
import javafx.event.ActionEvent;
import view.UserView.UserUI;

public class BranchEmployeeUI extends UserUI {
	BranchEmployee branchEmployee;
	public BranchEmployeeUI(User user, ActionEvent event) {
		super(user, event);
		initialize();
		 newWin = new OpenWindow(event,"/view/UserView/BranchEmployee/BranchEmployeeUI.fxml", "User Portal - "+ branchEmployee.getFirstname()+" "+branchEmployee.getLastname());
		 
		newWin.hideWindow();
	
	}

	void initialize () {
		branchEmployee= (BranchEmployee)user;
		ClientRunner.setUser(branchEmployee);
	}
}

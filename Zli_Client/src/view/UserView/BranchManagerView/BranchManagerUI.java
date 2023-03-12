package view.UserView.BranchManagerView;

import clientManager.ClientRunner;
import clientManager.OpenWindow;
import entities.users.User;
import entities.users.branchmanager.*;
import javafx.event.ActionEvent;
import view.UserView.UserUI;

public class BranchManagerUI extends UserUI{
BranchManager branchManager;
		public BranchManagerUI (User user,ActionEvent event) {
			super(user,event);
			initialize();
			newWin = new OpenWindow(event,"/view/UserView/BranchManagerView/BranchManagerUI.fxml","User Portal - "+ branchManager.getFirstname()+" "+branchManager.getLastname());
			newWin.hideWindow();
		}
		void initialize () {
			branchManager= (BranchManager)user;
			ClientRunner.setUser(branchManager);
		}
}

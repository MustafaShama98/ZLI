package view.UserView.MarketingWorkerView;

import clientManager.ClientRunner;
import clientManager.OpenWindow;
import entities.users.User;
import entities.users.MarketingWorker.*;
import javafx.event.ActionEvent;
import view.UserView.UserUI;

public class MarketingWorkerUI extends UserUI{
	MarketingWorker MarketingWorker;
	public MarketingWorkerUI(User user,ActionEvent event) {
		super(user,event);
		initialize();
		 newWin = new OpenWindow(event,"/view/UserView/MarketingWorkerView/MarketingWorkerPortalUI.fxml", "User Portal - "+ MarketingWorker.getFirstname()+" "+MarketingWorker.getLastname());
		 newWin.hideWindow();
		
		
	}
	void initialize () {
		MarketingWorker = (MarketingWorker)user;
		ClientRunner.setUser(MarketingWorker);
	}

}

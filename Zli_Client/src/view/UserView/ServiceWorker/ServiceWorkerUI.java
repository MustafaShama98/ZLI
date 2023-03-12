package view.UserView.ServiceWorker;

import clientManager.OpenWindow;
import entities.users.User;
import javafx.event.ActionEvent;
import view.UserView.UserUI;

public class ServiceWorkerUI extends UserUI {

	public ServiceWorkerUI(User user, ActionEvent event) {
		super(user, event);
		 newWin = new OpenWindow(event,"/view/UserView/ServiceWorker/ServiceWorker.Complaints.fxml","User Portal - "+ user.getUserName());
			newWin.hideWindow();	
	}
	public OpenWindow winRef() {
		return newWin;
	}

}


package view.UserView.CeoView;

import clientManager.OpenWindow;
import entities.users.User;
import javafx.event.ActionEvent;
import view.UserView.UserUI;

public class CeoUI extends UserUI{
	public CeoUI(User user,ActionEvent event) {
		super(user,event);
	 newWin = new OpenWindow(event,"/view/UserView/CeoView/CeoUI.fxml","User Portal - "+ user.getUserName());
	newWin.hideWindow();
	}
}

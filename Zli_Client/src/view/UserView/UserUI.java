package view.UserView;
import clientManager.OpenWindow;
import entities.users.User;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
public abstract class UserUI {
 protected User user;
 protected OpenWindow newWin;
 protected ActionEvent event;
 public UserUI( User user, ActionEvent event) {
	 this.event= event;
	 this.user = user;
	}
 public User getUser() {
	 return user;
 }
 public OpenWindow getWin() {
	 return newWin;
 }
 public void LoginScreen() {
     Stage s = (Stage) (((Node)event.getSource()).getScene().getWindow());
     s.show();
 }
}

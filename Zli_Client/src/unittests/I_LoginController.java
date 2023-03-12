package unittests;

import entities.Message;
import entities.enumE.MessageType;
import entities.users.User;
import view.UserView.UserUI;

public interface I_LoginController {
	/*
	 * G19
	 * Interface class that assists us to test login methods without in need
	 * of javafx or fxml dependency.
	 */
	public boolean input_empty() ;

	public static final UserUI window = null;
	
	public Message sendLoginToServer(User user);
	
	boolean isAlreadyLogin();
	boolean isFrozenAcc();
	boolean isDeniedAcc();
	boolean incorrectDetails();
}

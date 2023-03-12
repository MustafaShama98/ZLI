package clientManager;
	

import clientManager.OpenWindow;
import controllers.LoginController;
import entities.enumE.MessageType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * this method after connection with server, called ClientConnectionController class  
 */
public class ClientUI extends Application {
	static OpenWindow start;
	@Override
	public void start(Stage primaryStage) {
		
		 start = new OpenWindow(primaryStage,"/view/login.fxml", "Zli Login");
	
		//start.hideWindow();
	}
	public static void OrdersTablePage(ActionEvent event) {
		OpenWindow newWindow = new OpenWindow(event, "../view/OrdersLog.fxml", "Orders list" );
		newWindow.hideWindow();
	}
	public static void UpdateOrderTablePage(ActionEvent event) {
		@SuppressWarnings("unused")
		OpenWindow newWindow = new OpenWindow(event, "../view/EditOrder.fxml", "Order Editor" );
		//newWindow.hideWindow();
	}
	public static OpenWindow getStartWin() {
		return start;
	}
	public static void main(String[] args) {
		launch(args);
	
	}
}

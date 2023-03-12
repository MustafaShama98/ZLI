 package clientManager;
import java.io.IOException;

import clientManager.ClientRunner;
import controllers.users.customer.CustomerPortalController;
import entities.Message;
import entities.enumE.OpType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class OpenWindow {
private Stage stage;
private ActionEvent event;
private  FXMLLoader fxmlLoader;
private String WindowTitle;
	public OpenWindow(ActionEvent event, String fxml_path, String WindowTitle) {
		  Parent root;
		  this.WindowTitle=WindowTitle;
		  this.event = event;
	        try {
	         fxmlLoader = new FXMLLoader(OpenWindow.class.getResource(fxml_path));
	           // root = FXMLLoader.load(getClass().getResource(fxml_path));
	           	
	            stage = new Stage();
				
	            stage.setTitle(WindowTitle);
	        
	            closeWindow();
	            stage.getIcons().add(new Image(OpenWindow.class.getResource("/images/icon.png" ).toExternalForm()));
	            stage.setScene(new Scene(fxmlLoader.load()));
	            stage.show();
	            
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public OpenWindow(String fxml_path, String WindowTitle) {
		 this.WindowTitle=WindowTitle;
		  Parent root;
	        try {
	         fxmlLoader = new FXMLLoader(OpenWindow.class.getResource(fxml_path));
	           // root = FXMLLoader.load(getClass().getResource(fxml_path));
	           
	            stage = new Stage();
	            stage.setTitle(WindowTitle);
	            if (WindowTitle.equals("Cart")) {
					stage.initStyle(StageStyle.UNDECORATED);
				}
	            closeWindow();
	            stage.getIcons().add(new Image(OpenWindow.class.getResource("/images/icon.png" ).toExternalForm()));
	            stage.setScene(new Scene(fxmlLoader.load()));
	            stage.show();
	            
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public OpenWindow(ActionEvent event, String fxml_path, String WindowTitle, Object controller) {
		 this.WindowTitle=WindowTitle;
		  Parent root;
		  this.event = event;
	        try {
	         fxmlLoader = new FXMLLoader(OpenWindow.class.getResource(fxml_path));
	           // root = FXMLLoader.load(getClass().getResource(fxml_path));
	      
	            stage = new Stage();
	            stage.setTitle(WindowTitle);
	            closeWindow();
	            stage.getIcons().add(new Image(OpenWindow.class.getResource("/images/icon.png" ).toExternalForm()));
	            stage.setScene(new Scene(fxmlLoader.load()));
	            stage.show();
	            
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public OpenWindow(Stage stage, String fxml_path, String WindowTitle) {
		 this.WindowTitle=WindowTitle;
		  Parent root;
		
	        try {
	        	 fxmlLoader = new FXMLLoader(OpenWindow.class.getResource(fxml_path));
	     
	            this.stage = stage;
	           stage.getIcons().add(new Image(OpenWindow.class.getResource("/images/icon.png" ).toExternalForm()));
	            stage.setTitle(WindowTitle);
	            closeWindow();
	            root = (Pane)fxmlLoader.load();//<--- jar error
	            stage.setScene(new Scene(root));
	            stage.show();
	            
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public Stage getStage() {
		return stage;
	}
	public void hideWindow() {
		// Hide this current window
	            ((Node)(event.getSource())).getScene().getWindow().hide();
	            
	}
	public void previousWindow() {
		// Hide this current window
	            ((Node)(event.getSource())).getScene().getWindow();
	            
	}

	public void closeWindow() {

		stage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				 // prevent window from closing
				e.consume();
				if (dontClose(WindowTitle).equals(ButtonType.OK)) {
					if (ClientRunner.getClientRunner().isConnected()) {
						ClientRunner.getClientRunner()
								.SendReqToServer(new Message(OpType.Logout, ClientRunner.getUser()));
						ClientRunner.getClientRunner().quit();
					}
					stage.close();
					System.out.println("Closing..");
					System.exit(0);
				}else {
					System.out.println("cancel");
				}
			} 
		});

	}
	private ButtonType dontClose(String winTitle) {
		String[] str =winTitle.split(" -");
		System.out.println(str[0]);
		if(str[0] != "User Portal") {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Are you sure you want to close?");
			alert.showAndWait();
			System.out.println( alert.getResult());
			return alert.getResult();
		}
		return null;
	}
 public Object getController() {
		return fxmlLoader.getController();
	 }
 public ActionEvent getEvent() {
	 return this.event;
 }
}

package controllers.users.surveyExpert;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import controllers.LoginController;
import entities.Message;
import entities.MyFile;
import entities.enumE.MessageType;
import entities.enumE.OpType;
import entities.users.customer.Customer;
import entities.users.serviceexpert.SurveyExpert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class SurveyExpertController implements Initializable {

    @FXML
    private Label welcome;

    @FXML
    private MenuButton menuBtn;

    @FXML
    private MenuItem logoutBtn;

    @FXML
    private Button Submit;
    @FXML
    private Label sendingFile;

    @FXML
    private Label successful;

    @FXML
    private Label SelectFileFirst;

    String path;
    SurveyExpert surveyExpert;
    @FXML
    void Submit(ActionEvent event) {
    	if(path == null){
    		SelectFileFirst.setVisible(true);
    	
    	}else {
    		SelectFileFirst.setVisible(false);
    	 MyFile msg= new MyFile(path);
   	  String LocalfilePath=path;
   		
   	  try{

   		      File newFile = new File (LocalfilePath);
   		      		      
   		      byte [] mybytearray  = new byte [(int)newFile.length()];
   		      FileInputStream fis = new FileInputStream(newFile);
   		      BufferedInputStream bis = new BufferedInputStream(fis);			  
   		      
   		      msg.initArray(mybytearray.length);
   		      msg.setSize(mybytearray.length);
   		      sendingFile.setVisible(true);
   		      bis.read(msg.getMybytearray(),0,mybytearray.length);
   		      fis.close();
   			  bis.close();
   			OpType[] req = new OpType[2];
    		req[0] = OpType.Survey;
    		req[1] = OpType.ExpertSurvey;
    		
   		      ClientRunner.getClientRunner().SendReqToServer(
   		    		  new Message(req,msg));
   		   Message serverMsg = (Message) ClientRunner.getClientRunner().obj;
   		sendingFile.setVisible(false);
   		      if(serverMsg.getMessageType().Success == MessageType.Success) {
   		    	  successful.setVisible(true);
   		    	  path=null;
   		      }
   		    }
   		catch (Exception e) {
   			System.out.println("Error send (Files)msg) to Server");
   		}
    	}
   	  
    }

    @FXML
    void logOut_click(ActionEvent event) {
    	LoginController.Logout();
    }

    @FXML
    void uploadFile(ActionEvent event) {
    	SelectFileFirst.setVisible(false);
    		FileChooser fc = new FileChooser();
    			fc.getExtensionFilters().add(new ExtensionFilter("PDF File", "*.pdf"));
    			File f = fc.showOpenDialog(null);
    			if(f!=null)
    			{
    				path = f.getAbsolutePath();
    			}
    			
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		surveyExpert = (SurveyExpert)ClientRunner.getUser();
		welcome.setText("Welcome "+surveyExpert.getFirstname()+ "!");
	}

}

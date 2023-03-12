package controllers.users.branchEmployee;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import clientManager.ClientController;
import clientManager.ClientRunner;
import controllers.LoginController;
import entities.Message;

import entities.enumE.MessageType;
import entities.enumE.OpType;
import entities.enumE.Catalog.BranchType;
import entities.survey.Survey;
import entities.users.User;
import entities.users.branchemployee.BranchEmployee;
import entities.users.customer.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * This class responsible about all the controllers of BranchEmployeeUI.fxml
 * @author Carol
 *
 */
public class BranchEmployeeController implements Initializable {

    @FXML
    private Label welcome;

    @FXML
    private MenuButton menuBtn;

    @FXML
    private MenuItem logoutBtn;

    @FXML
    private Pane rootPane;

    @FXML
    private Spinner<Integer> S1;

    @FXML
    private Label Q1;

    @FXML
    private Spinner<Integer> S2;

    @FXML
    private Text Q2;

    @FXML
    private Spinner<Integer> S3;

    @FXML
    private Text Q3;

    @FXML
    private Spinner<Integer> S4;

    @FXML
    private Text Q4;

    @FXML
    private Spinner<Integer> S5;

    @FXML
    private Text Q5;

    @FXML
    private Spinner<Integer> S6;

    @FXML
    private Text Q6;

    @FXML
    private Label empty_error;
    
    @FXML
    private Label SentSuccessfully;
    
    @FXML
    private Button restart_button;
    
    @FXML
    private TableView<Survey> SurveyTable;
    
    @FXML
    private TableColumn<Survey,String> Product_column;

    @FXML
    private TableColumn<Survey,String> Dates_Column;

    @FXML
    private TableColumn<Survey,String> SurveyDurations_culmn;
    
    @FXML
    private TableColumn<Survey,String> SurveyId_column;
    

    @FXML
    private Button OpenSurvey_btn;

    @FXML
    private Label SelectItem_error;
    
    @FXML
    private VBox Survey_pane;

    @FXML
    private List<Spinner<Integer>> spinnerList;
    private ArrayList<Integer> Answers;
    BranchEmployee branchEmployee;
    private Message msg;
    private ObservableList<Survey> surveys_table_data=FXCollections.observableArrayList();
    private Survey survey;
    
/**
 * This method check if there is selected line in the table view SurveyTable if not it reveal error message if
 * exist it take the SutrveyId and send it to server then it take the message from the server that have the 
 * Questions in string and set it in the labels in the survey pane 
 * @param event click on the Open survey button 
 */
    @FXML
    void OpenSurvey(ActionEvent event) {
    	survey=SurveyTable.getSelectionModel().getSelectedItem();
    	if(survey==null) 
    	{
    		SelectItem_error.setVisible(true);
    		
    	}else {
		ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.OpenSurvey,survey.getSurveyID()));
		Message msg = (Message)ClientRunner.getClientRunner().obj;
		if(msg.getMessageType().Success == MessageType.Success) {
			SelectItem_error.setVisible(false);
			Survey_pane.setVisible(true);
			//SentSuccessfully.setVisible(true);
			String[] questions=new String[6];
			questions=(String[])msg.getMsg();
			Q1.setText(questions[0]);
			Q2.setText(questions[1]);
			Q3.setText(questions[2]);
			Q4.setText(questions[3]);
			Q5.setText(questions[4]);
			Q6.setText(questions[5]);

			
		}
    	}
    }
    /**
     * This method  check if on of the spinner 0 it return error message if not it get all the spinners value and 
     * send them to the server in ArrayList
     * @param event click on the submit button
     */
    @FXML
    void Submit(ActionEvent event) {
    	
    	if(checkSpinnerEmpty()) {
    		empty_error.setVisible(true);
    	}
    	else 
    	{
    		empty_error.setVisible(false);
    		Answers = new ArrayList<>(6);
    		for (int i = 0; i < 6; i++) {
				Answers.add((Integer) spinnerList.get(i).getValue());
				
			}
    		OpType[] req = new OpType[2];
    		req[0] = OpType.Survey;
    		req[1] = OpType.WorkerSurvey;
    		User user = ClientRunner.getUser();
    		BranchEmployee BE =(BranchEmployee) user;
    		//BE.setBranch(BranchType.North);
    		BE.setAnswers(Answers);
    		BE.setSurveyId(survey.getSurveyID());
    		ClientRunner.getClientRunner().SendReqToServer(new Message(req,BE));
    		Message msg = (Message)ClientRunner.getClientRunner().obj;
    		if(msg.getMessageType().Success == MessageType.Success) {
    			SentSuccessfully.setVisible(true);
    		}
    	}
    	
  
    }
    
/**
 * This method refresh the survey pane  
 * @param event click on the restart button
 */
    @FXML
    void restart(ActionEvent event) {
    	empty_error.setVisible(false);
    	SentSuccessfully.setVisible(false);
    	spinnerInitialize();
    }
    
    /**
     * This method to log out when we click on this button
     * @param event click on log out button
     */
    @FXML
    void logOut_click(ActionEvent event) {
    	LoginController.Logout();
    }
    
/**
 * this method is to initialise the welcom label  
 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		spinnerInitialize();
		branchEmployee = (BranchEmployee)ClientRunner.getUser();
		welcome.setText("Welcome " +branchEmployee.getFirstname()+" "+branchEmployee.getLastname()+ "!");
		getTable();
		
	}

	/**
	 * this method send message to the server to get Array List and insert it to table view SurveyTable 
	 */
	void getTable() {
		ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.ViewSurvey, branchEmployee));
		
		msg = (Message) ClientRunner.getClientRunner().obj; // from server
		
		
		surveys_table_data = (ObservableList<Survey>) FXCollections.observableArrayList(convertObjectToList(msg.getMsg()));
		
		SurveyTable.setItems(surveys_table_data);
		
		
		ClientRunner.getClientRunner().obj = null;
		LoadSurveysTable();
	}
	
	/**
	 * 
	 * This method Load Data to the table view SurveyTable
	 */
	 void LoadSurveysTable() {
		 
		 Product_column.setCellValueFactory(new PropertyValueFactory<>("Product"));		 
		 SurveyId_column.setCellValueFactory(new PropertyValueFactory<>("SurveyID"));
		 Dates_Column.setCellValueFactory(new PropertyValueFactory<>("SurveyDuration"));
		 SurveyDurations_culmn.setCellValueFactory(new PropertyValueFactory<>("BoughtDuration"));
		 
		 
		 SurveyTable.setVisible(true);
	 }
	    
	/**
	 * This method get object and convert it to List 
	 * @param obj object that the function get
	 * @return return the List
	 */
	public static List<?> convertObjectToList(Object obj) {
		List<?> list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			list = Arrays.asList((Object[]) obj);
		} else if (obj instanceof Collection) {
			list = new ArrayList<>((Collection<?>) obj);
		}
		return list;
	}
	
	/**
	 * This method initialise all the spinners to value 0
	 */
	private void spinnerInitialize() {
		spinnerList = new ArrayList<>(6);
		SpinnerValueFactory<Integer> v1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
		SpinnerValueFactory<Integer> v2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
		SpinnerValueFactory<Integer> v3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
		SpinnerValueFactory<Integer> v4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
		SpinnerValueFactory<Integer> v5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
		SpinnerValueFactory<Integer> v6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
		S1.setValueFactory(v1);
		S2.setValueFactory(v2);
		S3.setValueFactory(v3);
		S4.setValueFactory(v4);
		S5.setValueFactory(v5);
		S6.setValueFactory(v6);
		spinnerList.add(S1);
		spinnerList.add(S2);
		spinnerList.add(S3);
		spinnerList.add(S4);
		spinnerList.add(S5);
		spinnerList.add(S6);
	}
	
	/**
	 * This method check if one of the spinners is empty 
	 * @return return true empty spinner exist else false
	 */
	private boolean checkSpinnerEmpty() {
		int i;
		for(i=0;i<6;i++) {
		if(spinnerList.get(i).getValue() == 0) {
			return true;
			}
		}
		return false;
			
	}
	

}



package controllers.users.ServiceWorker;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import controllers.LoginController;
import entities.Message;
import entities.enumE.MessageType;
import entities.enumE.OpType;
import entities.users.Complaints;
import entities.users.Survey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.UserView.UserUI;

public class ServiceWorkerComplaintsController implements Initializable,Runnable {

    @FXML
    private Pane ComplaintsPane;

    @FXML
    private MenuButton menuBtn;

    @FXML
    private MenuItem logoutBtn;

    @FXML
    private Text newMsg;

    @FXML
    private TableView<Complaints> CmplaintsTable;

    @FXML
    private TableColumn<Complaints,Integer> CustomerId_Column;

    @FXML
    private TableColumn<Complaints,Integer> Complaints_Column;

    @FXML
    private Label success_label;

    @FXML
    private TextField CustomerId_field;

    @FXML
    private TextField Complaint_field;

    @FXML
    private Button Add_btn;

    @FXML
    private Button Refresh_btn;

    @FXML
    private ImageView refresh_btn;

    @FXML
    private Label empty_error1;

    @FXML
    private TextField MoneyField;

    @FXML
    private Button send;

    @FXML
    private Label empty_error;

    @FXML
    private Label Successful_label;

    @FXML
    private Label field_empty;

    @FXML
    private Label empty_error2;

    @FXML
    private Label error_userNotFound;

    @FXML
    private MenuButton menuBtn1;

    @FXML
    private MenuItem logoutBtn1;

    @FXML
    private Button restart_button;

    @FXML
    private Label SentSuccessfully;

    @FXML
    private Button Submit;
    
    @FXML
    private Pane CreateSurveyPane;

    @FXML
    private Button clean_button;

    @FXML
    private VBox Survey_pane;

    @FXML
    private Label SentSuccessfully1;

    @FXML
    private TextField Question1;

    @FXML
    private TextField Question2;

    @FXML
    private TextField Question3;

    @FXML
    private TextField Question4;

    @FXML
    private TextField Question5;

    @FXML
    private TextField Question6;

    @FXML
    private Button Create;

    @FXML
    private CheckBox North_Check;

    @FXML
    private CheckBox South_Check;

    @FXML
    private CheckBox East_Check;

    @FXML
    private CheckBox West_Check;

    @FXML
    private TextField ProductID_field;

    @FXML
    private DatePicker DurationStart_picker;

    @FXML
    private DatePicker DurationEnd_picker;

    @FXML
    private DatePicker StartDate_picker;

    @FXML
    private DatePicker EndDate_piker;

    @FXML
    private Label Empty_question_field;

    @FXML
    private Label success_label1;

    @FXML
    private Label Wrong_dates_select;

    @FXML
    private Label Empty_dates;
public Thread T1;

    
  private ObservableList<Complaints> complaints_table_data=FXCollections.observableArrayList();
  private Message msg;
  private boolean  isOneClick_complaints= true;
  //protected OpenWindow newWin;
  
  private String[] Questions=new String[6];
  private Survey survey=new Survey(Questions, null, null, null, null, null, null);
  private ArrayList<String> Branches;

    @FXML
    void Submit(ActionEvent event) {

    }



    @FXML
    void restart(ActionEvent event) {

    }

    
  @FXML
  void Send(ActionEvent event) {
  	if(input_empty()) {
  		setAllUnvisible();
  		field_empty.setVisible(true);
  	}
  	else 
  	{
  	Complaints com=CmplaintsTable.getSelectionModel().getSelectedItem();
  	//System.out.println(com.getCustomerID());
  	if(com==null) {
  		setAllUnvisible();
  		empty_error.setVisible(true);
  	}
  	else 
  	{
		ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.Compensation,com.getCustomerID()));
		Message msg = (Message)ClientRunner.getClientRunner().obj;
		if(msg.getMessageType() == MessageType.Success) {
	
		    setAllUnvisible();
		    refreshTable () ;
			Successful_label.setVisible(true);
			refreshTable () ;

			}}}
  }
  
  @FXML
  void logOut_click(ActionEvent event) {
  	LoginController.Logout();
  }
  
  @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
  	isOneClick_complaints=handleTableData(isOneClick_complaints, OpType.Complaints, complaints_table_data, CmplaintsTable);
		
	}
  
//  public void getSelectedItem() {
//      // check the table's selected item and get selected item
//      if (.getSelectionModel().getSelectedItem() != null) {
//          Person selectedPerson = table.getSelectionModel().getSelectedItem();
//          nameTextField.setText(selectedPerson.getName());
//          addressTextField.setText(selectedPerson.getAddress());
//      }
//      
  
  private void refreshTable () {
  	ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.Complaints));
  	msg = (Message) ClientRunner.getClientRunner().obj;
  	ObservableList<Complaints> data = (ObservableList<Complaints>) FXCollections.observableArrayList(convertObjectToList(msg.getMsg()));
  	CmplaintsTable.setItems(data);
  }
  
  private boolean handleTableData(boolean flag, OpType OptypeTable ,ObservableList<Complaints> table_data, TableView<Complaints> tableType ) {
		
		if(flag) { // has only 1 time access
			ClientRunner.getClientRunner().SendReqToServer(new Message(OptypeTable));// send request to server
			
			//handling the message from  server
			 messageFromServerHandler(table_data,tableType );
			 flag=false;
	    	}
			switch (OptypeTable) 
			{
			case Complaints: {
				LoadComplaintsTable();
				break;
			}
			default:
				
				throw new IllegalArgumentException("Unexpected value: " + OptypeTable);
		
			}
			return flag;
	    	
	}
  
	@SuppressWarnings("unchecked")
	private void messageFromServerHandler(ObservableList<Complaints> table_data, TableView<Complaints> tableType) {
		msg = (Message) ClientRunner.getClientRunner().obj; // from server
		
		
		table_data = (ObservableList<Complaints>) FXCollections.observableArrayList(convertObjectToList(msg.getMsg()));
		
		tableType.setItems(table_data);
		
		
		ClientRunner.getClientRunner().obj = null;
	
		
	}
	
	 void LoadComplaintsTable() {
		 
	    	CustomerId_Column.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
	    	Complaints_Column.setCellValueFactory(new PropertyValueFactory<>("Complaint"));
		    CmplaintsTable.setVisible(true);

	    }
	
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
	 * This method Check if one of the compensations fields is empty
	 * @return true if there is empty field else return false
	 */
	private boolean input_empty() {
		if( MoneyField.getText().trim().isEmpty())
			return true;
		else return false;
	}
	/**
	 * This method Check if one of the Create Complaint fields is empty
	 * @return true if there is empty field else return false
	 */
	private boolean input_empty2() {
		if(CustomerId_field.getText().trim().isEmpty() || Complaint_field.getText().trim().isEmpty() )
			return true;
			else return false;
	}
	
	  /**
   * This if one of the Create Complaint empty set error message visible else send the complaint to the server
   *   and set success message visible and clear the Create Complaint fields
   * @param event click on Add button
   */
  @FXML
  void Add_btn(ActionEvent event) {
		if (input_empty2()) 
		{
			setAllUnvisible();
			empty_error2.setVisible(true);
			

		}
		else {
			empty_error2.setVisible(false);
			//success_label.setVisible(true);
			int CID = Integer.valueOf(CustomerId_field.getText());
			System.out.println(CID);
			Complaints complaint=new Complaints(CID,Complaint_field.getText());
			//clientRunner.ClientConnect()
			//clientRunner.SendReqToServer(new Message(OpType.Login, user));
			
			ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.CheckCustumerId,complaint));
  		Message msg1 = (Message)ClientRunner.getClientRunner().obj;
  		if(msg1.getMessageType() == MessageType.Success) {
  			T1 = new Thread();
  		
  		ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.CreateComplaint,complaint));
  		Message msg = (Message)ClientRunner.getClientRunner().obj;
  		if(msg.getMessageType().Success == MessageType.Success) {
  			
  			error_userNotFound.setVisible(false);
  			success_label.setVisible(true);
  			refreshTable () ;
  			CustomerId_field.clear();
  			Complaint_field.clear();
  			//CmplaintsTable.refresh();
  			//ServiceWorkerComplaintsController.initialize(arg0,);
  		}else {
			

  		
  		}}
  		else {
  			error_userNotFound.setVisible(true);
  		}
  		
		}
	

  }
/**
* This method set all the error labels not invisible
*/
	public void setAllUnvisible() {
		empty_error2.setVisible(false);
		success_label.setVisible(false);
		error_userNotFound.setVisible(false);
		empty_error.setVisible(false);
		field_empty.setVisible(false);
		Successful_label.setVisible(false);
	}
	
	
    @FXML
    void clean(ActionEvent event) {
    	ClearTextFields();
    	success_label.setVisible(false);
    	Empty_question_field.setVisible(false);
    	Wrong_dates_select.setVisible(false);
    	Empty_dates.setVisible(false);
    }
    
    @FXML
    void Create(ActionEvent event) {    	
    	if(input_empty1()) 
    	{
    		Empty_question_field.setVisible(true);
    	}else 
    	{
    	if(StartDate_picker.getValue()==null || EndDate_piker.getValue()==null) 
    	{
    	Empty_question_field.setVisible(false);	
    	Empty_dates.setVisible(true);	
    	}else {
    		
    	if(!StartDate_picker.getValue().isBefore(EndDate_piker.getValue()) || StartDate_picker.getValue().isBefore(LocalDate.now()) 
    			|| EndDate_piker.getValue().isBefore(LocalDate.now())) {
    		Wrong_dates_select.setVisible(true);
        	Empty_question_field.setVisible(false);	
        	Empty_dates.setVisible(false);
    	}else {


    		Branches=new ArrayList<>(SelectedCheckBoxsCount());
    		Empty_question_field.setVisible(false);
    		Wrong_dates_select.setVisible(false);
    		Empty_dates.setVisible(false);

				Questions[0]=Question1.getText();
				Questions[1]=Question2.getText();	
				Questions[2]=Question3.getText();
				Questions[3]=Question4.getText();
				Questions[4]=Question5.getText();
				Questions[5]=Question6.getText();
				AddSelected_Branches();
				putDataInSurvey();			

				ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.CreateSurvey,survey));
				Message msg = (Message)ClientRunner.getClientRunner().obj;
				if(msg.getMessageType().Success == MessageType.Success) {
					ClearTextFields();
					success_label1.setVisible(true);
					}
				
				
    	}}}
    	
    	
    	
    	

    }
    /**
     * This method count the selected check boxes in the create survey page
     * @return number of check boxes
     */
    private int SelectedCheckBoxsCount() {
    	int count=0;
		if(North_Check.isSelected()) {
			count++;
			
		}
		if(South_Check.isSelected()) {
			count++;
			
		}
		if(East_Check.isSelected()) {
			count++;
			
		}
		if(West_Check.isSelected()) {
			count++;
			
		}
		return count;
    	
		
    }
    /**
     * this method clear all the text field,date picker and check boxes in creat Survey Window
     */
    private void ClearTextFields() {
    	Question1.clear();
    	Question2.clear();
    	Question3.clear();
    	Question4.clear();
    	Question5.clear();
    	Question6.clear();
    	ProductID_field.clear();
    	EndDate_piker.getEditor().clear();
    	StartDate_picker.getEditor().clear();
    	DurationStart_picker.getEditor().clear();
    	DurationEnd_picker.getEditor().clear();
    	North_Check.setSelected(false);
    	East_Check.setSelected(false);
    	South_Check.setSelected(false);
    	West_Check.setSelected(false);
    	
    }
	
    /**
     * This method put all the survey data we have get from the client (text fields) in the survey 
     * @return survey
     */
    private Survey putDataInSurvey() {
		survey.setQuestions(Questions);
		survey.setStartDate(StartDate_picker.getValue());
		survey.setEndDate(EndDate_piker.getValue());
		if(DurationEnd_picker.getValue()!=null) 
		{
		survey.setStartD(DurationStart_picker.getValue());
		}
		if(DurationEnd_picker.getValue()!=null) 
		{
		survey.setEndD(DurationEnd_picker.getValue());
		}
		if(SelectedCheckBoxsCount()!=0) {
		survey.setBranches(Branches);
		}
		if(ProductID_field.getText()!=null) {
		survey.setProduct(ProductID_field.getText());
		}
		return survey; 	
    }
    
    /**
     * This method add all the selected branches names to the Array List Branches 
     */
    private void AddSelected_Branches() {
		if(North_Check.isSelected()) {
			Branches.add("North");
			
		}
		if(South_Check.isSelected()) {
			Branches.add("South");
			
		}
		if(East_Check.isSelected()) {
			Branches.add("East");
			
		}
		if(West_Check.isSelected()) {
			Branches.add("West");
			
		}
    }
    
	/**
	 * This method Check if one of the questions fields is empty
	 * @return true if there is empty field else return false
	 */
	private boolean input_empty1() {
		if(Question1.getText().trim().isEmpty() || Question2.getText().trim().isEmpty()|| Question3.getText().trim().isEmpty()|| Question4.getText().trim().isEmpty()|| Question5.getText().trim().isEmpty()|| Question6.getText().trim().isEmpty() )
			return true;
			else return false;
	}


/**
 * thread for reminder timer
 */
	@Override
	public void run() {
		// TODO Auto-generated method stub	
		Message message = (Message) ClientRunner.getClientRunner().obj;

		System.out.println("24 hours has passed, please respond to complain of");
		
		newMsg.setVisible(false);
		newMsg.setText("24 hours has passed, please respond to complain of user ID: " + (int)message.getMsg());
	
		try {
		
			Thread.sleep(6000);
			newMsg.setVisible(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
}
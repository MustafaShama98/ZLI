package controllers.users.branchManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import entities.AccountCustomer;
import entities.Message;
import entities.Orders;
import entities.enumE.AccountStatus;
import entities.enumE.OpType;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;

public class mangeAccountsController implements Initializable{


	    @FXML
	    private TableView<AccountCustomer> AccountsTable;

	    @FXML
	    private TableColumn<AccountCustomer, String> FirstName;

	    @FXML
	    private TableColumn<AccountCustomer, String> LastName;

	    @FXML
	    private TableColumn<AccountCustomer, AccountStatus> Status;

	    @FXML
	    private Button freezeBtn;

	    @FXML
	    private Button UnfreezeBtn;

	    @FXML
	    private Button ConfirmBtn;
	    @FXML
	    private Label msg_select_account;
	    private ObservableList<AccountCustomer>account_table_data=FXCollections.observableArrayList();
	    private Message msg;
	

	    /**
	     * Functionality: unfreezing account StatusInSystem. which gives the customer the ability to enter the system. 
	     * @param event
	     */
	    @FXML
	    void ClickUnfreeze(ActionEvent event) {

	    	String[] str = new String[2];
	    	ObservableList<AccountCustomer> AccountList;
	    	AccountList=AccountsTable.getSelectionModel().getSelectedItems(); /// getting the line that the user clicked on .
	 
	    	if(AccountList.isEmpty()) { /// if the user didn't click on an item.
	    	   msg_select_account.setVisible(true);
	    	}
	    	else
	    	{   /// the user has selected an order.
	    		str[0]=AccountList.get(0).getFirstName();
	    		str[1]=AccountList.get(0).getLastName();

	    		Object myObject=str;
	        	ClientRunner.getClientRunner().SendReqToServer(new Message((OpType.UnFreezeAccountB_M),myObject)); /// send to server 
	        	msg=(Message)ClientRunner.getClientRunner().obj; // from server
	        	Boolean result = ((Boolean) msg.getMsg()).booleanValue();
	            ClientRunner.getClientRunner().obj = null;

	            if(result==false)
	        	{
	         	   msg_select_account.setVisible(true);

	        	}else
	        	{	
	         	   msg_select_account.setVisible(false);

	        		ShowTable();
	        	}

	    	}
	    }

	    /**
	     * 
	     * Functionality: confirming account StatusInSystem. which gives the customer the ability to enter the system and do all the operations.
	     * @param event
	     */
	    @FXML
	    void clickConfirm(ActionEvent event) {
	    	String[] str = new String[2];
	    	ObservableList<AccountCustomer> AccountList;
	    	AccountList=AccountsTable.getSelectionModel().getSelectedItems(); /// getting the line that the user clicked on .
	 
	    	if(AccountList.isEmpty()) { /// if the user didn't click on an item.
	    	   msg_select_account.setVisible(true);
	    	}
	    	else
	    	{   /// the user has selected an order.
	    		str[0]=AccountList.get(0).getFirstName();
	    		str[1]=AccountList.get(0).getLastName();

	    		Object myObject=str;
	        	ClientRunner.getClientRunner().SendReqToServer(new Message((OpType.ConfirmAccountB_M),myObject)); /// send to server 
	        	msg=(Message)ClientRunner.getClientRunner().obj; // from server
	        	Boolean result = ((Boolean) msg.getMsg()).booleanValue(); // get the result from the server 
	            ClientRunner.getClientRunner().obj = null;

	            if(result==false)
	        	{
	         	   msg_select_account.setVisible(true);

	        	}else
	        	{	
	         	   msg_select_account.setVisible(false);

	        		ShowTable();
	        	}

	    	}
	    }

	    /**
	     * Functionality: freezing account StatusInSystem. after this operation the customer cannot enter the systems
	     * @param event
	     */
	    @FXML
	    void clickFreeze(ActionEvent event) {

	    	String[] str = new String[2];
	    	ObservableList<AccountCustomer> AccountList;
	    	AccountList=AccountsTable.getSelectionModel().getSelectedItems(); /// getting the line that the user clicked on .
	 
	    	if(AccountList.isEmpty()) { /// if the user didn't click on an item.
	    	   msg_select_account.setVisible(true);
	    	}
	    	else
	    	{   /// the user has selected an order.
	    		str[0]=AccountList.get(0).getFirstName();
	    		str[1]=AccountList.get(0).getLastName();

	    		Object myObject=str;
	        	ClientRunner.getClientRunner().SendReqToServer(new Message((OpType.FreezeAccountB_M),myObject)); /// send to server 
	        	msg=(Message)ClientRunner.getClientRunner().obj; // from server
	        	Boolean result = ((Boolean) msg.getMsg()).booleanValue();///result from servers
	            ClientRunner.getClientRunner().obj = null;

	            if(result==false)
	        	{
	         	   msg_select_account.setVisible(true);

	        	}else
	        	{	
	         	   msg_select_account.setVisible(false);

	        		ShowTable();
	        	}

	    	}
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			ShowTable();
			
		}

		/**
		 ** Functionality: showing the table in the interface. after bringing the data from the server.
		 */
		/**
		 * 
		 */
		@SuppressWarnings("unchecked") void ShowTable() {
			// TODO Auto-generated method stub
			ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.MangeAccountsB_M,ClientRunner.getUser())); //send to server
			msg = (Message) ClientRunner.getClientRunner().obj; // result from server
			if(msg.getMsg()!=null)
			{
				account_table_data = (ObservableList<AccountCustomer>) FXCollections.observableArrayList(convertObjectToList(msg.getMsg()));
				AccountsTable.setItems(account_table_data);
			load();
		    ClientRunner.getClientRunner().obj = null;
			}
		}
		
		/**
		 * Functionality: putting the data inside the table
		 */
		private void load() {
			// TODO Auto-generated method stub
			FirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
			LastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
			Status.setCellValueFactory(new PropertyValueFactory<>("StatusInSystem"));
				
		}

		/**
		 * Functionality: converting from object to list.
		 * @param obj
		 * @return list
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
	}
package controllers.users.branchManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import controllers.LoginController;
import controllers.users.customer.CategoryTabController;
import controllers.users.customer.IndividualTabController;
import entities.users.User;
import entities.users.customer.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class branchManagerController implements Initializable {

	@FXML
	private Label UserLabel;

	@FXML
	private Button orders_btn;

	@FXML
	private Button reportsBtn;

	@FXML
	private MenuButton menuBtn;

	@FXML
	private MenuItem logoutBtn;
	@FXML
	private Button employeesAccounts;
	@FXML
	private Button mangeAccountsBtn;
	@FXML
	private Button createAccount;

	@FXML
	private Pane rootPane;
	@FXML
	private AnchorPane OrdersTab;
	@FXML
	private Pane ReportsTab;
	@FXML
	private AnchorPane reportLogTab;
	@FXML
	private AnchorPane mangeAccountTab;
	@FXML
	private AnchorPane mangeEmployeeTab;
	
	@FXML
	private AnchorPane NewAccountTab;
	
	
	private String path = "/view/UserView/BranchManagerView/";
	FXMLLoader ReportTabLoader; // for loading the report interface .
	FXMLLoader OrderTabLoader; // for loading the order interface.
	FXMLLoader reportLogTabLoader;
	FXMLLoader mangeAccountLoader;
	FXMLLoader mangeEmployeeAccountLoader;
	FXMLLoader createAccountLoader;
private int flag=0;
private int flag1=0;
private int flag2=0;
private int flag3=0;
private Customer customer=new Customer(new User(0,0));
	@FXML
	void logOut_click(ActionEvent event) {
		LoginController.Logout();
	}

	@FXML
	void selectOrders(ActionEvent event) {
		ordersController ordersController = OrderTabLoader.getController();	
		ordersController.ShowTable(); // always first page to show
		rootPane.getChildren().setAll(OrdersTab);
		OrdersTab.setManaged(true);
		OrdersTab.setVisible(true);
	//	ReportsTab.setVisible(false);
		//ReportsTab.setManaged(false);
	}

	@FXML
	void ManageEmployeesAccounts(ActionEvent event) {
		  if(flag3==0)/// first time to click on view reports
		  {
			  flag3=1;
			  
				try {
					mangeEmployeeAccountLoader = new FXMLLoader(getClass().getResource(path + "ManageEmployeesAccounts.fxml"));
					mangeEmployeeTab = mangeEmployeeAccountLoader.load();
					 mangeEmployeesAccountsController  employeeController =  mangeEmployeeAccountLoader.getController();	
					 employeeController.ShowTable(); // always first page to show
					rootPane.getChildren().setAll(mangeEmployeeTab);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  
		  }else {
		  
			  mangeEmployeesAccountsController  employeeController =  mangeEmployeeAccountLoader.getController();	
			  employeeController.ShowTable(); // always first page to show
			rootPane.getChildren().setAll(mangeEmployeeTab);
			mangeEmployeeTab.setManaged(true);
	     	mangeEmployeeTab.setVisible(true);
			OrdersTab.setVisible(false);
			OrdersTab.setManaged(false);
		  }
	}
	
	
	
	
	  @FXML void select_reports(ActionEvent event) { /// if he selected to view the reports
		  if(flag==0)/// first time to click on view reports
		  {
			  flag=1;
			  
				try {
					ReportTabLoader = new FXMLLoader(getClass().getResource(path + "ViewReportsManger.fxml"));
					ReportsTab = ReportTabLoader.load();
					 reportsController  reportsController =  ReportTabLoader.getController();	
			    	 reportsController.LoadComboBoxesValues(); // always first page to show
					rootPane.getChildren().setAll(ReportsTab);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  
		  }else {
		  
		    reportsController  reportsController =  ReportTabLoader.getController();	
	    	 reportsController.LoadComboBoxesValues(); // always first page to show
			rootPane.getChildren().setAll(ReportsTab);
			ReportsTab.setManaged(true);
			ReportsTab.setVisible(true);
			OrdersTab.setVisible(false);
			OrdersTab.setManaged(false);
		  }
		  

		}
		
		@FXML
		void MangeAccounts(ActionEvent event) { /// when he wants to view the accounts of the customers
			

			  if(flag1==0)/// first time to click on view reports
			  {
				  flag1=1;
				  
					try {
						mangeAccountLoader = new FXMLLoader(getClass().getResource(path + "MangeAccounts.fxml"));
						mangeAccountTab = mangeAccountLoader.load();
						 mangeAccountsController  MangeAccountsController =  mangeAccountLoader.getController();	
						 MangeAccountsController.ShowTable(); // always first page to show
						rootPane.getChildren().setAll(mangeAccountTab);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  
			  }else {
			  
				  mangeAccountsController  MangeAccountsController =  mangeAccountLoader.getController();	
					 MangeAccountsController.ShowTable();
					 rootPane.getChildren().setAll(mangeAccountTab);
					 mangeAccountTab.setManaged(true);
					 mangeAccountTab.setVisible(true);
				ReportsTab.setManaged(false);
				ReportsTab.setVisible(false);

				OrdersTab.setVisible(false);
				OrdersTab.setManaged(false);
			  }
			  
			
			}
			
		
		@FXML
		void CreateAccount(ActionEvent event) { 

			  if(flag2==0)/// first time to click on view reports
			  {
				  flag2=1;
				  
					try {
						createAccountLoader = new FXMLLoader(getClass().getResource(path + "CreateAccount.fxml"));
						NewAccountTab = createAccountLoader.load();
						 NewAccountController  newAccountController =  createAccountLoader.getController();	
						rootPane.getChildren().setAll(NewAccountTab);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  
			  }else {
			  
				  NewAccountController  newAccountController =  createAccountLoader.getController();	
					 rootPane.getChildren().setAll(NewAccountTab);
					 NewAccountTab.setManaged(true);
					 NewAccountTab.setVisible(true);
				ReportsTab.setManaged(false);
				ReportsTab.setVisible(false);
				mangeAccountTab.setManaged(false);
				mangeAccountTab.setVisible(false);

				
				OrdersTab.setVisible(false);
				OrdersTab.setManaged(false);
			  }
			  
		}

	/// this method for initializing the first screen that will appear to the branch
	/// manger and it will be :
	/// ViewOrders.fxml.
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		UserLabel.setText("Welcome " + ClientRunner.getUser().getUserName() + "!");

		try {
			OrderTabLoader = new FXMLLoader(getClass().getResource(path + "ViewOrders.fxml"));
			OrdersTab = OrderTabLoader.load();
			rootPane.getChildren().setAll(OrdersTab);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
package controllers.users.CeoController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import controllers.LoginController;
import controllers.users.branchManager.ordersController;
import controllers.users.branchManager.reportsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class CeoUIController implements Initializable {

    @FXML
    private Label UserLabel;

    @FXML
    private Button reportsBtn;
    @FXML
    private Button QuartleyReportsBtn;
    @FXML
    private Button twoQuartleyReports;
    @FXML
    private MenuButton menuBtn;

    @FXML
    private MenuItem logoutBtn;

    @FXML
    private Pane rootPane;
    @FXML
	private Pane MonthlyReportsTab;
    @FXML
	private Pane quartleyReportsTab;
    @FXML
  	private Pane SingleQuarterReort;
 
    private int flag=0;
    private int flag1=0;
	private String path = "/view/UserView/CeoView/";

    FXMLLoader MonthlyreportTabLoader;
    FXMLLoader QuartleyreportTabLoader;
    FXMLLoader singleQuarterTabLoader;
    
    
    /**
     * 	
	 * Functionality: logging out to the login screen
     * @param event
     */
    @FXML
    void logOut_click(ActionEvent event) {
    	LoginController.Logout();
    }
    
    /**
     * Functionality: clicking on view reports, and go to the reports screen
     * @param event
     */
    @FXML
    void select_reports(ActionEvent event) {
    	
    	reportsCeoController CeoReportsController = MonthlyreportTabLoader.getController();	
    	CeoReportsController.LoadComboBoxesValues(); // always first page to show
		rootPane.getChildren().setAll(MonthlyReportsTab);
		MonthlyReportsTab.setManaged(true);
		MonthlyReportsTab.setVisible(true);
    }
    
    /**
     * Functionality: clicking on view quarter reports, and go to the reports screen
     * @param event
     */
    @FXML
    void select_Quartley_reports(ActionEvent event) {
    	if(flag1==0)/// first time to click on view reports
		  {
			  flag1=1;
			  
				try {
					singleQuarterTabLoader = new FXMLLoader(getClass().getResource(path + "singleQuarterReport.fxml"));//load the interface
					SingleQuarterReort = singleQuarterTabLoader.load();
					 SingleQuartleyReportsController  singleQuartleyreportsController =  singleQuarterTabLoader.getController();	
					 singleQuartleyreportsController.LoadComboBoxesValues(); // always first page to show
					rootPane.getChildren().setAll(SingleQuarterReort);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  
		  }else {
		  
			  SingleQuartleyReportsController  singleQuartleyreportsController =  singleQuarterTabLoader.getController();	
			  singleQuartleyreportsController.LoadComboBoxesValues(); // always first page to show
			rootPane.getChildren().setAll(SingleQuarterReort);
			SingleQuarterReort.setManaged(true);
			SingleQuarterReort.setVisible(true);
			MonthlyReportsTab.setVisible(false);
			MonthlyReportsTab.setManaged(false);
		  }
    }
    
    /**
     * Functionality: clicking on view multiple  quarter reports, and go to the reports screen
     * @param event
     */
    @FXML
    void select_two_Quartley_reports(ActionEvent event) {
    	
    	 if(flag==0)/// first time to click on view reports
		  {
			  flag=1;
			  
				try {
					QuartleyreportTabLoader = new FXMLLoader(getClass().getResource(path + "ViewQuarterReportsCeo.fxml"));//load the interface
					quartleyReportsTab = QuartleyreportTabLoader.load();
					 quartleyReportsController  QuartleyreportsController =  QuartleyreportTabLoader.getController();	
					 QuartleyreportsController.LoadComboBoxesValues(); // always first page to show
					rootPane.getChildren().setAll(quartleyReportsTab);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  
		  }else {
		  
			  quartleyReportsController  QuartleyreportsController =  QuartleyreportTabLoader.getController();	
			  QuartleyreportsController.LoadComboBoxesValues(); // always first page to show
			rootPane.getChildren().setAll(quartleyReportsTab);
			quartleyReportsTab.setManaged(true);
			quartleyReportsTab.setVisible(true);
			MonthlyReportsTab.setVisible(false);
			MonthlyReportsTab.setManaged(false);
		  }
		  
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		UserLabel.setText("Welcome " + ClientRunner.getUser().getUserName() + "!");
		try {
			MonthlyreportTabLoader = new FXMLLoader(getClass().getResource(path + "ViewReport_CEO.fxml"));
			MonthlyReportsTab = MonthlyreportTabLoader.load();
	    	reportsCeoController CeoReportsController = MonthlyreportTabLoader.getController();	
	    	CeoReportsController.LoadComboBoxesValues(); // always first page to show
			rootPane.getChildren().setAll(MonthlyReportsTab);
			quartleyReportsTab.setManaged(false);
			quartleyReportsTab.setVisible(false);
			SingleQuarterReort.setManaged(false);
			SingleQuarterReort.setVisible(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	}



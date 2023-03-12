package controllers.users.CeoController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import server.ConnectionToClient;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import clientManager.ClientRunner;

import entities.Message;
import entities.MyFile;
import entities.enumE.BranchType;
import entities.enumE.MessageType;
import entities.enumE.OpType;
import entities.users.ceo.CEO;
public class reportsCeoController implements Initializable{

    @FXML
    private Pane rootPane;

    @FXML
    private ComboBox<String> Month;

    @FXML
    private ComboBox<String> Year;

    @FXML
    private ComboBox<String> click_report_type_list_view;

    @FXML
    private Button ViewReportbtn;

    @FXML
    private TextArea ReportTextArea;

    @FXML
    private ComboBox<String> BranchType;
    @FXML
    private Label notExistLabel;

    @FXML
    private Label missingLabel;

    private String selected_Month;
    private String selected_year;
    private String reportType;
    private String brTYPE;
	private static MyFile report;

    private BranchType branchType;
    private Message msg;
    private int flag=0;
    @FXML
    void ChooseBranchType(ActionEvent event) {brTYPE=BranchType.getValue();}
    @FXML
    void ChooseMonth(ActionEvent event) {selected_Month=Month.getValue();}
    @FXML
    void ChooseYear(ActionEvent event) {selected_year=Year.getValue(); }
    @FXML
    void selectReportType(ActionEvent event) {reportType=click_report_type_list_view.getValue();}

    
    /**
     *   
    /**
     * Functionality: clicking on view  reports, and go to the reports screen
     * @param event
     */
  
    @SuppressWarnings("static-access")
	@FXML
    void ClickView(ActionEvent event) {
    	missingLabel.setVisible(false);
		notExistLabel.setVisible(false);
    	ReportTextArea.setVisible(true);
    	CEO ceo=new CEO(ClientRunner.getUser());   //// need to check these lines 
    	
    	if(brTYPE==null||selected_year==null||selected_Month==null||reportType==null)
    		missingLabel.setVisible(true);
    	else {
    	ceo.setB_type1(brTYPE); /// setting the branch type
    	ceo.setYear1(selected_year); /// setting the year 
    	ceo.setMonth(selected_Month);
    	ceo.setRepType(reportType);
    	//////
    	ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.ViewReportCEO,ceo)); ///send request to server
		msg = (Message) ClientRunner.getClientRunner().obj; // from server -type MyFile
	    MyFile str=(MyFile)msg.getMsg();
	    ///System.out.println(str.getFileName());
	  
	
	    if(msg.getMsg()!=null)
		{ 
	    	paraseFileBytes(str);
	    String SS=new String(str.mybytearray);
	    ReportTextArea.appendText(SS);
	
		}else notExistLabel.setVisible(true);
	    
	    
		}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ReportTextArea.setVisible(false);
		missingLabel.setVisible(false);
		notExistLabel.setVisible(false);
	}
	/**
	 * Functionality: converting from object to list.
	 * @param obj
	 * @return list
	 */
	public static List<?> convertObjectToList(Object obj) {  //// converting an object to ArrayList
		List<?> list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			list = Arrays.asList((Object[]) obj);
		} else if (obj instanceof Collection) {
			list = new ArrayList<>((Collection<?>) obj);
		}
		return list;
	}
	/**
	 * Functionality: loading the values into the combo boxes
	 */
	public void LoadComboBoxesValues() {
		// TODO Auto-generated method stub
		 ObservableList<String> Months = FXCollections.observableArrayList("1","2", "3", "4", "5", "6", "7","8","9","10","11","12");
    	 Month.setItems(Months);
	   	 ObservableList<String> Years = FXCollections.observableArrayList("2022","2021","2020","2019","2018","2017","2016","2015","2014","2013","2013","2012","2011","2010");
	     Year.setItems(Years);
		 ObservableList<String> reportTypes = FXCollections.observableArrayList("Income","Orders","Performance");
		 click_report_type_list_view.setItems(reportTypes);
		 ObservableList<String> BraType = FXCollections.observableArrayList(branchType.NORTH.toString(),branchType.WEST.toString(),branchType.EAST.toString(),branchType.SOUTH.toString());
		 BranchType.setItems(BraType);
	}

	private static void paraseFileBytes(MyFile file) {
		String LocalfilePath = file.getFileName();
		int size = file.getSize();
		//String[] a = LocalfilePath.split("\\\\");
		//MyFile new_file = new MyFile(a[a.length - 1]);
		try {
				File newFile = new File("Reports\\"+LocalfilePath);
				FileOutputStream fis = new FileOutputStream(newFile);
			//	FileInputStream fis = new FileInputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fis);	
				bos.write(file.getMybytearray(), 0, size);
				fis.flush();
				bos.flush();
				fis.close();
				bos.close(); 
			}
			catch(Exception e) {
				System.err.println(e);
			}
	}
}

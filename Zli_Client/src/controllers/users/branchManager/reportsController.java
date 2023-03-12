
    package controllers.users.branchManager;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.URL;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import clientManager.ClientRunner;
import entities.Message;
import entities.MyFile;
import entities.Orders;
import entities.Report;
import entities.enumE.BranchType;
import entities.enumE.OpType;
import entities.enumE.ReportType;
import entities.users.branchmanager.BranchManager;
import entities.users.ceo.CEO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
    import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

    /**
     * @author ראמי עמאשה
     *
     */
    public class reportsController implements Initializable{
    	private static MyFile report;

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
        
        private AnchorPane ReportLogTable;
        FXMLLoader ReportLogLoader;

        @FXML
        private Label msg_label;
        private String selected_Month;
        private String selected_year;
        private String reportType;
        private BranchType branchType;
        private Message msg;
        private int flag=0;
        @FXML /// to know the selected month. 
        void ChooseMonth(ActionEvent event) {selected_Month=Month.getValue(); }
        @FXML/// to know the selected year.
        void ChooseYear(ActionEvent event) {selected_year=Year.getValue(); }
        @FXML///to know the selected report type.
        void selectReportType(ActionEvent event) {reportType=click_report_type_list_view.getValue();}

        /**
         * 
         * Functionality: viewing the reports  on the screen
         * @param event
         */
        @SuppressWarnings("unchecked")
		@FXML
        void ClickView(ActionEvent event) {   
        	
        	msg_label.setVisible(false);
        	ReportTextArea.setVisible(true);
        	BranchManager br=new BranchManager(ClientRunner.getUser());   //// need to check these lines 
        	br.setYear(selected_year); /// setting the year 
        	br.setMonth(selected_Month);
        	br.setRepTYPE(reportType);
     
        	//////
        	ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.ViewReportB_M,br));
    		msg = (Message) ClientRunner.getClientRunner().obj; // from server -type MyFile
    	    MyFile str=(MyFile)msg.getMsg();
    	 //   System.out.println(str.getFileName());
    	   
    	
    	    if(msg.getMsg()!=null)
    		{ paraseFileBytes(str);
    	    String SS=new String(str.mybytearray);
    	    ReportTextArea.appendText(SS);
    	
    		}
    	    else msg_label.setVisible(true);
    	    
    		
        }

    	/**
    	 * /**
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
    	}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			ReportTextArea.setVisible(false);
			
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
		
		private static void paraseFileBytes(MyFile file) {
			String LocalfilePath = file.getFileName();
			int size = file.getSize();
			//String[] a = LocalfilePath.split("\\\\");
			//MyFile new_file = new MyFile(a[a.length - 1]);
			try {
					File newFile = new File("Reports Branch Manager\\"+LocalfilePath);
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

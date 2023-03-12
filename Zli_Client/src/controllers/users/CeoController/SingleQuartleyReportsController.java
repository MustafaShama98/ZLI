package controllers.users.CeoController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import entities.Message;
import entities.MyFile;
import entities.enumE.BranchType;
import entities.enumE.OpType;
import entities.users.ceo.CEO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SingleQuartleyReportsController  implements Initializable {

    @FXML
    private Button ViewQuarterReportbtn;

    @FXML
    private ComboBox<String> Year;

    @FXML
    private ComboBox<String> branchType;

    @FXML
    private ComboBox<String> Quarter;

    @FXML
    private Label msgDetails;
    
    @FXML
    private Pane root;

    @FXML
    private Label notExistLabel;
    private Message msg;
    private String selected_year;
    private String selected_quarter;
    private String selected_branchType;
    private BranchType btype;
   
    @FXML
    void ChooseQuarter(ActionEvent event) {selected_quarter=Quarter.getValue();}
    @FXML
    void ChooseYear(ActionEvent event) {selected_year=Year.getValue();}
    @FXML
    void chooseBranchType(ActionEvent event) {selected_branchType=branchType.getValue();}
    
    /**
     * @param event
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
    void ClickViewQuarter(ActionEvent event) {
    	  
    	msgDetails.setVisible(false);
		notExistLabel.setVisible(false);
    	//ReportTextArea.setVisible(true);
    	CEO ceo=new CEO(ClientRunner.getUser());   //// need to check these lines 
    	if(selected_branchType==null||selected_quarter==null||selected_year==null)
    		msgDetails.setVisible(true);
    	else {
    	ceo.setB_type1(selected_branchType); /// setting the branch type
    	ceo.setQuarter1(selected_quarter);
    	ceo.setYear1(selected_year); /// setting the year 
    	//ceo.setMonth(selected_Month);
    	//ceo.setRepType(reportType);
    	//////
    	ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.ViewSingleQuarterReportCEO,ceo));//send request to server
		msg = (Message) ClientRunner.getClientRunner().obj; // from server -type MyFile
	    MyFile str=(MyFile)msg.getMsg();
	   // System.out.println(str.getFileName());
	  
	
	    if(msg.getMsg()!=null)
	    	///// we get the file from the server, it is in variable str, we put it in directory:
	    	//"QuarterSingleReports" and then we take the data from it to the initilaize the chart bars
		{  
	    if( paraseFileBytes(str)) 
	    	/// here we have refactored the code in order to  make it testable
	    	// this is the funcionalty that we nned to check if the data arrives to the client side successfully
	    { 
			    String SS=new String(str.mybytearray);
			    String[] data=SS.split(",");
			  
		//	    ReportTextArea.appendText(SS);
			    CategoryAxis xAxis = new CategoryAxis();
		        xAxis.setLabel("Time(days)");
		        NumberAxis yAxis = new NumberAxis();
		        yAxis.setLabel("Income(Shiekls)");
		        BarChart  chart1=new BarChart(xAxis,yAxis);
		        XYChart.Series dataSeries1 = new XYChart.Series();
		
		     ///  dataSeries1.setName("Income in Quarter "+ceo.getQuarter1()+", in branch "+ceo.getB_type1());
		        dataSeries1.getData().add(new XYChart.Data("1-10"   , Integer.parseInt((data[1]))));
		        dataSeries1.getData().add(new XYChart.Data("11-20"  , Integer.parseInt((data[3]))));
		        dataSeries1.getData().add(new XYChart.Data("21-30"  , Integer.parseInt((data[5]))));   
		        dataSeries1.getData().add(new XYChart.Data("31-40"  , Integer.parseInt((data[7]))));
		        dataSeries1.getData().add(new XYChart.Data("41-50"  , Integer.parseInt((data[9]))));
		        dataSeries1.getData().add(new XYChart.Data("51-60"  , Integer.parseInt((data[11]))));
		        dataSeries1.getData().add(new XYChart.Data("61-70"  , Integer.parseInt((data[13]))));
		        dataSeries1.getData().add(new XYChart.Data("71-80"  , Integer.parseInt((data[15]))));
		        dataSeries1.getData().add(new XYChart.Data("81-90"  , Integer.parseInt((data[17]))));
		    //   dataSeries1.getData().add(new XYChart.Data("21-30"  , data[19]));
		        chart1.getData().add(dataSeries1);
		        chart1.setPrefWidth(500);
		        chart1.setPrefHeight(240);
		        chart1.setLegendVisible(false);
		        chart1.setTranslateY(110);
		        chart1.setTranslateX(30);
		        //  root.setUserData(chart1);
		        root.getChildren().add(chart1);
		     //   chart1.setVisible(true);
				}else {notExistLabel.setVisible(true);}
	    }
    	}
    }
 	/**
	 * /**
 * Functionality: loading the values into the combo boxes
	 */
    public void LoadComboBoxesValues() {
		// TODO Auto-generated method stub
		
	   	 ObservableList<String> Years = FXCollections.observableArrayList("2022","2021","2020","2019","2018","2017","2016","2015","2014","2013","2013","2012","2011","2010");
	     Year.setItems(Years);
		 ObservableList<String> quarters = FXCollections.observableArrayList("1","2","3","4");
		 Quarter.setItems(quarters);
		 ObservableList<String> BraType = FXCollections.observableArrayList(btype.NORTH.toString(),btype.WEST.toString(),btype.EAST.toString(),btype.SOUTH.toString());
		 branchType.setItems(BraType);
	}
	public static boolean paraseFileBytes(MyFile file) { 
		/// this method was extracted 
		
		
		String LocalfilePath = file.getFileName();
		int size = file.getSize();
		//String[] a = LocalfilePath.split("\\\\");
		//MyFile new_file = new MyFile(a[a.length - 1]);
		try {
				File newFile = new File("QuarterSingleReports\\"+LocalfilePath);
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
				e.printStackTrace();	return false; 
			}return true;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		msgDetails.setVisible(false);
		notExistLabel.setVisible(false);
	}
   

}

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

public class quartleyReportsController implements Initializable {
	  @FXML
	    private Pane root;


    @FXML
    private Label missing_msg;

    @FXML
    private Label notFound_msg;

    @FXML
    private Button ViewQuarterReportbtn;

    @FXML
    private ComboBox<String> Year;

    @FXML
    private ComboBox<String> branchType;

    @FXML
    private ComboBox<String> Quarter;

    @FXML
    private ComboBox<String> Year1;

    @FXML
    private ComboBox<String> Quarter1;

    @FXML
    private ComboBox<String> BranchType1;
 
    
    private String selected_year;
    private String selected_quarter;
    private String selected_branchType;
    private BranchType btype;
    private String selected_year1;
    private String selected_quarter1;
    private String selected_branchType1;
    private Message msg;
    private Message msg1;
    @FXML
    void ChooseQuarter(ActionEvent event) {selected_quarter=Quarter.getValue();}
    @FXML
    void ChooseQuarter1(ActionEvent event) {selected_quarter1=Quarter1.getValue();}
    @FXML
    void ChooseYear(ActionEvent event) {selected_year=Year.getValue();}
    @FXML
    void ChooseYear1(ActionEvent event) {selected_year1=Year1.getValue();}
    @FXML
    void chooseBranchType(ActionEvent event) {selected_branchType=branchType.getValue();}
    @FXML
    void chooseBranchType1(ActionEvent event) {selected_branchType1=BranchType1.getValue();}
    
    /**
     *   
    /**
     * Functionality: clicking on view quarter reports, asking for report details and sending them to server
     * @param event
     * @param event
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
    void ClickViewQuarter(ActionEvent event) {
    	
    	missing_msg.setVisible(false);
    	notFound_msg.setVisible(false);
    	//ReportTextArea.setVisible(true);
    	CEO ceo=new CEO(ClientRunner.getUser());   //// need to check these lines 
    	if(selected_branchType==null||selected_quarter==null||selected_year==null)
    		missing_msg.setVisible(true);
    	else {
    	ceo.setB_type1(selected_branchType); /// setting the branch type
    	ceo.setQuarter1(selected_quarter);
    	ceo.setYear1(selected_year); 
    	ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.ViewTwoQuarterReportCEO,ceo)); //send request to server
    	msg = (Message) ClientRunner.getClientRunner().obj; // from server -type MyFile
	    MyFile str=(MyFile)msg.getMsg();
	    if(msg.getMsg()!=null)
		{  
	    paraseFileBytes(str);
	    String SS1=new String(str.mybytearray);
	    String[] data1=SS1.split(",");
	   ///creating the first chart
	    CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Time(days)");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Income(Shiekls)");
      BarChart  chart2=new BarChart(xAxis,yAxis);
        XYChart.Series dataSeries2 = new XYChart.Series();

     ///   dataSeries1.setName("Income in Quarter "+ceo.getQuarter1()+", in branch "+ceo.getB_type1());
        dataSeries2.getData().add(new XYChart.Data("1-10",   Integer.parseInt((data1[1]))));
        dataSeries2.getData().add(new XYChart.Data("11-20"  , Integer.parseInt((data1[3]))));
        dataSeries2.getData().add(new XYChart.Data("21-30"  ,Integer.parseInt((data1[5]))));   
        dataSeries2.getData().add(new XYChart.Data("31-40"  , Integer.parseInt((data1[7]))));
        dataSeries2.getData().add(new XYChart.Data("41-50"  , Integer.parseInt((data1[9]))));
        dataSeries2.getData().add(new XYChart.Data("51-60"  , Integer.parseInt((data1[11]))));
        dataSeries2.getData().add(new XYChart.Data("61-70"  , Integer.parseInt((data1[13]))));
        dataSeries2.getData().add(new XYChart.Data("71-80"  ,Integer.parseInt((data1[15]))));
        dataSeries2.getData().add(new XYChart.Data("81-90"  , Integer.parseInt((data1[17]))));
        chart2.getData().add(dataSeries2);
        chart2.setPrefWidth(187);
        chart2.setPrefHeight(131);
        chart2.setLegendVisible(false);
        chart2.setTranslateY(198);
        chart2.setTranslateX(296);
        root.getChildren().add(chart2);
        //  root.setUserData(chart1);
     //   chart1.setVisible(true);
        System.out.println("hhhhhhererereee: "+str.getFileName());
		}else {notFound_msg.setVisible(true);}
	

    
    	}
    	if(selected_branchType1==null||selected_quarter1==null||selected_year1==null)
    		missing_msg.setVisible(true);
    	else {
    	
    	ceo.setB_type1(selected_branchType1); /// setting the branch type
    	ceo.setQuarter1(selected_quarter1);
    	ceo.setYear1(selected_year1); 
    	ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.ViewTwoQuarterReportCEO,ceo));
    	msg1 = (Message) ClientRunner.getClientRunner().obj; // from server -type MyFile
	    MyFile str=(MyFile)msg1.getMsg();
	    if(msg1.getMsg()!=null)
		{  

	    	paraseFileBytes(str);
	    String SS=new String(str.mybytearray);
	    String[] data=SS.split(",");
	    //creating second chart
	    CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Time(days)");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Income(Shiekls)");
      BarChart  chart=new BarChart(xAxis,yAxis);
        XYChart.Series dataSeries = new XYChart.Series();

     ///   dataSeries1.setName("Income in Quarter "+ceo.getQuarter1()+", in branch "+ceo.getB_type1());
        dataSeries.getData().add(new XYChart.Data("1-10",   Integer.parseInt((data[1]))));
        dataSeries.getData().add(new XYChart.Data("11-20"  , Integer.parseInt((data[3]))));
        dataSeries.getData().add(new XYChart.Data("21-30"  ,Integer.parseInt((data[5]))));   
        dataSeries.getData().add(new XYChart.Data("31-40"  , Integer.parseInt((data[7]))));
        dataSeries.getData().add(new XYChart.Data("41-50"  , Integer.parseInt((data[9]))));
        dataSeries.getData().add(new XYChart.Data("51-60"  , Integer.parseInt((data[11]))));
        dataSeries.getData().add(new XYChart.Data("61-70"  , Integer.parseInt((data[13]))));
        dataSeries.getData().add(new XYChart.Data("71-80"  ,Integer.parseInt((data[15]))));
        dataSeries.getData().add(new XYChart.Data("81-90"  , Integer.parseInt((data[17]))));
        chart.getData().add(dataSeries);
        chart.setPrefWidth(187);
        chart.setPrefHeight(131);
        chart.setLegendVisible(false);
        chart.setTranslateY(198);
        chart.setTranslateX(72);
        root.getChildren().add(chart);
        //  root.setUserData(chart1);
     //   chart1.setVisible(true);
        System.out.println("hhhhhhererereee2222222222222222:"+str.getFileName());
		}else {notFound_msg.setVisible(true);}
	

    }
    
	}

  



	 /**
	 *   
    /**
     * Functionality: load the values into the combo boxes
     * 
     */
	 
	public void LoadComboBoxesValues() {
		// TODO Auto-generated method stub
	   	 ObservableList<String> Years = FXCollections.observableArrayList("2022","2021","2020","2019","2018","2017","2016","2015","2014","2013","2013","2012","2011","2010");
	     Year.setItems(Years);
	     Year1.setItems(Years);
		 ObservableList<String> quarters = FXCollections.observableArrayList("1","2","3","4");
		 Quarter.setItems(quarters);
		 Quarter1.setItems(quarters);
		 ObservableList<String> BraType = FXCollections.observableArrayList(btype.NORTH.toString(),btype.WEST.toString(),btype.EAST.toString(),btype.SOUTH.toString());
		 branchType.setItems(BraType);
		 BranchType1.setItems(BraType);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		notFound_msg.setVisible(false);
		missing_msg.setVisible(false);
	}

	private static void paraseFileBytes(MyFile file) {
		String LocalfilePath = file.getFileName();
		int size = file.getSize();
		//String[] a = LocalfilePath.split("\\\\");
		//MyFile new_file = new MyFile(a[a.length - 1]);
		try {
				File newFile = new File("Quarter Two Reports\\"+LocalfilePath);
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

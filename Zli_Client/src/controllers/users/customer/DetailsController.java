package controllers.users.customer;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import clientManager.OpenWindow;
import entities.Message;
import entities.enumE.OpType;
import entities.enumE.Catalog.BranchType;
import entities.users.Catalog;
import entities.users.customer.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetailsController implements Initializable
{
	    @FXML
	    private Button backBtn;
	    @FXML
	    private Pane rootPane;
	    
	    @FXML
	    private CheckBox DeliveryId;
	    @FXML
	    private VBox vbox_orderDetails;
	    @FXML
	    private Pane DeliveryPane;
	    @FXML
	    private Pane Order_Details;
	    @FXML
	    private TextField Name;
	    @FXML
	    private TextField Address;
	    @FXML
	    private TextField PhoneNumber;
	    @FXML
	    private Button Done_Delivery;
	    @FXML
	    private Button PaymentClick;
	    @FXML
	    private ChoiceBox<BranchType> ChooseBranch;
	    @FXML
	    private DatePicker SetDate;
	    @FXML
	    private TextField SetTime;
	    @FXML
	    private Label FirstOrder;
	    @FXML
	    private Label Total_Price;
	    @FXML
	    private Label Discount_lable;
	    @FXML
	    private Label Delivery_cost;
	    @FXML
	    private Label Order_Price;
	    @FXML
	    private Label Discount_Branch;
	    @FXML
	    private Button Confirm;
	    
	    @FXML
	    private Label Thank_label;
	    @FXML
	    private Pane OK_thankyou;
	    @FXML
	    private CheckBox Express;
	    @FXML
	    private Label Choose;

	    
    private Message msg;
	private Object event;
	ObservableList<BranchType> listOfBranches = FXCollections.observableArrayList();
    ArrayList<String> OrderDetails=new ArrayList<>();
    ArrayList<String> cu_Details= new ArrayList<>();
    Map<String,String> SalesMap= new HashMap<String,String>();
    private Map<String, BouquteListController > Details = new HashMap<String, BouquteListController>();

    static String choosebranch;
	static String selectedDate;
	Boolean flag=true,flag2=true,flag3=false,flag4=false;
	Double TotalSale=0.0,Delivary=0.0,Total=0.0,BranchSale=0.0,FirstSale=0.0,Price=0.0;
	String str="";
	private CustomerPortalController customerPortalController;
	private VBox CartList;
    @SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
   {  
    	Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
    	OK_thankyou.setVisible(false);
    	rootPane.setVisible(true);
    	Choose.setVisible(false);
    	OrderDetails.add(0,String.valueOf(ClientRunner.getUser().getId()));
    	OrderDetails.add(1," ");
    	OrderDetails.add(2," " );//branch
    	OrderDetails.add(3,"Purchased");//Activity
    	OrderDetails.add(4,"Waiting");//Status
    	OrderDetails.add(5,"TAKE_AWAY");
    	OrderDetails.add(6," ");//name
    	OrderDetails.add(7,"  ");//address
    	OrderDetails.add(8," ");//PhoneNumber
    	OrderDetails.add(9,"0");//DelivaryPrice
    	OrderDetails.add(10,"0");//OrderPrice
    	OrderDetails.add(11,"0");//Total
    	OrderDetails.add(12,formatter.format(date));///
    	OrderDetails.add(13," ");
    	Delivery_cost.setText("0.0");
    	Discount_lable.setText("0");
    	FirstOrder.setVisible(false);
    	
    	OpType[] req = new OpType[2];///// here?
	    req[0]=OpType.Table;
	    req[1]=OpType.ViewMyOrder;
    	DeliveryPane.setVisible(false);//hide the pane
    	FirstOrder.setVisible(false);
      Customer customer = (Customer) ClientRunner.getUser();
    	ClientRunner.getClientRunner().SendReqToServer(new Message(req,customer));// send request to server
		msg = (Message) ClientRunner.getClientRunner().obj; // from server
    	if(msg.getMsg()==null)
    	{
    		FirstOrder.setVisible(true);
    		Discount_lable.setText("20%");
   	        FirstSale=20.0;
    	}

    	ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.Sales));
    	msg = (Message) ClientRunner.getClientRunner().obj; // from server
    	SalesMap=(HashMap<String,String>) msg.getMsg();
    	
    	
    	ClientRunner.getClientRunner().obj=null;
    	LoadData();
    	ChooseBranch.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
    		choosebranch = newValue.toString();
		});
    	SetDate.valueProperty().addListener((v, old, newdate) -> {
			selectedDate = newdate.toString();
		});	
    	
	}
    private void addDetails() {
    	for (BouquteListController controlle2 : Details.values()) 
    	    { 
         	   vbox_orderDetails.getChildren().add(new Label(controlle2.ViewDetails()));
    		   Price+=controlle2.OrderPrice();
    		   str+=controlle2.ViewDetails();
    		   
			}
    	System.out.println(Price);
    	Order_Price.setText(Price.toString()+"$");
    	OrderDetails.set(10,String.valueOf(Price));
    	OrderDetails.set(1,str);
    	
	}
	public Map<String, BouquteListController> getDetails() {
		return Details;
	}
	public void setDetails(Map<String, BouquteListController> details) {
		this.Details = details;
		
	}
	@FXML
    void Click_Back(ActionEvent event) 
    {
    	 
((Node)(event.getSource())).getScene().getWindow().hide();
Stage s = (Stage) (((Node)((EventObject) this.event).getSource()).getScene().getWindow());
        s.show();
    }

    @FXML
    void Click_Delivery(ActionEvent event)
    {
    	 
    	if(flag)
    	{
    		Delivery_cost.setText("20$");
    		DeliveryPane.setVisible(true);
    		Delivary=20.0;
    		flag=false;
    	}
    	else
    	{
    		DeliveryPane.setVisible(false);
    		OrderDetails.set(5,"TAKE_AWAY");
    		OrderDetails.set(6,null);//name
    		OrderDetails.set(7,null);//address
    		OrderDetails.set(8,null);//PhoneNumber
    		OrderDetails.set(9,"0");//DelivaryPrice
    		Delivery_cost.setText("0");
    		Delivary=0.0;
    		flag=true;
    	}
    	
    }
    @FXML
    void Click_Express(ActionEvent event) {
    	if(flag2)
    	{
    		OrderDetails.set(5,"EXPRESS");
    		SetDate.setDisable(true);
    		SetTime.setDisable(true);
    		flag2=false;
    		
    	}
    	else
    	{
    		OrderDetails.set(5,"DELIVARY");
    		SetTime.setDisable(false);
    		SetDate.setDisable(false);
    		flag2=true;
    	}
    		

    }

 	@SuppressWarnings("unchecked")
	private void LoadData() {
 		listOfBranches.removeAll(listOfBranches);
 		listOfBranches.addAll(Catalog.getBranchArray2());
 		ChooseBranch.getItems().addAll(listOfBranches);
 	}
 	
    @FXML
    void Done_BranchDetails(ActionEvent event) 
    {
    	
    	
    	OrderDetails.set(2,choosebranch);//branch
    	OrderDetails.set(13,selectedDate+" "+SetTime.getText());
    	String sale=SalesMap.get(choosebranch);
    	if(sale!=null) {
    	Discount_Branch.setText(sale);
    	String[] str=sale.split("%");
        BranchSale=Double.parseDouble(str[0]);	
    	}
    	flag4=true;
    }

    @FXML
    void Done_Delivery_Click(ActionEvent event)//return from server
    {
    	OrderDetails.set(5,"DELIVARY");
    	OrderDetails.set(6,Name.getText());//name
    	OrderDetails.set(7,Address.getText());//address
    	OrderDetails.set(8,PhoneNumber.getText());//PhoneNumber	
    	OrderDetails.set(9,"20");//DelivaryPrice
    } 
    
    @FXML
    void Payment_Click(ActionEvent event)
    {
    	if(flag4) {
    	
    	rootPane.setVisible(false);
    	OK_thankyou.setVisible(true);
    	Thank_label.setVisible(false);
    	addDetails();
    	Total=Price;
    	Total+=Delivary;
    	TotalSale=100-(BranchSale+FirstSale);
    	if(TotalSale!=0)
    	{
    		Total=Total*(TotalSale/100);
    	}
    	
    	OrderDetails.set(11,Total.toString());
    	Total_Price.setText(Total.toString()+"$");
    	Choose.setVisible(false);
    	}
    	else
    	{
    		Choose.setVisible(true);
    	}
    	flag3=true;
    }
    @FXML
    void Click_Confirm(ActionEvent event) {
	 ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.AddOrder,(Object)OrderDetails));// send request to server
	 msg = (Message) ClientRunner.getClientRunner().obj; // from server
	 boolean  result=(Boolean)msg .getMsg();
	 if(result)
	 {
		 Thank_label.setVisible(true);
	
	     
	 }

    CartList.getChildren().removeAll(CartList.getChildren());
	 Details.clear();
	customerPortalController.resetIndex();
	
	
    }
    
    
	public void setEvent(ActionEvent event) {
		this.event=event;
		
	}
	public void setCustomerController(CustomerPortalController customerPortalController) {
		
		this.customerPortalController = customerPortalController;
		
	}
	public void setCartList(VBox cart_List2) {
		
		this.CartList = cart_List2;
		
	}
}



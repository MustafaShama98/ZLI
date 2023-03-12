package controllers.users.customer;


import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import entities.Message;
import entities.Orders;
import entities.enumE.OpType;
import entities.users.Catalog;
import entities.users.customer.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * This class for customer orders 
 * 
 *
 */

public class MyOrdersController  implements Initializable {
	    @FXML
	    private Button Cancle_Btn;

	    @FXML
	    private Label Cancellation;

	    @FXML
	    private Label CancellationSucceeded;

	    @FXML
	    private Label selectOrder;

	    @FXML
	    private Button BackBtn;

	    @FXML
	    private TableView<Orders> MyOrdersTable;

	    @FXML
	    private TableColumn<Orders,Integer> OrderNumID;

	    @FXML
	    private TableColumn<Orders, String> DetailsID;

	    @FXML
	    private TableColumn<Orders,Double> TotalPriceID;

	    @FXML
	    private TableColumn<Orders, String> ActivityID;

	    @FXML
	    private TableColumn<Orders, String> StatusID;

	    @FXML
	    private TableColumn<Orders, String> OrderDateID;

	    @FXML
	    private TableColumn<Orders, String> ReceivedDateID;

	    @FXML
	    private TableColumn<Orders, String> SupplyTypeID;

    private ActionEvent event;
    private ObservableList<Orders> order_table_data=FXCollections.observableArrayList();
    private Message msg;
    /**
     * back to previous window
     * @param event
     */
    @FXML
    void ClickBack(ActionEvent event)
    {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }
    /**
     * check if its possible to cancel the order and or send to manager to cancel nor give message that cant cancel order
     * because the order are more than 3 hours confirmed
     * @param event
     */
    @SuppressWarnings("unchecked")
	@FXML
    void selectCancle(ActionEvent event) {
    	ObservableList<Orders> OrderList;
    	OrderList=MyOrdersTable.getSelectionModel().getSelectedItems();
    	if(OrderList.isEmpty())
    	{
    		selectOrder.setVisible(true);
    		Cancellation.setVisible(false);
     	    CancellationSucceeded.setVisible(false);
    	}
    	else
    	{
    	Integer OrderNum= Integer.valueOf(OrderList.get(0).getOrderNumber());
    	Object myObject = OrderNum;
    	ClientRunner.getClientRunner().SendReqToServer(new Message((OpType.Cancel),myObject));
    	msg = (Message) ClientRunner.getClientRunner().obj; // from server
    	Boolean result = ((Boolean) msg.getMsg()).booleanValue();
        ClientRunner.getClientRunner().obj = null;
    	if(result==false)
    	{
    		selectOrder.setVisible(false);
    	   Cancellation.setVisible(true);
    	   CancellationSucceeded.setVisible(false);
    	}else
    	{	
    		CancellationSucceeded.setVisible(true);
    		Cancellation.setVisible(false);
    		selectOrder.setVisible(false);
    		ShowTable();
    	    
    	
    	}
    	}
    	
    }

    /**
	 * initialize function
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ShowTable();
		
	}
	
	@SuppressWarnings("unchecked")
	public void ShowTable()
	{
		
		OpType[] req = new OpType[2];
		req[0]=	OpType.Table;
		req[1]=OpType.ViewMyOrder;
	
    	ClientRunner.getClientRunner().SendReqToServer(new Message(req,ClientRunner.getUser()));
    	System.out.println("AFTER");
		msg = (Message) ClientRunner.getClientRunner().obj; // from server
		if(msg.getMsg()!=null)
		{
		order_table_data = (ObservableList<Orders>) FXCollections.observableArrayList(convertObjectToList(msg.getMsg()));
		MyOrdersTable.setItems(order_table_data);
		load();
		
	    ClientRunner.getClientRunner().obj = null;
		}
	}
	/**
	 * this function to convert object to list
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
	 * load data to customer orders java FX
	 */
private void load() {
	OrderNumID.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
	DetailsID.setCellValueFactory(new PropertyValueFactory<>("Details"));
	TotalPriceID.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
	ActivityID.setCellValueFactory(new PropertyValueFactory<>("Activity"));
	StatusID.setCellValueFactory(new PropertyValueFactory<>("Status"));
	OrderDateID.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
	ReceivedDateID.setCellValueFactory(new PropertyValueFactory<>("ReceivedDate"));
	SupplyTypeID.setCellValueFactory(new PropertyValueFactory<>("SupplyType"));
	
		
	}
/**
 * 
 * @param  set event
 */
public void setEvent(ActionEvent event) {
		this.event=event;
	}

}

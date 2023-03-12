package controllers.users.branchManager;


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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ordersController  implements Initializable {

    @FXML
    private TableView<Orders> OrdersTable;

    @FXML
    TableColumn<Orders, Integer> OrderNumber;

    

    @FXML
    private TableColumn<Orders, String> OrderDetails;

    @FXML
    private TableColumn<Orders, Double> Price;

    @FXML
    private TableColumn<Orders, String> Activity;

    @FXML
    private TableColumn<Orders, String> Status;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button UnconfirmBtn;

    @FXML
    private Label msg_select_order;
    
    @FXML
    private ImageView back;
    
    private ObservableList<Orders> order_table_data=FXCollections.observableArrayList();
    private Message msg;

    /**
     *Functionality: unconfirming the order status
     * @param event
     */
    @FXML
    void ClickUnconfirm(ActionEvent event) { /// if the user want to un-confirm an order.

    	ObservableList<Orders> OrderList;
    	OrderList=OrdersTable.getSelectionModel().getSelectedItems(); /// getting the line that the user clicked on .
 
    	if(OrderList.isEmpty()) { /// if the user didn't click on an item.
    	   msg_select_order.setVisible(true);
    	}
    	else
    	{   /// the user has selected an order.
    		Integer OrderNum=Integer.valueOf(OrderList.get(0).getOrderNumber());
    		Object myObject=OrderNum;
        	ClientRunner.getClientRunner().SendReqToServer(new Message((OpType.UnConfirmOrderB_M),myObject)); /// send to server 
        	msg=(Message)ClientRunner.getClientRunner().obj; // from server
        	Boolean result = ((Boolean) msg.getMsg()).booleanValue();
            ClientRunner.getClientRunner().obj = null;

            if(result==false)
        	{
         	   msg_select_order.setVisible(true);

        	}else
        	{	
         	   msg_select_order.setVisible(false);

        		ShowTable();
        	}

    	}
    }

    /**
     *Functionality: confirming the order status
     * @param event
     */
    @FXML
    void clickConfirm(ActionEvent event) {

    	ObservableList<Orders> OrderList;
    	OrderList=OrdersTable.getSelectionModel().getSelectedItems(); /// getting the line that the user clicked on .
 
    	if(OrderList.isEmpty()) { /// if the user didn't click on an item.
    	   msg_select_order.setVisible(true);
    	}
    	else
    	{   /// the user has selected an order.
    		Integer OrderNum=Integer.valueOf(OrderList.get(0).getOrderNumber());
    		Object myObject=OrderNum;
        	ClientRunner.getClientRunner().SendReqToServer(new Message((OpType.ConfirmOrderB_M),OrderNum)); /// send to server 
        	msg=(Message)ClientRunner.getClientRunner().obj; // from server
        	
        	Boolean result = ((Boolean) msg.getMsg()).booleanValue();
            ClientRunner.getClientRunner().obj = null;
            if(result==false)
        	{
         	   msg_select_order.setVisible(true);

        	}else
        	{	
         	   msg_select_order.setVisible(false);

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
	@SuppressWarnings("unchecked")
	public void ShowTable() {
		

		ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.ViewMyOrderB_M,ClientRunner.getUser()));//send request to server
		msg = (Message) ClientRunner.getClientRunner().obj; // from server
		if(msg.getMsg()!=null)
		{
		order_table_data = (ObservableList<Orders>) FXCollections.observableArrayList(convertObjectToList(msg.getMsg()));
		OrdersTable.setItems(order_table_data);
		load();
	    ClientRunner.getClientRunner().obj = null;
		}
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
	 * Functionality: putting the data inside the table
	 */
	private void load() {  
		//// this method for loading the data 
		OrderNumber.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
		OrderDetails.setCellValueFactory(new PropertyValueFactory<>("Details"));
		Price.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
		 Activity.setCellValueFactory(new PropertyValueFactory<>("Activity"));
		 Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
			
		}

}

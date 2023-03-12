package controllers.users.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import entities.Message;
import entities.enumE.OpType;
import entities.users.Catalog;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class CategoryTabController implements Initializable{
	    @FXML
	    private Button birthdayBtn;
	    @FXML
	    private Button weddingBtn;
	    @FXML
	    private Button MOMBtn;
	    @FXML
	    private Button GraduationBtn;
	    @FXML
	    private TableView<Catalog> birthdayTable;
	    @FXML
	    private TableColumn<Catalog, String> Details_Birthday;
	    @FXML
	    private TableColumn<Catalog, String> Type_Birthday;
	    @FXML
	    private TableColumn<Catalog, String> Name_Birthday;
	    @FXML
	    private TableColumn<Catalog,String> Color_Birthday;
	    @FXML
	    private TableColumn<Catalog,Double> Price_Birthday;
	    @FXML
	    private TableView<Catalog> weddingTable;
	    @FXML
	    private TableColumn<Catalog, String> Details_Wedding;
	    @FXML
	    private TableColumn<Catalog, String> Type_Wedding;
	    @FXML
	    private TableColumn<Catalog, String> Name_Wedding;
	    @FXML
	    private TableColumn<Catalog, String> Color_Wedding;
	    @FXML
	    private TableColumn<Catalog, Double> Price_Wedding;
	    @FXML
	    private TableView<Catalog> MOMTable;
	    @FXML
	    private TableColumn<Catalog, String> Details_MOM;
	    @FXML
	    private TableColumn<Catalog, String> Type_MOM;
	    @FXML
	    private TableColumn<Catalog, String> Name_MOM;

	    @FXML
	    private TableColumn<Catalog,String> Color_MOM;

	    @FXML
	    private TableColumn<Catalog, Double> Price_MOM;

	    @FXML
	    private TableView<Catalog> GraduationTable;


	    @FXML
	    private TableColumn<Catalog, String> Details_Graduation;
	    @FXML
	    private TableColumn<Catalog, String> Type_Graduation;

	    @FXML
	    private TableColumn<Catalog, String> Name_Graduation;

	    @FXML
	    private TableColumn<Catalog, String> Color_Graduation;

	    @FXML
	    private TableColumn<Catalog,Double> Price_Graduation;
	    ObservableList<Catalog> CatalogList;
	    TableView<Catalog> tableType;
	    ArrayList<String> item=new ArrayList<String>();

	    private ObservableList<Catalog> birthday_table_data=FXCollections.observableArrayList();
	    private ObservableList<Catalog> wedding_table_data=FXCollections.observableArrayList();
	    private ObservableList<Catalog> Mom_table_data=FXCollections.observableArrayList();
	    private ObservableList<Catalog> Graduation_table_data=FXCollections.observableArrayList();
	    private boolean isOneClick_wedding = true;
	    private boolean  isOneClick_birthday = true;  
	    private boolean isOneClick_Mom = true;
	    private boolean  isOneClick_Graduation= true;
	  
	    
	    private Message msg;
	    @FXML
	    private Button AddToCart;

		//private Object VboxList;

		//private List<Catalog> paneList;

		private Map<String, BouquteListController > itemsList= new HashMap<String, BouquteListController>();;

		private int index=0;
		private List<Pane> paneList = new ArrayList<Pane>();

		private CustomerPortalController CustomerPortalController_;
		VBox VboxList; 
		   /**
		   * initialize function 
		   */
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			isOneClick_birthday=handleTableData(isOneClick_birthday, OpType.Birthday, birthday_table_data, birthdayTable);
		}
	    @FXML
	    void ClicktoCart(ActionEvent event) {
	    	CatalogList = tableType.getSelectionModel().getSelectedItems();
	  		if (CatalogList.isEmpty()) {
	 		}
	  		else
	  		{
	  			if(!(item.contains(CatalogList.get(0).getName())))
	  			item.add(CatalogList.get(0).getName());
	  		}

	    }
	  
		/**
		 * 
		 * @param event if clicked Graduation tab
		 */
		  @FXML
		    void Graduation_tab_clicked(ActionEvent event) {
	isOneClick_Graduation= handleTableData(isOneClick_Graduation, OpType.Graduation, Graduation_table_data, GraduationTable);
				
		    }
		  /**
		   * 
		   * @param event if clicked MOM tab
		   */
		    @FXML
		    void MOM_tab_clicked(ActionEvent event) {
		isOneClick_Mom= handleTableData(isOneClick_Mom, OpType.MOM, Mom_table_data, MOMTable);
			   
		    }
		    /**
		     * 
		     * @param event if clicked birthday tab
		     */
		    @FXML
		    void birthday_tab_Click(ActionEvent event) {
	           isOneClick_birthday=handleTableData(isOneClick_birthday, OpType.Birthday, birthday_table_data, birthdayTable);
				
				
		    }
		    /**
		     * 
		     * @param event if clicked wedding tab
		     */
		    @FXML
		    void wedding_tab_clicked(ActionEvent event) {
	           isOneClick_wedding= handleTableData(isOneClick_wedding, OpType.Wedding, wedding_table_data, weddingTable);
				
		    }
		    
		    /**
			 * 
			 * @param flag - Handles only 1 time access to request table data from the server.
			 * @param OptypeTable - Enum, to identify table type
			 * @param table_data - ObservableList
			 * @param tableType - TableView JavaFX
			 */
			private boolean handleTableData(boolean flag, OpType OptypeTable ,ObservableList<Catalog> table_data, TableView<Catalog> tableType ) {
				
				if(flag) { // has only 1 time access
					ClientRunner.getClientRunner().SendReqToServer(new Message(requestType(OptypeTable)));// send request to server
					
					//handling the message from  server
					 messageFromServerHandler(table_data,tableType );
					 flag=false;
			    	}
					switch (OptypeTable) 
					{
					case Wedding: {
						 LoadWeddingTable();
						break;
					}
					case Birthday: {
						LoadBirthdayTable();
						break;
					}
					case Graduation:{
						LoadGraduationTable();
						break;
					}
					case MOM:{
						LoadMomTable();
						break;
					}
					default:
						
						throw new IllegalArgumentException("Unexpected value: " + OptypeTable);
				
					}
					return flag;
			    	
			}
			   
			/**
			 * 
			 * @param tableName
			 * @return put tableName in req[2]
			 */
	private OpType[] requestType(OpType tableName) {
		OpType[] req = new OpType[3];
		req[0] = OpType.Table;
		req[1] = OpType.Catalog;
		switch (tableName) {
		case Birthday : {
			req[2]= OpType.Birthday;
			break;
		}
		case Wedding: {
			req[2] = OpType.Wedding;
			break;
		}
		case MOM:
		{
			req[2] = OpType.MOM;
			break;
		}
		case Graduation:
		{
			req[2] = OpType.Graduation;
			break;
		}
		default:
			
			throw new IllegalArgumentException("Unexpected value: " + tableName);
		}
		return req;
	}
	 /**
	  * 
	  * @param table_data
	  * @param tableType
	  */
	@SuppressWarnings("unchecked")
	private void messageFromServerHandler(ObservableList<Catalog> table_data, TableView<Catalog> tableType) {
		msg = (Message) ClientRunner.getClientRunner().obj; // from server
		
		
		table_data = (ObservableList<Catalog>) FXCollections.observableArrayList(convertObjectToList(msg.getMsg()));
		
		tableType.setItems(table_data);
		
		this.tableType=tableType;
		ClientRunner.getClientRunner().obj = null;
	
		
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
	 * load Birthday tables
	 */
	 void LoadBirthdayTable() {
	    	Name_Birthday.setCellValueFactory(new PropertyValueFactory<>("Name"));
	    	Details_Birthday.setCellValueFactory(new PropertyValueFactory<>("Details"));
		    Color_Birthday.setCellValueFactory(new PropertyValueFactory<>("Color")); 
		    Type_Birthday.setCellValueFactory(new PropertyValueFactory<>("Type"));
	    	Price_Birthday.setCellValueFactory(new PropertyValueFactory<>("Price"));
		    birthdayTable.setVisible(true);
			weddingTable.setVisible(false);
			MOMTable.setVisible(false);
			GraduationTable.setVisible(false);	
	    }
	 /**
	  * load data to Wedding table
	  */
	 void LoadWeddingTable() {
	    	Name_Wedding.setCellValueFactory(new PropertyValueFactory<>("Name"));
	    	Details_Wedding.setCellValueFactory(new PropertyValueFactory<>("Details"));
	    	Color_Wedding.setCellValueFactory(new PropertyValueFactory<>("Color")); 
	    	Type_Wedding.setCellValueFactory(new PropertyValueFactory<>("Type"));
	    	Price_Wedding.setCellValueFactory(new PropertyValueFactory<>("Price"));
	    	weddingTable.setVisible(true);
			birthdayTable.setVisible(false);
			MOMTable.setVisible(false);
			GraduationTable.setVisible(false);
	    	
			
	    }
	 /**
	  * load data to Graduation table
	  */
	 void LoadGraduationTable() {
	    	
		     Type_Graduation.setCellValueFactory(new PropertyValueFactory<>("Type"));
		     Details_Graduation.setCellValueFactory(new PropertyValueFactory<>("Details"));
	     	 Name_Graduation.setCellValueFactory(new PropertyValueFactory<>("Name"));
	    	 Color_Graduation.setCellValueFactory(new PropertyValueFactory<>("Color"));
	         Price_Graduation.setCellValueFactory(new PropertyValueFactory<>("Price"));
	         GraduationTable.setVisible(true);
			 birthdayTable.setVisible(false);
			 weddingTable.setVisible(false);
			 MOMTable.setVisible(false);
	  
			
	    }
	 /**
	  * load data to MOM table
	  */
	 void LoadMomTable() {
	    	
		    Type_MOM.setCellValueFactory(new PropertyValueFactory<>("Type"));
		    Details_MOM.setCellValueFactory(new PropertyValueFactory<>("Details"));
	        Name_MOM.setCellValueFactory(new PropertyValueFactory<>("Name"));
	    	Color_MOM.setCellValueFactory(new PropertyValueFactory<>("Color"));
	    	Price_MOM.setCellValueFactory(new PropertyValueFactory<>("Price"));
	    	MOMTable.setVisible(true);
	    	birthdayTable.setVisible(false);
	    	weddingTable.setVisible(false);
			GraduationTable.setVisible(false);
			
	    	
			
	    }
	 

	 /**
	  * when customer click in AddToCart button the function check if the selection of the item/bouquet that 
	  * we want to add to cart from witch table 
	  * @param event
	  */

		   @FXML
		    void AddToCart(ActionEvent event) {
			 
			   String name=null,temp = null;
		    	Catalog[] catalogArr = new Catalog[4];
		    	catalogArr[0] = birthdayTable.getSelectionModel().getSelectedItem();
		    	if(!(GraduationTable.getSelectionModel().getSelectedItem()== null))
		    	{
		    	 System.out.println("GraduationTable"); 
		    	 name = GraduationTable.getSelectionModel().getSelectedItem().getName();
			   
		    	 CustomerPortalController_.add(name,GraduationTable);
			    	 GraduationTable.getSelectionModel().clearSelection();
			    	 
		    	}
		    	if(!(MOMTable.getSelectionModel().getSelectedItem()== null))
		    	{
		    	 System.out.println("FlowersTable");
			    	 name = MOMTable.getSelectionModel().getSelectedItem().getName();
			    	 CustomerPortalController_.add(name,MOMTable);
			    	 MOMTable.getSelectionModel().clearSelection();
			    	 
		    	}
		    	if(!(weddingTable.getSelectionModel().getSelectedItem()== null))
		    	{
		    		System.out.println("FlowerPotsTable");
			    	 name = weddingTable.getSelectionModel().getSelectedItem().getName();
			    	 CustomerPortalController_.add(name,weddingTable);
			    	 weddingTable.getSelectionModel().clearSelection();
			    	
		    	}
		    	
		    		if(!(birthdayTable.getSelectionModel().getSelectedItem() == null))
		    	{
		    			System.out.println("wedding");
			    	 name = birthdayTable.getSelectionModel().getSelectedItem().getName();
			    	 CustomerPortalController_.add(name,birthdayTable);
			    	 birthdayTable.getSelectionModel().clearSelection();
			    	 
		    	}
		
			 
		    	
		    }
		   /**
		    * 
		    * @param event-> set event (CustomerPortalController)
		    */
			 public void setEvent(CustomerPortalController event) {
			    	this.CustomerPortalController_ = event;
			    }
			 
}



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
	import entities.users.Catalog;

import entities.Message;
	import entities.enumE.OpType;
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

public class IndividualTabController implements Initializable{
	/**
	 * this class has the individual tables
	 * 
	 *
	 */


	    @FXML
	    private Button Seedlings;
	    @FXML
	    private Button FlowerPots;
		@FXML
	    private Button FlowerBranches;

	    @FXML
	    private Button Flowers;

	    @FXML
	    private TableView<Catalog> SeedlingsTable;

	    @FXML
	    private TableColumn<Catalog, String> DetailsSeedlings;

	    @FXML
	    private TableColumn<Catalog, String> NameSeedlings;

	    @FXML
	    private TableColumn<Catalog, String> ColorSeedlings;

	    @FXML
	    private TableColumn<Catalog, Double> PriceSeedlings;

	    @FXML
	    private TableView<Catalog> FlowerPotsTable;

	    public TableView<Catalog> getSeedlingsTable() {
			return SeedlingsTable;
		}


		public TableView<Catalog> getFlowerPotsTable() {
			return FlowerPotsTable;
		}


		public TableView<Catalog> getFlowerBranchesTable() {
			return FlowerBranchesTable;
		}


		public TableView<Catalog> getFlowersTable() {
			return FlowersTable;
		}


		@FXML
	    private TableColumn<Catalog, String> DetailsFlowerPots;

	    @FXML
	    private TableColumn<Catalog, String> NameFlowerPots;

	    @FXML
	    private TableColumn<Catalog, String> ColorFlowerPots;

	    @FXML
	    private TableColumn<Catalog, Double> PriceFlowerPots;

	    @FXML
	    private TableView<Catalog> FlowerBranchesTable;

	    @FXML
	    private TableColumn<Catalog, String> DetailsFlowerBranches;

	    @FXML
	    private TableColumn<Catalog,String> NameFlowerBranches;

	    @FXML
	    private TableColumn<Catalog, String> ColorFlowerBranches;

	    @FXML
	    private TableColumn<Catalog, Double> PriceFlowerBranches;

	    @FXML
	    private TableView<Catalog> FlowersTable;

	    @FXML
	    private TableColumn<Catalog, String> DetailsFlowers;

	    @FXML
	    private TableColumn<Catalog, String> NameFlowers;

	    @FXML
	    private TableColumn<Catalog, String> ColorFlowers;

	    @FXML
	    private TableColumn<Catalog, Double> PriceFlowers;
	    
	    private ObservableList<Catalog>Flowers_table_data=FXCollections.observableArrayList();
	    private ObservableList<Catalog> FlowerBranches_table_data=FXCollections.observableArrayList();
	    private ObservableList<Catalog> Seedlings_table_data=FXCollections.observableArrayList();
	    private ObservableList<Catalog> FlowerPots_table_data=FXCollections.observableArrayList();
	    private boolean isOneClick_Flowers = true;
	    private boolean  isOneClick_FlowerBranches = true;  
	    private boolean isOneClick_FlowerPots = true;
	    private boolean  isOneClick_Seedlings= true;
	    private Message msg;
String name;
VBox VboxList;
private List<Pane> paneList = new ArrayList<Pane>();
private int index=0;
private Map<String, BouquteListController > itemsList = new HashMap<String, BouquteListController>();

		private CustomerPortalController CustomerPortalController_;
		/**
		 * initialize function Show Seedlings table
		 */
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	isOneClick_Seedlings= handleTableData(isOneClick_Seedlings, OpType.Seedlings, Seedlings_table_data, SeedlingsTable);
			
		}
		
	    /**
	     * FlowerPots_tab_clicked
	     * @param event
	     */
	    @FXML
	    void FlowerPots_tab_clicked(ActionEvent event) {
	    	isOneClick_FlowerPots= handleTableData(isOneClick_FlowerPots, OpType.FlowerPots, FlowerPots_table_data, FlowerPotsTable);
	    }
	    /**
	     * Flowers_tab_clicked
	     * @param event
	     */
	    @FXML
	    void Flowers_tab_clicked(ActionEvent event) {
	    	isOneClick_Flowers= handleTableData(isOneClick_Flowers, OpType.Flowers, Flowers_table_data, FlowersTable);
	    }
	    /**
	     * Seedlings_tab_Click
	     * @param event
	     */
	    @FXML
	    void Seedlings_tab_Click(ActionEvent event) {
	    	isOneClick_Seedlings= handleTableData(isOneClick_Seedlings, OpType.Seedlings, Seedlings_table_data, SeedlingsTable);
	    }
	    /**
	    * FlowerBranches_tab_clicked
	    * @param event
	    */
	    @FXML
	    void FlowerBranches_tab_clicked(ActionEvent event) {
	    	isOneClick_FlowerBranches= handleTableData(isOneClick_FlowerBranches, OpType.FlowerBranches, FlowerBranches_table_data,FlowerBranchesTable);
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
			case Seedlings: {
				 LoadSeedlingsTable();
				break;
			}
			case FlowerPots: {
				LoadFlowerPotsTable();
				break;
			}
			case Flowers:{
				LoadFlowersTable();
				break;
			}
			case FlowerBranches:{
				LoadFlowerBranchesTable();
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
	 * @return
	 */
	private OpType[] requestType(OpType tableName) {
	
		OpType[] req = new OpType[3];
		req[0] = OpType.Table;
		req[1] = OpType.Indviduals;
		switch (tableName) {
		case Flowers: {
			req[2]= OpType.Flowers;
			break;
		}
		case FlowerBranches: {
			req[2] = OpType.FlowerBranches;
			break;
		}
		case FlowerPots:
		{
			req[2] = OpType.FlowerPots;
			break;
		}
		case Seedlings:
		{
			req[2] = OpType.Seedlings;
			break;
		}
		default:
			
			throw new IllegalArgumentException("Unexpected value: " + tableName);
		}
		return req;
	}
	 
	@SuppressWarnings("unchecked")
	private void messageFromServerHandler(ObservableList<Catalog> table_data, TableView<Catalog> tableType) {
		msg = (Message) ClientRunner.getClientRunner().obj; // from server
		table_data = (ObservableList<Catalog>) FXCollections.observableArrayList(convertObjectToList(msg.getMsg()));
		tableType.setItems(table_data);
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
	 * Adding to cart the items
	 * @param event
	 */
	   @FXML
	    void addToCart(ActionEvent event) {
		   
		   String name=null,temp = null;
	    	
	    	if(!(SeedlingsTable.getSelectionModel().getSelectedItem()== null))
	    	{
	    	 System.out.println("SeedlingsTable");
	    	 int i=SeedlingsTable.getSelectionModel().getSelectedIndex();
		    	 name = SeedlingsTable.getSelectionModel().getSelectedItem().getName();
		    	 CustomerPortalController_.add(name,SeedlingsTable);
		    	 SeedlingsTable.getSelectionModel().clearSelection();
		    	 
	    	}
	    	if(!(FlowersTable.getSelectionModel().getSelectedItem()== null))
	    	{
	    	 System.out.println("FlowersTable");
		    	 name = FlowersTable.getSelectionModel().getSelectedItem().getName();
		    	 CustomerPortalController_.add(name,FlowersTable);
		    	 FlowersTable.getSelectionModel().clearSelection();
		    	 
	    	}
	    	if(!(FlowerPotsTable.getSelectionModel().getSelectedItem()== null))
	    	{
	    		System.out.println("FlowerPotsTable");
		    	 name = FlowerPotsTable.getSelectionModel().getSelectedItem().getName();
		    	 CustomerPortalController_.add(name,FlowerPotsTable);
		    	 FlowerPotsTable.getSelectionModel().clearSelection();
		    	
	    	}
	    	
	    		if(!(FlowerBranchesTable.getSelectionModel().getSelectedItem() == null))
	    	{
	    			System.out.println("wedding");
		    	 name = FlowerBranchesTable.getSelectionModel().getSelectedItem().getName();
		    	 CustomerPortalController_.add(name,FlowerBranchesTable);
		    	 FlowerBranchesTable.getSelectionModel().clearSelection();
		    	 
	    	}
	    }
	   

	 public void add(String name) {
		  if(name != null) {
				FXMLLoader g = new FXMLLoader(getClass().getResource("../../../view/UserView/CustomerView/cartItem.fxml"));
				VboxList= CustomerPortalController_.getCart_List();
				if(CustomerPortalController_.getCart_List()==null)
					System.out.println("null");
				else {
					System.out.println("not null");
				}
		    	try {
		    		
				paneList.add(index, (Pane) (g.load()));
		    		
				BouquteListController controller =  (BouquteListController)g.getController(); 
				if (itemsList.containsKey(name)) {//if already selected
					itemsList.get(name).IncSpinner();
					paneList.remove(index);
		
				} else { // else add new one to list
					((BouquteListController) g.getController()).setEvent(this);
					((BouquteListController) g.getController()).index=index;
					itemsList.put(name, (BouquteListController) g.getController());
					
					controller.setBouquteName(name);
					VboxList.getChildren().add(index, paneList.get(index));
					System.out.println(VboxList.getChildren());
					index++;
				}
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			  }
	 }
	 /**
	  * set CustomerPortalController event
	  * @param event
	  */
		 public void setEvent(CustomerPortalController event) {
		    	this.CustomerPortalController_ = event;
		    }
		 /**
		  * loading FlowerBranches to table in javafx
		  */ 
	 void LoadFlowerBranchesTable() {
		NameFlowerBranches.setCellValueFactory(new PropertyValueFactory<>("Name"));
		DetailsFlowerBranches.setCellValueFactory(new PropertyValueFactory<>("Details"));
    	ColorFlowerBranches.setCellValueFactory(new PropertyValueFactory<>("Color"));
    	PriceFlowerBranches.setCellValueFactory(new PropertyValueFactory<>("Price"));
	    
	    FlowerBranchesTable.setVisible(true);
	    FlowersTable.setVisible(false);
	    FlowerPotsTable.setVisible(false);
	    SeedlingsTable.setVisible(false);
		
	}

	 /**
      * loading Flower to table in javafx
      */
	private void LoadFlowersTable() {
		NameFlowers.setCellValueFactory(new PropertyValueFactory<>("Name"));
		DetailsFlowers.setCellValueFactory(new PropertyValueFactory<>("Details"));
		ColorFlowers.setCellValueFactory(new PropertyValueFactory<>("Color"));
    	PriceFlowers.setCellValueFactory(new PropertyValueFactory<>("Price"));
	    
	    FlowersTable.setVisible(true);
	    FlowerBranchesTable.setVisible(false);
	    FlowerPotsTable.setVisible(false);
	    SeedlingsTable.setVisible(false);
		
	}

	/**
	 * loading FlowerPots to table in javafx
	 */
	 void LoadFlowerPotsTable() {
		NameFlowerPots.setCellValueFactory(new PropertyValueFactory<>("Name"));
		DetailsFlowerPots.setCellValueFactory(new PropertyValueFactory<>("Details"));
		ColorFlowerPots.setCellValueFactory(new PropertyValueFactory<>("Color"));
    	PriceFlowerPots.setCellValueFactory(new PropertyValueFactory<>("Price"));
	    FlowerPotsTable.setVisible(true);
	    FlowerBranchesTable.setVisible(false);
	    FlowersTable.setVisible(false);
	    SeedlingsTable.setVisible(false);
		
	}

	 /**
		 * loading Seedlings to table in javafx
		 */
	 void  LoadSeedlingsTable() {
		NameSeedlings.setCellValueFactory(new PropertyValueFactory<>("Name"));
		DetailsSeedlings.setCellValueFactory(new PropertyValueFactory<>("Details"));
		ColorSeedlings.setCellValueFactory(new PropertyValueFactory<>("Color"));
    	PriceSeedlings.setCellValueFactory(new PropertyValueFactory<>("Price"));
	    SeedlingsTable.setVisible(true);
	    FlowerBranchesTable.setVisible(false);
	    FlowersTable.setVisible(false);
	    FlowerPotsTable.setVisible(false);
	}

}


	

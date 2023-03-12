
package controllers.users.MarketingWorker;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import clientManager.ClientRunner;
import controllers.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.DoubleStringConverter;
import entities.users.*;
import entities.Message;
import entities.enumE.OpType;
import entities.enumE.Catalog.BranchType;
import  entities.users.Catalog;
import entities.users.Sales;
import entities.users.MarketingWorker.MarketingWorker;
import entities.enumE.Catalog.*;

public class MarketingWorkerPortalController  implements Initializable{
	

	    @FXML
	    private Button UpdateCatalog;
	    @FXML
	    private Pane SalePane;

	    @FXML
	    private Pane CatalogPane;
	    @FXML
	    private Button ADD;

    @FXML
    private Label UserLabel;

    @FXML
    private Button Update;

    @FXML
    private Button SalesBtn;


    @FXML
    private TableView<Catalog> CatalogTable;

    @FXML
    private TableColumn<Catalog, String> Code;

    @FXML
    private TableColumn<Catalog, String> Name;

    @FXML
    private TableColumn<Catalog, String> Details;

    @FXML
    private TableColumn<Catalog, String> Category;

    @FXML
    private TableColumn<Catalog, String> Type;

    @FXML
    private TableColumn<Catalog, String> Occasions;

    @FXML
    private TableColumn<Catalog, String> Color;

    @FXML
    private TableColumn<Catalog,Double> Price;
    @FXML
    private TableColumn<Catalog, String>edit;
    @FXML
    private Button Add;
    @FXML
    private Label successfully;

    @FXML
    private Label Error;

    @FXML
    private Button Edit;

    @FXML
    private ChoiceBox<BranchType> BranchID;

    @FXML
    private Button UpdateSaleId;

    @FXML
    private TextField SaleID;

    @FXML
    private Button Save;
    @FXML
    private TableView<Sales> SalesTable;

    @FXML
    private TableColumn<Sales, String> BranchNameId;

    @FXML
    private TableColumn<Sales, String> SalesId;

    @FXML
    private TableColumn<Sales, String> ActivationID;

    @FXML
    private TableColumn<Sales, String> ActionId;


    ObservableList<BranchType> Branchlist = FXCollections.observableArrayList();
    ObservableList<Catalog>RowData=FXCollections.observableArrayList();
    private ObservableList<Catalog>CatalogData=FXCollections.observableArrayList();
    private ObservableList<Sales>SalesData=FXCollections.observableArrayList();
    ArrayList<String> updatelist=new ArrayList<String>();
    ArrayList<String> newupdatelist=new ArrayList<String>();
    Message msg;
    String selectBranch="All",NewOccasions,NewType,NewColor,NewCategory,NewDetails,NewName,NewPrice,code;
    int flag2=0;
    ObservableList<Type> Typelist = FXCollections.observableArrayList();
    ObservableList< Occasions>  Occasionslist = FXCollections.observableArrayList();
    ObservableList<Colors> Colorslist = FXCollections.observableArrayList();
    ObservableList<Category> Categorylist = FXCollections.observableArrayList();
	ArrayList<String> CategoryarrayList = new ArrayList<String>();
	ArrayList<String> TypearrayList = new ArrayList<String>();
	ArrayList<String> OccasionsarrayList = new ArrayList<String>();
	ArrayList<String> ColorsarrayList = new ArrayList<String>();
	OpType[] req2= new OpType[2];//request Type
	OpType[] req = new OpType[2];//request Type
	/**
	 * In the beginning, we will present all the catalog data ,client sends a new message to the server
	 * to show the Catalog table (from the Database) in the table view(in the MarketingWorkerUI),
	 * and update the name label in the ,MarkitingWorkerUI.
	 */
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MarketingWorker marketingWorker = (MarketingWorker)ClientRunner.getUser();
		 UserLabel.setText("Welcome "+marketingWorker.getFirstname()+ "!");//update label name
		 Branchlist.addAll(Catalog.getBranchArray());
	 	 BranchID.getItems().addAll(Branchlist);
	 	 LoadLists();
		SalePane.setVisible(false);
		successfully.setVisible(false);
		Error.setVisible(false);
		ShowCatalogTable();
		req2[0]=OpType.UpdateCatalog;
	}
	/**
	 * Load data to the Table(in the MarketingWorker UI)
	 */
	private void Load() {
	Code.setCellValueFactory(new PropertyValueFactory<>("Code"));
	Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
	Details.setCellValueFactory(new PropertyValueFactory<>("Details"));
	Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
	Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
	Occasions.setCellValueFactory(new PropertyValueFactory<>("Occasions"));
	Color.setCellValueFactory(new PropertyValueFactory<>("Color"));
	Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
	}
	/*
	 * to Update Catalog
	 */
	  @FXML
	void Click_catalog(ActionEvent event) {
		    CatalogPane.setVisible(true);
			SalePane.setVisible(false);
			successfully.setVisible(false);
			Error.setVisible(false);
			ShowCatalogTable();
	    }   
	  /**
	   * Catalog Table from DB 
	   */
	private void ShowCatalogTable() {
		req[0]=OpType.Table;
		req[1]=OpType.ViewCatalog ;
		ClientRunner.getClientRunner().SendReqToServer(new Message(req));// send request to server to catalog data
		msg = (Message) ClientRunner.getClientRunner().obj; //  message from server
		CatalogData = (ObservableList<Catalog>) FXCollections.observableArrayList(convertObjectToList(msg.getMsg()));
		CatalogTable.setItems(CatalogData);
		Load();
		addButtonToTable();
		
	}
	/**
	 * Add delete and edit button to CatalogTable
	 */
	private void addButtonToTable() {
	        Callback<TableColumn<Catalog,String>, TableCell<Catalog,String>> cellFactory = new Callback<TableColumn<Catalog,String>, TableCell<Catalog,String>>() {
	            @Override
	            public TableCell<Catalog,String> call(final TableColumn<Catalog,String> param) {
	            final TableCell<Catalog,String> cell = new TableCell<Catalog,String>() {
	                    private final Button editbutton = new Button("edit");
	                    private final Button deleteButton = new Button("delete");
	                    private final HBox pane = new HBox(editbutton, deleteButton);
	                    {
	                    	    editbutton.setOnAction((ActionEvent event) -> {
	                            Catalog data = getTableView().getItems().get(getIndex());
	                            updatelist.clear();
	                            updatelist.add(0,String.valueOf(data.getCode()));
	                        	updatelist.add(1,data.getName());
	                        	updatelist.add(2,data.getDetails());
	                        	updatelist.add(3,data.getCategory());
	                        	updatelist.add(4,data.getType());
	                        	updatelist.add(5,data.getOccasions());
	                        	updatelist.add(6,data.getColor());
	                        	updatelist.add(7,Double.toString(data.getPrice()));
	                            System.out.println("selectedData: " + data);
	                            flag2=1;
	                            Edit();
	                        });
	                            deleteButton.setOnAction((ActionEvent event) -> {
	                            Catalog data = getTableView().getItems().get(getIndex());
	                            Delete(data);  
	                        });
	                    }
	                    @Override
	                    public void updateItem(String item, boolean empty) {
	                        super.updateItem(item, empty);
	                        setGraphic(empty ? null : pane);
	                    }
	                };
	                return cell;
	            }
	        };
	        edit.setCellFactory(cellFactory);
	}
	 /**
	   * Delete row from the table AND update in dataBase
	   */
	private  void Delete(Catalog data) { 
	    	   successfully.setVisible(false);
	    	   Error.setVisible(false);
	    	  
	    	    int itemCode=data.getCode();
	    		Object myObject = itemCode;
	        	req2[1]=OpType.Delete;
	    		ClientRunner.getClientRunner().SendReqToServer(new Message(req2,myObject));// send request to server
	    		Message Deletemsg = (Message) ClientRunner.getClientRunner().obj; // from server
	        	Boolean result = ((Boolean) Deletemsg.getMsg()).booleanValue();
	            ClientRunner.getClientRunner().obj = null;
	        	if(result)
	        	{
	        	    
	        		CatalogTable.getItems().removeAll(data);	
	        		successfully.setVisible(true);
	        	}
	        	else
	        	{
	        		Error.setVisible(true);
	        	}
	   		}
	  /**
	     * Edit the catalog Data, The table is edible now, Edit all cells without code is Automatic
	     *
	     * @param event
	     */
	    
    private  void Edit() {
    	    CatalogTable.setEditable(true);
	    	successfully.setVisible(false);
	    	Error.setVisible(false);
	        Name.setCellFactory(TextFieldTableCell.forTableColumn());
	  	    Details.setCellFactory(TextFieldTableCell.forTableColumn());
	  	    Category.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), FXCollections.observableArrayList(CategoryarrayList)));
	  	    Type.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), FXCollections.observableArrayList(TypearrayList)));
	  	    Occasions.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), FXCollections.observableArrayList(OccasionsarrayList)));
	  	    Color.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), FXCollections.observableArrayList(ColorsarrayList)));
	  	    Price.setCellFactory(TextFieldTableCell.forTableColumn( new DoubleStringConverter()));
	        Name.setOnEditCommit(new EventHandler<CellEditEvent<Catalog,String>>(){
	  	    @Override
	  	    public void handle(CellEditEvent<Catalog,String> event)
	  	   {
	  		  Catalog Catalog=event.getRowValue();
	  		  Catalog.setName(event.getNewValue());
	  		  NewName=event.getNewValue();
	  	   }
	         });
	       Details.setOnEditCommit(new EventHandler<CellEditEvent<Catalog,String>>(){
	  	   @Override
	  	   public void handle(CellEditEvent<Catalog,String> event)
	  	   {
	  		 Catalog Catalog=event.getRowValue();
	  		 Catalog.setDetails(event.getNewValue());
	  		 NewDetails=event.getNewValue();
	  	   }
	         });
	       Category.setOnEditCommit(new EventHandler<CellEditEvent<Catalog,String>>(){
	  	   @Override
	  	   public void handle(CellEditEvent<Catalog,String> event)
	  	   {
	  		  Catalog Catalog=event.getRowValue();
	  		  Catalog.setCategory(event.getNewValue());
	  		  NewCategory=event.getNewValue();
	  	   }
	         });
	       Type.setOnEditCommit(new EventHandler<CellEditEvent<Catalog,String>>(){
	  	   @Override
	  	   public void handle(CellEditEvent<Catalog,String> event)
	  	   {
	  		  Catalog Catalog=event.getRowValue();
	  		  Catalog.setType(event.getNewValue());
	  		  NewType=event.getNewValue();
	  	   }
	         });
	       Occasions.setOnEditCommit(new EventHandler<CellEditEvent<Catalog,String>>(){
	  	   @Override
	  	   public void handle(CellEditEvent<Catalog,String> event)
	  	   {
	  		  Catalog Catalog=event.getRowValue();
	  		  Catalog.setOccasions(event.getNewValue());
	  		  NewOccasions=event.getNewValue();
	  	   }
	         });
	       Color.setOnEditCommit(new EventHandler<CellEditEvent<Catalog,String>>(){
	  	   @Override
	  	   public void handle(CellEditEvent<Catalog,String> event)
	  	  {
	  		 Catalog Catalog=event.getRowValue();
	  		 Catalog.setColor(event.getNewValue());
	  		 NewColor=event.getOldValue();
	  	  }
	        });
	      Price.setOnEditCommit(new EventHandler<CellEditEvent<Catalog,Double>>(){
	  	  @Override
	  	  public void handle(CellEditEvent<Catalog,Double> event)
	  	  {
	  		Catalog Catalog=event.getRowValue();
	  		if(event.getNewValue()==null)
	  		{
	  			Catalog.setPrice(0.0);
	  		}
	  		
	  		Catalog.setPrice(event.getNewValue());	
	  		NewPrice=event.getNewValue().toString();
	  	  }
	        });
	  }
    /**
     * Function to add item in catalog
     * @param event
     */
    @FXML
    void Click_ADD(ActionEvent event) {

    	successfully.setVisible(false);
    	Error.setVisible(false);
    	Catalog newRow=new Catalog(0,null,null,null,null,null,null,0);
        CatalogData.add(newRow);
        CatalogTable.setItems(CatalogData);

	    flag2=2;
	    Edit();
    }

    /**
     * Save Edit the table (add or edit) , send request to server to update the database
     * @param event
     */
    @FXML
    void Click_Save(ActionEvent event) {
    	successfully.setVisible(false);
    	Error.setVisible(false);
    	 if(flag2==1)
         {
       	newupdatelist.add(0,updatelist.get(0));
    	newupdatelist.add(1,NewName);
    	newupdatelist.add(2,NewDetails);
    	newupdatelist.add(3,NewCategory);
    	newupdatelist.add(4,NewType);
    	newupdatelist.add(5,NewOccasions);
    	newupdatelist.add(6,NewColor);
    	newupdatelist.add(7,NewPrice);
    	for(int i=1;i<updatelist.size();i++)
    	{
    		if(newupdatelist.get(i)!=null)
    		{
    			updatelist.set(i, newupdatelist.get(i));
    		}	
        }
    	req2[1]=OpType.Edit;
        ClientRunner.getClientRunner().SendReqToServer(new Message(req2,(Object)updatelist));// send request to server
        Message Editmsg = (Message) ClientRunner.getClientRunner().obj; //  message from server
        Boolean result = ((Boolean)  Editmsg.getMsg()).booleanValue();
    	ClientRunner.getClientRunner().obj = null;
   	if(result)
   	{	
   		successfully.setVisible(true);
   		CatalogTable.setEditable(false);
   	
   	}
   	else
   	{
   		 Error.setVisible(true);
   	}
      flag2=0;
      updatelist.clear();
   	  newupdatelist.clear();
         }
        else if(flag2==2)

       	   {
        	
           	updatelist.add(0,NewName);
           	updatelist.add(1,NewDetails);
           	updatelist.add(2,NewCategory);
           	updatelist.add(3,NewType);
           	updatelist.add(4,NewOccasions);
           	updatelist.add(5,NewColor);
           	updatelist.add(6,NewPrice);
           	req2[1]=OpType.ADD;
       	ClientRunner.getClientRunner().SendReqToServer(new Message(req2,(Object)updatelist)); // send request to server
       	Message Addmsg = (Message) ClientRunner.getClientRunner().obj; //  message from server
       	Boolean result = ((Boolean)Addmsg.getMsg()).booleanValue();
        ClientRunner.getClientRunner().obj = null;
       	if(result)
       	{
       		successfully.setVisible(true);
       		CatalogTable.setEditable(false);
       	}
       	else
       		Error.setVisible(true);
      
        	flag2=0;
        	updatelist.clear();
        	CatalogTable.refresh();
       	 	CatalogTable.setEditable(false);
    }
    }
    /**
     * Adding promotions to the branches, 
     * the worker set the branch and sale value to update/adding promotions
     * @param event
     */
    @FXML
    void Click_Sales(ActionEvent event) 
    
    {
    	CatalogPane.setVisible(false);
		SalePane.setVisible(true);
    	successfully.setVisible(false);
    	Error.setVisible(false);
    	ShowSalesTable();
 		addButtonToSalesTable();
 		BranchID.getSelectionModel().selectFirst();
 		BranchID.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {selectBranch = newValue.toString();});	   
    }
    /**
     * View Sales Table from DB
     */
	public void  ShowSalesTable()
	{
		req[0]=OpType.Table;
		req[1]=OpType.ViewSales;
		ClientRunner.getClientRunner().SendReqToServer(new Message(req));// send request to server to catalog data
		msg = (Message) ClientRunner.getClientRunner().obj; //  message from server
		SalesData = (ObservableList<Sales>) FXCollections.observableArrayList(convertObjectToList(msg.getMsg()));
		SalesTable.setItems(SalesData);
		LoadSalesTable();
	}
	/**
	 * Load Sales Data
	 */
	private void LoadSalesTable() {
		BranchNameId.setCellValueFactory(new PropertyValueFactory<>("BranchName"));
		SalesId.setCellValueFactory(new PropertyValueFactory<>("Sales"));
		ActivationID.setCellValueFactory(new PropertyValueFactory<>("Activation"));
		
	}

    private void addButtonToSalesTable() {
		
		Callback<TableColumn<Sales,String>, TableCell<Sales,String>> cellFactory = new Callback<TableColumn<Sales,String>, TableCell<Sales,String>>() {
            @Override
            public TableCell<Sales,String> call(final TableColumn<Sales,String> param) {
            final TableCell<Sales,String> cell = new TableCell<Sales,String>() {
                    private final Button Activitybutton = new Button("Change Activtaion");
       
                  
                    {
                    	Activitybutton.setOnAction((ActionEvent event) -> {
                           Sales Sale = getTableView().getItems().get(getIndex());
                        
                           ShowSalesTable();
                           ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.SaleActivity,(Object)Sale.getBranchName()));// send request to server
                   		   Message SaleActivemsg = (Message) ClientRunner.getClientRunner().obj; // from server
                   		    
                   		Boolean result = ((Boolean) SaleActivemsg.getMsg()).booleanValue();
        	            ClientRunner.getClientRunner().obj = null;
        	        	if(result)
        	        	{
        	        		   ShowSalesTable();
                   		
        	        	}
                        });
                            
                    }
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : Activitybutton);
                    }
                };
                return cell;
            }
        };
        ActionId.setCellFactory(cellFactory);
}
	
    private void LoadLists() {
  	
	  Categorylist.removeAll(Categorylist);
	  Categorylist.addAll(Catalog.getCategoryArray());
	  for (int i = 0; i < Categorylist.size(); i++) {
	  	CategoryarrayList.add(Categorylist.get(i).toString());
	  }
	  Typelist.removeAll(Typelist);
	  Typelist.addAll(Catalog.getTypeArray());
	  for (int i = 0; i < Typelist.size(); i++) {
	  	TypearrayList.add(Typelist.get(i).toString());
	  }
	  Occasionslist.removeAll(Occasionslist);
	  Occasionslist.addAll(Catalog.getOccasionsArray());
	  for (int i = 0; i < Occasionslist.size(); i++) {
	  	OccasionsarrayList.add(Occasionslist.get(i).toString());
	  }
	  Colorslist.removeAll(Colorslist);
	  Colorslist.addAll(Catalog.getColorsArray2());
	  for (int i = 0; i < Colorslist.size(); i++) {
	  	ColorsarrayList.add(Colorslist.get(i).toString());
	  }
	  }
	  
    /**
     * Send request to server with branch name and sale to update the sale in dataBase.
     * @param event
     */
    @FXML
    void Click_UpdateSale(ActionEvent event)
    {
    updatelist.clear();
    successfully.setVisible(false);
	Error.setVisible(false);
	updatelist.add(0, selectBranch);
	updatelist.add(1, SaleID.getText());
	req2[1]=OpType.UpdateSale;
		ClientRunner.getClientRunner().SendReqToServer(new Message(req2,(Object)updatelist));// send request to server
	Message Salemsg = (Message) ClientRunner.getClientRunner().obj; // from server
    Boolean change= ((Boolean) Salemsg.getMsg()).booleanValue();
    if(change)
    {
    	 successfully.setVisible(true);
    	 ShowSalesTable();
 	     SaleID.clear();
    }else
    {
    	Error.setVisible(true);
    }
    ClientRunner.getClientRunner().obj = null;
}
	
	 
	            
	            

				
		
	/**
	 * this function to convert object to list
	 */
	List<?> convertObjectToList(Object obj) {
		List<?> list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			list = Arrays.asList((Object[]) obj);
		} else if (obj instanceof Collection) {
			list = new ArrayList<>((Collection<?>) obj);
		}
		return list;
	}
	    
	    /**
	     * User LogOut, send request to the server to logout the user and update the active status
	     * @param event
	     */
	        @FXML
	     void logOut_click(ActionEvent event) {
	        	LoginController.Logout();
	        }
	
}
	            

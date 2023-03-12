
package controllers.users.customer;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import com.sun.xml.internal.bind.v2.model.core.ID;

import clientManager.ClientRunner;
import clientManager.OpenWindow;
import controllers.LoginController;
import entities.users.Catalog;

import entities.users.customer.Customer;
import entities.Message;
import entities.Orders;
import entities.enumE.MessageType;
import entities.enumE.OpType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.UserView.UserUI;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
/**
 * 
 * this class the controller of the custumerPortalUI
 *
 */
public class CustomerPortalController implements Initializable
{		   @FXML
	   private VBox Cart_List;
public VBox getCart_List() {
	return Cart_List;
}

@FXML
private ScrollPane scrollPane;
/**
 * This class after the log in to the customer that have the catalog and the main page 
 * @return scrollPane
 */
public ScrollPane getScrollPane() {
	return scrollPane;
}
	   @FXML
	    private Label UserLabel;

	    @FXML
	    private Button category_btn;

	    @FXML
	    private Button individualBtn;

	    @FXML
	    private ImageView Cart;

	    @FXML
	    private MenuButton menuBtn;

	    @FXML
	    private MenuItem myOrders;
	   
	    @FXML
	    private MenuItem logoutBtn;
	    @FXML
	    private BorderPane categoryTab;
	    @FXML
	    private BorderPane individualTab;
	    @FXML
	    private Pane rootPane;
	    @FXML
	    private Button CreateBtn;
	    @FXML
	    private TitledPane messages_list;
	    @FXML
	    private MenuItem Account;
	    VBox VboxList;
	    @FXML
	    private TitledPane cartClick;
	    public TitledPane getcartClick() {
	    	return this.cartClick;
	    }
	    CategoryTabController  CategoryController;
	    IndividualTabController  IndividualController;
	    @FXML
	    private  VBox list_Message;
	    
	    Map<String, BouquteListController > Createmap=new HashMap  <String, BouquteListController >() ;
//	    private String path = "../../../view/UserView/CustomerView/";
	    private String path = "/view/UserView/CustomerView/";
	    int flag=0;
	    private ActionEvent CustomerPortalEvent;
	    FXMLLoader CategoryTabLoader;
	    FXMLLoader  IndividualTabLoader;
	    FXMLLoader MessageListController;
	    String username;
	 
	    private Message msg,msg1;
		private Object event;
		private ActionEvent event1;
	    private ArrayList<Orders> confirmation=new ArrayList<>();
	    private ArrayList<Orders> cancellation=new ArrayList<>();

		private int categoryFlag=0;

		private Scene scene;
		CreateYourBouquteController controller ;
		private Customer customer;
		Map<String, BouquteListController> CreatedBoq = new HashMap<String, BouquteListController>() ;
		private Map<String, BouquteListController > itemsList = new HashMap<String, BouquteListController>();
		private CreateYourBouquteController CreateYourBouquteController_;
		private int index=0;
		
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}

		private List<Pane> paneList =new ArrayList<Pane>();
		
		/**
		 * 
		 * @return CreatedBoq map there key is string and there value is BouquteListControllers
		 */
		 public Map<String, BouquteListController> getCreatedBoq() {
			
			return CreatedBoq;
		
		}
		 /**
		  * 
		  * @param createdBoq
		  */
		public void setCreatedBoq(Map<String, BouquteListController> createdBoq) {
			this.CreatedBoq = createdBoq;
		}
		/**
		 * 
		 * @return customer
		 */
		public Customer getCustomer() {
				return customer;
			}
		/**
		 * 
		 * @param set customer details
		 */
			public void setCustomer(Customer customer) {
				this.customer = customer;
			}

			
		    
			/**
		     * initialize function 
		     */
		    public void initialize(URL arg0, ResourceBundle arg1) {
		    	scrollPane = new ScrollPane();
		    	customer = (Customer)ClientRunner.getUser();
				UserLabel.setText("Welcome "+customer.getFirstname()+ "!");
		
			
			if(flag==0)
			{
		IndividualTabLoader =new FXMLLoader(getClass().getResource("/view/UserView/CustomerView/Individual_tab.fxml"));
		try {
			
			individualTab =(BorderPane) IndividualTabLoader.load();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		flag=1;
			}
			else
			{
				IndividualController =  IndividualTabLoader.getController();	
				IndividualController.setEvent(this);
				IndividualController.LoadSeedlingsTable(); // always first page to show

			}
			rootPane.getChildren().setAll(individualTab);
			try {
				 CategoryTabLoader = new FXMLLoader(getClass().getResource(path +"Category_tab.fxml"));
				 categoryTab = CategoryTabLoader.load();
				 CategoryTabController categoryController = CategoryTabLoader.getController();
			    	categoryController.setEvent(this);
				   	categoryController.LoadBirthdayTable(); // always first page to show
					rootPane.getChildren().setAll(categoryTab);
			
				    categoryTab.setVisible(true);
			    	categoryTab.setManaged(true);
			    	if(flag == 1) {
				    	individualTab.setVisible(false);
				    	individualTab.setManaged(false);
				    	}
				rootPane.getChildren().setAll(categoryTab);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		   
	    /**
         * open new window that have the orders of the customer and the status of them 
         * @param event
         */
	    @FXML
	    void myOrders_Click(ActionEvent event) {
	    	OpenWindow newWin = new OpenWindow(path+"MyOrders.fxml", "Your Orders - "+customer.getFirstname()+" "+customer.getLastname());
	    	MyOrdersController controller=(MyOrdersController)(newWin.getController());
	    	controller.setEvent(event);

	    }
	    /**
	     * open new window that have thw details of the account of the customer
	     * @param event
	     */
	    @FXML
	    void Click_Account (ActionEvent event) {
	    	OpenWindow newWin = new OpenWindow(path+"AccountDetails.fxml", "Your Account - "+customer.getFirstname()+" "+customer.getLastname());
	    	AccountController controller=(AccountController)(newWin.getController());
	    	
	    	controller.setCustomer(customer);
	    	controller.setEvent(event);
	    }
/**
 * Method: responsible for selecting the category button
 * 
 */
	    @FXML
	    void selectCategory(ActionEvent event) {
	    	//System.out.println("scrollpane category "+scrollPane);
	    	CategoryTabController categoryController = CategoryTabLoader.getController();	
	    	
	    	if(categoryController == null) {
	    		System.out.println("null category");
	    	}
	    	
	    	categoryController.setEvent(this);
		   	categoryController.LoadBirthdayTable(); // always first page to show
		   
			rootPane.getChildren().setAll(categoryTab);
	
		    categoryTab.setVisible(true);
	    	categoryTab.setManaged(true);
	    	if(flag == 1) {
	    	individualTab.setVisible(false);
	    	individualTab.setManaged(false);
	    	}
			
	    }
	    /**
	     * open individual tab that have the tables of individual items in the catalog 
	     * @param event
	     */
		@FXML
		void select_individual(ActionEvent event) {
			if(flag==0)
			{
		IndividualTabLoader =new FXMLLoader(CustomerPortalController.class.getResource(path+"Individual_tab.fxml"));
		try {
			individualTab = IndividualTabLoader.load();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		flag=1;
			}
			else
			{
				IndividualController =  IndividualTabLoader.getController();	
				IndividualController.setEvent(this);
				IndividualController.LoadSeedlingsTable(); // always first page to show

			}
			rootPane.getChildren().setAll(individualTab);
			individualTab.setManaged(true);
			individualTab.setVisible(true);

			categoryTab.setVisible(false);
			categoryTab.setManaged(false);

		}
		/**
		 * get  BouquteListController to delete 
		 * @param BL
		 */
		 
		public void BouquteDelete(BouquteListController BL) {
			CreateYourBouquteController_.Delete2(BL);
		}
		/**
		 * create the bouquet 
		 * @param event
		 */
	    @FXML
	    void SelectCreate(ActionEvent event) {
	    	OpenWindow newWin = new OpenWindow(event,path+"CreateYourBouqute.fxml", "Create Your Bouqute - "+ customer.getFirstname()+" "+customer.getLastname());
			CreateYourBouquteController_ =  (CreateYourBouquteController)(newWin.getController());
			CreateYourBouquteController_.setEvent(event);
			CreateYourBouquteController_.setReference(this);	    	
			System.out.println("after"+Cart_List.getChildren());
	    }
	    /**
		  *  A function that deletes an item from the cartList.
		  * @param c : receives BouquteListController
		  */
		 	public void Delete(BouquteListController c) {
		 		// in case already exists but value is 1
		 		
		 		if (itemsList.containsKey(c.getBouquteName()) && c.getSpinner().getValue().intValue() == 1) {
		 			int i;
		 			i = itemsList.get(c.getBouquteName()).index;
		 			for (BouquteListController controller : itemsList.values()) {
		 				if (controller.index > i) {
		 					controller.index--;
		 				}
		 			}
		 			if(c.isCreatedBouqute) {
		 				int counter =0;
		 				int id = c.bouquteID;
		 				while(counter<1000) {
		 					deleteCompactedBoq(id,i);
		 					counter++;
		 				}
		 	
		 			}
		 			VboxList.getChildren().remove(paneList.get(i));
		 			paneList.remove(i);
		 			index--;
		 			itemsList.remove(c.getBouquteName());
		 			
		 		}

		 		else if (itemsList.containsKey(c.getBouquteName())) {// if already exists
		 			itemsList.get(c.getBouquteName()).DecSpinner();// decrease spinner
		 		}
		 		

		 	}
		 	/**
		     * A function that adds an item to the cart
		     * @param name: name of the item in the cart
		     * @param table: Reference of source of table type that being added to the cart
		     */
			public void add(String name, TableView<?> table) {
				if (name != null) {
					FXMLLoader g = new FXMLLoader(
							getClass().getResource(path+"cartItem.fxml"));
					VboxList = Cart_List;
					
					try {
						paneList.add(index, (Pane) (g.load()));
						BouquteListController controller = (BouquteListController) g.getController();
						if (itemsList.containsKey(name)) {// if already selected
							itemsList.get(name).IncSpinner();
							paneList.remove(index);

						} else { // else add new one to list	
							controller.setEvent(this);
							controller.index = index;
						
		
							
							if (!(table.getId().equals("CatalogTable"))) {
								controller.setCatalog((Catalog) table.getSelectionModel().getSelectedItem());
								
							}
							if (table.getId().equals("CatalogTable")) {
							controller.isCreatedBouqute=true;
							controller.isFather=true;
							controller.bouquteID = CreateYourBouquteController_.bouquteID;
							}	
							itemsList.put(name, controller);
							controller.setBouquteName(name);
							VboxList.getChildren().add(index, paneList.get(index));
							index++;

						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	    
			public void deleteCompactedBoq(int id, int i) {
				Iterator<Entry<String, BouquteListController>>  it=       itemsList.entrySet().iterator();
 				Entry<String, BouquteListController> prev = null;
 				Entry<String, BouquteListController> current= null;
 		
 				if (it.hasNext())
 				{
 					prev = it.next();
 				
 				while(it.hasNext()) {
 		

 					if(id  == prev.getValue().bouquteID ) {
 						
 						 current = it.next();
 						 if( current !=null  && !current.getValue().isFather){
 						System.out.println("removed" +current.getKey());
 						 it.remove();
 						 prev = current;
 						//itemsList.remove(item.getKey());
 						 }
 					}
 					
 					
// 					System.out.println("loop");
// 					if( current !=null && current.getValue().isFather  ) {
// 						System.out.println("index" + i);
// 						VboxList.getChildren().remove(paneList.get(i));
// 			 			paneList.remove(i);
// 			 			index--;
// 			 			it.remove();
// 			 			//itemsList.remove(c.getBouquteName());
// 					}
 			
 			}
 				}
			}
	    /**
		  * 
		  * @param combine the pane v to Vbox
		  */
	    public void combine(Pane v) {
	    	Cart_List.getChildren().add(v);
	    
	    }
	    /**
	     * 
	     * @param combine the data of item in itemsList
	     */
	    public void combineData(Map<String, BouquteListController> m)  {
	    	itemsList.putAll(m);
	    }
	    /**
	     * 
	     * @param event
	     */
	    public void setEvent(ActionEvent event) {
			this.event=event;
		}
	    /**
	     * when clicked on message buttom then sent to server request to check if there is 
	     * orders confirmed or confirmed cancellation by branch manager 
	     * @param event
	     */
	    @SuppressWarnings("unchecked")
		@FXML
	    void clicked_messages(MouseEvent event)
	    {
	    
		    	list_Message.getChildren().clear();
		    	ClientRunner.getClientRunner().SendReqToServer(new Message(OpType.Messages,ClientRunner.getUser()));// send request to server
	    	   msg = (Message) ClientRunner.getClientRunner().obj; // from server
	    	   if(msg.getMsg()!=null)
		       {
	    		   ArrayList<String> CustomerMessages=new ArrayList<String>();
	    		   CustomerMessages=(ArrayList<String>)msg.getMsg();
	    		   
	    		   for(String s:CustomerMessages)
	    		   {
	    			   System.out.println(s);
	    			   list_Message.getChildren().add(new Label(s));
	    		   }
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
	     *  clear function that clear cart list
	     */
	    public void  Clear()
	    {
	    	itemsList.clear();
	    	Cart_List.getChildren().clear();
	    }
		/**
		 * this function when clicked "click_checkout" button then opened new window that have the details of the order
		 *
		 * @param event
		 */
			@FXML
			 void Click_CheckOut(ActionEvent event)
			  {
				

				OpenWindow newWin2 = new OpenWindow(event,path+"DetailsAndConfirmPayment.fxml", "Your Order Details  - "+ customer.getFirstname()+" "+customer.getLastname());
				newWin2.hideWindow();
				DetailsController DetailsController_=(DetailsController)(newWin2.getController());	
				DetailsController_.setCustomerController(this);
				DetailsController_.setCartList(this.Cart_List);
				DetailsController_.setEvent(event);
				DetailsController_.setDetails(itemsList);
				for (String item : itemsList.keySet()) {
					System.out.print(item+" ");
					
				}
			
			  }
			
             public Map<String, BouquteListController> getItemsList() {
				return itemsList;
			}
			public void setItemsList(Map<String, BouquteListController> itemsList) {
				this.itemsList = itemsList;
			}
			/**
			 * set scene function
			 * @param s
			 */
       public void setScene(Scene s) {
	       this.scene = s;
        }
       
       
       public void resetIndex() {
    	   this.index=0;
       }
       /**
	     * log out and return to login window
	     * @param event
	     */ 
   @FXML
   void logOut_click(ActionEvent event) {
   	LoginController.Logout();
   		
   }
	}

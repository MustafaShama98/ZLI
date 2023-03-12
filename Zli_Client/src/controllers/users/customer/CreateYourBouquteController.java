package controllers.users.customer;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import clientManager.ClientRunner;
import clientManager.OpenWindow;
import entities.CustomBouqute;
import entities.Message;

import entities.enumE.OpType;
import entities.enumE.Catalog.Colors;
import entities.users.Catalog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreateYourBouquteController implements Initializable {
	@FXML
	private Label boq_name;
	 @FXML
	 private TitledPane titlePane;
    @FXML
    private ChoiceBox<Colors> ChooseColor;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button cart;
    @FXML
    private Button backBtn;
    @FXML
    private Pane rootPane;
    @FXML
    private Button AddtoCart;
    @FXML
    private VBox VboxList;
    @FXML
    private Button AddtoBouqute;
    @FXML
    private Label Select;
    @FXML
    private Button DoneBtn;
    @FXML
    private TextField custom_Bouqute_Name;
    @FXML
    private Pane centerPane;

    @FXML
    private BorderPane borderPane_Main_Center;
    @FXML
    private TableView<Catalog> CatalogTable;
    @FXML
    private TableColumn<Catalog,String> CodeID;
    @FXML
    private TableColumn<Catalog,String> CategoryID;
    @FXML
    private TableColumn<Catalog,String> NameID;
    @FXML
    private TableColumn<Catalog, String> ColorID;
    @FXML
    private TableColumn<Catalog, String> OccasionsID;
    @FXML
    private TableColumn<Catalog, String> TypeID;
    @FXML
    private TableColumn<Catalog,Double> PriceID;
    @FXML
    private TableColumn<Catalog, String> DetailsID;
    @FXML
    private TextField MinID;
    @FXML
    private TextField MaxID;
    @FXML
    private Label NotFound;
    String Customname;
   
    @FXML
    private VBox cart_vbox;
    private ObservableList<Catalog>CatalogData=FXCollections.observableArrayList();
    private ObservableList<Catalog>Data= FXCollections.observableArrayList();
    private String path = "/view/UserView/CustomerView/";
    List<Pane> paneList;
    private int index=0;
    private ActionEvent event;
    private Message msg;
    public int bouquteID;
    ObservableList<Catalog> CatalogList;
    private Map<String, BouquteListController > BouqueList;
    private Map<String, Map<String, BouquteListController> > createdBouqueList;
    /**
     * 
     * @return BouqueList map 
     */
    public Map<String, BouquteListController> getBouqueList() {
		return BouqueList;
	}
    private Map<Catalog,Integer> Bouqute;
    
	ArrayList<String> tmp;
    String name;
    int flag=0;
    
    static String selectedColor="All";
	Double Max=0.0,Min=0.0;
	ObservableList<Colors> list = FXCollections.observableArrayList();
	FXMLLoader d;
	BouquteListController b;
	private CustomerPortalController CustomerPortalController_;
	Map<String, BouquteListController> CreatedBoq = new HashMap<String, BouquteListController>() ;
	
	/**
	 * 
	 * @return CreatedBoq
	 */
	public Map<String,BouquteListController> getCreatedBoq() {
		return CreatedBoq;
	}
	/**
	 * 
	 * @param createdBoq
	 */
	public void setCreatedBoq(Map<String, BouquteListController> createdBoq) {
		this.CreatedBoq = createdBoq;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Bouqute = new HashMap<Catalog,Integer>();
		createdBouqueList = new HashMap<String, Map<String, BouquteListController> >();
		
		BouqueList = new HashMap<String, BouquteListController>();
		 tmp=new ArrayList<String>();
		    paneList = new ArrayList<Pane>(50);
		    OpType[] req = new OpType[2];
			req[0]=	OpType.Table;
			req[1]=OpType.ViewCatalog;
			ClientRunner.getClientRunner().SendReqToServer(new Message(req));// send request to server
			msg = (Message) ClientRunner.getClientRunner().obj; // from server
			CatalogData = (ObservableList<Catalog>) FXCollections.observableArrayList(convertObjectToList(msg.getMsg()));
			CatalogTable.setItems(CatalogData);
			Load();
		
			ClientRunner.getClientRunner().obj = null;
			
			list.removeAll(list);
			list.addAll(Catalog.getColorsArray());
			ChooseColor.getItems().addAll(list);

			ChooseColor.getSelectionModel().selectFirst();
			ChooseColor.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
				selectedColor = newValue.toString();
		});		 
			
		 
		
			/**
			 * load data to table 
			 */	
	}
	private void Load() {
		CodeID.setCellValueFactory(new PropertyValueFactory<>("Code"));
		NameID.setCellValueFactory(new PropertyValueFactory<>("Name"));
		DetailsID.setCellValueFactory(new PropertyValueFactory<>("Details"));
		CategoryID.setCellValueFactory(new PropertyValueFactory<>("Category"));
		TypeID.setCellValueFactory(new PropertyValueFactory<>("Type"));
		OccasionsID.setCellValueFactory(new PropertyValueFactory<>("Occasions"));
		ColorID.setCellValueFactory(new PropertyValueFactory<>("Color"));
    	PriceID.setCellValueFactory(new PropertyValueFactory<>("Price"));
    	
	   
		
	}
	/**
     * when clicked back go to the previous window
     * @param event
     */
    @FXML
    void Click_Back(ActionEvent event) {
    	selectedColor=null;
     ((Node)(event.getSource())).getScene().getWindow().hide();
      Stage s = (Stage) (((Node)this.event.getSource()).getScene().getWindow());
      s.show();
    }
    /**
     * clicked done to show the catalog by price range or color or both
     * @param event
     */
    @FXML
    void ClickDone(ActionEvent event) {
    	Data.clear();
    	NotFound.setVisible(false);
  if(!(MinID.getText().trim().isEmpty() )&& !(MaxID.getText().trim().isEmpty()))  
  {
	  Min=Double.parseDouble(MinID.getText());
	  Max=Double.parseDouble(MaxID.getText());
  }
  for(int i=0;i<CatalogData.size();i++)
  {
	
  	if((CatalogData.get(i).getColor().equals(selectedColor)) && (CatalogData.get(i).getPrice())>=Min && (CatalogData.get(i).getPrice())<=Max )
  	{
  		
  		Data.add(new Catalog (CatalogData.get(i).getCode(),CatalogData.get(i).getName(),CatalogData.get(i).getDetails(),CatalogData.get(i).getCategory(),CatalogData.get(i).getType(),CatalogData.get(i).getOccasions(),CatalogData.get(i).getColor(),CatalogData.get(i).getPrice()));
  	}
  	else if(Min==0.0 && Max==0.0 && (CatalogData.get(i).getColor().equals(selectedColor)) )
  	{
  		
  		Data.add(new Catalog (CatalogData.get(i).getCode(),CatalogData.get(i).getName(),CatalogData.get(i).getDetails(),CatalogData.get(i).getCategory(),CatalogData.get(i).getType(),CatalogData.get(i).getOccasions(),CatalogData.get(i).getColor(),CatalogData.get(i).getPrice()));
  	}
  	
  	else if((CatalogData.get(i).getPrice())>=Min&& (CatalogData.get(i).getPrice())<=Max &&selectedColor.equals("All"))
  	{
  		Data.add(new Catalog (CatalogData.get(i).getCode(),CatalogData.get(i).getName(),CatalogData.get(i).getDetails(),CatalogData.get(i).getCategory(),CatalogData.get(i).getType(),CatalogData.get(i).getOccasions(),CatalogData.get(i).getColor(),CatalogData.get(i).getPrice()));	 
     }
  	
  }
  System.out.println(Data);

 if(!Data.isEmpty())
 {
	
	CatalogTable.setItems(Data);
	Load();
 }
 else if (Data.isEmpty() && (selectedColor.equals("All")))
 {
	  
	    CatalogTable.setItems(CatalogData);
		Load();
 }
 else
 {
	    NotFound.setVisible(true);
	   
 }

 
MinID.clear();
MaxID.clear();
Min=0.0;
Max=0.0;

  }

    /**
     * 
     * @return Catalog Table
     */
public TableView<Catalog> getCatalogTable() {
		return CatalogTable;
	}
/**
 * 
 * @param Delete From the list
 */
    public void Delete2(BouquteListController c) {
    	
    	 //in case already exists but value is 1
    	if (BouqueList.containsKey(c.getBouquteName()) && c.getSpinner().getValue().intValue() == 1)
    	{
    	System.out.println(b.index);
    	int i = (BouqueList.get(c.getBouquteName())).index;
		VboxList.getChildren().remove(paneList.get(i));
		paneList.remove(i);
		index--;
		for (BouquteListController controller : BouqueList.values()) {
			if (controller.index > i) {
				controller.index--;
			}
		}
		BouqueList.remove(c.getBouquteName());
		

    	} else if(BouqueList.containsKey(c.getBouquteName())) {//if already exists
    		BouqueList.get(c.getBouquteName()).DecSpinner();//decrease spinner	
    	}

    	
    }
    /**
     * add item/bouquet to your bouquet
     * @param event
     */
	@FXML
	void ClicktoBouqute(ActionEvent event) {
		int count=0;
		d = new FXMLLoader(getClass().getResource(path + "cartItem.fxml"));
		

		try {
			flag = 0;
			Select.setVisible(false);
			CatalogList=CatalogTable.getSelectionModel().getSelectedItems();
			name= CatalogTable.getSelectionModel().getSelectedItem().getName();
             

			if (CatalogList.isEmpty()) {
				Select.setVisible(true);
			}

			else {
				
				
				paneList.add(index, (Pane) (d.load()));
				b = (BouquteListController) d.getController();
				if (BouqueList.containsKey(name)) {//if already selected
					BouqueList.get(name).IncSpinner();
				    count=BouqueList.get(name).getCount();
					
					paneList.remove(index);
				} else {
					// else add new one to list
					//Bouqute.put(CatalogTable.getSelectionModel().getSelectedItem(), 1);
					((BouquteListController) d.getController()).setEvent(this);
					((BouquteListController) d.getController()).setCatalog(CatalogTable.getSelectionModel().getSelectedItem());	
					((BouquteListController) d.getController()).index=index;
					
					((BouquteListController) d.getController()).bouquteID =this.bouquteID;
					((BouquteListController) d.getController()).isCreatedBouqute=true;
					BouqueList.put(name, (BouquteListController) d.getController());
					b.setBouquteName(name);
					VboxList.getChildren().add(index, paneList.get(index));
					index++;
				}
				
			}
			//CatalogTable.getSelectionModel().clearSelection();
		} catch (IOException e) {

			e.printStackTrace();
		}
 
	}
	/**
	 * add your bouquet to the cart
	 * @param event
	 */
    @FXML
    void ClicktoCart(ActionEvent event) {
    	Customname = custom_Bouqute_Name.getText();
    if(custom_Bouqute_Name.getText().isEmpty()) {
    	boq_name.setVisible(true);
    }else {
    	//make new map created bouqute under 1 name
    	boq_name.setVisible(false);
    	custom_Bouqute_Name.clear();
    	createdBouqueList.put(Customname, BouqueList);
    	CustomerPortalController_.combineData(BouqueList);
    	System.out.println(CatalogTable.getId());
    	CustomerPortalController_.add(Customname,CatalogTable);
     	
    	VboxList.getChildren().clear();
    	index=0;
    	bouquteID++;
    }
    }


	public VBox getVboxList() {
		return VboxList;
	}
	public List<Pane> getPaneList() {
		return paneList;
	}
	public int getIndex() {
		return index;
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
 * 
 * @param event : pass reference for previous window
 */
	public void setEvent(ActionEvent event) {
		this.event=event;
	}
	 public void setReference(CustomerPortalController event) {
	    	this.CustomerPortalController_ = event;
	    }
	
}

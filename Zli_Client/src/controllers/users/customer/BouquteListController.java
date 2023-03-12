package controllers.users.customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.users.Catalog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class BouquteListController implements Initializable {
	Catalog item;
	
	SpinnerValueFactory<Integer> valueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
    private CreateYourBouquteController controller;
	private CustomerPortalController controller2;
	private CategoryTabController Categorycontroller;
	private IndividualTabController Individualcontroller;
	private CreateYourBouquteController CreateYourBouquteController_;
	private CustomerPortalController CustomerPortalController_; 
	int bouquteID=0;
	boolean isCreatedBouqute=false;
	boolean isFather = false;
	@FXML
    private Spinner<Integer> spinner;
    @FXML
    private Button Delete;
   
    @FXML
    private Label bouquteName;
    private int count; 
    public int index;
    /**
     * initialize function   
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		valueFactory.setValue(1);
		spinner.setValueFactory(valueFactory);
		count = spinner.getValue();
		
	}
	/**
	 * set catalog
	 * @param c
	 */
	public void setCatalog(Catalog c) {
		item = c;
	}
	/**
	 * 
	 * @return item(catalog)
	 */
	public Catalog getCatalog() {
		return item;
	}
	/**
	 * 
	 * @return string that have the details of the order
	 */
	public String ViewDetails() {
		count=spinner.getValue();
		String str="Item Name:" +" "+item.getName() +" "+"Price: "+" "+ item.getPrice() + " "+"Quantity: "+" "+count +" ";
		return str;
	}
	/**
	 * 
	 * @return price of order
	 */
	public Double OrderPrice() {
		count=spinner.getValue();
		Double price= item.getPrice()*count;
		return price;
	}
	
    /**
     * 
     * @return count of items
     */
    public int getCount() {
    	return count;
    }
   
   
/**
 *  Calling CreateYourBouqueController delete function by 
 *  passing this instance to identify it. 
 * @param event
 */
    @FXML
    void ClickDelete(ActionEvent event) {
    System.out.println("delete");
    	if(CustomerPortalController_ != null) {
    		System.out.println("customer");	
    		CustomerPortalController_.Delete(this);
    	}
    
    	if(CreateYourBouquteController_!=null)
    	{
    		System.out.println("boq");
    		CreateYourBouquteController_.Delete2(this);
    	}
}
    
    /**
     * 
     * @param name
     */
    public void setBouquteName(String name) {
    this.bouquteName.setText(name);
    }
    
	public String getBouquteName() {
		return bouquteName.getText();
	}
	
	/**
	 * 
	 * @return spinner (the amount of this item/bouquet)
	 */
	public Spinner<Integer> getSpinner() {
		return spinner;
	}
	/**
	 * increase spinner
	 */
	public void IncSpinner() {
		spinner.increment();
		count = spinner.getValue();
	}
	/**
	 * Decrease spinner
	 */
	public void DecSpinner() {
		spinner.decrement();
		count = spinner.getValue();
	}
	/**
	 * 
	 * @param num
	 */
	public void setSpinner(int num) {
		
		valueFactory.setValue(num);
		spinner.setValueFactory(valueFactory);
		count = spinner.getValue();
	}

	/**
	 * 
	 * @param event : pass reference for previous window
	 */
		public void setEvent (CustomerPortalController event) {
			this.CustomerPortalController_ = event;
		}
		public void setEvent(CategoryTabController c) {
			this.Categorycontroller=c;
		}
		public void setEvent(IndividualTabController c) {
			this.Individualcontroller=c;
		}
		public void setEvent(CreateYourBouquteController c) {
			this.CreateYourBouquteController_=c;
		}
}

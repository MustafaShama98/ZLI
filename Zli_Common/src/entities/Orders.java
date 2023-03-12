package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Orders implements Serializable {
	private static final long serialVersionUID = -1767892544856045696L;
	private int OrderNumber;
	private String Details;
	private String OrderDate;
	private String ReceivedDate;
	private String Activity;
	private String Status;
	private String SupplyType;
	private double TotalPrice;
	
	
	// this constructor for the branch manager when he wants to view the orders.
			public Orders(int orderNumber,String details,double totalPrice,String activity ,String status) {
				super();
				this.OrderNumber = orderNumber;
				this.Details = details;
				this.Activity = activity;
				this.Status = status;
				this.TotalPrice = totalPrice;
				
			}///// 
	public Orders(int orderNumber, String details,double totalPrice,String activity ,String status,String OrderDate,String ReceivedDate,String SupplyType ) {
		super();
		this.OrderNumber = orderNumber;
		this.Details = details;
		this.OrderDate = OrderDate;
		this.ReceivedDate=ReceivedDate;
	    this.SupplyType=SupplyType;
		this.Activity = activity;
		this.Status = status;
		this.TotalPrice = totalPrice;
	}
	public Orders(int orderNumber)
	{
		super();
		this.OrderNumber = orderNumber;
	}
	
	public String getReceivedDate() {
		return ReceivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.ReceivedDate = receivedDate;
	}
	public int getOrderNumber() {
		return OrderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.OrderNumber = orderNumber;
	}
	public double getTotalPrice() {
		return TotalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.TotalPrice = totalPrice;
	}
	public String getDetails() {
		return Details;
	}
	public String getSupplyType() {
		return SupplyType;
	}

	public void setSupplyType(String supplyType) {
		this.SupplyType = supplyType;
	}

	public void setDetails(String details) {
		this.Details = details;
	}

	public String getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(String OrderDate) {
		this.OrderDate = OrderDate;
	}
	
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		this.Status = status;
	}
	public String getActivity() {
		return Activity;
	}
	public void setActivity(String activity) {
		this.Activity = activity;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	}

package entities.users;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
/**
 * This class have the Survey data (The 6 Questions,Survey start date,Survey end date,Start duration date,End duration date,Branches,Product)
 * @author Carol
 *
 */
public class Survey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3525466546518905877L;
	
	private String[] Questions;
	private LocalDate StartDate;
	private LocalDate EndDate;
	private LocalDate StartD;
	private LocalDate EndD;
	private ArrayList<String> Branches;
	private String Product;
	
	public Survey(String[]questions,LocalDate startDate,LocalDate endDate,LocalDate startD,LocalDate endD,ArrayList<String> Branches,String product) {
		this.Questions=questions;
		this.StartDate=startDate;
		this.EndDate=endDate;
		this.StartD=startD;
		this.EndD=endD;
		this.Branches=Branches;
		this.Product=product;
	}
	
	public String[] getQuestions() {
		return Questions;
	}
	
	public LocalDate getStartDate() {
		return StartDate;
	}
	
	public LocalDate getEndDate() {
		return EndDate;
	}
	
	public LocalDate getStartD() {
		return StartD;
	}
	
	public LocalDate getEndD() {
		return EndD;
	}
	
	public ArrayList<String> getBranches() {
		return Branches;
	}
	
	public String getProduct() {
	return Product;
	}
	
	/////////////////////////
	
	public void setQuestions(String[]questions) {
		this.Questions=questions;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.StartDate=startDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.EndDate=endDate;
	}
	
	public void setStartD(LocalDate startD) {
		this.StartD=startD;
	}
	
	public void setEndD(LocalDate endD) {
		this.EndD=endD;
	}
	
	public void setBranches(ArrayList<String> branches ) {
		this.Branches=branches;
	}
	
	public void setProduct(String product) {
	this.Product=product;
	}
}

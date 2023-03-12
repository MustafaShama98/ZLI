package entities.users;

import java.io.Serializable;
import java.util.ArrayList;

import entities.enumE.Catalog.BranchType;
import entities.enumE.Catalog.Category;
import entities.enumE.Catalog.Colors;

import entities.enumE.Catalog.Occasions;
import entities.enumE.Catalog.Type;
public class Catalog implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7083200625764363962L;
	private double price;
    private String Details;
    private String Color;
    private String Name;
    private String Type1;
    private String category;
    private String Occasions1;
    private int  Code;
   private int amount;
   
   public int getAmount() {
	return amount;
}


public void setAmount(int amount) {
	this.amount = amount;
}

ArrayList<String> Row=new ArrayList<>();
   
//   public  ArrayList<String> GetRow (String Code,String Name,String Details,String Category, String Type1,String Occasions1,String Color, double price)
//   {
//	   Row.add(Code);
//	   Row.add(Name);
//	   Row.add(Details);
//	   Row.add(Category);
//	   Row.add(Type1);
//	   Row.add(Occasions1);
//	   Row.add(Color);
//	   Row.add(Double.toString(price));
//	  
//	return Row;
//	   
//   }
//    
//	


	public Catalog (String Name,String Details,String Color, String Type1, double price)
    {
    	
    	this.Name=Name;
    	this.Details=Details;
    	this.Color=Color;
    	this.Type1=Type1;
    	this.price=price;
    	
    }
	
	
	public Catalog(int Code,String Name,String Details,String Category, String Type1,String Occasions1,String Color, double price)
    {
		this.Code=Code;
    	this.category=Category;
    	this.Name=Name;
    	this.Details=Details;
    	this.Color=Color;
    	this.Type1=Type1;
    	this.Occasions1=Occasions1;
    	this.price=price;
    }
	


    public Catalog(String Name, String Details,String Color, double price)
    {
    
    	this.Name=Name;
    	this.Details=Details;
    	this.Color=Color;
    	this.price=price;
    }

    public int  getCode() {
		return Code;
	}

	public void setCode(int code) {
		this.Code = code;
	}

	public String getOccasions() {
		return Occasions1;
	}

	public void setOccasions(String occasions1) {
		this.Occasions1 = occasions1;
	}


public String getCategory() {
		return category;
	}




	public void setCategory(String category) {
		this.category = category;
	}




public String getType() {
		return Type1;
	}

	public void setType(String type1) {
		this.Type1 = type1;
	}
	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDetails() {
		return Details;
	}

	public void setDetails(String Details) {
		this.Details = Details;
	}
	
	public static ArrayList<Colors> getColorsArray () {//////////////////////
		ArrayList<Colors> colorsList = new ArrayList<>();
		colorsList.add(Colors.All);
		colorsList.add(Colors.Blue);
		colorsList.add(Colors.Red);
		colorsList.add(Colors.Pink);
		colorsList.add(Colors.Red);
		colorsList.add(Colors.Yellow);
		colorsList.add(Colors.Green);
		colorsList.add(Colors.White);
		colorsList.add(Colors.Orange);
		colorsList.add(Colors.Multicolor);
		colorsList.add(Colors.Black);
		colorsList.add(Colors.Purple);
	
		return  colorsList;
	}
	
	
	public static ArrayList<Colors> getColorsArray2() {//////////////////////
		ArrayList<Colors> colorsList = new ArrayList<>();
	
		colorsList.add(Colors.Blue);
		colorsList.add(Colors.Red);
		colorsList.add(Colors.Pink);
		colorsList.add(Colors.Red);
		colorsList.add(Colors.Yellow);
		colorsList.add(Colors.Green);
		colorsList.add(Colors.White);
		colorsList.add(Colors.Orange);
		colorsList.add(Colors.Multicolor);
		colorsList.add(Colors.Black);
		colorsList.add(Colors.Purple);
	
		return  colorsList;
	}
	
	 
public static ArrayList<Category> getCategoryArray () {//////////////////////
			ArrayList<Category> CategoryList = new ArrayList<>();
			CategoryList.add(Category.Item);
			CategoryList.add(Category.Product);
			return  CategoryList;
}
		public static ArrayList<BranchType> getBranchArray () {//////////////////////
			ArrayList<BranchType> BranchList = new ArrayList<>();
			BranchList.add(BranchType.ALL);
			BranchList.add(BranchType.EAST);
			BranchList.add(BranchType.NORTH);
			BranchList.add(BranchType.WEST);
			BranchList.add(BranchType.EAST);
			return  BranchList;
		}
		public static ArrayList<BranchType> getBranchArray2 () {//////////////////////
			ArrayList<BranchType> BranchList = new ArrayList<>();
			
			BranchList.add(BranchType.EAST);
			BranchList.add(BranchType.NORTH);
			BranchList.add(BranchType.SOUTH);
			BranchList.add(BranchType.WEST);
			return  BranchList;
		}
		
			
			
		public static ArrayList<Occasions> getOccasionsArray () {//////////////////////
			ArrayList<Occasions> OccasionsList = new ArrayList<>();
			OccasionsList.add(Occasions.Birthday);
			OccasionsList.add(Occasions.Graduation);
			OccasionsList.add(Occasions.MOM);
			OccasionsList.add(Occasions.Wedding);
			return  OccasionsList;
		}
		
		public static ArrayList<Type> getTypeArray () {//////////////////////
			ArrayList<Type> TypeList = new ArrayList<>();
			TypeList.add(Type.Bouqute);
			TypeList.add(Type.FlowerBranches);
			TypeList.add(Type.FlowerPots);
			TypeList.add(Type.Flowers);
			TypeList.add(Type.Seedlings);
			return  TypeList;
		}
	
}
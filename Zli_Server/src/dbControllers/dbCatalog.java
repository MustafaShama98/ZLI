package dbControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.enumE.OpType;
import entities.users.Catalog;
import serverManager.mysqlConnection;

public class dbCatalog {
	public static ArrayList<Catalog> CatalogTableList;
	public static ArrayList<Catalog> IndvidualsTableList;
	public static ArrayList<Catalog> CatalogList;
	public  mysqlConnection sql;
   

    public static ArrayList<Catalog> LoadCatalogTableType(Connection conn1, OpType TableType) throws SQLException
	{ 
    	CatalogTableList = new ArrayList<Catalog>();
    	boolean found = false;
			String query  = "SELECT * FROM catalog WHERE Category=? AND Occasions= ?;";
			PreparedStatement statment = conn1.prepareStatement(query);
			statment.setString(1,"Product");
			statment.setString(2,TableType.toString());
			ResultSet result = statment.executeQuery();
			if (found = result.next()) {
				do {	
					CatalogTableList.add(new Catalog(result.getString("Name"),result.getString("Details"), result.getString("Color"),result.getString("Type"),result.getDouble("Price")));
				}while(result.next());

			
				}
		return CatalogTableList;
	}
    
    public static ArrayList<Catalog> LoadIndvidualsTableType(Connection conn1, OpType TableType) throws SQLException
	{ 
    	IndvidualsTableList = new ArrayList<Catalog>();
    	boolean found = false;
			String query  = "SELECT * FROM catalog WHERE Category=? AND Type = ? ;";
			PreparedStatement statment = conn1.prepareStatement(query);
			statment.setString(1,"Item");
			statment.setString(2, TableType.toString());
			ResultSet result = statment.executeQuery();
			if (found = result.next()) {
				do {	
					
					IndvidualsTableList.add(new Catalog(result.getString("Name"),result.getString("Details"), result.getString("Color"),result.getDouble("price")));
				}while(result.next());
			}
		return IndvidualsTableList;
	}
    public static ArrayList<Catalog> LoadCatalogTable(Connection conn1) throws SQLException
	{ 
    	CatalogList = new ArrayList<Catalog>();
    	boolean found = false;
			String query  = "SELECT * FROM catalog ;";
			PreparedStatement statment = conn1.prepareStatement(query);
			
			ResultSet result = statment.executeQuery();
			if (found = result.next()) {
				do {	
					
					CatalogList.add(new Catalog(result.getInt("Code"),result.getString("Name"),result.getString("Details"),result.getString("Category"),result.getString("Type"),result.getString("Occasions") ,result.getString("Color"),result.getDouble("price")));
				}while(result.next());

			}
		return CatalogList;
	}
    public static Boolean Delete (Connection conn,Object CodeObject) 
    
    {
    	
    	 int  Code =(int)CodeObject ;
    	 PreparedStatement st;
		try {
			st = conn.prepareStatement("DELETE FROM catalog WHERE Code = ?;");
			st.setInt(1,Code);
           st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
        
        
	return true;
     

    }
    public static Boolean Edit(Connection conn1,Object object) 
    {
	ArrayList<String>myList=(ArrayList<String>)object;
  
   Boolean found=true;
			
		    try {
		    PreparedStatement st = conn1.prepareStatement("UPDATE catalog SET Name=?,Details=?,Category=?,Type=?,Occasions=?,Color=?,Price=? WHERE Code = ?;");
		    st.setString(8,myList.get(0));
			st.setString(1, myList.get(1));
		    st.setString(2,myList.get(2));
		    st.setString(3,myList.get(3));
			st.setString(4,myList.get(4));
			 st.setString(5,myList.get(5));
		    st.setString(6,myList.get(6));
		    st.setDouble(7,Double.parseDouble(myList.get(7)));
		    st.executeUpdate(); 
			} catch (SQLException e) {
				
				e.printStackTrace();
			return false;
			}
		   
return true;
}
    
    
	public static boolean ADD(Connection conn1,Object object) 
    {
   ArrayList<String>myList=(ArrayList<String>)object;
   PreparedStatement st;
   Boolean found=true;
   String query ;
           
		    try { 
		    st = conn1.prepareStatement("INSERT INTO catalog(Name,Details,Category,Type,Occasions,Color,Price) VALUES ( ?, ?,?,?,?,?,?);");
            st.setString(1,myList.get(0));
			st.setString(2,myList.get(1));
		    st.setString(3,myList.get(2));
			st.setString(4,myList.get(3));
			st.setString(5,myList.get(4));
		    st.setString(6,myList.get(5));
		    st.setDouble(7,Double.parseDouble(myList.get(6)));
		    st.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
				return false;
			}
		   
	
			return true;
    }		
}


package dbControllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Message;
import entities.enumE.DB_Message;
import entities.enumE.MessageType;
import entities.enumE.userType;
import entities.users.User;
import entities.users.MarketingWorker.MarketingWorker;
import entities.users.branchemployee.BranchEmployee;
import entities.users.branchmanager.BranchManager;
import entities.users.ceo.CEO;
import entities.users.customer.Customer;
import entities.users.serviceexpert.SurveyExpert;
import entities.users.serviceworker.ServiceWorker;
import server.ConnectionToClient;
public class dbLogin {
		
	public static Connection conn;
	 private User user;
	 static int flag=1;
		public dbLogin(Connection conn) {
			this.conn = conn;
		}
		public static DB_Message find_user(Connection conn, User user) {
			String query = "SELECT usertype,active_status,userID FROM login WHERE username = ? AND password = ? ;";
			System.out.println(conn );
			boolean found = false;
			int active = 0;
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, user.getUserName());
				stmt.setString(2, user.getPassword());
				ResultSet result = stmt.executeQuery();

				if (found = result.next()) {
					do {
						active = result.getInt("active_status");
						if (active == 1) {
							return DB_Message.isLogged;
						} else {
							user.setLoggedIn(1);
							user.setUserType(userType.valueOf(result.getString("usertype")));

							user.setId(Integer.valueOf(result.getString("userID")));
							PreparedStatement st = conn.prepareStatement("UPDATE login SET active_status = ? WHERE userID = ?;");
							st.setInt(1, 1);
							st.setString(2, String.valueOf(user.getId()));
							st.executeUpdate();
						
							return DB_Message.found;
						}
					} while (result.next());
				} else {
					return DB_Message.notfound;
				}
			}
			catch (SQLException e) {e.printStackTrace();}
			return DB_Message.notfound;

		}
				
	
		public static DB_Message loginHandler(Connection conn, User user, ConnectionToClient client) throws IOException, SQLException {
			dbLogin.conn =conn;
					switch (find_user(conn, user)) {
					case found: {
						switch (user.getUserType()) {
						case Customer: {
							Customer customer = new Customer(user);
							setCustomer(customer);
							if(customer.getStatusInSystem().equals("FROZEN"))
							{
								client.sendToClient(new Message(user,MessageType.IsFrozen));
							return DB_Message.isFrozen;
							}
							customer.getStatusInSystem();
							user = (User)customer;
							client.sendToClient(new Message(user,MessageType.Success));
							break;
							
						}
						case BranchEmployee :{
							BranchEmployee B_E = new BranchEmployee(user);
							setBranchEmployee(B_E);
							if(B_E.getPermission().equals("DENIED"))
							{client.sendToClient(new Message(user,MessageType.IsDenied));
							return DB_Message.isDenied;}
							user = (User)B_E;
							client.sendToClient(new Message(user,MessageType.Success));
							break;
						}
						case SurveyExpert:{
						SurveyExpert SE = new SurveyExpert(user);
						setExpert(SE);
						user = (User)SE;
						client.sendToClient(new Message(user,MessageType.Success));
							break;
						}
						case MarketingWorker :{
						MarketingWorker NW = new MarketingWorker(user);
						setMarketingWorker(NW);
						user=(User)NW;
						client.sendToClient(new Message(user,MessageType.Success));
						break;
						
						}case BranchManager :{
							BranchManager BM=new BranchManager(user);
							setBranchManager(BM);
							BM.setFirstname(user.getUserName());

							user=(User)BM;
							client.sendToClient(new Message(user,MessageType.Success));
							break;
						
					}
						case CEO:{
						CEO ceo=new CEO(user);
						user=(User)ceo;
						client.sendToClient(new Message(user,MessageType.Success));
						break;
					}
						case ServiceWorker:{
							//ServiceWorker serviceWorker= new ServiceWorker(user.getUserType(),user.getUserName(),user.getPassword(),user.getId());
						//	==user=(User)serviceWorker;
							System.out.println("Here");
							System.out.println(user.getUserType());
							client.sendToClient(new Message(user,MessageType.Success));
							System.out.println("34");
							break;
						}
						
						
						default:
							throw new IllegalArgumentException("Unexpected value: " + user.getUserType());
						}
						
						return DB_Message.found;
					}
					case  notfound : {
						client.sendToClient(new Message(MessageType.Fail));
						return DB_Message.notfound;
					}
					case isLogged :{
						client.sendToClient(new Message(MessageType.IsLogin));
						return DB_Message.isLogged;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + 	find_user(conn, user));
					}
					
				}	
		

		public static String setBranchEmployee(BranchEmployee branchEmployee) {
			String query = "SELECT * FROM branchemployee WHERE UserID = ? ;";
			PreparedStatement stmt;
			ResultSet result;
			try {
				stmt = conn.prepareStatement(query);
				stmt.setString(1,String.valueOf(branchEmployee.getId()));
				result = stmt.executeQuery();
					 while (result.next()) {
						 branchEmployee.setFirstname(result.getString("Firstname"));
						 branchEmployee.setLastname(result.getString("Lastname"));
						 branchEmployee.setPhoneNumber(result.getString("PhoneNumber"));
						 branchEmployee.setEmail(result.getString("Email"));
						 branchEmployee.setBranch(branchEmployee.stringToBranchType(result.getString("Branch")));
						 branchEmployee.setPermission(result.getString("permission"));
					 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 	 branchEmployee.getPermission();
		}
		private static void setExpert(SurveyExpert surveyExpert) {
			String query = "SELECT * FROM surveyexpert WHERE UserID = ? ;";
			PreparedStatement stmt;
			ResultSet result;
			try {
				stmt = conn.prepareStatement(query);
				stmt.setString(1,String.valueOf(surveyExpert.getId()));
				result = stmt.executeQuery();
					 while (result.next()) {
						 surveyExpert.setFirstname(result.getString("Firstname"));
						 surveyExpert.setLastname(result.getString("Lastname"));
						 surveyExpert.setPhoneNumber(result.getString("PhoneNumber"));
						 surveyExpert.setEmail(result.getString("Email"));
						 surveyExpert.setStatusInSystem(result.getString("StatusInSystem"));
					 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		private static void setBranchManager(BranchManager branchManager) {
			String query = "SELECT * FROM branchmanager WHERE UserID = ? ;";
			PreparedStatement stmt;
			ResultSet result;
			try {
				System.out.println("hello");
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, branchManager.getId());
				result = stmt.executeQuery();
					 while (result.next()) {
					
						 branchManager.setLastname(result.getString("LastName"));
						 branchManager.setFirstname(result.getString("FirstName"));
					 }
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		public static String setCustomer(Customer customer) {
			String query = "SELECT * FROM zli.customer WHERE userID = ? ;";
			PreparedStatement stmt;
			ResultSet result;
			try {
				stmt = conn.prepareStatement(query);
				stmt.setInt(1,customer.getId() );
				result = stmt.executeQuery();
					 while (result.next()) {
						customer.setCreditCardCVVCode(result.getString("CreditCardCVVCode"));
						customer.setCreditCardDateOfExpiration(result.getString("CreditCardDateOfExpiration"));
						customer.setCreditCardNumber(result.getString("CreditCardNumber"));
						customer.setFirstname(result.getString("Firstname"));
						customer.setLastname(result.getString("Lastname"));
						customer.setPhoneNumber(result.getString("PhoneNumber"));
						customer.setEmail(result.getString("Email"));
						customer.setStatusInSystem(result.getString("StatusInSystem"));
					
					 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return customer.getStatusInSystem();
			
		}
		private static void setMarketingWorker(MarketingWorker MarketingWorker) {
			String query = "SELECT * FROM marketingworker WHERE UserID = ? ;";
			PreparedStatement stmt;
			ResultSet result;
			try {
				stmt = conn.prepareStatement(query);
				stmt.setString(1,String.valueOf(MarketingWorker.getId()));
				result = stmt.executeQuery();
					 while (result.next()) {
						 MarketingWorker.setFirstname(result.getString("Firstname"));
						 MarketingWorker.setLastname(result.getString("Lastname"));
						 MarketingWorker.setPhoneNumber(result.getString("PhoneNumber"));
						 MarketingWorker.setEmail(result.getString("Email"));
						MarketingWorker.setStatusInSystem(result.getString("StatusInSystem"));
					 }
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
				

//public static User UpdateStatus(Connection conn, User user) throws SQLException
//{
//	PreparedStatement st = conn.prepareStatement("UPDATE login SET active_status = ? WHERE userID = ?;");
//	
//	 user.setLoggedIn(0);
//	 st.setInt(1,0);
//	 
//    st.setString(2,user.getId());
//    st.executeUpdate();
//	
//	return user;
//		
//}




}

package serverManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dbControllers.*;
import server.*;
import timeManager.ComplaintsReminder;
import entities.Message;
import entities.enumE.DB_Message;
import entities.enumE.MessageType;
import entities.enumE.OpType;
import entities.survey.Survey;
import entities.users.Complaints;
import entities.users.Sales;
import entities.users.User;
import entities.users.branchemployee.BranchEmployee;
import entities.users.customer.Customer;
import gui.ServerUIController;

public class ServerHandler {
	public static Connection conn = ServerConsole.getConn();

	private static Message newMSG;
	private static boolean flag=false;// to identify if login has done successfully.
	private static User usertmp;
	protected static User getUser() {
		return usertmp;
	}
	/**
	 * 
	 * @param msg
	 * @param client
	 * @return - Return true if login was successful, otherwise false. Used to print the clients connected into ServerUI. 

	 */
	protected static boolean manageTheMsg(Object msg, ConnectionToClient client) throws IOException, SQLException {
		newMSG = (Message) msg;
		
		switch (newMSG.getOpType()) {
	
		case Login: { 
			flag=false;
			 usertmp = (User) newMSG.getMsg();
			 DB_Message status=dbLogin.loginHandler(conn, usertmp, client);
			if (status == DB_Message.found) 
			{flag=true;}
			
				
		return flag;	
		}
	    case Table:{ //nested switch case
			
			switch (newMSG.getOpTypeExtraIndex(1)) {
			case Catalog: {
				
					client.sendToClient(new Message(dbCatalog.LoadCatalogTableType(conn, newMSG.getOpTypeExtraIndex(2))));
				    break;
			}case Indviduals: {
				
					client.sendToClient(new Message(dbCatalog.LoadIndvidualsTableType(conn, newMSG.getOpTypeExtraIndex(2))));
				    break;
			}
			case ViewMyOrder:
			{
			
				User user = (User) newMSG.getMsg();
				client.sendToClient(new Message(dbOrders.LoadOrdersTable(conn,user)));
				break;
				
				
			}
			
			case  ViewCatalog:
			{
				client.sendToClient(new Message(dbCatalog.LoadCatalogTable(conn)));
				break;
			}
			case ViewSales:
			{
				client.sendToClient(new Message(dbSales.LoadSalesTable(conn)));
				break;
			}
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + newMSG.getOpTypeExtra());
			}
			
			break;
		}
	    
		case Cancel:
		{
			
			client.sendToClient(new Message(dbOrders.Cancel(conn,newMSG.getMsg() , usertmp)));
			break;
		}
		case ViewMyOrderB_M:	{ /// the case when the branch manager wants to view the orders 
			try {
				User user = (User) newMSG.getMsg();
				client.sendToClient(new Message(dbOrder_BranchManager.LoadOrdersTableB_M(conn, user)));
			} catch (Exception e) { /// need to change it to SQLException
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
	}
		case ConfirmOrderB_M:{ /// this case when the branch manager wants to confirm an order or confirm an order cancellation
			//User user = (User) newMSG.getMsg();
			
			try {
				client.sendToClient(new Message(dbOrder_BranchManager.ConfirmOrder(conn,newMSG.getMsg())));
			} catch (Exception e) { /// need to change it to SQLException
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
			
		}
		case UnConfirmOrderB_M:{ /// the case when the branch manager wants to unconfirm an order
			
			try {
				client.sendToClient(new Message(dbOrder_BranchManager.UnConfirmOrder(conn,newMSG.getMsg(), usertmp)));
			} catch (Exception e) { /// need to change it to SQLException
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		}
		case ViewReportB_M: /// this case when the branch manager wants to view the PDF's
		{
			try {
				client.sendToClient(new Message(dbReports_BranchManager.ViewReports(conn,newMSG.getMsg(), usertmp)));
			} catch (Exception e) { /// need to change it to SQLException
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
			 
		}
		case MangeAccountsB_M:	{ //the case when the branch manager wants to view the customers account and manage them
			try {
				User user = (User) newMSG.getMsg();
				client.sendToClient(new Message(dbMangeAccountsB_M.LoadOrdersTableB_M(conn, user)));
			} catch (Exception e) { /// need to change it to SQLException
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
	}
case UnFreezeAccountB_M:{/// THE CASE WHEN THE BRANCH MANAGER WANTS TO UNFREEZE A CUSTOMERS ACCOUNT
			
			try {
				client.sendToClient(new Message(dbMangeAccountsB_M.UnFreezeAccount(conn,newMSG.getMsg(), usertmp)));
			} catch (Exception e) { /// need to change it to SQLException
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		}
case ConfirmAccountB_M:{/// THE CASE WHEN THE BRANCH MANAGER WANTS TO CONFIRM A CUSTOMERS ACCOUNT
	
	try {
		client.sendToClient(new Message(dbMangeAccountsB_M.ConfirmAccount(conn,newMSG.getMsg(), usertmp)));
	} catch (Exception e) { /// need to change it to SQLException
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
break;
}
case FreezeAccountB_M:{ /// THE CASE WHEN THE BRANCH MANAGER WANTS TO FREEZE A CUSTOMERS ACCOUNT
	
	try {
		client.sendToClient(new Message(dbMangeAccountsB_M.FreezeAccount(conn,newMSG.getMsg(), usertmp)));
	} catch (Exception e) { /// need to change it to SQLException
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
break;
}
case CreateAccountB_M: {/// the case when the branch manager wants to register a new customer
	try {
		
		client.sendToClient(new Message(db_CreateAccountB_M.CreateAccount(conn,newMSG.getMsg(), usertmp)));
	} catch (Exception e) { /// need to change it to SQLException
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
break;
}
case ViewReportCEO: {/// the case when the CEO wants to VIEW REPORT
	try {
		
		client.sendToClient(new Message(db_ViewMonthlyReportCEO.ViewMonthlyReport(conn,newMSG.getMsg(), usertmp)));
	} catch (Exception e) { /// need to change it to SQLException
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
break;
}
case ViewSingleQuarterReportCEO: {/// the case when the CEO wants to view a quarter report
	try {
		
		client.sendToClient(new Message(db_ViewSingleQuarterReportCEO.ViewQuarterReport(conn,newMSG.getMsg(), usertmp)));
	} catch (Exception e) { /// need to change it to SQLException
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
break;
}
case ViewTwoQuarterReportCEO: {/// the case when the CEO wants to view two quarter reports and compare between them
	try {
		
		client.sendToClient(new Message(db_ViewTwoQuarterReportCEO.ViewQuarterReport(conn,newMSG.getMsg(), usertmp)));
	} catch (Exception e) { /// need to change it to SQLException
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
break;
}
case ApproveEmployeeAccountB_M:{ /// the case when the branch manager wants to approve access permissions for the employee
	
	try {
		client.sendToClient(new Message(dbMangeEmployeesAccountsB_M.ApproveAccount(conn,newMSG.getMsg(), usertmp)));
	} catch (Exception e) { /// need to change it to SQLException
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
break;
}
case MangeEmployeesAccountsB_M:	{ /// the case when the branch manager wants to manage the employees accounts 
	try {
		User user = (User) newMSG.getMsg();
		client.sendToClient(new Message(dbMangeEmployeesAccountsB_M.LoadOrdersTableB_M(conn, user)));
	} catch (Exception e) { /// need to change it to SQLException
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
break;
}
case DenyEmployeeAccountB_M:{/// the case when the branch manager wants to deny access permissions for the employee
	
	try {
		client.sendToClient(new Message(dbMangeEmployeesAccountsB_M.DenyAccount(conn,newMSG.getMsg(), usertmp)));
	} catch (Exception e) { /// need to change it to SQLException
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
break;
}
		case UpdateCatalog:
		{
			switch (newMSG.getOpTypeExtraIndex(1)) {
			case Delete:
		{
			client.sendToClient(new Message(dbCatalog.Delete(conn,newMSG.getMsg())));
			break;
		}
		case Edit:
		{
			client.sendToClient(new Message(dbCatalog.Edit(conn,newMSG.getMsg())));
			break;
		}
		case UpdateSale:
		{	
			client.sendToClient(new Message(dbSales.UpdateSales(conn,newMSG.getMsg())));
			break;
		}
		case ADD:
		{
			
			client.sendToClient(new Message(dbCatalog.ADD(conn,newMSG.getMsg())));
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + newMSG.getOpTypeExtraIndex(1));
		}
		break;
		}
		
		case Survey: {
			switch (newMSG.getOpTypeExtraIndex(1)) {
			case WorkerSurvey: {
				BranchEmployee BE = (BranchEmployee) newMSG.getMsg();
				client.sendToClient(new Message(dbBranchEmployee.ParasingSurveyData(conn, BE)));
				break;
			}
			case ExpertSurvey: {
				surveyExpertHandler.handleFileReceived(newMSG.getMsg(),conn, client);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + newMSG.getOpTypeExtraIndex(1));
			}
			break;
			
		
		}
		case Sales:
		{
			client.sendToClient(new Message(dbSales.Sales(conn)));
			break;
		}
		
		case SaleActivity:
		{
			client.sendToClient(new Message(dbSales.SalesActivity(conn,newMSG.getMsg())));
			break;
		}
		case AddOrder:
		{
			client.sendToClient(new Message(dbOrders.details(conn, newMSG.getMsg())));
			break;
		}
		

		case Complaints:
		{
			client.sendToClient(new Message(dbComplaints.LoadComplaintsTableType(conn)));
		    break;
		}
		
		case CreateComplaint:
			
		{
			Complaints complain = (Complaints)(newMSG.getMsg());
			ComplaintsReminder complainTimer =new ComplaintsReminder(complain,10000, client);//10 seconds
			//System.out.println("1"+client);
			client.sendToClient(new Message(dbComplaints.ParasingComplaintData(conn, (Complaints)(newMSG.getMsg()))));
		    break;
		}
		case CheckCustumerId:
		{
			//client.sendToClient(new Message(dbComplaints.find_user(conn,(Complaints)(newMSG.getMsg() ))));
			
			if (dbComplaints.find_user(conn,(Complaints)newMSG.getMsg()).equals("Found")) 
			{
				client.sendToClient(new Message(MessageType.Success));
				
			}else //if(dbLogin.find_user(conn, usertmp).equals("Not found")) 
			{
				client.sendToClient(new Message(MessageType.Fail));
			}
		    break;
		}
		
		case Compensation:
		{
			
			int CustomerID= (int)newMSG.getMsg();
			System.out.println("before cancel");
			
			System.out.println("after cancel");
			client.sendToClient(new Message(dbCompensation.DeleteComplaintData(conn,CustomerID)));
			if(ComplaintsReminder.complainsTimerList.get(CustomerID) != null)
			ComplaintsReminder.complainsTimerList.get(CustomerID).cancel();
			System.out.println("after cancel2");
		    break;
		}
		case compensation:
		{
			User user = (User) newMSG.getMsg();
			client.sendToClient(new Message(dbMessage.LoadCompensation(conn,user)));
			break;
		}
		case CreateSurvey:
		{
			client.sendToClient(new Message(dbSurvey.InsertSurveyData(conn,(Survey)newMSG.getMsg( ))));
		    break;
		}
		
		case ViewSurvey:
		{
			client.sendToClient(new Message(dbSurvey.LoadSurveysTable(conn, (BranchEmployee)newMSG.getMsg( ))));
			break;
		}
		
		case OpenSurvey:
		{
			client.sendToClient(new Message(dbSurvey.getSurveyQuestions(conn, (String)newMSG.getMsg( ))));
			break;
		}
		case Messages:
		{
			User user = (User) newMSG.getMsg();
			System.out.println("Server");
			client.sendToClient(new Message(dbMessage.LoadMessages(conn,user)));
			break;
		}
	
		case Logout: {
			User user = (User) newMSG.getMsg();
			PreparedStatement st = conn.prepareStatement(
			"UPDATE login SET active_status = ? WHERE userID = ?;");
			user.setLoggedIn(0);
			st.setInt(1, 0);
			st.setInt(2,user.getId());
			st.executeUpdate();
			client.sendToClient(new Message(user, MessageType.Success));
			ServerUIController.sv.ClientDisconnected(user);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + newMSG.getOpType());

		}
		return false;
		
	}
}
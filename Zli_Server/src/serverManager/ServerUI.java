package serverManager;

import javafx.application.Application;
import javafx.stage.Stage;
import serverManager.ServerConsole;

import java.util.Vector;
import gui.*;

public class ServerUI extends Application {
	final public static int DEFAULT_PORT =5555;
	public static ServerConsole serverRunner= null;
	 public static ServerUIController frame= new ServerUIController();
	public static void main( String args[] ) throws Exception
	   {   
		 launch(args);
	  } // end main
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub				  		
		//frame = new ServerUIController();
		frame.start(primaryStage);

	}
	
	public static ServerConsole runServer(String p, String user, String password, String name)
	{
		 int port = 0; //Port to listen on

	        try
	        {
	        	port = Integer.parseInt(p); //Set port to 5555
	          
	        }
	        catch(Throwable t)
	        {
	        	System.out.println("ERROR - Could not connect!");
	        }
	    	
	         ServerConsole serverRunner = new ServerConsole( port,user,password,name);
	        
	         try 
	        {
	        	 serverRunner.listen(); //Start listening for connections
	        } 
	        catch (Exception ex) 
	        {
	          System.out.println("ERROR - Could not listen for clients!");
	        }
	        return serverRunner;
	}
	

}

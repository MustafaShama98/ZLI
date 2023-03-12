package clientManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import controllers.LoginController;
import entities.users.User;
/**
 * Singleton class for client runner
 * @author Mustafa Sh.
 *
 */
public final class ClientRunner  extends ClientController{
	private static User user = null;
	private static ClientRunner run = null;
	 private ClientRunner(String host, int port) throws IOException {
		super(host, port);
		// TODO Auto-generated constructor stub
	}

	 public static ClientRunner getClientRunner()  {
		 if(run == null) {
			
				try {
					run = new ClientRunner( InetAddress.getLocalHost().getHostAddress().toString(), ClientRunner.DEFAULT_PORT);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		 }
		 return run;
	
	 }
	 
	 public static ClientRunner getClientRunner(String name)  {
	
		 if(run == null && LoginController.class.getName().equals(name)) { //in case caller is login class
			 try {
				run = new ClientRunner( InetAddress.getLocalHost().getHostAddress().toString(), ClientRunner.DEFAULT_PORT);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 return run;
		 
	 }
	
	public void ClientConnect() {
		// TODO Auto-generated method stub
		
		super.ClientConnect();
	}
	public static void setUser(User user1) {
		user = user1;
	}
	 public static User getUser() {
		 return user;
	 }


}
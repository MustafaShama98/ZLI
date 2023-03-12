// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package clientManager;


import client.*;
import controllers.LoginController;
import entities.Message;
import entities.enumE.MessageType;
import entities.enumE.OpType;
import entities.enumE.TimeType;
import javafx.application.Platform;

import java.io.*;

/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ClientController extends AbstractClient {
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */


	ClientUI clientManage;
	  /**
	   * The default port to connect on.
	   */
		
		protected static int DEFAULT_PORT = 5555;
	   
	public boolean isAwaitResponse() {
		return awaitResponse;
	}

	public Object obj = null;
	private boolean awaitResponse = false;
	private static int timer=0;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 * 
	 * @param ip       of the server
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	protected ClientController(String host, int port)  {
		super(host,port); // Call the superclass constructor
		
			
	}
	

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object msg) // this method to handle message from server
	{
		timer=0;
		System.out.println("Handling the messsage from server.");
		try {
			awaitResponse = false;
			obj = msg;
			Message message = (Message) msg;
			if((msg instanceof MessageType 
			&& ((MessageType)msg)== MessageType.AllOut) ){
				if(obj instanceof MessageType) {
					LoginController l =(LoginController)ClientUI.getStartWin().getController();
					//l.startThread();
					Platform.runLater(l);

				}
				
			}
			if((message.TimeType != null
					&& ((message.TimeType)== TimeType.Done)) ){
				LoginController l =(LoginController)ClientUI.getStartWin().getController();
				//ServiceWorkerController SWC = (ServiceWorkerController) LoginController.getUserWindow().getWin().getController();
				//ServiceWorkerComplaintsController C = (ServiceWorkerComplaintsController)SWC.ComplainPageController();
//				if(C ==null) {
//					System.out.println("null");
//				}
				Platform.runLater(l);
						
					}


		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}

	}
	
	public void ClientConnect() {
		try {
			openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */

	public void SendReqToServer(Object message) {

		try {
			
			sendToServer(message);
			awaitResponse = true;

			// wait for response
			while (awaitResponse) {
				
				try {
					timer += 300;
					Thread.sleep(350);
					System.out.println("Waiting for message reply");
				
					if(timer > 3000) {
						//sendToServer(new Message(OpType.Logout,ClientRunner.getUser()));
						System.out.println("Time is out.");
					//	awaitResponse=false;
						quit();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not send message to server: Terminating client." + e);
			quit();
		}
	}

	/**
	 * method called after a connection has been established.
	 */

	@Override
	protected void connectionEstablished() {
		super.connectionEstablished();
		
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}

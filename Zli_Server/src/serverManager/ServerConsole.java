// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package serverManager;

import server.*;
import server.ObservableServer;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import entities.Message;
import entities.enumE.MessageType;
import entities.users.User;
import gui.ServerUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class ServerConsole extends ObservableServer  {
	// Class variables *****************
	public mysqlConnection sql = null;
	
	private static Connection conn = null;
	public static Connection getConn() {
		return conn;
	}

	private TextArea client_connected_log = null;
	private TextArea server_messages;
	private Label offline_txt;
	private Label online_txt;
	public boolean serverOffline=false;
	public boolean flag=false;
	private boolean isConnected=false;
	int port; String user; String password; String name;
	/**
	 * The default port to listen on.
	 */
	// final public static int DEFAULT_PORT = 5555;

	// Constructors ******************

	public boolean isConnected() {
		return isConnected;
	}
	
	public void setClient_connected1(TextArea txtArea) {
		this.client_connected_log = txtArea;
	}
	public void setClient_connected1(String String) {
		this.client_connected_log.setText(String);
	}
	public TextArea getClientsLog() {
		return this.client_connected_log;
	}
	public void set_ServerMessages(TextArea serverMessages) {
		this.server_messages = serverMessages;
	}

	public void set_offline_online_buttons(Label online_txt, Label offline_txt) {
		this.online_txt = online_txt;
		this.offline_txt = offline_txt;
	}

	public void setOffline() {

		if (offline_txt == null)
			System.out.println("null");
		offline_txt.setVisible(true);
		online_txt.setVisible(false);

	}

	public void setOnline() {

		offline_txt.setVisible(false);
		online_txt.setVisible(true);

	}
	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port     The port number to connect on.
	 * @param name
	 * @param password
	 * @param user
	 * 
	 */

	public ServerConsole(int port, String user, String password, String name) {
		super(port);
		this.port=port;
		this.user=user;
		this.password=password;
		this.name=name;
	}

	public void SqlConnect(int port, String user, String password, String name) {
		sql = mysqlConnection.Connect(user, password, name);
		conn = mysqlConnection.getConncetion();

	}
	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 * @param
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		
		
		try {
			flag=ServerHandler.manageTheMsg(msg,client);
			if(flag)NEWclientConnected(client);
//			else {
//				System.out.println("Invalid request from client.");
//				client.sendToClient(new Message(MessageType.Fail));
//			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 * 
	 * @throws SQLException
	 */
	protected void serverStarted() {
	
		setOnline();
		
		isConnected=true;
		server_messages.appendText("- Server listening for connections on port " + getPort());
		server_messages.appendText("\n");
		mysqlConnection.set_ServerMessages(this.server_messages);
		SqlConnect(port, user, password, name);
		
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
	
		//ServerUI.frame.initialize(null, null);
		//ServerUI.frame.setOffline();
		//ServerUI.frame.checkIfOffline();
		//flag =true;
		isConnected=false;
		setOffline();
		server_messages.appendText("- Server has stopped listening for connections.");
		server_messages.appendText("\n");
	}


	protected void NEWclientConnected(ConnectionToClient client) {
	
		// TODO Auto-generated method stub
		String ip =client.getInetAddress().toString();
		//super.clientConnected(client);
		
		client_connected_log.appendText(
				ServerHandler.getUser().getUserType()+" - "+  ServerHandler.getUser().getUserName() +
				" connected from IP address: " + ip.substring(1));
		client_connected_log.appendText("\n");
		}
		

	
	public void ClientDisconnected(User user) {
		// TODO Auto-generated method stub
		//super.clientDisconnected(client);
		client_connected_log.appendText(user.getUserName() + " Disconnected." );
		client_connected_log.appendText("\n");

	}


	
}
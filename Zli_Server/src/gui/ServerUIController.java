package gui;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

import entities.Message;
import entities.enumE.MessageType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser.ExtensionFilter;
import server.*;
import serverManager.ServerConsole;
import serverManager.ServerHandler;
import serverManager.ServerUI;
import serverManager.mysqlConnection;

public class ServerUIController implements Initializable, Runnable {

	String temp = "";

	@FXML
	private Button btnExit = null;
	@FXML
	private Button btnDone = null;

	@FXML
	private TextField portxt;
    @FXML
    private TextArea server_messages;
	@FXML
	private TextField usertxt;
	@FXML
	private TextField nametxt;

	@FXML
	private TextField passwordtxt;

	@FXML
	public TextArea client_connect;
	@FXML
	private Label offline_txt;
	@FXML
	public Label test;
	@FXML
	
	private Label online_txt;

	private String ip, p, name, password, user;

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getip() {
		return ip;
	}

	public void setip(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public static ServerConsole sv = null;
	mysqlConnection sql;

	private boolean isConnected;
    private int flag1=0;
	private boolean flag;
	private Thread  t1;

	private String path;
	public void Done(ActionEvent event) throws Exception {// Start running the server from here
		
		p = portxt.getText();
		user = usertxt.getText();
		password = passwordtxt.getText();
		name = nametxt.getText();

		if (p.trim().isEmpty()) {
			System.out.println("You must enter a port number!!");

		} else {
			// Stage primaryStage = new Stage();
			// FXMLLoader loader = new FXMLLoader();
			if(sv == null) {
			sv = ServerUI.runServer(p, user, password, name); // Server starter, starts only one instance
			 
			}
			else sv.listen();
			sv.setClient_connected1(client_connect); 
			sv.set_ServerMessages(this.server_messages);
			sv.set_offline_online_buttons(online_txt, offline_txt);
			ip =InetAddress.getLocalHost().getHostAddress().toString();
			
			t1 = new Thread(this);
			t1.start();
			flag = false;
			offline_txt.setVisible(false);
			online_txt.setVisible(true);
			

		    }
		}
		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		//setOffline();
		//System.out.println(System.identityHashCode(offline_txt));
		
	}
	public void start(Stage primaryStage) throws Exception {
	
		
//		offline_txt = new Label("offline");
//		offline_txt.setLayoutX(386); offline_txt.setLayoutY(245);
//		offline_txt.setTextFill(Paint.valueOf("#17bd4b"));
//		offline_txt.setVisible(false);
		Parent root = FXMLLoader.load(getClass().getResource("ServerUI.fxml"));	
		
		Scene scene = new Scene(root);	
	
		
		primaryStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
//				if (ServerConsole.getConn() != null) {
//					try {
//						PreparedStatement st = ServerConsole.getConn().prepareStatement("UPDATE login SET active_status = 0;");
//						st.executeUpdate();
//						server_messages.appendText("- All users have been disconnected.\n");
//						
//					} catch (SQLException e4) {
//						// TODO Auto-generated catch block
//						e4.printStackTrace();
//					}
//				}
				if (sv != null) {
					try {
						sv.sql.closeConnection();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						sv.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.out.println("Closing server..");
				System.exit(0);
			}
		});
		primaryStage.getIcons().add(new Image(ServerUIController.class.getResource("/images/server.png" ).toExternalForm()));
		primaryStage.setTitle("ZLI Server Controller");
	
	
		primaryStage.setScene(scene);
		primaryStage.show();
		

	}

	 /**
     * this method takes the information of all the users that have accounts in Zli
     *  from loginn table in externaldata and puts them in login table in zli 
     * @param event
     * @throws SQLException
     */
    @FXML
    void importClick(ActionEvent event) throws SQLException {
    	if (flag1==0) //can click on import one time only
    	{ 
    		flag1=1;
    		ResultSet Rs;
        	PreparedStatement ps1;
        	try {
        	Connection conn = mysqlConnection.getConncetion();
       
        	Statement ps = conn.createStatement();
        	
        	Rs = ps.executeQuery("SELECT * FROM externaldata.login");
        	while(Rs.next()) {
        		ps1 = conn.prepareStatement("INSERT INTO zli.login (userID,username,password,usertype,active_status)"
        	                              + "VALUES (?,?,?,?,?)");
        		ps1.setInt(1, Rs.getInt(1));
        		ps1.setString(2, Rs.getString(2));
        		ps1.setString(3, Rs.getString(3));
        		ps1.setString(4, Rs.getString(4));
        		ps1.setInt(5, 0);
        		ps1.execute();
        	}
    		server_messages.appendText("-Data Imported Successfully");

            } catch (SQLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
            }
    	}

}
    	
    	
    	


	@FXML
	void exitBtn() {
		
			
//			try {
//				this.sql = sv.sql;
//				sql.closeConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
		server_messages.clear();
		if (ServerConsole.getConn() != null) {
			try {
				PreparedStatement st = ServerConsole.getConn().prepareStatement("UPDATE login SET active_status = 0;");
				st.executeUpdate();
				sv.getClientsLog().clear();
				sv.setClient_connected1("- All users have been disconnected.\n");
				sv.sendToAllClients(MessageType.AllOut);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			sv.stopListening();
			flag = true;
			setOffline();
	
			System.out.println("Server Closed");
			
		

	}
	
	public void setOffline() {
		
			if(offline_txt==null) System.out.println("null");
			offline_txt.setVisible(true);
			online_txt.setVisible(false);
		
	}
	public void setOnline() {
		
		
		offline_txt.setVisible(false);
		online_txt.setVisible(true);
	
}

	public void setOffline_txt(boolean offline_txt) {
		this.offline_txt.setVisible(offline_txt);
	}
	public Label getOffline_txt() {
		return offline_txt;
	}

	public void setOnline_txt(boolean online_txt) {
		this.online_txt.setVisible(online_txt);
	}
	/**
	 * Thread function to constantly checking if there's a WIFI connection,
	 * if not then the server disconnects automatically. 
	 */
	@Override
	public void run() {
		while (true) {
		
			try {
				
				   isConnected = ip.equals(InetAddress.getLocalHost().getHostAddress().toString());
			        if (!isConnected) {
			        	exitBtn();
			        	server_messages.appendText("- Server shutdown due to WIFI disconnection.");
			        	server_messages.appendText("\n");

			        	} 
			        Thread.sleep(40);     
			} catch (InterruptedException | UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        if (this.flag)  
	            break;
	        }
	        
}

	public static void importClick() {
		// TODO Auto-generated method stub
		
	}


}
package timeManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


import entities.Message;
import entities.enumE.TimeType;
import entities.users.Complaints;
import server.ConnectionToClient;


/**
 * To send a reminder to service worker regarding a specific comlpaint from customer after passing 24 hours
 * from receiving the complaint.
 * @author mostf
 *
 */
public class ComplaintsReminder extends TimerTask {

	public int userID; // customer id - complain id
	int complainID;
	long delay;
	Timer time;
	private ConnectionToClient client;
	String TimeUp = " Reminder for 24 hours passed for complaints. ";
	String complainMSG;
	public static HashMap<Integer,ComplaintsReminder > complainsTimerList=new HashMap<Integer,ComplaintsReminder>();

	public ComplaintsReminder(Complaints complain, long delay, ConnectionToClient client) {
		this.userID = complain.getCustomerID();
		this.complainMSG = complain.getComplaint();
		this.complainID = complain.getComplainID();
		this.client = client;
		this.delay = delay;
		time = new Timer(true);
		time.schedule(this, delay, 1);
		complainsTimerList.put(userID, this);
	}
	@Override
	public void run() {
		System.out.println(
				"Reminder for 24 hours passed for complaints. " + userID);
		complainsTimerList.remove(userID);
		try {
	Message msg = new Message(TimeType.Done,userID);
			client.sendToClient(msg);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		time.cancel();
	}
	
	
	public void stopTimer() {
		time.cancel();
		System.out.println("Timer has stopped.");
	}
}
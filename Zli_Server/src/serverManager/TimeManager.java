package serverManager;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;


import entities.Message;
import entities.enumE.TimeType;
import entities.enumE.userType;
import server.ConnectionToClient;

public class TimeManager  {
	public static Timer Deliverytimer =  new Timer();
	
	public static Timer ReportsTimer = new Timer();
	public static Timer ComplaintsTimer = new Timer();
	public 	static long  hours_3  = 1000 * 60 * 180;
	public static long  hours_1  = 1000 * 60 * 60;
	public  static long  hours_24  = 1000 * 60 * 60 * 24;
	public 	static long min = 1000 * 60;
	static int randomNum = ThreadLocalRandom.current().nextInt(30,180 +1);
	
	public class MonthlyReports extends TimerTask {
	    @Override
	    
	    public void run() {
	     System.out.println("Monthly reports has been sent.");
	    }
	}
	public static class Delivery extends TimerTask {
		
		
	    @Override
	    public void run() {
	     System.out.println("Delivery");
	    }
	}
	
	public static void main(String[] args) {
		long shippingTime = randomNum *  min; // random shipping time  from 30 mins to 3 hours
		//ReportsTimer.scheduleAtFixedRate(new TimeManager.MonthlyReports(), 0, hours_24);
		//ComplaintsTimer.scheduleAtFixedRate(new TimeManager().  new ComplaintsReminder(3) ,0, 1000*2);
		//ComplaintsTimer.scheduleAtFixedRate(new TimeManager().  new ComplaintsReminder(2) ,0, 1000*7);
		//TimeManager tm =new TimeManager();
//	ComplaintsReminder complainTimer =tm.new ComplaintsReminder(2,1000);
		//ComplaintsReminder complainTimer1 =tm.new ComplaintsReminder(4,3000);
		//Deliverytimer.schedule(new TimeManager.Delivery() ,0,  shippingTime);
//		 Scanner in = new Scanner(System.in);
//		 String s = in.nextLine();
//	
//		switch (TimeType.valueOf(s.toString())) {
//		case Deliverytimer: {
//			stopTimer(TimeType.Deliverytimer);
//			break;
//		}
//		case ComplaintsTimer:{
//			stopTimer(TimeType.ComplaintsTimer);
//			break;
//		}
//		case ReportsTimer:{
//			stopTimer(TimeType.ReportsTimer);
//			break;
//		}
//		default:
//			throw new IllegalArgumentException("Unexpected value: " + TimeType.valueOf(s.toString()));
//		}
		System.out.println("here");
		return;
		
	}
	
	
	public static void stopTimer(TimeType type) {
		
		switch (type) {
		case Deliverytimer: {
			Deliverytimer.cancel();
			System.out.println("Stopped timer.");
			break;
		}
		case ComplaintsTimer:{
			ComplaintsTimer.cancel();
			System.out.println("Stopped timer.");
			break;
		}
		case ReportsTimer:{
			ReportsTimer.cancel();
			System.out.println("Stopped timer.");
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		ReportsTimer.cancel();
	}
}

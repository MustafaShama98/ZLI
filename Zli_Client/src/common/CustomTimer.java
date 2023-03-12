package common;

public class CustomTimer {
	
	public static void Wait(int msTime) {
		
	long doUntil = msTime + System.currentTimeMillis(); 
	while ( System.currentTimeMillis() != doUntil);//timer to wait for server
	}
}

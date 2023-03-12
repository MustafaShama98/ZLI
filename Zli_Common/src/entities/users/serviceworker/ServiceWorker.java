package entities.users.serviceworker;
/// Service Worker enters the information about the order and handles the complaints,and he can also 
// Compensate the customer .
import entities.enumE.userType;
import entities.users.User;

public class ServiceWorker extends User {

	public ServiceWorker(userType userType, String username, String password,int Id) {
		super(userType, username, password,Id);
		
	}
}
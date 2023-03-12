package opreations;

import entities.*;
import entities.users.User;

/*
 * Class to handle messages from server to client to parse data
 */
public class LoginMessageHandler extends IMessageHandler {
	
	public LoginMessageHandler(Message msg) {
		super(msg);
	}

	public Message returnMsg() {
		
		return newMsg;
	}
}

package opreations;

import entities.Message;

public abstract class IMessageHandler{
	Message newMsg;
	
	public IMessageHandler(Message msg ) {
		this.newMsg = (Message)msg;
	}
}

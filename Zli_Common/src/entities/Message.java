package entities;
import java.io.Serializable;
import entities.enumE.MessageType;
import entities.enumE.OpType;
import entities.enumE.TimeType;
//import entities.enumE.userType;
import entities.users.User;
public class Message implements Serializable {
	private static final long serialVersionUID = -3309518074865299483L;
	// Type of the operation we want from the server to make.
    private OpType OpType;
    private OpType[] OpTypeExtra;
    // If the operation that returns from the server succeeded\failed.
    private MessageType MessageType;
    
//    private userType UserType;
    private Object msg;
    
    private User user;
	public TimeType TimeType;

	/**
	 * Contractor for sending a message from the CLIENT to the SERVER.
	 *
	 * @param done the operation type
	 * @param msg  - 
	 
	 */
    public Message(OpType t, Object msg) {
	super();
	this.OpType = t;
	this.msg = msg;
    }
    
    public Message(OpType OpType) {
    	super();
    	this.OpType = OpType;
    	this.msg = msg;
        }
    
    public Message(OpType[] OpType) {
    	super();
    	this.OpTypeExtra = OpType;
    	this.msg = msg;
    	
        }
    public Message(OpType[] OpType,User user) {
    	super();
    	this.OpTypeExtra = OpType;
    	this.msg = user;
    	
        }
    public Message(OpType[] OpType,Object msg) {
    	super();
    	this.OpTypeExtra = OpType;
    	this.msg = msg;
    	
        }
    /**
     * Contractor for sending a message from the SERVER to the CLIENT.
     *
     * @param OpType the operation type
     * @param msg - 
     * @param MessageType the return message type
     */
    public Message( User user ,MessageType MessageType) {
    	super();
	this.user = user;
	this.MessageType = MessageType;
    }
    public Message( Object obj ,MessageType MessageType) {
    	super();
	this.msg = obj;
	this.MessageType = MessageType;
	
    }
    public Message( Object obj ) {
    	super();
	this.msg = obj;
    }

    public Message(TimeType done, Object msg) {
    	super();
    	this.TimeType = done;
    	this.msg = msg;
        }
    public Message(MessageType MessageType) {
    	super();
	this.MessageType = MessageType;
    }
    
    public Message(MessageType success, User user2) {
	this.MessageType= success;
	this.user=user2;
	}

	//--------------------------
	public OpType getOpType() {
		if (OpType == null)
		return OpTypeExtra[0];
		else return OpType;
	}
	public OpType[] getOpTypeExtra() {
		return OpTypeExtra;
	}
	public OpType getOpTypeExtraIndex(int i) {
		
		try {
			if(i>=1)
		return OpTypeExtra[i];
			
		} catch (Exception e) {
			System.out.println("Invalid index access.");
		}
	return null;
	}
	public MessageType getMessageType() {
		return MessageType;
	}
	public User getUser() {
		return this.user;
	}
	public Object getMsg() {
		return msg;
	}
	public void setOpType(OpType opType) {
		this.OpType = opType;
	}
	public void setMessageType(MessageType messageType) {
		MessageType = messageType;
	}
	
	public void setMsg(Object msg) {
		this.msg = msg;
	}
}

package entities.enumE;

import java.io.Serializable;
/**
 * enum for message type
 *
 */

public enum MessageType implements Serializable{
	Success,
	Fail,
	Found,
	wrongDetalis,//wrong password
	IsLogin,
	AllOut, IsDenied, IsFrozen;
	
}

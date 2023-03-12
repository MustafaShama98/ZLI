package entities.enumE;

import java.io.Serializable;
/**
 * enum for user type
 *
 */
public enum userType implements Serializable{
	Unknown,Customer,BranchManager, CEO, BranchEmployee, SurveyExpert, MarketingWorker, ServiceWorker;
}

package view.UserView.surveyExpertView;

import clientManager.ClientRunner;
import clientManager.OpenWindow;
import entities.users.User;
import entities.users.customer.Customer;
import entities.users.serviceexpert.SurveyExpert;
import javafx.event.ActionEvent;
import view.UserView.UserUI;

public class SurveyExpertUI extends UserUI{
	SurveyExpert surveyExpert;
	public SurveyExpertUI(User user,ActionEvent event) {
		super(user,event);
		initialize();
		 newWin = new OpenWindow(event,"/view/UserView/surveyExpertView/surveyExpertUl.fxml", "Survey Expert Portal - "+ surveyExpert.getFirstname()+" "+surveyExpert.getLastname());
		newWin.hideWindow();
	}
	void initialize () {
		surveyExpert = (SurveyExpert)user;
		ClientRunner.setUser(surveyExpert);
	}
}

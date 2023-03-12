package entities.users.ceo;
// CEO can view reports of all branches.
import entities.enumE.userType;
import entities.users.User;

public class CEO extends User{
	private  String b_type1,b_type2;
	private String quarter1,quarter2,year1,year2;
	private int flag=0;
	/// Constructor
	public CEO(User user) {
		super(userType.CEO,user.getUserName(),user.getPassword(),user.getId(),user.getisLoggedIn());
	}
	

public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
private String month;
public String getMonth() {
	return month;
}
public void setMonth(String month) {
	this.month = month;
}
public String getRepType() {
	return repType;
}
public void setRepType(String repType) {
	this.repType = repType;
}
private String repType;

	public String getB_type1() {
		return b_type1;
	}
	public void setB_type1(String b_type1) {
		this.b_type1 = b_type1;
	}
	public String getB_type2() {
		return b_type2;
	}
	public void setB_type2(String b_type2) {
		this.b_type2 = b_type2;
	}
	public String getQuarter1() {
		return quarter1;
	}
	public void setQuarter1(String quarter1) {
		this.quarter1 = quarter1;
	}
	public String getQuarter2() {
		return quarter2;
	}
	public void setQuarter2(String quarter2) {
		this.quarter2 = quarter2;
	}
	public String getYear1() {
		return year1;
	}
	public void setYear1(String year1) {
		this.year1 = year1;
	}
	public String getYear2() {
		return year2;
	}
	public void setYear2(String year2) {
		this.year2 = year2;
	}
}

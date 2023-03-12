package entities.users;

import java.io.Serializable;

public class Sales implements Serializable
{
	private static final long serialVersionUID = 7083200625764363962L;
	private String BranchName;
	private String Sales;
	private String Activation;
	
	public Sales(String Sale)
	{
		this.Sales=Sale;
	}

	public Sales(String BranchName, String Sales, String Activation) {
		this.Sales=Sales;
		this.BranchName = BranchName;
		this.Activation = Activation;
		
	}

	public String getActivation() {
		return Activation;
	}

	public void setActivation(String activation) {
		this.Activation = activation;
	}

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		this.BranchName = branchName;
	}

	public String getSales() {
		return Sales;
	}

	public void setSales(String sales) {
		this.Sales = sales;
	}
}


public class Banker extends BankAccount{
	
	


	private String userID;
	private String userName;
	
	
	

	public Banker(String clientID, String accountType, String clientName, String contact, int accountNo,
			int currentBalance, int previousTransaction) {
		super(clientID, accountType, clientName, contact, accountNo, currentBalance, previousTransaction);
		// TODO Auto-generated constructor stub
	}
	


	public Banker(String userID,String userName) {
		super();
		this.userID=userID;	
		this.userName=userName;
	}


	public Banker() {
		
	}
	



	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void addAccount() {
		
	}

	

}


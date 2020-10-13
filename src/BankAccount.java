import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class BankAccount {
	
	protected String clientID;
	protected String accountType;
	protected String ClientName;
	protected String Contact;
	protected int accountNo;
	protected int currentBalance;
	protected int previousTransaction;
	
	
	
	
	
	
	public BankAccount(String clientID, String accountType, String clientName, String contact, int accountNo,
			int currentBalance, int previousTransaction) {
		this.clientID = clientID;
		this.accountType = accountType;
		this.ClientName = clientName;
		this.Contact = contact;
		this.accountNo = accountNo;
		this.currentBalance = currentBalance;
		this.previousTransaction = previousTransaction;
	}
	



	public BankAccount() {
		// TODO Auto-generated constructor stub
	}




	public String getClientID() {
		return clientID;
	}



	public void setClientID(String clientID) {
		this.clientID = clientID;
	}



	public String getClientName() {
		return ClientName;
	}



	public void setClientName(String clientName) {
		ClientName = clientName;
	}



	public String getContact() {
		return Contact;
	}



	public void setContact(String contact) {
		Contact = contact;
	}



	public String getAccountType() {
		return accountType;
	}



	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}



	public int getAccountNo() {
		return accountNo;
	}



	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}



	public int getCurrentBalance() {
		return currentBalance;
	}



	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}



	public int getPreviousTransaction() {
		return previousTransaction;
	}



	public void setPreviousTransaction(int previousTransaction) {
		this.previousTransaction = previousTransaction;
	}



	 @Override
	public String toString() {
		return "BankAccount [clientID=" + clientID + ", accountType=" + accountType + ", ClientName=" + ClientName
				+ ", Contact=" + Contact + ", accountNo=" + accountNo + ", currentBalance=" + currentBalance
				+ ", previousTransaction=" + previousTransaction + "]";
	}




	//public void listAccounts(String clientID) {}
	public void listAllAccounts(ArrayList<BankAccount> accounts) {
		System.out.println("clientID \t accountNo \t accountType \t ClientName \t Contact");
		for(int i=0;i<accounts.size();i++) {
			if (accounts.get(i) instanceof BankAccount) {
				System.out.println(accounts.get(i).clientID + "\t" + accounts.get(i).accountNo + "\t" +accounts.get(i).accountType + "\t" + accounts.get(i).ClientName + "\t" + accounts.get(i).Contact );
			}
		}
	}
	
	public int listAccounts(ArrayList<BankAccount> accounts,String clientID) {
		int i=0;
		for( i=0;i<accounts.size();i++) {
			if (accounts.get(i) instanceof BankAccount) {
				if (accounts.get(i).clientID.equals(clientID))
					System.out.println(accounts.get(i).clientID + "\t" + accounts.get(i).accountNo + "\t" +accounts.get(i).accountType + "\t" + accounts.get(i).ClientName + "\t" + accounts.get(i).Contact );
				}
			}
		return i;
	}
	
	public int generateAccountNumber(ArrayList<BankAccount> accounts) {
		int accountnumber=0;
	
		for(int i=0;i<accounts.size();i++) {
			if (accounts.get(i) instanceof BankAccount) {
				accountnumber=accounts.get(i).accountNo;
		}
			
	}
		return accountnumber++;
	}
	
	public boolean isexistusername(ArrayList<BankAccount> accounts,String username) {
		for(int i=0;i<accounts.size();i++) {
			if (accounts.get(i) instanceof LoginPage) {
				if(((LoginPage) accounts.get(i)).getUsername().equals(username)){
					return true;
				}		
				
			}
		
		}
		
		return false;
	}
	
	
	
	public void addAccount(ArrayList<BankAccount> accounts) throws FileNotFoundException
	{
		try {
			FileOutputStream f = new FileOutputStream(new File("/Users/syedrabi/Project_1/BankingProject/src/Accountdetails.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(this);
			o.close();
			System.out.println("Account created successfully " +this.clientID + "\t" + this.accountNo + "\t" +this.accountType + "\t" + this.ClientName + "\t" + this.Contact );		
			
		} catch (IOException e) {
	        System.out.println(" cannot write to file " );
	        
		}
	
	}
	
}

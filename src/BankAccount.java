import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.Serializable;


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
	public void listAllAccounts() {
		
			System.out.println(this.clientID + "\t" + this.accountNo + "\t" +this.accountType + "\t" + this.ClientName + "\t" + this.Contact );
			
		
	}
	
	public void listAccounts(String clientID) {

	
					System.out.println(this.clientID + "\t" + this.accountNo + "\t" +this.accountType + "\t" + this.ClientName + "\t" + this.Contact );

		
	}
	
	public int generateAccountNumber() {
		int accountnumber=0;
	
		File file = new File("AccountDetails.txt");
		if (file.length() == 0) 
		{ 
			accountnumber = 10001;
		}
		else
			accountnumber= this.accountNo;
			
		return accountnumber;
	}
	
	public boolean isexistusername(String username) {
		
				if((this.getClientID()).equals(username)){
					return true;
				}		
		
		return false;
	}
	
	
	
	public void addAccount() throws FileNotFoundException
	{
		String details=null;
		try {
			FileWriter myWriter = new FileWriter("AccountDetails.txt",true);
			
			
			details = this.clientID + "," +this.accountType + "," + this.ClientName + "," + this.Contact + "," + this.accountNo + "," + this.currentBalance + "," + previousTransaction;
			myWriter.write(details);
			myWriter.write("\n");
			myWriter.close();
			System.out.println("Account created successfully \n" +this.clientID + "\t" + this.accountNo + "\t" +this.accountType + "\t" + this.ClientName + "\t" + this.Contact );		
			
		} catch (IOException e) {
	        System.out.println(" cannot write to file " );
	        
		}
	
	}
	
	public void deleteAccount(ArrayList<BankAccount> accounts, int index,String clientID ,String accountType , int accountNo)  throws FileNotFoundException
	{
		boolean status =false;
		if(this.accountNo==accountNo && this.clientID.equals(clientID) && this.accountType.equals(accountType)) 
		{
			try {
					accounts.remove(index);
					status =true;	
			} catch (IndexOutOfBoundsException e) {
		        System.out.println(" cannot delete the account " );
			}
		}
		if (status==true)
			 System.out.println("successfully deleted the account");
		else
			System.out.println("Please verify the details entered and try again");
		try {
			File file = new File("AccountDetails.txt");
			Scanner scan = new Scanner(new File("AccountDetails.txt"));
			File tempFile = new File("AccountDetails.tmp");
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			while(scan.hasNext()) {
				String line = scan.nextLine().toString();
				String[] details = line.split(",");
				if (!(details[0].equals(clientID) && details[1].equals(accountType) && Integer.parseInt(details[4])==accountNo))
				{
					pw.println(line);
					pw.flush();
				}
					
				}
			pw.close();
			if (!file.delete()) {
		        System.out.println("Could not delete file");
		        return;
		      }
			if (!tempFile.renameTo(file))
		        System.out.println("Could not rename file");

		}catch (IOException e) {
	        System.out.println(" cannot delete the account " );
	        
		}
		
	}
	
	public void viewaAccountDetails(ArrayList<BankAccount> accounts,String clientID) {
		for( int i=0;i<accounts.size();i++) {
			if (accounts.get(i) instanceof BankAccount) {
				if((((BankAccount) accounts.get(i)).getClientID()).equals(clientID))
					System.out.println(accounts.get(i).toString());			
				}
			}
	}
	
	public boolean makeTransfer(ArrayList<BankAccount> accounts,String clientID,int sourceAccountNo,int destAccountNo,int amount) {
		boolean status=false;
		for( int i=0;i<accounts.size();i++) {
			if (accounts.get(i) instanceof BankAccount) {
				if(((BankAccount) accounts.get(i)).getAccountNo() == destAccountNo) {
					status=true;
				}
				else
				{
					System.out.println("Destination account doesnot exist");
					return false;
				}
			}
		}
					
		for(int i=0;i<accounts.size();i++) {
			if (accounts.get(i) instanceof BankAccount) {
				if(((BankAccount) accounts.get(i)).getClientID().equals(clientID) && 
						((BankAccount) accounts.get(i)).getAccountNo() == sourceAccountNo &&
						((BankAccount) accounts.get(i)).getCurrentBalance() < amount)
					
				{
					System.out.println("Your account balance is less than the amoun to be transfered. Cant do the transaction");
					return false;
				}
				else if(((BankAccount) accounts.get(i)).getClientID().equals(clientID) && 
						((BankAccount) accounts.get(i)).getAccountNo() == sourceAccountNo &&
						((BankAccount) accounts.get(i)).getCurrentBalance() >= amount)
				{
						((BankAccount) accounts.get(i)).setCurrentBalance((((BankAccount) accounts.get(i)).getCurrentBalance())-amount);
						((BankAccount) accounts.get(i)).setPreviousTransaction(- amount);
						status=true;
						return true;
				}
				if(status==true)
					if(((BankAccount) accounts.get(i)).getAccountNo() == destAccountNo )
					{
						((BankAccount) accounts.get(i)).setCurrentBalance((((BankAccount) accounts.get(i)).getCurrentBalance())+amount);
					}
			}
				
		}
		return false;
	}
	
	public boolean makeDeposit(ArrayList<BankAccount> accounts,String clientID,int sourceAccountNo,int amount) {
		for(int i=0;i<accounts.size();i++) {
			if (accounts.get(i) instanceof BankAccount) {
				if(((BankAccount) accounts.get(i)).getClientID().equals(clientID) && 
						((BankAccount) accounts.get(i)).getAccountNo() == sourceAccountNo)
				{
					((BankAccount) accounts.get(i)).setCurrentBalance((((BankAccount) accounts.get(i)).getCurrentBalance())+amount);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean payUtils(ArrayList<BankAccount> accounts,String clientID,int sourceAccountNo,int amount) {
		for(int i=0;i<accounts.size();i++) {
			if (accounts.get(i) instanceof BankAccount) {
				if(((BankAccount) accounts.get(i)).getClientID().equals(clientID) && 
						((BankAccount) accounts.get(i)).getAccountNo() == sourceAccountNo &&
						((BankAccount) accounts.get(i)).getCurrentBalance() < amount)
					
				{
					System.out.println("Your account balance is less than the amoun to be payed. Cant do the transaction");
					return false;
				}
				else if(((BankAccount) accounts.get(i)).getClientID().equals(clientID) && 
						((BankAccount) accounts.get(i)).getAccountNo() == sourceAccountNo &&
						((BankAccount) accounts.get(i)).getCurrentBalance() >= amount)
				{
						((BankAccount) accounts.get(i)).setCurrentBalance((((BankAccount) accounts.get(i)).getCurrentBalance())-amount);
						((BankAccount) accounts.get(i)).setPreviousTransaction(- amount);
						return true;
				}
			}
		}
		return false;
	}
	
	public boolean editName(ArrayList<BankAccount> accounts,String clientID,String name)
	{
		for(int i=0;i<accounts.size();i++) {
			if (accounts.get(i) instanceof BankAccount) {
				if(((BankAccount) accounts.get(i)).getClientID().equals(clientID)) {
					((BankAccount) accounts.get(i)).setClientName(name);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean editContact(ArrayList<BankAccount> accounts,String clientID,String contact)
	{
		for(int i=0;i<accounts.size();i++) {
			if (accounts.get(i) instanceof BankAccount) {
				if(((BankAccount) accounts.get(i)).getClientID().equals(clientID)) {
					((BankAccount) accounts.get(i)).setContact(contact);
					return true;
				}
			}
		}
		return false;
	}

	
}

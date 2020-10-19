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
	
	
	//Constructor for the class BankAccount
	
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
	


	//No argument constructor
	public BankAccount() {
		// TODO Auto-generated constructor stub
	}


    //Get and Set methods

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


	 /*
	  * This method is lists the restricted account details
	  * @param : null
	  * @return: void
	  * Simply prints the basic customer account details on deman
	  */
	
	public void listAccounts() {
					System.out.println("  "+this.clientID + "           " + this.accountNo + "           " +this.accountType + "       " + this.ClientName + "      " + this.Contact );
	
	}
	/*
	 * Method to auto generate the Account number for new bank account creation
	 * @param : null
	 * @return : int (the possible  previous account number)
	 */

	public int generateAccountNumber() {
		int accountnumber=0;
	
		File file = new File("AccountDetails.txt");
		if (file.length() == 0) 
		{ 
			accountnumber = 10000;
		}
		else
			accountnumber= this.accountNo;
			
		return accountnumber;
	}
	/*
	 * Method to check if the given client does hold a valid account
	 * @param ; string ClientID to be validated
	 * @return: boolean -returns true if the given client exist in bank account list
	 */
	public boolean isExistClientID(String clientID) {
		
				if((this.getClientID()).equals(clientID)){
					return true;
				}		
		
		return false;
	}
	
	/*
	 * Method to create New account on demand
	 * This method stores the AccountDetails.txt file with the newly created account details
	 * @param : no
	 * @return :void
	 */
	
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
			System.out.println("\n\nPlease create new login credentials after 24 hours to manage your account if not already exit \n\n\n");
			
		} catch (IOException e) {
	        System.out.println(" cannot write to file " );
	        
		}
	
	}
	/**
	* Method to delete an account
	* The given account record will be deleted from the array list as well as from the Accountdetails.txt file
	* @return : void
	* @param : Arraylist of objects, index of arraylist client iid, account number and the account type to be deleted  
	*/
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

    /**
     * Determine if the specified number of milliseconds have elapsed since the
     * ElapsedTimeMonitor was instantiated or reset().
     *
     * @param milliseconds
     *            the number of milliseconds that may have passed
     * @return true if the specified number of milliseconds have elapsed,
     *         otherwise false
     */
	public void viewAccountDetails(ArrayList<BankAccount> accounts,String clientID) {
		
		for(BankAccount a:accounts)
			if (a !=null && (a.getClass().getName()).equals("BankAccount"))
			{
				if((((BankAccount) a).getClientID()).equals(clientID))
					System.out.println(((BankAccount)a).toString() + "\n");
			}
			
	}
	/*
	 * Method to do transfer of amount from one account to another account within the bank
	 * @param : Arraylist of objects, String clientID,int sourceAccountNo-source client ID and Account number
	 * 			int destAccountNo - Destination Account number to be credited 
	 * 			int amount- Amount to be transfered
	 * @return : Boolean :returns true on successful transfer completion 
	 */
	
	public boolean makeTransfer(ArrayList<BankAccount> accounts,String clientID,int sourceAccountNo,int destAccountNo,int amount) throws FileNotFoundException {
		boolean status=false;
		for(BankAccount a:accounts) {
			if (a !=null && (a.getClass().getName()).equals("BankAccount"))
			{
				if(((BankAccount) a).getAccountNo() == destAccountNo) {
					status=true;
					break;
				}
			}
		}
		if(status==false)
		{
			System.out.println("Destination account does not exist");
			return false;
		}

					
		for(BankAccount a:accounts) {
			if (a !=null && (a.getClass().getName()).equals("BankAccount"))
			{
				if(((BankAccount) a).getClientID().equals(clientID) && 
						((BankAccount) a).getAccountNo() == sourceAccountNo ) {
					
				
						if(((BankAccount) a).getCurrentBalance() < amount)
					
						{
							System.out.println("Your Account doesn't have sufficient balance for this transaction. Can't do the transaction");
							return false;
						}

						else if(((BankAccount) a).getCurrentBalance() >= amount)
						{
							((BankAccount)a).setCurrentBalance((((BankAccount)a).getCurrentBalance())-amount);
							((BankAccount) a).setPreviousTransaction(- amount);
							updateAmount(((BankAccount)a).getClientID(),((BankAccount)a).getAccountNo(),((BankAccount) a).getCurrentBalance(),-amount);
							status=true;
						}
				}
			}
		}
		for(BankAccount a:accounts) {
			if (a !=null && (a.getClass().getName()).equals("BankAccount"))
			{
				
				if(status==true && ((BankAccount) a).getAccountNo() == destAccountNo )
				{
					((BankAccount) a).setCurrentBalance((((BankAccount) a).getCurrentBalance())+amount);
					updateAmount(((BankAccount)a).getClientID(),((BankAccount)a).getAccountNo(),((BankAccount) a).getCurrentBalance(),+amount);
					return true;
				}
			}
				
		}
		return false;
	}
	/*
	 * Method allows the client/customer to make deposit to his/her account
	 * @paramA :rraylist of obejcts, String clientID,int sourceAccountNo-source client ID and Account number
	 * 			int amount- Amount to be deposited
	 * @return ; boolean -on successful deposit completion returns true
	 */
	public boolean makeDeposit(ArrayList<BankAccount> accounts,String clientID,int sourceAccountNo,int amount) throws FileNotFoundException {
		for(BankAccount a:accounts) {
			if (a !=null && (a.getClass().getName()).equals("BankAccount"))
			{
				if(((BankAccount) a).getClientID().equals(clientID) && 
						((BankAccount) a).getAccountNo() == sourceAccountNo)
				{
					((BankAccount) a).setCurrentBalance((((BankAccount) a).getCurrentBalance())+amount);
					updateAmount(((BankAccount)a).getClientID(),((BankAccount)a).getAccountNo(),((BankAccount) a).getCurrentBalance(),+amount);
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * Method allows the client/customer to pay utility bills on demand
	 * @paramA :Arraylist of objects, String clientID,int sourceAccountNo-source client ID and Account number
	 * 			int amount- Amount to be payed
	 * @return ; boolean -on successful payment completion returns true
	 */
	public boolean transaction(ArrayList<BankAccount> accounts,String clientID,int sourceAccountNo,int amount) throws FileNotFoundException {
		for(BankAccount a:accounts) {
			if (a !=null && (a.getClass().getName()).equals("BankAccount"))
			{
				if(((BankAccount) a).getClientID().equals(clientID) && 
				((BankAccount) a).getAccountNo() == sourceAccountNo ) {
					if(((BankAccount) a).getCurrentBalance() < amount)
					
					{
						System.out.println("Your Account doesn't have sufficient balance for this transaction. Cant do the transaction");
						return false;
					}
				
				 	if(((BankAccount) a).getCurrentBalance() >= amount)
				 	{
						((BankAccount) a).setCurrentBalance((((BankAccount) a).getCurrentBalance())-amount);
						((BankAccount) a).setPreviousTransaction(-amount);
						updateAmount(((BankAccount)a).getClientID(),((BankAccount)a).getAccountNo(),((BankAccount) a).getCurrentBalance(),-amount);
						return true;
				 	}
				 }
			}
				
		}
		
		return false;
	}
	
	
	/*
	 * Method allows the customer to edit the name on demand
	 * @param: Array list of objects, string Client ID for which the name change to be done
	 * 		   String name: New name to be updated
	 * @return: boolean - on successful update of name returns true
	 */
	public boolean editName(ArrayList<BankAccount> accounts,String clientID,String name)
	{
		for(BankAccount a:accounts) 
		{
			if (a !=null && (a.getClass().getName()).equals("BankAccount"))
			{
				if(((BankAccount) a).getClientID().equals(clientID)) {
					((BankAccount) a).setClientName(name);
					try 
					{
						File file = new File("AccountDetails.txt");
						Scanner scan = new Scanner(new File("AccountDetails.txt"));
						File tempFile = new File("AccountDetails.tmp");
						PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
						String updatedline=null;
						while(scan.hasNext()) {
							String line = scan.nextLine().toString();
							String[] details = line.split(",");
							pw.println(line);
							pw.flush();
								if (details[0].equals(clientID))
								{
									updatedline=details[0]+","+details[1]+","+name+","+ details[3]+","+details[4]+","+details[5]+","+details[6];
									pw.println(updatedline);
									pw.flush();
								}
								
							}
					
						pw.close();
						if (!file.delete()) {
					        System.out.println("Could not delete file");
					  
					      }
						if (!tempFile.renameTo(file))
					        System.out.println("Could not rename file");

					}
				
					catch (IOException e) 
					{
					System.out.println(" cannot update the account details" );
					}
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * Method allows the customer to update the Contact details on demand
	 * @param: Array list of objects, string Client ID for which the Contact update to be done
	 * 		   String contact: New contact to be updated
	 * @return: boolean - on successful update of contact returns true
	 */
	public boolean editContact(ArrayList<BankAccount> accounts,String clientID,String contact)
	{
		for(BankAccount a:accounts) {
			if (a !=null && (a.getClass().getName()).equals("BankAccount"))
			{
				if(((BankAccount) a).getClientID().equals(clientID)) {
					((BankAccount) a).setContact(contact);
					try 
					{
						File file = new File("AccountDetails.txt");
						Scanner scan = new Scanner(new File("AccountDetails.txt"));
						File tempFile = new File("AccountDetails.tmp");
						PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
						String updatedline=null;
						while(scan.hasNext()) {
							String line = scan.nextLine().toString();
							String[] details = line.split(",");
							pw.println(line);
							pw.flush();
								if (details[0].equals(clientID))
								{
									updatedline=details[0]+","+details[1]+","+details[2]+","+ contact+","+details[4]+","+details[5]+","+details[6];
									pw.println(updatedline);
									pw.flush();
								}
								
							}
					
						pw.close();
						if (!file.delete()) {
					        System.out.println("Could not delete file");
					  
					      }
						if (!tempFile.renameTo(file))
					        System.out.println("Could not rename file");

					}
				
					catch (IOException e) 
					{
					System.out.println(" cannot update the account details" );
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public void updateAmount(String clientID,int sourceAccountNo,int currentAmount ,int diff) throws FileNotFoundException
	{
		try 
		{
			File file = new File("AccountDetails.txt");
			Scanner scan = new Scanner(new File("AccountDetails.txt"));
			File tempFile = new File("AccountDetails.tmp");
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String updatedline=null;
			while(scan.hasNext()) {
				String line = scan.nextLine().toString();
				String[] details = line.split(",");
				pw.println(line);
				pw.flush();
					if ((details[0].equals(clientID) && Integer.parseInt(details[4])==accountNo))
					{
						updatedline=details[0]+","+details[1]+","+details[2]+","+ details[3]+","+details[4]+","+currentAmount+","+diff;
						pw.println(updatedline);
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

			}
		catch (IOException e) {
		System.out.println(" cannot update the account details" );
	        
		}
	}
	
}

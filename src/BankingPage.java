import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class BankingPage {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		ArrayList<BankAccount> accounts=new ArrayList<BankAccount>();

		
        parsefile(accounts);
		
		
		label:	
		while (true)
		{
			System.out.println("Enter the type of user login :(1 or 2)\n\n \t1.Banker \t\t 2.Customer");
			int choice=sc.nextInt();
			String username=null;
			String password=null;
			String userType = null;
			int ch =0;
			
				switch(choice) {
				case 1: System.out.println("If existing user PRESS 1 . if you want to create new credentials PRESS 2");
				ch=sc.nextInt();
				
				if (ch==1)
				{
					
					System.out.println("enter the Username");
					username=sc.next();
					System.out.println("enter the password");
					password=sc.next();
					userType="Banker";
					boolean status=false;
					for(int i=0;i<accounts.size();i++) {
						if (accounts.get(i) instanceof LoginPage) {
							 status=((LoginPage) accounts.get(i)).login(username,password,userType);
							 if (status==true) 
							 {
								System.out.println("successfully logged in");
							 	
								System.out.println("Select the operation you would like to do 1.List accounts \n2.Add account \n3. Delete Acount ");
								int opt=sc.nextInt();
								
								switch(opt)
								{
								case 1:
									accounts.add(new BankAccount());
									((BankAccount) accounts.get(accounts.size()-1)).listAllAccounts(accounts);
								case 2:
									String ClientID=null;
									String ClientName=null;
									String Contact=null;
									System.out.println("Is the customer already holding any other account(y/n)");
									String op1=sc.next();
									if (op1=="y" || op1=="Y") 
									{
										System.out.println("Enter the Client ID:");
										 ClientID=sc.next();
										accounts.add(new BankAccount());
										int index=0;
										if(((BankAccount) accounts.get(accounts.size()-1)).isexistusername(accounts,ClientID)) {
											index=((BankAccount) accounts.get(accounts.size()-1)).listAccounts(accounts,ClientID);
											System.out.println("Auto populating ClientName:"+ ((BankAccount) accounts.get(index)).getClientName() +" and contact:"+((BankAccount) accounts.get(index)).getContact());
											ClientName=((BankAccount) accounts.get(index)).getClientName();
											Contact=((BankAccount) accounts.get(index)).getContact();
										}
										else {
											System.out.println("Given CclientID doesn't exist. Please enter the values manually");
											System.out.println("Enter the Client Name:");
											ClientName=sc.next();
											System.out.println("Enter the Contact:");
											Contact=sc.next();
										}
									}
									else {
										System.out.println("Enter the Client ID:");
										 ClientID=sc.next();
										System.out.println("Enter the Client Name:");
										 ClientName=sc.next();
										System.out.println("Enter the Contact:");
										 Contact=sc.next();
									}
									System.out.println("Enter the Account Type to be created: 1.savings account \t 2 current account");
									String accountType=null;
									int ch1=sc.nextInt();
									if(ch1==1)
										 accountType="Savings Account";
									else if (ch1==2)
										 accountType="Current Account";
									accounts.add(new BankAccount());
									int accountNo= ((BankAccount) accounts.get(accounts.size()-1)).generateAccountNumber(accounts);
									int currentBalance=1000;
									int previousTransaction=0;
									accounts.add(new BankAccount(ClientID,accountType,ClientName,Contact,accountNo,currentBalance,previousTransaction));
									((BankAccount) accounts.get(accounts.size()-1)).addAccount(accounts);
								}
								
								
							 }
					
						
						
						else 
						{
							System.out.println("Login failed .Would you like to retry (y/n) ");
							String option=sc.next();
							if (option=="y" || option=="Y") 
								continue label ; 
							
							if (option=="n" || option=="N")
								System.exit(0);
						}
						}
				}
				}
				else if (ch==2)
				{
					System.out.println("enter the Username");
					username=sc.next();
					System.out.println("enter the password");
					password=sc.next();
					userType="Banker";
					accounts.add(new LoginPage(username,password,userType));
//					while (true) {
//						if(((LoginPage) accounts.get(accounts.size()-1)).isexistusername(accounts) == true){
//							System.out.println("Reenter the Username as the entered user name is already exist");
//							username=sc.next();	
//							((LoginPage) accounts.get(accounts.size()-1)).setUsername(username);
//						}
//						else
//							break;
//					}
					((LoginPage) accounts.get(accounts.size()-1)).createLogin();
					System.exit(0);
				}
					
					
				case 2: System.out.println("If existing user PRESS 1 . if you want to create new credentials PRESS 2");
				ch=sc.nextInt();
				
				if (ch==1)
				{
					
					System.out.println("enter the Username");
					username=sc.next();
					System.out.println("enter the password");
					password=sc.next();
					userType="Customer";
					boolean status=false;
					for(int i=0;i<accounts.size();i++) {
						if (accounts.get(i) instanceof LoginPage) {
							 status=((LoginPage) accounts.get(i)).login(username,password,userType);
							 if (status==true) 
							 {
								System.out.println("successfully logged in");
							 	break;
							 }
					
						
						
						else 
						{
							System.out.println("Login failed .Would you like to retry (y/n) ");
							String option=sc.next();
							if (option=="y" || option=="Y") 
								continue label ; 
							
							if (option=="n" || option=="N")
								System.exit(0);
						}
						}
				}
				}
				else if (ch==2)
				{
					System.out.println("enter the Username");
					username=sc.next();
					System.out.println("enter the password");
					password=sc.next();
					userType="Customer";
					accounts.add(new LoginPage(username,password,userType));
//					while (true) {
//						if(((LoginPage) accounts.get(accounts.size()-1)).isexistusername(accounts) == true){
//							System.out.println("Reenter the Username as the entered user name is already exist");
//							username=sc.next();	
//							((LoginPage) accounts.get(accounts.size()-1)).setUsername(username);
//						}
//						else
//							break;
//					}
					((LoginPage) accounts.get(accounts.size()-1)).createLogin();
					System.exit(0);
				}
			}
		}
	}
	
	
	public static void parsefile(ArrayList<BankAccount> accounts) throws FileNotFoundException {

		try {
			
			Scanner scan = new Scanner(new File("/Users/syedrabi/Project_1/BankingProject/src/logindetails.txt"));
			while(scan.hasNext()) {
				String line = scan.nextLine().toString();
				String[] credential = line.split(",");
				accounts.add(new LoginPage(credential[0],credential[1],credential[2]));
			}	
		}catch (IOException e) {
	        System.out.println(" cannot write to file " );
		}
		try {
			Scanner scan = new Scanner(new File("/Users/syedrabi/Project_1/BankingProject/src/AccountDetails.txt"));
			while(scan.hasNext()) {
				String line = scan.nextLine().toString();
				String[] details = line.split(",");
				accounts.add(new BankAccount(details[0],details[1], details[2], details[3],Integer.parseInt(details[4].trim()), Integer.parseInt(details[5].trim()), Integer.parseInt(details[6].trim())));
			}	
		}catch (IOException e) {
	        System.out.println(" cannot write to file " );
		}
	}
	
}
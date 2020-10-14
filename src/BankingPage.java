import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class BankingPage {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		ArrayList<BankAccount> accounts=new ArrayList<BankAccount>();

		createDefaultUsers();
        parsefile(accounts);
        
		
		
		label:	
		while (true)
		{
			System.out.println("Enter the type of user login \n\n \t1.Banker \t\t 2.Customer\n\n Press (1 or 2):");
			int choice=sc.nextInt();
			String username=null;
			String password=null;
			String userType = null;
			int ch =0;
			
				switch(choice) {
				case 1: System.out.println(" Existing user PRESS - 1  \n To create new credentials PRESS - 2\n");
				ch=sc.nextInt();
				
				if (ch==1)
				{
					
					System.out.println("Enter the Username");
					username=sc.next();
					System.out.println("Enter the password");
					password=sc.next();
					userType="Banker";
					boolean status=false;
					for(BankAccount a:accounts) {
						if (a instanceof LoginPage) {
							 status=((LoginPage)a).login(username,password,userType);
							 if(status==true)
								 break;
						}
					}
							 if (status==true) 
							 {
								 boolean exist=false;
								 for(BankAccount a:accounts)
									if (a !=null && (a.getClass().getName()).equals("Banker"))
									{
										exist=((Banker)a).isUserExist(username);
										if (exist)
											break;
									}
									if(!exist) {
										System.out.println("You are not an active user. Contact the bank for further details");
										System.exit(0);
									}
									else
										System.out.println("successfully logged in");
								int exitChoice=1;
								do {
								System.out.println("Select the operation you would like to do\n\n1.List accounts \n\n2.Add account \n\n3. Delete Acount\n ");
								System.out.println("Enter your option (1/2/3):");
								int opt=sc.nextInt();
								
								
									switch(opt)
									{
									case 1:
										System.out.println("clientID \t accountNo \t accountType \t ClientName \t Contact");
										for(BankAccount a:accounts)
											if (a !=null && (a.getClass().getName()).equals("BankAccount"))
											{
													((BankAccount) a).listAccounts();
											}
										break;
										
									case 2:
										String ClientID=null;
										String ClientName=null;
										String Contact=null;
										System.out.println("Is the customer already holding any other account(press 1 for yes / 0 for no)");
										int op1=sc.nextInt();
										if (op1==1) 
										{
											System.out.println("Enter the Client ID:");
											 ClientID=sc.next();
											 boolean isexist=false;
											for(BankAccount a:accounts) {
												if (a !=null && (a.getClass().getName()).equals("BankAccount"))
												{
													if(((BankAccount) a).isExistUsername(ClientID)) {
														((BankAccount) a).listAccounts();
														ClientName=((BankAccount) a).getClientName();
														Contact=((BankAccount) a).getContact();
														isexist=true;
													}
												}
											}
													if(!isexist) {
														System.out.println("Given ClientID doesn't exist. Please enter the values manually\n");
														System.out.println("Enter the Client Name:");
														ClientName=sc.next();
														System.out.println("Enter the Contact:");
														Contact=sc.next();
													}
										}
										else if(op1==0){
											System.out.println("Enter the Client ID:");
											 ClientID=sc.next();
											System.out.println("Enter the Client Name:");
											 ClientName=sc.next();
											System.out.println("Enter the Contact:");
											 Contact=sc.next();
										}
										System.out.println("Enter the Account Type to be created:\n\n 1.Savings account \t 2 Current account\n");
										String accountType=null;
										int ch1=sc.nextInt();

										if(ch1==1)
											 accountType="Savings";
										else if (ch1==2)
											 accountType="Current";
										
										int accountNo=((BankAccount) accounts.get(accounts.size()-1)).generateAccountNumber();
										accountNo++;
										int currentBalance=1000;
										int previousTransaction=0;
										accounts.add(new BankAccount(ClientID,accountType,ClientName,Contact,accountNo,currentBalance,previousTransaction));
										((BankAccount) accounts.get(accounts.size()-1)).addAccount();
										break;
									case 3:
										System.out.println("Enter the Client ID to be deleted:");
										ClientID=sc.next();
										System.out.println("Enter the Account No to be deleted:");
										accountNo=sc.nextInt();
										System.out.println("confirm the account type to be deleted:");
										accountType=sc.next();
										int index=0;
										for(BankAccount a:accounts) {
											index++;
											if (a !=null && (a.getClass().getName()).equals("BankAccount"))
												if((((BankAccount)a).getClientID().equals(ClientID)) &&
														(((BankAccount)a).getAccountType().equals(accountType))&&
																(((BankAccount)a).getAccountNo()==accountNo)){
													((BankAccount)a).deleteAccount(accounts,index,ClientID,accountType,accountNo);
													break;
												}
										}
										break;
									}
									System.out.println("Do you want to perform any other task\n press 1 to continue \n 0 to quit");
									exitChoice=sc.nextInt();
								}while(exitChoice==1);
								System.exit(0);
							 }
					
						
						
						else 
						{
							System.out.println("Login failed .Would you like to retry (Press \n 1 - for retry\n0 - for Quit) ");
							int option=sc.nextInt();
							if (option==1) 
								continue label ; 
							
							if (option==0)
								System.exit(0);
						}
						
				
				}
				else if (ch==2)
				{
					System.out.println("Enter the Username");
					username=sc.next();
					System.out.println("Enter the password");
					password=sc.next();
					userType="Banker";
					accounts.add(new LoginPage(username,password,userType));
					boolean stat=true;
					do {
						for(BankAccount a:accounts) {
							if (a !=null && (a.getClass().getName()).equals("Banker"))
							{
								if(((Banker) a).isUserExist(username)) {
									System.out.println("Reenter the Username as the entered user name is already exist\n");
									username=sc.next();	
									((LoginPage) accounts.get(accounts.size()-1)).setUsername(username);
									stat=true;
								}
							}
						if(stat=false)
							break;
						}
					}while(stat=false);
					((LoginPage) accounts.get(accounts.size()-1)).createLogin();
					System.exit(0);
				}
					
					
				case 2: System.out.println("If existing user PRESS 1 . if you want to create new credentials PRESS 2");
				ch=sc.nextInt();
				
				if (ch==1)
				{
					
					System.out.println("Enter the Username");
					username=sc.next();
					System.out.println("Enter the password");
					password=sc.next();
					userType="Customer";
					boolean status=false;
					for(BankAccount a:accounts) {
						if (a instanceof LoginPage) {
							 status=((LoginPage)a).login(username,password,userType);
							 if(status==true)
								 break;
							}
						}
							 if (status==true) 
							 {
								 boolean exist=false;
								 for(BankAccount a:accounts)
									if (a !=null && (a.getClass().getName()).equals("BankAccount"))
									{
										exist=((BankAccount)a).isExistUsername(username);
										if (exist)
											break;
									}
									if(!exist) {
										System.out.println("You are not an active user to access");
										System.exit(0);
									}
									else
										System.out.println("successfully logged in");
								int exitChoice=1;
								do {
									System.out.println("Select the operation you would like to do\n\n1.View Account \n\n2.Transfer Amount \n\n3.Deposit Amount \n\n4.Pay utilities \n\n5.Update Personal Details\n");
									System.out.println("Enter the option between 1 to 5:\n");
									int ch2=sc.nextInt();
								
								
									switch(ch2)
									{
										case 1: 
											((BankAccount) accounts.get(accounts.size()-1)).viewaAccountDetails(accounts,username);
											break;
										
										case 2:
											System.out.println("Your account Details");
											((BankAccount) accounts.get(accounts.size()-1)).viewaAccountDetails(accounts,username);
											System.out.println("Confirm the Account number from which the transaction to be done (from the list shown) ");
											int sourceAccountNo=sc.nextInt();
											System.out.println("Enter the Account number for which amount to be transfered");
											int destAccountNo=sc.nextInt();
											System.out.println("Enter the Amount to be transfered");
											int amount=sc.nextInt();
											boolean transfer=((BankAccount) accounts.get(accounts.size()-1)).makeTransfer(accounts,username,sourceAccountNo,destAccountNo,amount);
											if(transfer)
												System.out.println("Transaction succcessful");
											else
												System.out.println("Transaction failed");
											break;
											
										case 3:
											System.out.println("Your account Details");
											((BankAccount) accounts.get(accounts.size()-1)).viewaAccountDetails(accounts,username);
											System.out.println("Confirm the Account number to be deposited\n");
											int accountNo=sc.nextInt();
											System.out.println("Enter the Amount to be deposited\n");
											int deposit=sc.nextInt();
											boolean dep=((BankAccount) accounts.get(accounts.size()-1)).makeDeposit(accounts,username,accountNo,deposit);
											if(dep)
												System.out.println("Deposit succcessful");
											else
												System.out.println("Deposit failed");
											break;
											
										case 4:
											System.out.println("Your account Details\n");
											((BankAccount) accounts.get(accounts.size()-1)).viewaAccountDetails(accounts,username);
											
											System.out.println("Select the Utility type to make payment \n1.Mobile Recharge\n2.Electricity Bill\n3.Wifi Bill\n4.Insurance\n5.Gas Bill\n");
											int util=sc.nextInt();
											String Utility=null;
											if (util==1)
												Utility="Mobile Recharge";
											else if(util==2)
												Utility="Electricity Bill";
											else if(util==3)
												Utility="Wifi Bill";
											else if(util==4)
												Utility="Insurance";
											else if(util==5)
												Utility="Gas Bill";
											
											System.out.println("\nConfirm the Account number from which the utils to be payed");
											int accountNum=sc.nextInt();
											System.out.println("\nEnter the Amount ");
											int payment=sc.nextInt();
											accounts.add(new BankAccount());
											boolean success=((BankAccount) accounts.get(accounts.size()-1)).payUtils(accounts,username,accountNum,payment);
											if (success)
												System.out.println("\nThe amount " + payment + " has been payed towards the " +Utility);
											else
												System.out.println("\nPayment Failed");
											break;
											
										case 5:
											System.out.println("You can edit the Name and Contact details . Enter your choice to edit \n\n1.Name\n2.Contact \n");
											int editCh=sc.nextInt();
											accounts.add(new BankAccount());
											if (editCh==1) {
												System.out.println("\nEnter the New name to be updated");
												String name =sc.next();
												boolean ename=((BankAccount) accounts.get(accounts.size()-1)).editName(accounts,username,name);
												if(ename) {
													
													System.out.println("\nUpdate name succcessful");
													((BankAccount) accounts.get(accounts.size()-1)).viewaAccountDetails(accounts,username);
												}
												else
													System.out.println("\nUpdate name failed");
											}
											else if(editCh==2)
											{
												System.out.println("\nEnter the New Contact to be updated");
												String contact =sc.next();
												boolean econtact=((BankAccount) accounts.get(accounts.size()-1)).editContact(accounts,username,contact);
												if(econtact) {
													((BankAccount) accounts.get(accounts.size()-1)).viewaAccountDetails(accounts,username);
													System.out.println("\nUpdate contact succcessful");
												}
												else
													System.out.println("\nUpdate contact failed");
											}
											break;
											
											
										
									}
									System.out.println("Do you want to perform any other task\n press 1 to continue \n 0 to quit");
									exitChoice=sc.nextInt();
							}while(exitChoice==1);
							System.exit(0);
							
						}
					
						
						
						else 
						{
							System.out.println("Login failed .Would you like to retry (Press \n 1 - for retry \n0 - for Quit) ");
							int option=sc.nextInt();
							if (option==1) 
								continue label ; 
							
							if (option==0)
								System.exit(0);
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
					boolean stat=true;
					do {
						for(BankAccount a:accounts) {
							if (a !=null && (a.getClass().getName()).equals("BankAccount"))
							{
								if(((BankAccount) a).isExistUsername(username)) {
									System.out.println("Reenter the Username as the entered user name is already exist\n");
									username=sc.next();	
									((LoginPage) accounts.get(accounts.size()-1)).setUsername(username);
									stat=true;
								}
							}
						if(stat=false)
							break;
						}
					}while(stat=false);
					((LoginPage) accounts.get(accounts.size()-1)).createLogin();
					System.exit(0);
				}
			}
		}
	}
	
	
	public static void parsefile(ArrayList<BankAccount> accounts) throws FileNotFoundException {

		try {
			
			Scanner scan = new Scanner(new File("logindetails.txt"));
			while(scan.hasNext()) {
				String line = scan.nextLine().toString();
				String[] credential = line.split(",");
				accounts.add(new LoginPage(credential[0],credential[1],credential[2]));
			}	
		}catch (IOException e) {
	        System.out.println(" cannot read to file logindetails " );
		}
		try {
			Scanner scan = new Scanner(new File("AccountDetails.txt"));
			while(scan.hasNext()) {
				String line = scan.nextLine().toString();
				String[] details = line.split(",");
				accounts.add(new BankAccount(details[0],details[1], details[2], details[3],Integer.parseInt(details[4].trim()), Integer.parseInt(details[5].trim()), Integer.parseInt(details[6].trim())));
			}	
		}catch (IOException e) {
	        System.out.println(" cannot read to file AccountDetails" );
		}try {
			
			Scanner scan = new Scanner(new File("UserDetails.txt"));
			while(scan.hasNext()) {
				String line = scan.nextLine().toString();
				String[] user = line.split(",");
				accounts.add(new Banker(user[0],user[1]));
			}	
		}catch (IOException e) {
	        System.out.println(" cannot read to file UserDetails " );
		}
	}
	public static void createDefaultUsers()throws FileNotFoundException{
		 
			try {
				 File loginFile= new File("logindetails.txt");
				 
				 if(loginFile.length()==0) {
					FileWriter myWriter = new FileWriter("logindetails.txt",true);
					
					
					String credentials1 = "Syed,test1,Customer" ;
					String credentials2 = "Adarsh,test2,Customer" ;
					String credentials3 = "Kaur,test3,Banker" ;
					String credentials4 = "Wajeeh,test4,Banker" ;
					myWriter.write(credentials1);
					myWriter.write("\n");
					myWriter.write(credentials2);
					myWriter.write("\n");
					myWriter.write(credentials3);
					myWriter.write("\n");
					myWriter.write(credentials4);
					myWriter.write("\n");
					myWriter.close();
				 }

			}catch (IOException e) {
		        System.out.println(" cannot write to file " );
			}
			try {
				 File loginFile= new File("AccountDetails.txt");
				 
				 if(loginFile.length()==0) {
					FileWriter myWriter1 = new FileWriter("AccountDetails.txt",true);
					
					
					String account1 = "Syed,Savings,SyedRabiyama,9789547607,100001,10000,100" ;
					String account2 = "Syed,Current,SyedRabiyama,9789547607,100002,1000,1000" ;
					String account3 = "Adarsh,Savings,Adharshdeep,9876543210,100003,5000,1000" ;

					myWriter1.write(account1);
					myWriter1.write("\n");
					myWriter1.write(account2);
					myWriter1.write("\n");
					myWriter1.write(account3);
					myWriter1.write("\n");
					myWriter1.close();
				 }

			}catch (IOException e) {
		        System.out.println(" cannot write to file " );
			}
			try {
				 File loginFile= new File("UserDetails.txt");
				 
				 if(loginFile.length()==0) {
					FileWriter myWriter2 = new FileWriter("UserDetails.txt",true);
					
					
					String user1="Kaur,Narindar";
					String user2="Wajeeh,Husainee";


					myWriter2.write(user1);
					myWriter2.write("\n");
					myWriter2.write(user1);
					myWriter2.write("\n");
					myWriter2.close();
				 }

			}catch (IOException e) {
		        System.out.println(" cannot write to file " );
			}
			
	}
}
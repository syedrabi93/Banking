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
		
		//Arraylist creation for the superclss BankAccount
		ArrayList<BankAccount> accounts=new ArrayList<BankAccount>();

		//Call to create default rerecords in file system incase of empty files for testing purpose
		createDefaultUsers();
		//Call to initiate the arraylistof objects by parsing files
        parsefile(accounts);

        
		
		//goto label for recursive login try on failure
		label:	
		//loops until the user opts to quit
		while (true)
		{
			System.out.println("Enter the type of user login \n\n \t1.Banker \t\t 2.Customer\n\n\n\nPress (1 or 2):");
			int choice=sc.nextInt();
			String username=null;
			String password=null;
			String userType = null;
			int ch =0;
			
				switch(choice) {
				//if choice of do bankers login & activities
				case 1: System.out.println("\tExisting user PRESS - 1 \n\tTo create new credentials PRESS - 2\n");
				ch=sc.nextInt();
				
				if (ch==1)
				{
					
					System.out.println("Enter the Username:");
					username=sc.next();
					System.out.println("Enter the password:");
					password=sc.next();
					userType="Banker";
					boolean status=false;
					for(BankAccount a:accounts) {
						if (a instanceof LoginPage) {
							//calls login method to validate credentials
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
										//checks if the user is active bank employee
										exist=((Banker)a).isUserExist(username);
										if (exist)
											break;
									}
									if(!exist) {
										System.out.println("You are not an active user. Contact the bank for further details");
										System.exit(0);
									}
									else
										System.out.println("successfully logged in\n");
									
								int exitChoice=1;
								do {
									
									System.out.println("Select the operation you would like to do\n\n\t1.List accounts \n\n\t2.Add account \n\n\t3.Delete Acount\n ");
									System.out.println("Enter your option (1/2/3):");
									int opt=sc.nextInt();
									
									//switch to activities based on user 
									switch(opt)
									{
									case 1:
										System.out.println("| \bClientID  |\t accountNo  |\t accountType  |\t ClientName  |\t    Contact   |");
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
										System.out.println("Is the customer already holding any other account (press 1 for yes / 0 for no)");
										int op1=sc.nextInt();
										//if exiting user list the exiting accounts and auto populate personal details
										if (op1==1) 
										{
											System.out.println("Enter the Client ID:");
											 ClientID=sc.next();
											 boolean isexist=false;
											for(BankAccount a:accounts) {
												if (a !=null && (a.getClass().getName()).equals("BankAccount"))
												{
													if(((BankAccount) a).isExistClientID(ClientID)) {
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
										//if new customer get the details from user
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
										int accountNo=0;
										//auto generate next possible acccount id
										for(BankAccount a:accounts) 
											if (a !=null && (a.getClass().getName()).equals("BankAccount"))
												accountNo=((BankAccount) a).generateAccountNumber();
										
										accountNo++;
										int currentBalance=1000;
										int previousTransaction=0;
										//add new bankaccount objects with new client details.
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
										//loop through the objects and delete the account
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
										
									default:
										 System.out.println("\nThe Entered option is invalid.Please try again\n");
										 break;
									}
									System.out.println("Do you want to perform any other task\nPress 1 to continue \n 0 to Quit");
									exitChoice=sc.nextInt();
								}while(exitChoice==1);
								System.out.println("\n\n \t\tThanks you. Logging out !!");
								System.exit(0);
							 }
					
						
						
						else 
						{
							System.out.println("Login failed .Would you like to retry \nPress \n1 - for retry\n0 - for Quit ");
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
					
					//if choice 2 do client login and related activities
				case 2: System.out.println("\tExisting Customer PRESS - 1 \n\tTo create new credentials PRESS - 2\n");
				ch=sc.nextInt();
				
				if (ch==1)
				{
					
					System.out.println("Enter the Username:");
					username=sc.next();
					System.out.println("Enter the password:");
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
										exist=((BankAccount)a).isExistClientID(username);
										if (exist)
											break;
									}
									if(!exist) {
										System.out.println("You are not an active user to access");
										System.exit(0);
									}
									else
										System.out.println("\t\tSuccessfully logged in\n");
								int exitChoice=1;
								do {
									System.out.println("Select the operation you would like to do\n\n\t1.View Account \n\n\t2.Transfer Amount \n\n\t3.Deposit Amount \n\n\t4.Withdraw Amount\n\n\t5.Pay utilities \n\n\t6.Update Personal Details\n");
									System.out.println("Enter the option between 1 to 5:\n");
									int ch2=sc.nextInt();
								
								
									switch(ch2)
									{
										case 1: 
											((BankAccount) accounts.get(accounts.size()-1)).viewAccountDetails(accounts,username);
											break;
										
										case 2:
											System.out.println("Your account Details");
											((BankAccount) accounts.get(accounts.size()-1)).viewAccountDetails(accounts,username);
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
											((BankAccount) accounts.get(accounts.size()-1)).viewAccountDetails(accounts,username);
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
											((BankAccount) accounts.get(accounts.size()-1)).viewAccountDetails(accounts,username);
											System.out.println("\nConfirm the Account number from which you want to withdraw the amount");
											int accNum=sc.nextInt();
											System.out.println("\nEnter the Amount ");
											int withdrawal=sc.nextInt();
											accounts.add(new BankAccount());
											boolean stat=((BankAccount) accounts.get(accounts.size()-1)).transaction(accounts,username,accNum,withdrawal);
											if (stat)
												System.out.println("\nThe amount " + withdrawal + " has been withdrawn");
											else
												System.out.println("\nTransacction Failed");
											break;
											
										case 5:
											System.out.println("Your account Details\n");
											((BankAccount) accounts.get(accounts.size()-1)).viewAccountDetails(accounts,username);
											
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
											boolean success=((BankAccount) accounts.get(accounts.size()-1)).transaction(accounts,username,accountNum,payment);
											if (success)
												System.out.println("\nThe amount " + payment + " has been payed towards the " +Utility);
											else
												System.out.println("\nPayment Failed.Please verify the details entered and try again\n");
											break;
											
										case 6:
											System.out.println("You can edit the Name and Contact details . Enter your choice to edit \n\n1.Name\n2.Contact \n");
											int editCh=sc.nextInt();
											accounts.add(new BankAccount());
											if (editCh==1) {
												System.out.println("\nEnter the New name to be updated");
												String name =sc.next();
												boolean ename=((BankAccount) accounts.get(accounts.size()-1)).editName(accounts,username,name);
												if(ename) {
													
													System.out.println("\nUpdate name succcessful");
													((BankAccount) accounts.get(accounts.size()-1)).viewAccountDetails(accounts,username);
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
													((BankAccount) accounts.get(accounts.size()-1)).viewAccountDetails(accounts,username);
													System.out.println("\nUpdate contact succcessful");
												}
												else
													System.out.println("\nUpdate contact failed");
											}
											break;
											
										 default:
											 System.out.println("\nThe Entered option is invalid.Please try again\n");
											 break;
											
											
										
									}
									System.out.println("Do you want to perform any other task\nPress 1 to continue \n 0 to quit");
									exitChoice=sc.nextInt();
							}while(exitChoice==1);
								System.out.println("\n\n \t\tThanks you. Loggin out !!");
							System.exit(0);
							
						}
					
						
						
						else 
						{
							System.out.println("Login failed .Would you like to retry \nPress \n1 - for retry \n0 - for Quit ");
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
								if(((BankAccount) a).isExistClientID(username)) {
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
				default:
					 System.out.println("\nThe Entered option is invalid.Please try again\n");
					 break;
			}
				
				
		}
	}
	
	/*
	 * Initial method to parse the files holding Account details, user details and the login details
	 * and create the array of objects for further use
	 * @param : Araraylist to add objects of all three classed (BankAccount,LoginPage,Banker) from the corressponding  files 
	 * @return :void
	 */
	public static void parsefile(ArrayList<BankAccount> accounts) throws FileNotFoundException {

		try {
			
			Scanner scan = new Scanner(new File("LoginDetails.txt"));
			while(scan.hasNext()) {
				String line = scan.nextLine().toString();
				if(!line.isEmpty()) {
					String[] credential = line.split(",");
					accounts.add(new LoginPage(credential[0],credential[1],credential[2]));
				}
			}	
		}catch (IOException e) {
	        System.out.println("cannot read to file logindetails" );
		}
		try {
			Scanner scan = new Scanner(new File("AccountDetails.txt"));
			while(scan.hasNext()) {
				String line = scan.nextLine().toString();
				if(!line.isEmpty()) {
					String[] details = line.split(",");
					accounts.add(new BankAccount(details[0],details[1], details[2], details[3],Integer.parseInt(details[4].trim()), Integer.parseInt(details[5].trim()), Integer.parseInt(details[6].trim())));
				}
			}	
		}catch (IOException e) {
	        System.out.println("cannot read to file AccountDetails" );
		}try {
			
			Scanner scan = new Scanner(new File("UserDetails.txt"));
			while(scan.hasNext()) {
				String line = scan.nextLine().toString();
				if(!line.isEmpty()) {
					String[] user = line.split(",");
					accounts.add(new Banker(user[0],user[1]));
				}
			}	
		}catch (IOException e) {
	        System.out.println("cannot read to file UserDetails " );
		}
	}
	
	/*
	 * For Test purpose, if incase the files holding account details and logins are empty, it requires to manually insert values and test.
	 * To avoid that i have added default values that can be used at anytime of building this project if incase the files are empty
	 * creates default credentails andthe corresponding accounts fortesting purpose
	 * @param : null
	 * @return :void
	 */
	public static void createDefaultUsers()throws FileNotFoundException{
		 
			try {
				 File loginFile= new File("LoginDetails.txt");
				 
				 if(loginFile.length()==0) {
					FileWriter myWriter = new FileWriter("LoginDetails.txt",true);
					
					
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
					myWriter2.write(user2);
					myWriter2.write("\n");
					myWriter2.close();
				 }

			}catch (IOException e) {
		        System.out.println(" cannot write to file " );
			}
			
	}
}
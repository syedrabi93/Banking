import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter; 




public class LoginPage extends Banker{
	
	private String username;
	private String password;
	private String userType;
	
	Scanner sc = new Scanner(System.in);


	//  Constructor for all members inclusing super class
	public LoginPage(String clientID, String accountType, String clientName, String contact, int accountNo,
			int currentBalance, int previousTransaction, String username, String password, String userType) {
		super(clientID, accountType, clientName, contact, accountNo, currentBalance, previousTransaction);
		this.username = username;
		this.password = password;
		this.userType = userType;

	}
	
	//Constructor for the members of LoginPage class
	public LoginPage(String username, String password, String userType) {
		super();
		this.username=username;
		this.password=password;
		this.userType=userType;
		
	}

	//getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	/*
	 * Method to validate login credentials based oon user type and credentials
	 * @params :String username
	 * 			String password
	 * 			String userType
	 * @return : boolean -returns true on successful login
	 */
	public boolean login (String username,String password, String userType)  {
		String uname =null;
		String passwd=null;
		String uType=null;
		
		uname=this.getUsername();
		passwd=this.getPassword();
		uType=this.getUserType();
		
		if ((uname.equals(username)) && (passwd.equals(password)) && (uType.equals(userType)))
		{
			return true;
		}
		return false;
	}
	
//	public void createLogin () throws FileNotFoundException {
//		try {
//			FileOutputStream f = new FileOutputStream(new File("/Users/syedrabi/Project_1/BankingProject/src/logindetails.txt"));
//			ObjectOutputStream o = new ObjectOutputStream(f);
//			o.writeObject(this);
//			o.close();
//			
//		} catch (IOException e) {
//	        System.out.println(" cannot write to file " );
//	        
//		}
//	}
		
	/*
	 * Method to create logins for the users /customers on demand
	 * Method stores logindetails file with new credentials for future usage
	 * @oaram: null
	 * @return void
	 */
	public void createLogin () throws FileNotFoundException {
		
	    String credentials =null;
		try {
			FileWriter myWriter = new FileWriter("logindetails.txt",true);
			
			
			credentials = this.username + "," +this.password + "," + this.userType ;
			myWriter.write(credentials);
			myWriter.write("\n");
			myWriter.close();
			System.out.println("Login created successfully.Please login from the beginning");
		
		}catch (IOException e) {
	        System.out.println(" cannot write to file " );
		}
	
			
			
	}
//
//	
//	public boolean login (String username,String password, String userType) throws FileNotFoundException {
//		
//		try {
//			
//			Scanner scan = new Scanner(new File("/Users/syedrabi/Project_1/BankingProject/src/logindetails.txt"));
//			while(scan.hasNext()) {
//				String line = scan.nextLine().toLowerCase().toString();
//				if(line.contains(username)) {
//					String[] credential = line.split(",");
//					if ((credential[1].equals(password)) && (credential[2].equals(userType)))
//						return true;			
//				}
//			}	
//	}catch (IOException e) {
//        System.out.println(" cannot write to file " );
//	}
//		return false;
//	}
}

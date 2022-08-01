import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

public class Signup_login {
	public String role(String role) {
		System.out.println("*****************Press 1 if you are a admin**********************");
		System.out.println("*****************Press 2 if you are a User***********************");
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		int secret_code = 8225;
		
		switch (i) {
		case 1: 
			System.out.println("Enter The Secret code given for a admin");
			int code = in.nextInt();
			if(code==secret_code) {
				System.out.println("Access accepted...You are accepted for the admin role....");
				role = "Admin";
			}
			else {
				System.err.println("Access denied... Your access for the admin role could not be processed....This page is terminated try again!!!");
				System.exit(0);
			}
		    break;
		case 2:
			System.out.println("You are accepted for the user role....");
			role = "User";
			break;
		default : 
			System.err.println("Wrong Choice");
		    break;
	}
		return role;
		
	}
	public void Sign_up(String key,String value) {
		Hashtable<String, String> h1 = new Hashtable<String, String>();
		Scanner sc = new Scanner(System.in);
			h1.put(key, value);
			System.out.println("Re-enter the password: ");
			String Re_passw = sc.next();
			if(!Re_passw.equals(value)) {
				System.err.println("*************The password is mismatching...Try again*************");
				System.exit(0);
			}
			Signup_login  sl = new Signup_login();
			String role = sl.role(null);
			Book bk = new Book();
			try {
			Connection con = bk.getconn();
			String s1 = "insert into user_signup(username,password,role) values(?,?,?)";
			 PreparedStatement stmt = con.prepareStatement(s1);
			 stmt.setString(1,key);
		       stmt.setString(2,value);
		       stmt.setString(3, role);
		       int insert = stmt.executeUpdate();
		       if(insert>0) {
		    	   if(Re_passw.equals(value)){
						System.out.println("*************************Your account is successfully created************************");
						login();
					}
					else {
						System.err.println("*************The password is mismatching...Try again*************");
						System.exit(0);
					}
		       }
		      
		       }catch(SQLException e) {
		    	   System.out.println("********You already have an account, you will be redirected to the login page********");
		    	   login(); 
		       }    
		}
	public String getCaptcha() {
	    char data[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
	            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
	            'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
	            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
	            'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
	            '7', '8', '9' };
	    char index[] = new char[7];

	    Random r = new Random();
	    int i = 0;

	    for (i = 0; i < (index.length); i++) {
	        int ran = r.nextInt(data.length);
	        index[i] = data[ran];
	    }
	    return new String(index);
	}
	public void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("*                                                                                   *");
		System.out.println("*                                                                                   *");
		System.out.println("*****I am not a robot:::Enter the given captcha to continue with the login page******");
		Signup_login  sl = new Signup_login();
		String Real_captcha = sl.getCaptcha();
		System.out.println(Real_captcha);
		String Captcha = sc.next();
		if(Captcha.equals(Real_captcha)) {
			System.out.println("*****************The captcha is correct*****************");
			System.out.println("*************Redirectiong to the Login Page*************");
		}
		else {
			System.err.println("**********The captcha is incorrect...Try again**********");
			System.exit(0);
		}
	}
}

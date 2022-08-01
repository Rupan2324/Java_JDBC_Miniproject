
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Services {
	public void Sign_log() throws SQLException{
		System.out.println("Press 1 to Sign Up if You don't have a account OR");
		System.out.println("Press 2 to login if you already have a account");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		Signup_login sp = new  Signup_login();
		switch (i) {
		case 1: 
			sp.Sign_up(null, null);
		    break;
		case 2:
			sp.login();
		default : 
			System.err.println("Wrong Choice");
		    break;
	}
	}
	
	public static void main(String[] args) throws SQLException {
		String No="y";
		String s = "y";
		String key="",value="";
		outer: while (No.equals("y")) {

		Scanner sc1 = new Scanner(System.in);
		System.out.println("*****************************************************************");
		System.out.println("*                                                               *");
		System.out.println("*                                                               *");
		System.out.println("*************WELCOME TO HOME TECHNICIAN AND SERVICES*************");
		System.out.println("*********Press 1 to Sign Up if You don't have an account*********");
		System.out.println("*********Press 2 to login if you already have an account*********");
		int i = sc1.nextInt();
		Signup_login sp = new  Signup_login();
		if(i == 1) {
			System.out.println("*****************Redirectiong to the Signup Page*****************");
			 System.out.println("***Your username should be in the format of firstname_lastname(or)initial***");
			System.out.println("Create a new username: ");
			key = sc1.next();
			System.out.println("Create a new password: ");
			value = sc1.next();
			sp.Sign_up(key,value);
		}
		else if(i==2) {
			sp.login();
		}
		else { 
			System.err.println("Wrong Choice");
			System.exit(0);
	}
		String usern,passw;
		System.out.println("Now You are ready for the Login...");
		System.out.println("Please Enter The Login Details:");
		 System.out.println("Enter your Username: ");
		 usern = sc1.next();
		 System.out.println("Enter your password: ");
		 passw = sc1.next();
		 Book bk = new Book();
			Connection con = bk.getconn();
			Statement stmt = con.createStatement();
			String query = "select * from user_signup where username ='"+usern+"' and password='"+passw+"';";
			ResultSet rs = stmt.executeQuery(query);
			String u="",p="";
			while(rs.next()) {
				u=rs.getString(1);
				p=rs.getString(2);
			
			}
		if(usern.equals(u) && passw.equals(p))
		{
		System.out.println("*****************your authentication is successfull******************");
		String query4 = "select role from user_signup where username='"+usern+"';";
	    rs = stmt.executeQuery(query4);
	    String z ="";
	    while(rs.next()) {
			z=rs.getString(1);
	    }
	    if(z.equals("Admin")) {
	    	Admin_access ad = new Admin_access();
	    	ad.admin();
	    	
	    }
	    else {
		while (s.equals("y")) {
			System.out.println("SERVICES PROVIDED BY US ARE");
			System.out.println("CAR WASHING");
			System.out.println("ELECTRICIAN");
			System.out.println("TV REPAIR");
			System.out.println("PLUMBER");
        System.out.println("Press 1 to START your booking or Press 2 to EXIT");
		Scanner sc = new Scanner(System.in);
		int j = sc.nextInt();

		Book bk1 = new Book();
		switch (j) {
		case 1: 
			bk1.Services_Home();
		    break;
		case 2:
			System.out.println("************Session Closed...Thank You Visit Again...*************");
			System.exit(0);
		default : 
			System.err.println("Wrong Choice");
		    break;
	}
		String query1 = "select role from user_signup where username='"+usern+"';";
	    rs = stmt.executeQuery(query1);
	    String m ="";
	    while(rs.next()) {
			m=rs.getString(1);
		
		}
		String Continue="";
		Scanner in = new Scanner(System.in);
		if(m.equals("User")) {
			System.out.println("*********As a user you can review your bookings details till now...If you want to verify type 'yes'...*********");
			Continue = in.next();
			if(Continue.equals("yes")) {
				String query2 = "select * from services where name ='"+usern+"';";
				rs = stmt.executeQuery(query2);

				while(rs.next()) {
					System.out.println("Name: "+rs.getString(1)+"\tAddress: "+rs.getString(2)+"\tType_of_service: "+rs.getString(3)+"\tBooking date: "+rs.getString(4)+"\tPhone Number: "+rs.getString(5));
				}
			}
			else {
				System.out.println("************Session Closed...Thank You Visit Again...*************");
				System.exit(0);
			}
		}
		System.out.println("If you want to continue again then press y/n");
		s = sc1.next();
		if (s.equals("n"))		{
		    
			System.out.println("************Session Closed...Thank You Visit Again...*************");
			System.exit(0);
		}
		}
	    }
		}
		else {
			System.err.println("INVALID DETAILS... Please Enter Correct Details....Incase you don't have an account please sign up...Enter y to start again...");
		 	 No = sc1.next();
			 }
		}
	  }
}

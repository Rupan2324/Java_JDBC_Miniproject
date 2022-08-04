
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Book {
	public Connection getconn()
	{
	Connection con = null;
	try
	{
	String url= "jdbc:mysql://localhost:3306/prodapt_miniproject";
	String username="root";
	String pass="root";
	con = DriverManager.getConnection(url,username,pass);
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return con;
	}
	Scanner in = new Scanner(System.in);

	public void Services_Home() {
			System.out.println("*****Please enter User details*****");
			try {
				String m = "";
		
			 Connection con = getconn();
			 String s1 = "insert into services(name,address,Type_of_service,booking_date,phone_no) values(?,?,?,?,?)";
			 PreparedStatement stmt = con.prepareStatement(s1);
			 System.err.println("NOTE: kindly avoid to book for any other persons from your account...Use your account for your own bookings...");
			 			System.out.println("...Your name should be same as your username...");
			             System.out.println("Enter your Name: ");
			             String name = in.nextLine();
			             System.out.println("Enter your address: ");
			             String address = in.nextLine();
			             System.out.println("Enter the type of service you want");
			             String Type_of_service = in.nextLine();
			             if(Type_of_service.equalsIgnoreCase("CAR WASHING") || 
			            		 Type_of_service.equalsIgnoreCase("ELECTRICIAN") ||
			            		 Type_of_service.equalsIgnoreCase("TV REPAIR") ||
			            		 Type_of_service.equalsIgnoreCase("PLUMBER")){
			             System.out.println("Enter the booking date in the format of 'YYYY-MM-DD': ");
			             String date = in.nextLine();
			        
			             System.out.println("Enter your 10 digit phone number: ");
			             String phone_no = in.nextLine();
			             if(phone_no.length()==10) {
			       stmt.setString(1,name);
			       stmt.setString(2,address);			     
			       stmt.setString(3, Type_of_service);
			       stmt.setString(4,date);
			       stmt.setString(5,phone_no);
			       System.out.println("*************Slot booked...Your Technician Will Be There Ontime...*************");
			       int insert = stmt.executeUpdate();
			             }
			             else {
			            	 System.err.println("Sorry..Phone number should be in 10 digits..");
			    
			             }
			             }
			             else {
					    	   System.err.println("Sorry this service is not provided here...");
					       }
			}
			catch(SQLException esql) {
				System.err.println("Fill all the required fields... "+esql);
			}
			       
		
	}
}


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.Scanner;

public class Admin_access {
	public void admin() {
		System.out.println("****************************WELCOME ADMIN*******************************");
	Scanner in = new Scanner(System.in);
		Admin_access ad = new Admin_access();
    	ad.displayServices();
	System.out.println("*****************************");
	System.out.println("*                           *");
	System.out.println("Press 1 to insert any details");
	System.out.println("Press 2 to update any details");
	System.out.println("Press 3 to delete any details");
	System.out.println("Press 4 to view any details");
	System.out.println("*                           *");
	System.out.println("*****************************");
	int i = in.nextInt();

	switch(i) {
	case 1:
		ad.insert();
	case 2:
		ad.update();
		break;
	case 3:
		ad.delete();
		break;
	case 4:
		ad.select();
		break;
	default:
		System.err.println("Wrong Choice");
		break;
	}
	}
	public void insert() {
		Scanner in = new Scanner(System.in);
		System.out.println("*****Please enter User details*****");
		try {
			String m = "";
	      Book bk = new Book();
		 Connection con = bk.getconn();
		 String s1 = "insert into services(name,address,Type_of_service,booking_date,phone_no) values(?,?,?,?,?)";
		 PreparedStatement stmt = con.prepareStatement(s1);
		             System.out.println("Enter user's Name: ");
		             String name = in.nextLine();
		             System.out.println("Enter user's address: ");
		             String address = in.nextLine();
		             System.out.println("Enter the type of service they want");
		             String Type_of_service = in.nextLine();
		             if(Type_of_service.equalsIgnoreCase("CAR WASHING") || 
		            		 Type_of_service.equalsIgnoreCase("ELECTRICIAN") ||
		            		 Type_of_service.equalsIgnoreCase("TV REPAIR") ||
		            		 Type_of_service.equalsIgnoreCase("PLUMBER")){
		             System.out.println("Enter the booking date in the format of 'YYYY-MM-DD': ");
		             String date = in.nextLine();
		        
		             System.out.println("Enter their 10 digit phone number: ");
		             String phone_no = in.nextLine();
		             if(phone_no.length()==10) {
		       stmt.setString(1,name);
		       stmt.setString(2,address);			     
		       stmt.setString(3, Type_of_service);
		       stmt.setString(4,date);
		       stmt.setString(5,phone_no);
		       System.out.println("A row is successfully inserted");
		       int insert = stmt.executeUpdate();
		             }
		             else {
		            	 System.err.println("Sorry..Phone number should be in 10 digits..");
		    
		             }
		             }
		             else {
				    	   System.err.println("Sorry this service is not provided here...");
				       }
		             String r = "";
		     		System.out.println("If you want to continue again then press y");
		     		r=in.next();
		             if(r.equals("y")) {
		             	Admin_access ad = new Admin_access();
		             	ad.admin();
		             }
		             else {
		             	System.out.println("************Session Closed...Thank You Visit Again...*************");
		     			System.exit(0);
		             }
		}
		catch(SQLException esql) {
			System.err.println("Fill all the required fields... "+esql);
		}
		   
	}
	
	public void update() {
		Scanner in = new Scanner(System.in);
		Book bk = new Book();
		try {
		Connection con = bk.getconn();
		System.out.println("Enter Name: ");
        String name = in.nextLine();
        System.out.println("Enter address: ");
        String address = in.nextLine();
        System.out.println("Enter the type of service They want");
        String Type_of_service = in.nextLine();
        System.out.println("Enter the booking date in the format of 'YYYY-MM-DD': ");
        String date = in.nextLine();
        System.out.println("Enter Their 10 digit phone number: ");
        String phone_no = in.nextLine();
        System.out.println("Under which customer's name you want to update the details: ");
        String C_name = in.nextLine();
        
        String update = "update services set name = '"+name+"',address = '"+address+"', Type_of_service = '"+Type_of_service +"',"
        		+ "booking_date = '"+date+"',phone_no = '"+phone_no+"' where name = '"+C_name+"' ;";
		PreparedStatement stmt = con.prepareStatement(update);
		int success = stmt.executeUpdate();
		if(success>0) {
			System.out.println("A row is successfully updated...");
		}
		else {
			System.err.println("Something went wrong...");
		}
		String r = "";
		System.out.println("If you want to continue again then press y");
		r=in.next();
        if(r.equals("y")) {
        	Admin_access ad = new Admin_access();
        	ad.admin();
        }
        else {
        	System.out.println("************Session Closed...Thank You Visit Again...*************");
			System.exit(0);
        }
		}catch(Exception e) {
			System.err.println("Some Error occured "+e);
		}
	}
	
	public void delete() {
		Scanner in = new Scanner(System.in);
		Book bk = new Book();
		try {
			Connection con = bk.getconn();
			System.out.println("which customer's details you want to delete Enter the customer's name: ");
	        String C_name = in.nextLine();
	        System.out.println("Enter 'y' if you really want to delete the details");
	        String m = in.next();
	        if(m.equals("y")) {
			String delete ="delete from services where name = '"+C_name+"';";
			PreparedStatement stmt = con.prepareStatement(delete);
			int success = stmt.executeUpdate();
			if(success>0) {
				System.out.println("A row is successfully deleted...");
			}
			else {
				System.err.println("Something Went wrong...");
			}
			String r = "";
			System.out.println("If you want to continue again then press y");
			r=in.next();
	        if(r.equals("y")) {
	        	Admin_access ad = new Admin_access();
	        	ad.admin();
	        }
	        else {
	        	System.out.println("************Session Closed...Thank You Visit Again...*************");
				System.exit(0);
	        }
	        }
	        else {
	        	System.out.println("Your request for deleting a detail is cancelled...");
	        }
		
		}catch(Exception e) {
			System.err.println("Something went wrong: "+e);
		}
	}
	
	public void select() {
		Scanner in = new Scanner(System.in);
		Book bk = new Book();
		try {
			Connection con = bk.getconn();
			Statement stmt = con.createStatement();
			System.out.println("which customer's details you want to See Enter the customer's name: ");
	        String C_name = in.nextLine();
			String show = "select * from services where  name = '"+C_name+"';";
			ResultSet rs = stmt.executeQuery(show);
			 Formatter fmt = new Formatter();
	          fmt.format("%14s %14s %14s %14s %15s\n", "Name","Address","Type_of_Service","Booking_date","phone_no");
	          int i=0;
	          while(rs.next()) {
	              String name,Address,Type_of_Service,Booking_date,phone_no;
	              name = rs.getString("name");
	              Address= rs.getString("address");
	              Type_of_Service = rs.getString("Type_of_service");
	              Booking_date=rs.getString("booking_date");
	              phone_no=rs.getString("phone_no");
	              fmt.format("%14s %14s %15s %14s %15s\n",name,Address,Type_of_Service,Booking_date,phone_no);
	              i++;
	          }
	          System.out.println(fmt);
              System.out.println(i+" row(s) Shown...");	
			String r = "";
			System.out.println("If you want to continue again then press y");
			r=in.next();
	        if(r.equals("y")) {
	        	Admin_access ad = new Admin_access();
	        	ad.admin();
	        }
	        else {
	        	System.out.println("************Session Closed...Thank You Visit Again...*************");
				System.exit(0);
	        }
		}catch(Exception e) {
			System.err.println("Sorry...Something went wrong: "+e);
		}
	}
	
	public void displayServices(){
		Book bk = new Book();
		try {
			Connection con = bk.getconn();
		Statement stmt = con.createStatement();
		String query2 = "select * from services;";
		ResultSet rs = stmt.executeQuery(query2);
        System.out.println("Table Services");
          Formatter fmt = new Formatter();
          fmt.format("%14s %14s %14s %14s %15s\n", "Name","Address","Type_of_Service","Booking_date","phone_no");
          while(rs.next()) {
              String name,Address,Type_of_Service,Booking_date,phone_no;
              name = rs.getString("name");
              Address= rs.getString("address");
              Type_of_Service = rs.getString("Type_of_service");
              Booking_date=rs.getString("booking_date");
              phone_no=rs.getString("phone_no");
              fmt.format("%14s %14s %15s %14s %15s\n",name,Address,Type_of_Service,Booking_date,phone_no);
          }
          System.out.println(fmt);
		}catch(Exception e) {
			System.err.println("Cannot display the table"+e);
		}

    }
}

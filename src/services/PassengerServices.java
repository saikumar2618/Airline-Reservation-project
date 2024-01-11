package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import validation.ValidateAdmin;
import validation.ValidatePassenger;
import dao.AdminDao;
import dao.PassengerDao;

public class PassengerServices {
	
		public static boolean addNewUser(Connection conn) {
			ValidatePassenger validate=new ValidatePassenger();
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the first name: ");
	        String fname=sc.next();
	        System.out.println("Enter the last name: ");
	        String lname=sc.next();
	        int age = 0;
	        int pincode = 0;
	        long mobile = 0;
	        String city = null;
			String state = null;
			String password=null;
	        try {
	        System.out.println("Enter the age: ");
	        age=sc.nextInt();
	        System.out.println("Enter the city: ");
	        city=sc.next();
	        System.out.println("Enter the state: ");
	        state=sc.next();
	        System.out.println("Enter the pincode: ");
	        pincode=sc.nextInt();
	        System.out.println("Enter the mobileno.: ");
	        mobile=sc.nextLong();
	        System.out.println("Enter the password: ");
	        password=sc.next();
	        boolean result=validate.checkInputs(fname, lname, age, city, state, pincode, mobile,password); 
	        if(result) {
	        	boolean res=PassengerDao.addNewUser(conn, fname, lname, age, city, state, pincode, mobile,password);
	        	if(res) {
	        		
	        		return true;
	        	}
	        	else {
	        		
	        		return false;
	        	}
	        }
	        else {
	        	return false;
	        }
	        }catch(Exception e) {
	        	System.err.println("Invalid Inputs");
	        	//e.printStackTrace();
	        	return false;
	        }
		}
		
		public static boolean checkCredentials(Connection conn) throws SQLException {
	 		Scanner sc=new Scanner(System.in);
	 		int id=0;
	 		try {
	 		System.out.println("Enter UserId to login: ");
	 		 id=sc.nextInt();
	 		}catch(Exception e) {
	 			System.out.println("User id must be number");
	 		}
	 		System.out.println("Enter password: ");
	 		String password=sc.next();
	 		
			boolean res=PassengerDao.checkCredentials(conn, id, password);
	 		if(res) {
	 			
	 			return true;
	 		}
	 		else {
	 			return false;
	 		}
	 	}
		
		public static void getProfile(Connection conn){
			System.out.println(PassengerDao.getProfile(conn));
		}
		
		public static void bookFlight(Connection conn) throws SQLException {
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the FlightId to book the flight");
			String fid=sc.next();
			System.out.println("Select the class to be booked");
			System.out.println("1. First class");
			System.out.println("2. Business class");
			System.out.println("3. Economy class");
			int ch=sc.nextInt();
			String className = null;
			boolean result=true;
			switch(ch) {
			case 1:
				className="First_class";
				break;
			case 2:
				className="Business_class";
				break;
			case 3:
				className="Economy_class";
				break;
			default:
				System.out.println("Invalid choice");
				result=false;
			}
			if(result) {
				boolean res=PassengerDao.bookFlight(conn, fid, className);
				if(res) {
					System.out.println("Happy Journey");
				}
				else {
					System.out.println("Please Try Again");
				}
				
			}
			
		}
		
		public static void cancelBooking(Connection conn) throws SQLException {
			System.out.println("Enter the bookingId to cancel booking");
			Scanner sc=new Scanner(System.in);
			int id=0;
			try {
			 id=sc.nextInt();
			}catch(Exception e) {
				System.out.println("Id should be number");
			}
			System.out.println("Cancelling the booking");
			PassengerDao.cancelBooking(conn, id);
		}
		
		
		public static void getBooking(Connection conn){
			System.out.println(PassengerDao.getBooking(conn));
		}
		
		
		public static void updateName(Connection conn) {
			ValidatePassenger validate=new ValidatePassenger();
			System.out.println("Enter the first name");
			Scanner sc=new Scanner(System.in);
			String fname=sc.next();
			System.out.println("Enter the last name");
			String lname=sc.next();
			boolean result=validate.checkName(fname);
			boolean res=validate.checkName(lname);
			if(result && res) {
				System.out.println("Updating the Name");
				PassengerDao.updateName(conn,fname,lname);
				
	 		}
	 		else {
	 			System.err.println("Invalid Name");
	 		}
			
			
		}
		
		public static void updateAge(Connection conn) {
			ValidatePassenger validate=new ValidatePassenger();
			System.out.println("Enter the updated age");
			Scanner sc=new Scanner(System.in);
			int age=0;
			try {
			age=sc.nextInt();
			}catch(Exception e) {
				System.out.println("Invalid age");
			}
			boolean result=validate.checkAge(age);
			if(result) {
				System.out.println("Updating the Name");
				PassengerDao.updateAge(conn, age);
				
	 		}
	 		else {
	 			System.err.println("Invalid Age");
	 		}
		}
		
		
		public static void updateAddress(Connection conn) {
			ValidatePassenger validate=new ValidatePassenger();
			System.out.println("Enter the city");
			Scanner sc=new Scanner(System.in);
			String city=sc.next();
			System.out.println("Enter the state");
			String state=sc.next();
			System.out.println("Enter the pincode");
			int pincode=0;
			try {
			pincode=sc.nextInt();
			}catch(Exception e) {
				System.out.println("Invalid pincode");
			}
			boolean result=validate.checkName(city);
			boolean res=validate.checkName(state);
			boolean response=validate.checkPincode(pincode);
			if(result && res && response) {
				System.out.println("Updating the address");
				PassengerDao.updateAddress(conn, city, state, pincode);
				
	 		}
	 		else {
	 			System.err.println("Invalid inputs");
	 		}	
		}
		
		
		public static void updateMobile(Connection conn) {
			ValidatePassenger validate=new ValidatePassenger();
			System.out.println("Enter the updated Mobile No.");
			Scanner sc=new Scanner(System.in);
			long mobile=0;
			try {
			mobile=sc.nextLong();
			}catch(Exception e) {
				System.out.println("Invalid mobile");
			}
			boolean result=validate.checkMobile(mobile);
			if(result) {
				System.out.println("Updating the Mobile");
				PassengerDao.updateMobile(conn, mobile);
				
	 		}
	 		else {
	 			System.err.println("Invalid Mobile");
	 		}
		}
		
		public static void updatePassword(Connection conn) {
			ValidatePassenger validate=new ValidatePassenger();
			System.out.println("Enter the Password");
			Scanner sc=new Scanner(System.in);
			String password=sc.next();
			boolean response=validate.checkPassword(password);
			if(response) {
				System.out.println("Updating the password");
				PassengerDao.updatePassword(conn, password);
				
	 		}
	 		else {
	 			System.err.println("Password length must be greater than 8 and less than 20");
	 		}	
		}
}

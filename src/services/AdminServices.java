package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import dao.AdminDao;
import entity.Flight;
import java.util.ArrayList;
import java.util.Date;
import validation.ValidateAdmin;

public class AdminServices {
	
	 	public  boolean checkCredentials(Connection conn) throws SQLException {
	 		Scanner sc=new Scanner(System.in);
	 		System.out.println("Enter adminId: ");
	 		int id=0;
	 		String password="";
	 		try {
	 		 id=sc.nextInt();
	 		System.out.println("Enter password: ");
	 		 password=sc.next();
	 		}catch(Exception e) {
	 			System.out.println("Id should be a number");
	 		}
			boolean res=AdminDao.checkCredentials(conn, id, password);
	 		if(res) {
	 			return true;
	 		}
	 		else {
	 			return false;
	 		}
	 	}
	 	
	 	public void addFlight(Connection conn) throws ParseException, SQLException {
	 		ValidateAdmin validate=new ValidateAdmin();
	 		Scanner sc=new Scanner(System.in);
	 		System.out.println("Enter the FlightId");
	 		String id=sc.next();
	 		System.out.println("Enter the source");
	 		String source=sc.next();
	 		System.out.println("Enter the destination");
	 		String destination=sc.next();
	 		System.out.println("Enter the Flight Name");
	 		String name=sc.next();
	 		System.out.println("Enter departure date dd-mm-yyyy");
			String date=sc.next();
			double Fprice=0.0;
			double Bprice=0.0;
			double Eprice=0.0;
			try {
			 System.out.println("Enter the price for first class");
	 		 Fprice=sc.nextDouble();
	 		 System.out.println("Enter the price for business class");
	 		 Bprice=sc.nextDouble();
	 		 System.out.println("Enter the price for economy class");
	 		 Eprice=sc.nextDouble();
			}catch(Exception e) {
				System.err.println("Please enter valid price");
			}
	
	 		boolean result=validate.checkInputs(id, source, destination, name, date,Fprice,Bprice,Eprice);
	 		if(result) {
	 			SimpleDateFormat ft1=new SimpleDateFormat("dd-MM-yyyy");
	 			java.util.Date dtob=ft1.parse(date);
				long ms=dtob.getTime();
				java.sql.Date sdt=new java.sql.Date(ms);
	 			//System.out.println("Valid Inputs");
				AdminDao.addFlight(conn, id, source, destination, name, sdt,Fprice,Bprice,Eprice);
				
	 		}
	 		else {
	 			System.err.println("Invalid Inputs");
	 		}
	 		
	 	}
	 	
		public  void getFlight(Connection conn){
			AdminDao.getFlight(conn);
		}
		
		public static void cancelFlight(Connection conn) throws SQLException {
			System.out.println("Enter the FlightId to delete");
			Scanner sc=new Scanner(System.in);
			String id=sc.next();
			System.out.println("Cancelling the Flight");
			AdminDao.cancelFlight(conn, id);
		}
		
		public static void updateSource(Connection conn) {
			ValidateAdmin validate=new ValidateAdmin();
			System.out.println("Enter FlightId to update the source");
			Scanner sc=new Scanner(System.in);
			String id=sc.next();
			System.out.println("Enter the updated source");
			String newSource=sc.next();
			boolean result=validate.checkName(newSource);
			if(result) {
				System.out.println("Updating the source");
				AdminDao.updateSource(conn, id, newSource);
				
	 		}
	 		else {
	 			System.err.println("Invalid source");
	 		}
		}
		
		public static void updateDestination(Connection conn) {
			ValidateAdmin validate=new ValidateAdmin();
			System.out.println("Enter FlightId to update the destination");
			Scanner sc=new Scanner(System.in);
			String id=sc.next();
			System.out.println("Enter the updated destination");
			String newDestination=sc.next();
			boolean result=validate.checkName(newDestination);
			if(result) {
				System.out.println("Updating the destination");
				AdminDao.updateDestination(conn, id, newDestination);
				
	 		}
	 		else {
	 			System.err.println("Invalid destination");
	 		}	
		}
		
		public static void updateFlightName(Connection conn) {
			ValidateAdmin validate=new ValidateAdmin();
			System.out.println("Enter FlightId to update the Flight Name");
			Scanner sc=new Scanner(System.in);
			String id=sc.next();
			System.out.println("Enter the updated Flight Name");
			String newName=sc.next();
			boolean result=validate.checkName(newName);
			if(result) {
				System.out.println("Updating the Flight Name");
				AdminDao.updateFlightName(conn, id, newName);
	 		}
	 		else {
	 			System.err.println("Invalid Name");
	 		}
		}
		
		public static void updateFlightDate(Connection conn) throws ParseException {
			ValidateAdmin validate=new ValidateAdmin();
			System.out.println("Enter FlightId to update the Flight Date");
			Scanner sc=new Scanner(System.in);
			String id=sc.next();
			System.out.println("Enter the updated Flight Date");
			String newDate=sc.next();
			boolean result=validate.isValidDate(newDate);
	 		if(result) {
	 			SimpleDateFormat ft1=new SimpleDateFormat("dd-MM-yyyy");
	 			java.util.Date dtob=ft1.parse(newDate);
				long ms=dtob.getTime();
				java.sql.Date sdt=new java.sql.Date(ms);
	 			//System.out.println("Valid Inputs");
				System.out.println("Updating the departure date");
				AdminDao.updateFlightDate(conn, id, sdt);
				
	 		}
	 		else {
	 			System.err.println("Invalid Date");
	 		}
	 	}
		
		public  void getBooking(Connection conn){
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter FlightId to get booking details");
			String id=sc.next();
			System.out.println(AdminDao.getBooking(conn, id));
		}
}

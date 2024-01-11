package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import entity.Passenger;
import entity.Flight;
import entity.Address;
import entity.Bookings;


public class PassengerDao {
	
   //Static passenger object to set and get particular pid
   static Passenger passenger=new Passenger();
   
   //Add new Passenger
   public static boolean addNewUser(Connection conn,String fname,String lname,int age,String city,String state,int pincode,long mobile,String password) throws SQLException {
		try {
		String stmt="insert into passenger values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ppr=conn.prepareStatement(stmt);
		
		int min = 11111;
		int max = 99999;
		//Generate random int value from 50 to 100
		int passengerId= (int)Math.floor(Math.random()*(max-min+1)+min);
		ppr.setLong(1, passengerId);
		ppr.setString(2, fname);
		ppr.setInt(3, age);
		ppr.setString(4,city);
		ppr.setString(5,state);
		ppr.setInt(6,pincode);
		ppr.setString(7,lname);
		ppr.setLong(8,mobile);
		ppr.setNString(9, password);
		int res=ppr.executeUpdate();
		if(res>0) {
			ppr.close();
			System.out.println("Signed up successfully");
			return true;
			
		}
		else {
			ppr.close();
			System.err.println("Error occured while adding");
			return false;
		}
		}catch(SQLIntegrityConstraintViolationException e) {
			System.err.println("FlightId already exists");
			return false;
		}
		
	}
	
   
   //Login check
	public static boolean checkCredentials(Connection conn,int id,String password) throws SQLException {
		String st1="Select * from passenger where passengerid=? and password=?";
		PreparedStatement stmt=conn.prepareStatement(st1);
		stmt.setInt(1, id);
		stmt.setString(2, password);
		ResultSet res=stmt.executeQuery();
		boolean isValid = false;
		try {
			isValid = res.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isValid) {
			  passenger.setpId(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	//Get particular passenger's details
	public static ArrayList<Passenger> getProfile(Connection conn) {
		// oracle 5 reccords ---------------: java Arraylist
		ArrayList<Passenger> itemList = new ArrayList<Passenger>();
		int pid=passenger.getpId();
		
		try {
		String st="select * from passenger where passengerid=?";
		PreparedStatement stmt=conn.prepareStatement(st);
		stmt.setInt(1, pid);
		ResultSet rs = stmt.executeQuery(); 
		while (rs.next()) {
		int id= rs.getInt("passengerid"); 
		String fname= rs.getString("fname"); 
		int age = rs.getInt("age");
		String city= rs.getString("city"); 
		String state= rs.getString("state");
		int pincode= rs.getInt("pincode");
		String lname= rs.getString("lname"); 
		long mobile=rs.getLong("mobile");
		String password= rs.getString("password"); 
		
		Address address=new Address(city,state,pincode);
		Passenger passenger1=new Passenger(id, fname, lname,age,address,mobile,password);
		
		itemList.add(passenger1);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return itemList;
	}
	
	// To book a flight
	public static boolean bookFlight(Connection conn, String FlightId, String className) throws SQLException {
		String st1 = "select seat_availability from classes where flightid=? and class_type=?";
		PreparedStatement ps = conn.prepareStatement(st1);
		ps.setString(1, FlightId);
		ps.setString(2, className);
		ResultSet rs = ps.executeQuery();
		int avail = 0;
		boolean flag = false;
		if (rs.next()) {
			avail = rs.getInt("SEAT_AVAILABILITY");
			flag = true;
		}
		String stmt = "insert into booking(bookingid,pid,fid,class_type) values (airline.nextval,?,?,?)";
		PreparedStatement ppr = conn.prepareStatement(stmt);
		int pid = passenger.getpId();
		ppr.setInt(1, pid);
		ppr.setString(2, FlightId);
		ppr.setString(3, className);
		if (flag == true) {
			if (avail > 0) {
				int res = ppr.executeUpdate();
				String stmt1 = "update classes set seat_availability=seat_availability-1 where flightid=? and class_type=?";
				ppr = conn.prepareStatement(stmt1);
				ppr.setString(1, FlightId);
				ppr.setString(2, className);
				int res1 = ppr.executeUpdate();
				if (res > 0 && res1 > 0) {
					ppr.close();
					System.out.println("Booked successfully");
					return true;

				} else {
					ppr.close();
					System.err.println("Error occured while booking");
					return false;
				}
			} else {
				System.out.println("Seats are full for the selected class..");
				return false;
			}

		} else {
			System.err.println("Invalid flightId");
			return false;
		}

	}
	
	//Cancel booking of particular flight
	public static void cancelBooking(Connection conn,int id) throws SQLException {
		try {
	
			String st1="Select fid,class_type from booking where bookingid=?";
			PreparedStatement stmt=conn.prepareStatement(st1);
			stmt.setInt(1, id);
			ResultSet res=stmt.executeQuery();
		if(res.next()) {
			String fid=res.getString("fid");
			String className= res.getString("class_type");
			String st2="Update classes set seat_availability=seat_availability+1 where class_type=? and flightid=?";
			PreparedStatement stmt2=conn.prepareStatement(st2);
			stmt2.setString(1, className);
			stmt2.setString(2, fid);
			stmt2.executeUpdate();
		    String st3="Delete from booking where bookingid=?";
		    PreparedStatement stmt3=conn.prepareStatement(st3);
		    stmt3.setInt(1, id);
		    stmt3.executeUpdate();
		    stmt3.close();
		    System.out.println("Booking Cancelled Successfully");
		}
		else {
			System.err.println("Booking does not exists");
		}
		stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Get all the booking details for particular passenger
	public static ArrayList<Bookings> getBooking(Connection conn) {
		// oracle 5 reccords ---------------: java Arraylist
		ArrayList<Bookings> itemList = new ArrayList<Bookings>();
		int pid=passenger.getpId();
		
		try {
		String st="select * from booking where pid=?";
		PreparedStatement stmt=conn.prepareStatement(st);
		stmt.setInt(1, pid);
		ResultSet rs = stmt.executeQuery(); 
		while (rs.next()) {
		int id= rs.getInt("bookingid"); 
		String fid= rs.getString("fid"); 
		java.util.Date bdate=rs.getDate("bookingdate");
		String className=rs.getString("class_type");
		
		
		Bookings booking=new Bookings(id,fid,pid,(Date) bdate,className);
		itemList.add(booking);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return itemList;
	}
	
	//Update name for particular passenger
	public static void updateName(Connection conn,String fname,String lname) {
		try {
			int pid=passenger.getpId();
			String st1="Update passenger set fname=?,lname=? where passengerid=?";
			PreparedStatement stmt1=conn.prepareStatement(st1);
			stmt1.setString(1, fname);
			stmt1.setString(2, lname);
			stmt1.setInt(3,pid);
			stmt1.executeUpdate();
			stmt1.close();
			System.out.println("Updated the Name Successfully");
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	//Update age for particular passenger
	public static void updateAge(Connection conn,int age) {
		try {
			int pid=passenger.getpId();
			String st1="Update passenger set age=? where passengerid=?";
			PreparedStatement stmt1=conn.prepareStatement(st1);
			stmt1.setInt(1, age);
			stmt1.setInt(2,pid);
			stmt1.executeUpdate();
			stmt1.close();
			System.out.println("Updated the age Successfully");
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	//Update address for particular passenger
	public static void updateAddress(Connection conn,String city,String state,int pincode) {
		try {
			int pid=passenger.getpId();
			String st1="Update passenger set city=?,state=?,pincode=? where passengerid=?";
			PreparedStatement stmt1=conn.prepareStatement(st1);
			stmt1.setString(1, city);
			stmt1.setString(2, state);
			stmt1.setInt(3,pincode);
			stmt1.setInt(4,pid);
			stmt1.executeUpdate();
			stmt1.close();
			System.out.println("Updated the Address Successfully");
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	//Update mobile no. for particular passenger
	public static void updateMobile(Connection conn,long mobile) {
		try {
			int pid=passenger.getpId();
			String st1="Update passenger set mobile=? where passengerid=?";
			PreparedStatement stmt1=conn.prepareStatement(st1);
			stmt1.setLong(1, mobile);
			stmt1.setInt(2,pid);
			stmt1.executeUpdate();
			stmt1.close();
			System.out.println("Updated the Mobile no. Successfully");
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	//Update password for particular passenger
	public static void updatePassword(Connection conn,String password) {
		try {
			int pid=passenger.getpId();
			String st1="Update passenger set password=? where passengerid=?";
			PreparedStatement stmt1=conn.prepareStatement(st1);
			stmt1.setString(1, password);
			stmt1.setInt(2,pid);
			stmt1.executeUpdate();
			stmt1.close();
			System.out.println("Updated the Password Successfully");
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
}

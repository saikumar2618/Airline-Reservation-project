package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Bookings;
import entity.Flight;
import entity.businessClass;
import entity.economyClass;
import entity.firstClass;

public class AdminDao {
		
		//Login Check
		public static boolean checkCredentials(Connection conn,int id,String password) throws SQLException {
			String st1="Select * from admin where adminid=? and password=?";
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
				return true;
			}
			else {
				return false;
			}
		}
		
		//Add Flight
		public static void addFlight(Connection conn,String flightId,String source,String destination,String name,java.sql.Date date,double Fprice,double Bprice,double Eprice) throws SQLException {
			try {
			String stmt="insert into flight values (?,?,?,?,?)";
			PreparedStatement ppr=conn.prepareStatement(stmt);
			ppr.setString(1, flightId);
			ppr.setString(2, source);
			ppr.setString(3, destination);
			ppr.setString(4,name);
			ppr.setDate(5,date);
			//ppr.setDouble(6,price);
			int res=ppr.executeUpdate();
			String stmt1="insert into classes values(?,'First_class',?,30)";
			ppr=conn.prepareStatement(stmt1);
			ppr.setString(1, flightId);
			ppr.setDouble(2,Fprice);
			int res1=ppr.executeUpdate();
			String stmt2="insert into classes values(?,'Business_class',?,110)";
			ppr=conn.prepareStatement(stmt2);
			ppr.setString(1, flightId);
			ppr.setDouble(2,Bprice);
			int res2=ppr.executeUpdate();
			String stmt3="insert into classes values(?,'Economy_class',?,220)";
			ppr=conn.prepareStatement(stmt3);
			ppr.setString(1, flightId);
			ppr.setDouble(2,Eprice);
			int res3=ppr.executeUpdate();
			
			if(res>0 && res1>0 && res2>0 && res3>0) {
				ppr.close();
				System.out.println("Flight details added successfully");
				
			}
			else {
				ppr.close();
				System.err.println("Error occured while adding");
			}
			}catch(SQLIntegrityConstraintViolationException e) {
				System.err.println("FlightId already exists");
			}
			
		}
		
		
		//Get all flight details
		public static ArrayList<Flight> getFlight(Connection conn) {
			ArrayList<Flight> itemList = new ArrayList<Flight>();
			
			try {
			Statement stmt = conn.createStatement();
			String st1 = "select * from flight";
			ResultSet rs = stmt.executeQuery(st1); 



			while (rs.next()) {
			ArrayList<firstClass> itemList1 = new ArrayList<firstClass>();
			ArrayList<businessClass> itemList2 = new ArrayList<businessClass>();
			ArrayList<economyClass> itemList3 = new ArrayList<economyClass>();
			String id= rs.getString("flightid"); 
			String source = rs.getString("source");
			String destination= rs.getString("destination"); 
			String name= rs.getString("flightname");
			java.util.Date date= rs.getDate("departuredate");
			//double price=rs.getDouble("price");

			//Flight item=new Flight(id,source,destination,name,(Date) date);
			
			
			//itemList.add(item);
			String st2 = "select price,seat_availability from classes where flightid=? and class_type='First_class'";
			PreparedStatement ps=conn.prepareStatement(st2);
			ps.setString(1, id);
			ResultSet res1 = ps.executeQuery();
			if(res1.next()) {
				double price=res1.getDouble("price");
				int seats=res1.getInt("seat_availability");
				firstClass first=new firstClass(id,source,destination,name,(Date) date,price,seats);
				itemList1.add(first);
			}
			for(int i=0;i<itemList1.size();i++)
			{
				System.out.println(itemList1.get(i));
			}
			
			String st3 = "select price,seat_availability from classes where flightid=? and class_type='Business_class'";
			ps=conn.prepareStatement(st3);
			ps.setString(1, id);
			ResultSet res2 = ps.executeQuery();
			if(res2.next()) {
				double price=res2.getDouble("price");
				int seats=res2.getInt("seat_availability");
				businessClass business=new businessClass(id,source,destination,name,(Date) date,price,seats);
				itemList2.add(business);
			}
			for(int i=0;i<itemList2.size();i++)
			{
				System.out.println(itemList2.get(i));
			}
			
			String st4 = "select price,seat_availability from classes where flightid=? and class_type='Economy_class'";
			ps=conn.prepareStatement(st4);
			ps.setString(1, id);
			ResultSet res3 = ps.executeQuery();
			if(res3.next()) {
				double price=res3.getDouble("price");
				int seats=res3.getInt("seat_availability");
				economyClass economy=new economyClass(id,source,destination,name,(Date) date,price,seats);
				itemList3.add(economy);
			}
			for(int i=0;i<itemList3.size();i++)
			{
				System.out.println(itemList3.get(i));
			}
			System.out.println("========================");
			
			}
			} catch (SQLException e) {
			e.printStackTrace();
			}
			return itemList;
		}
		
		
		//Cancel Particular Flight details
		public static void cancelFlight(Connection conn,String id) throws SQLException {
			try {
		
			String st1="Select * from flight where flightid=?";
			PreparedStatement stmt=conn.prepareStatement(st1);
			stmt.setString(1, id);
			ResultSet res=stmt.executeQuery();
			if(res.next()) {
			String st="Delete from classes where flightid=?";
			stmt=conn.prepareStatement(st);
			stmt.setString(1, id);
			stmt.executeUpdate();
			String st3="Delete from booking where fid=?";
			PreparedStatement stmt2=conn.prepareStatement(st3);
			stmt2.setString(1, id);
			stmt2.executeUpdate();
			stmt2.close();
			String st2="Delete from flight where flightid=?";
			PreparedStatement stmt1=conn.prepareStatement(st2);
			stmt1.setString(1, id);
			stmt1.executeUpdate();
			stmt1.close();
			System.out.println("Flight Cancelled Successfully");
			}
			else {
				System.err.println("Flight does not exists");
			}
			stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//Update source for particular flight
		public static void updateSource(Connection conn,String id,String newSource) {
			try {
				String st="Select * from flight where flightid=?";
				PreparedStatement stmt=conn.prepareStatement(st);
				stmt.setString(1, id);
				ResultSet res=stmt.executeQuery();
				if(res.next()) {
				String st1="Update flight set source=? where flightid=?";
				PreparedStatement stmt1=conn.prepareStatement(st1);
				stmt1.setString(1, newSource);
				stmt1.setString(2, id);
				stmt1.executeUpdate();
				stmt1.close();
				System.out.println("Updated the source Successfully");
				}
				else {
					System.err.println("Flight does not exists");
				}
				stmt.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
		
		//Update destination for particular flight
		public static void updateDestination(Connection conn,String id,String newDestination) {
			try {
				String st="Select * from flight where flightid=?";
				PreparedStatement stmt=conn.prepareStatement(st);
				stmt.setString(1, id);
				ResultSet res=stmt.executeQuery();
				if(res.next()) {
				String st1="Update flight set destination=? where flightid=?";
				PreparedStatement stmt1=conn.prepareStatement(st1);
				stmt1.setString(1, newDestination);
				stmt1.setString(2, id);
				stmt1.executeUpdate();
				stmt1.close();
				System.out.println("Updated the destination Successfully");
				}
				else {
					System.err.println("Flight does not exists");
				}
				
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
		
		//Update name for particular flight
		public static void updateFlightName(Connection conn,String id,String newName) {
			try {
				String st="Select * from flight where flightid=?";
				PreparedStatement stmt=conn.prepareStatement(st);
				stmt.setString(1, id);
				ResultSet res=stmt.executeQuery();
				if(res.next()) {
				String st1="Update flight set flightname=? where flightid=?";
				PreparedStatement stmt1=conn.prepareStatement(st1);
				stmt1.setString(1, newName);
				stmt1.setString(2, id);
				stmt1.executeUpdate();
				stmt1.close();
				System.out.println("Updated the flight name Successfully");
				}
				else {
					System.err.println("Flight does not exists");
				}
				stmt.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
		
		//Update departure date for particular flight
		public static void updateFlightDate(Connection conn,String id,Date newDate) {
			try {
				String st="Select * from flight where flightid=?";
				PreparedStatement stmt=conn.prepareStatement(st);
				stmt.setString(1, id);
				ResultSet res=stmt.executeQuery();
				if(res.next()) {
				String st1="Update flight set departuredate=? where flightid=?";
				PreparedStatement stmt1=conn.prepareStatement(st1);
				stmt1.setDate(1, newDate);
				stmt1.setString(2, id);
				stmt1.executeUpdate();
				stmt1.close();
				System.out.println("Updated the departure date Successfully");
				}
				else {
					System.err.println("Flight does not exists");
				}
				stmt.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
		
		//Get booking details for particular flight
		public static ArrayList<Bookings> getBooking(Connection conn,String fid) {
			// oracle 5 reccords ---------------: java Arraylist
			ArrayList<Bookings> itemList = new ArrayList<Bookings>();
			try {
			String st="select * from flight where flightid=?";
			PreparedStatement stmt=conn.prepareStatement(st);
			stmt.setString(1, fid);
			ResultSet rs = stmt.executeQuery(); 
			if (rs.next()) {
				String st1="select * from booking where fid=?";
				PreparedStatement stmt1=conn.prepareStatement(st1);
				stmt1.setString(1, fid);
				ResultSet rs1 = stmt1.executeQuery();
				while(rs1.next()) {
				int id= rs1.getInt("bookingid"); 
				int pid= rs1.getInt("pid"); 
				java.util.Date bdate=rs1.getDate("bookingdate");
				String className=rs1.getString("class_type");
				Bookings booking=new Bookings(id,fid,pid,(Date) bdate,className);
				itemList.add(booking);
					}
				}
			else {
				 System.out.println("FlightId does not exists");
			}
			} catch (SQLException e) {
			e.printStackTrace();
			}
			return itemList;
		
	}
}


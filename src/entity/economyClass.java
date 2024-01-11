package entity;

import java.sql.Date;

public class economyClass extends Flight{
	private String classtype="Economy_class";
	private double price;
	private int availableSeats;
	
	public economyClass() {
		this.price=0.0;
		this.availableSeats=0;
	}

	public economyClass(String id,String source,String destination,String name,Date date,double price, int availableSeats) {
		super(id,source,destination,name,date);
		this.price = price;
		this.availableSeats = availableSeats;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	@Override
	public String toString() {
		return "economyClass [classtype=" + classtype + ", price=" + price + ", availableSeats=" + availableSeats + "]";
	}

	
	
	

}

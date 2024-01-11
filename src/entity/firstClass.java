package entity;

import java.sql.Date;

public class firstClass extends Flight{
	private String classtype="First_class";
	private double price;
	private int availableSeats;
	
	public firstClass() {
		this.price=0.0;
		this.availableSeats=0;
	}

	public firstClass(String id,String source,String destination,String name,Date date,double price, int availableSeats) {
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
		return super.toString()+"\n"+"firstClass [classtype=" + classtype + ", price=" + price + ", availableSeats=" + availableSeats + "]";
	}

	
	
	

}

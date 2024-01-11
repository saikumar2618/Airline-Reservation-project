package entity;
import java.sql.Date;

public class Flight {
	
	private String flightId ;
	private String source ;
	private String destination;
	private String flightName;
	//private double price;
	private Date departureDate;
	
	public Flight() {
		this.flightId="" ;
		this.source="" ;
		this.destination="";
		this.flightName="";
		//this.price=0.0;
	}

	
	public Flight(String flightId, String source, String destination, String flightName,Date departureDate) {
		super();
		this.flightId = flightId;
		this.source = source;
		this.destination = destination;
		this.flightName = flightName;
		//this.price = price;
		this.departureDate = departureDate;
	}


	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	
//	public double getPrice() {
//		return price;
//	}
//
//	public void setPrice(double price) {
//		this.price = price;
//	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", source=" + source + ", destination=" + destination + ", flightName="
				+ flightName + ", departureDate=" + departureDate + "]";
	}

	
	
	
	
}

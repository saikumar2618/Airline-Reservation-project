package entity;
import java.sql.Date;

public class Bookings {
	
	private int bookingId;
	private String flightId ;
	private int pId;
	private Date bookingdate;
	private String classType;
	
	public Bookings() {
		this.bookingId=0;
		this.flightId="" ;
		this.pId=0;
		this.classType="";
	}

	public Bookings(int bookingId, String flightId, int pId, Date bookingdate, String classType) {
		super();
		this.bookingId = bookingId;
		this.flightId = flightId;
		this.pId = pId;
		this.bookingdate = bookingdate;
		this.classType = classType;
		
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public Date getBookingdate() {
		return bookingdate;
	}

	public void setBookingdate(Date bookingdate) {
		this.bookingdate = bookingdate;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	@Override
	public String toString() {
		return "Bookings [bookingId=" + bookingId + ", flightId=" + flightId + ", pId=" + pId + ", bookingdate="
				+ bookingdate + ", classType=" + classType + "]";
	}

	
	
}


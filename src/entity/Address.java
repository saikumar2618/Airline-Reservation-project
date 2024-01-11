package entity;

public class Address {
	 String city;
	 String State;
	 int pincode;
	 
	public Address(String city, String state,int pincode) {
		super();
		this.city = city;
		this.State = state;
		this.pincode = pincode;
	}
	
	@Override
	public String toString() {
		return "Address [city=" + city + ", State=" + State + ", pincode=" + pincode + "]";
	}
	
}

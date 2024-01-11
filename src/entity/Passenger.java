package entity;
 
public class Passenger {
	
	private int pId;  
	private String passengerFname;
	private String passengerLname;
	private int age;
	private Address address;
	private long mobile;
	private String password;
	
	public Passenger() {
		this.pId=0;  
		this.passengerFname="";
		this.passengerLname="";
		this.age=0;
		this.mobile=0;
		this.password="";
	}

	public Passenger(int pId, String passengerFname, String passengerLname, int age,Address address,
			long mobile,String password) {
		super();
		this.pId = pId;
		this.passengerFname = passengerFname;
		this.passengerLname = passengerLname;
		this.age = age;
		this.address=address;
		this.mobile = mobile;
		this.password = password;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getPassengerFname() {
		return passengerFname;
	}

	public void setPassengerFname(String passengerFname) {
		this.passengerFname = passengerFname;
	}

	public String getPassengerLname() {
		return passengerLname;
	}

	public void setPassengerLname(String passengerLname) {
		this.passengerLname = passengerLname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Passenger [pId=" + pId + ", passengerFname=" + passengerFname + ", passengerLname=" + passengerLname
				+ ", age=" + age + ", address=" + address + ", mobile=" + mobile + ", password=" + password + "]";
	}

	
	
	
	
	
}

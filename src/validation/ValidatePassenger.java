package validation;

public class ValidatePassenger {
	
	public boolean checkName(String name) {
		char a;
		boolean isValid=false;
		for(int i=0;i<name.length();i++) {
			a=name.charAt(i);
			if(a>=65 && a<=90 || a>=97 && a<=122) {
				isValid=true;
			}
			else {
				isValid=false;
				break;
			}
		}
		if(isValid==true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkAge(int age) {
		if(age>99 || age<1) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean checkMobile(long mobile) {
		String regex = "(0/91)?[7-9][0-9]{9}";
		String mob=String.valueOf(mobile);
	    if(mob.matches(regex)) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	public boolean checkPassword(String password) {
	    if(password.length()>7 && password.length()<15) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	public boolean checkPincode(int pincode) {
		String regex = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$";
		String pin=String.valueOf(pincode);
	    if(pin.matches(regex)) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	public boolean checkInputs(String fname,String lname,int age,String city,String state,int pincode,long mobile,String password) {
		
		if(!checkName(fname)) {
			System.out.println("Invalid First name");
			return false;
		}
		else if(!checkName(lname)) {
			System.out.println("Invalid Last name");
			return false;
		}
		else if(!checkAge(age)) {
			System.out.println("Please enter the valid age");
			return false;
		}
		else if(!checkName(city)) {
			System.out.println("Invalid city");
			return false;
		}
		else if(!checkName(state)) {
			System.out.println("Invalid state");
			return false;
		}
		
		else if(!checkPincode(pincode)) {
			System.out.println("Please enter valid pincode");
			return false;
		}
		else if(!checkMobile(mobile)) {
			System.out.println("Please enter the valid mobileno.");
			return false;
		}
		else if(!checkPassword(password)) {
			System.out.println("Password length should be greater than 7 and less than 15");
			return false;
		}
		else {
			return true;
		}
	}
}

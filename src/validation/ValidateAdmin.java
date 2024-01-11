package validation;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class ValidateAdmin {
	
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
	
	public boolean isValidDate(String date) throws ParseException {
		SimpleDateFormat ft1=new SimpleDateFormat("dd-MM-yyyy");
		ft1.setLenient(false);
		java.util.Date parsed=null;
		DateFormat dfor = new SimpleDateFormat("dd-MM-yyyy");
		Calendar obj = Calendar.getInstance();
		String currentDate= dfor.format(obj.getTime());
		try {
			parsed=ft1.parse(date);
//			System.out.println(parsed);
			java.util.Date parsed1 = ft1.parse(currentDate);
//			System.out.println(parsed1);
			int year=parsed.getYear();
//			System.out.println(parsed.getYear());
//			System.out.println(year);
			
			if(year<=123 && parsed.after(parsed1)) {
			return true;
			}
			else {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
		
	}
	
	
		public boolean checkInputs(String id,String source,String destination,String name,String date,double Fprice,double Bprice,double Eprice) throws ParseException {
			if(id=="") {
				System.out.println("Id cannot be empty");
				return false;
			}
			else if(!checkName(source)) {
				System.out.println("Invalid Source");
				return false;
			}
			else if(!checkName(destination)) {
				System.out.println("Invalid Destination");
				return false;
			}
			else if(!checkName(name)) {
				System.out.println("Invalid Name");
				return false;
			}
			else if(date=="") {
				System.out.println("Please enter the departure date");
				return false;
			}
			else if(!isValidDate(date)) {
				System.out.println("Please enter valid departure date");
				return false;
			}
			else if(Fprice<1000 || Fprice>1000000) {
				System.out.println("Please enter the valid price");
				return false;
			}
			else if(Eprice<1000 || Eprice>1000000) {
				System.out.println("Please enter the valid price");
				return false;
			}
			else if(Bprice<1000 || Bprice>1000000) {
				System.out.println("Please enter the valid price");
				return false;
			}
			else if(source == destination) {
				System.out.println("Source and Destination Should not be the same");
				return false;
			}
			
			else {
				return true;
			}
		}	
}

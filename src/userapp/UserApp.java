package userapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import entity.Passenger;
import services.AdminServices;
import services.PassengerServices;


public class UserApp {

	public static void main(String[] args) throws SQLException, ParseException {
		
		//Intialization of Connection object
		Connection conn=null;
		
		try {
			
			//Database Connection Setup
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			System.out.println("Welcome to Airline Reservation System");
			
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
		}
		
		
		Scanner sc=new Scanner(System.in);
		
		//AdminServices Object
		AdminServices adminServices=new AdminServices();
		
		String choice;
		int a;
		
		//Admin/User Menu
		do{
			
			System.out.println("1. Admin\r\n"
					+ "2. User\r\n"
					+ "3. Exit\r\n"
					);
			
			System.out.println("Enter the choice: ");
			 a=sc.nextInt();
			 	 
		switch(a) {
		case 1: //Credentials Check
				boolean result=adminServices.checkCredentials(conn);
				
				if(result) {
					
					System.out.println("Successfully Logged in");
					String response;
					
					//Menu for Admin Operations
						do {
							System.out.println("Choose option from below");
							System.out.println("1. Add Flight\r\n"
									+ "2. Display Flights\r\n"
									+ "3. Update Source\r\n"
									+ "4. Update Destination\r\n"
									+ "5. Update Flight Name\r\n"
									+ "6. Update Departure Date\r\n"
									+"7.Cancel Flight\r\n"
									+"8.Display Bookings\r\n"
									+"9.Exit\r\n");
							
							int ch=sc.nextInt();
								
							switch(ch) {
							 	case 1: adminServices.addFlight(conn);
							 			break;
							 	case 2: adminServices.getFlight(conn);
							 			break;
							 	case 3: adminServices.updateSource(conn);
							 			break;
								case 4: adminServices.updateDestination(conn);
					 					break;
								case 5: adminServices.updateFlightName(conn);
			 							break;
								case 6: adminServices.updateFlightDate(conn);
										break;
							 	case 7: adminServices.cancelFlight(conn);
							 			break;
							 	case 8: adminServices.getBooking(conn);
							 			break;
							 	case 9: System.exit(0);
							 			break;
							 	default:System.out.println("Invalid Choice");
							}
							
							System.out.println("Do you want to continue? Y/N");
							response=sc.next();
							
							}while(response.equals("Y") || response.equals("y"));
					}
				
				else {
					System.out.println("Wrong Credentials");
				}
				
				break;
				
			case 2: //Menu for New/Existing user
					System.out.println("1. New User\r\n"
					+ "2. Existing User\r\n"
					+ "3. Exit\r\n"
					);
					
					int response=sc.nextInt();
					String pass;
					
					switch(response) {
					case 1: //Signup and SignupCheck
							if(!PassengerServices.addNewUser(conn)) {
									break;
								}
							
					case 2: //Login Check
							if(PassengerServices.checkCredentials(conn)) {
							System.out.println("Logged in succesfully");
							
							//Menu for User
							do {
								System.out.println("Choose option from below");
								System.out.println("1. Display Flights\r\n"
										+ "2. Book Flight\r\n"
										+ "3. Cancel Booking\r\n"
										+ "4. Display Profile\r\n"
										+ "5. Display my bookings\r\n"
										+ "6. Update Name\r\n"
										+ "7. Update Age\r\n"
										+ "8. Update Address\r\n"
										+"9.  Update Mobile\r\n"
										+"10. Update Password\r\n"
										+"11. Exit\r\n");
								
									int ch=sc.nextInt();
									
								switch(ch) {
								 	case 1: adminServices.getFlight(conn);
								 			break;
								 	case 2: PassengerServices.bookFlight(conn);
								 			break;
								 	case 3: PassengerServices.cancelBooking(conn);
								 			break;
									case 4: PassengerServices.getProfile(conn);
								 			break;
									case 5: PassengerServices.getBooking(conn);
				 							break;
									case 6: PassengerServices.updateName(conn);
											break;
								 	case 7: PassengerServices.updateAge(conn);
								 			break;
									case 8: PassengerServices.updateAddress(conn);
						 					break;
									case 9: PassengerServices.updateMobile(conn);
				 							break;
									case 10: PassengerServices.updatePassword(conn);
		 									break;
									case 11:  System.exit(0);
						 					break;
									default:System.out.println("Invalid Choice");
								}
								
								System.out.println("Do you want to continue? Y/N");
								pass=sc.next();
								
								}while(pass.equals("Y") || pass.equals("y"));
						}
					
							else {
								System.out.println("Wrong Credentials");
							}
								break;
					case 3:System.exit(0);
							break;
					default: System.out.println("Invalid Choice");
					
					}
					
				break;
	
		case 3:System.exit(0);
				break;
		default: System.out.println("Invalid Choice");
		}
		
		System.out.println("Do you want to continue: ");
		choice=sc.next();
		
		}while(choice.equals("Y") || choice.equals("y"));

	}

}

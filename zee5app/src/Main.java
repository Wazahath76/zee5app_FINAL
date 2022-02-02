package com.zee.zee5app;

import java.net.URL;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.Impl.UserServiceImpl;
import com.zee.zee5app.service.Impl.MovieServiceImpl;
import com.zee.zee5app.service.Impl.SeriesServiceImpl;
import com.zee.zee5app.service.Impl.SubscriptionServiceImpl;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.SeriesService;

public class Main {

	public static void main(String[] args) throws 
	InvalidIdLengthException, InvalidNameException, InvalidPasswordException, InvalidEmailException, InvalidAmountException, IdNotFoundException {
		// TODO Auto-generated method stub
		UserService service = UserServiceImpl.getInstance();
		//Use of parameterized constructor
		//Register register5 = new Register(String id, String firstName, String lastName, String email, String password);
		Register register6 = new Register("afs140","Akshay", "Sharma", "as9272@yahoo.com", "Mac");
		System.out.println(register6);
		
		//Create register object
		Register register = new Register();
		// Register : class name
		// register: reference which will refer your object - in stack memory
		// new : is used to create object - in heap memory
		// Register(): default constructor
		
		//here in Main we prefer try/catch method as if we use throw method it will hold off all the execution
		// in other like service, repo etc. we prefer throw method
		
	    //this is 1st approach as id is not validating name will also not be validated as everything will give null
		try {
			register.setId("rg0001");
			register.setFirstName("rajiv");
			register.setLastName("gupta"); 
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    catch (InvalidNameException e) {
	    	e.printStackTrace();
	    }
		try {
			register.setEmail("rg321@gmail.com");
		} catch (InvalidEmailException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			register.setPassword("Sdadr4");
		} catch (InvalidPasswordException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//String result1  = service.addUser(register);
		//System.out.println(result1+"checkpoint1");
		
		//System.out.println(service.getUserById("rg00001").get()+"checkpoint2");
		
		System.out.println(register);
		System.out.println(register.toString());
		  
		// whenever you will print the referenfe then it will call toString() method
		System.out.println(register.getEmail());
		
		//Create login object
		
		Login login  = new Login();
		
		login.setUserName("rg123");
		login.setPassword("sds");
		
		System.out.println(login);
		
		// we dont introduce private here to make it accessible
		//now this line can connect to different files with UserServiceImpl2 and so on
		
		// main is consuming the service
		// if we even call this 100 times it will create only 1 object now
		
		// now only id part in register will not be show other
		
		System.out.println("\n    THIS IS FOR USER\n");
		
		for(int i =1; i<=4;i++) {
			Register register2 = new Register();
			try {
				register2.setId("rg000"+i);
				
			} catch (InvalidIdLengthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//this is 2nd approach
			try {
				register2.setFirstName("rajiv");
				register2.setLastName("gupta"+i);
			} catch (InvalidNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(Exception e) {
				
			}
			//it is base class of all exceptions
			// max available hierarchy is till throwable not above it i.e. Object class
			catch(Throwable e) {
				
			}
			//this will give error even though Object is a parent class of throwable
			//It stops at throwable coz Object class is parent class to other methods like toString, hashCode etc.
//			catch(Object e) {
//				
//			}
			
			try {
				register2.setEmail("rg2321@gml.com");
			} catch (InvalidEmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String result = service.addUser(register2);
			//System.out.println(result);
			
		}
		
		//Register register2 = service.getUserById("rg01") ;
		//System.out.println(register2!=null);

		
		for (Register register3 : service.getAllUsers()) {
			if(register3!=null)
			 System.out.println(register3);
					
		}
	
		//we use try catch in main
		
		try {
			Optional<Register> optional = service.getUserById("rg0001");
			System.out.println(optional);
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("id not found");
			e.printStackTrace();
		}
		
		//now we delete 1 id
		try {
			service.deleteUserById("rg0001");
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		service.getAllUserDetails().forEach(e->System.out.println(e));
		
		System.out.println("\n    THIS IS FOR SUBSCRIPTION\n");
			
			
		SubscriptionService service2 = SubscriptionServiceImpl.getInstance();
		
		for(int i =1; i<=3;i++) {
			Subscription subscription = new Subscription();
			try {
			subscription.setId("sub00"+i);
			}
			catch (InvalidIdLengthException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			subscription.setAutoRenewal(null);
			subscription.setDateOfPurchase(null);
			subscription.setExpiryDate(null);
			try {
				subscription.setAmount(1500);
			} catch (InvalidAmountException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			subscription.setPaymentMode(null);
			subscription.setStatus(null);
			subscription.setType(null);
			
			String result = service2.addSubscription(subscription);
			//System.out.println(result);
			
		}

		
		for (Subscription subscription : service2.getAllSubscription()) {
			if(subscription!=null)
			    System.out.println(subscription);
					
		}
		
		try {
			Optional<Subscription> optional = service2.getSubscriptionById("sub001");
			System.out.println(optional);
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("id not found");
			e.printStackTrace();
		}
		
		try {
			service2.deleteSubscription("sub001");
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("\n    THIS IS FOR MOVIES\n");
		
		MovieService service3 = MovieServiceImpl.getInstance();
		for(int i =1; i<=5;i++) {
			Movie movie = new Movie();
			try {
			movie.setId("mov00"+i);
			}
			catch (InvalidIdLengthException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				movie.setMovieName("abc"+i);
			} catch (NameNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			movie.setAgeLimit(null);
			movie.setCast(new String[] {"hrk","abt","tdc","sdf"});
			movie.setGenre("action"+i);
			movie.setLength(null);
			movie.setReleaseDate(null);
			movie.setTrailer(null);
			String result = service3.addMovie(movie);
			//System.out.println(result);
			
		}
		
		for (Movie movie : service3.getAllMovie()) {
			if(movie!=null)
			 System.out.println(movie);
					
		}
		
		try {
			Optional<Movie> optional = service3.getMovieById("mov001");
			System.out.println(optional);
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("id not found");
			e.printStackTrace();
		}
		
		try {
			service3.deleteMovie("mov001");
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("\n    THIS IS FOR SERIES\n");
		
		SeriesService service4 = SeriesServiceImpl.getInstance();
		for(int i =1; i<=7;i++) {
			Series series = new Series();
			series.setId("sr000"+i);
			try {
				series.setSeriesName("xyz"+i);
			} catch (NameNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			series.setAgeLimit(null);
			series.setCast(new String[] {"ytd","efd","gfh","trd"});
			series.setGenre("thriller"+i);
			series.setLength(null);
			series.setReleaseDate(null);
			series.setTrailer(null);
			String result = service4.addSeries(series);
			//System.out.println(result);
			
		}
		
		for (Series series : service4.getAllSeries()) {
			if(series!=null)
			 System.out.println(series);
					
		}
		
		
		try {
			Optional<Series> optional = service4.getSeriesById("sr0001");
			System.out.println(optional);
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("id not found");
			e.printStackTrace();
		}
		
		try {
			service4.deleteSeries("sr0001");
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("\nAfter updatig the series");
		
		try {
			Series series5 = new Series("sr0002","xyz1",null,null,null,null,null,null);
			service4.modifySeries("sr0002", series5);
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidIdLengthException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//this gives an error now coz its an interface thing
		//UserRepository repository = new UserRepository();
		
		//UserRepository repository = null;
	    
		service4.getAllSeries().forEach(e->System.out.println(e));

	}

}

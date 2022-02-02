//package com.zee.zee5app;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Optional;
//
//import javax.naming.NameNotFoundException;
//
//import com.zee.zee5app.dto.Login;
//import com.zee.zee5app.dto.Register;
//import com.zee.zee5app.dto.Subscription;
//import com.zee.zee5app.exception.IdNotFoundException;
//import com.zee.zee5app.exception.InvalidAmountException;
//import com.zee.zee5app.exception.InvalidEmailException;
//import com.zee.zee5app.exception.InvalidIdLengthException;
//import com.zee.zee5app.exception.InvalidNameException;
//import com.zee.zee5app.exception.InvalidPasswordException;
//import com.zee.zee5app.repository.UserRepository;
//import com.zee.zee5app.dto.Movie;
//import com.zee.zee5app.dto.ROLE;
//import com.zee.zee5app.dto.Series;
//import com.zee.zee5app.service.UserService;
//import com.zee.zee5app.service.Impl.UserServiceImpl;
//import com.zee.zee5app.service.Impl.LoginServiceImpl;
//import com.zee.zee5app.service.Impl.MovieServiceImpl;
//import com.zee.zee5app.service.Impl.SeriesServiceImpl;
//import com.zee.zee5app.service.Impl.SubscriptionServiceImpl;
//import com.zee.zee5app.service.SubscriptionService;
//import com.zee.zee5app.service.LoginService;
//import com.zee.zee5app.service.MovieService;
//import com.zee.zee5app.service.SeriesService;
//
//public class Main2 {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		System.out.println("\nTHIS IS FOR USERS\n");
//		
//		Register register = null;
//		UserService service = null;
//		try {
//			register = new Register("ab00021","riya","sharma","riya@gmail.com","sdSEs2",null);
//			register.setContactnumber(new BigDecimal("9813973123"));
//			
//			service = UserServiceImpl.getInstance();
//			
//			String result = service.addUser(register);
//			System.out.println(result);
//		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException | InvalidPasswordException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		
//		}
//	
//		Optional<Register> register1 = null;
//		
//		
//		try {
//			service = UserServiceImpl.getInstance();
//			register1 = service.getUserById("ab0001");
//			
//			System.out.println(register1.get());
//		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException | InvalidPasswordException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		
//		} catch (IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			service = UserServiceImpl.getInstance();
//			Optional<List<Register>> optional = service.getAllUserDetails();
//			
//			if(optional.isEmpty()) {
//				System.out.println("there are no records");
//			}
//			else {
//				optional.get().forEach(e-> System.out.println(e));
//			}	
//			
//		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException | InvalidPasswordException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			service = UserServiceImpl.getInstance();
//			service.deleteUserById("ab0001");
//		} catch (IOException | IdNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		try {
//			service = UserServiceImpl.getInstance();
//         	for (Register register2 : service.getAllUsers()) {
//				if(register2!= null)
//					System.out.println(register2);
//			}
//			
//		} catch (IOException | InvalidIdLengthException | InvalidNameException | InvalidEmailException | InvalidPasswordException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("\nTHIS IS FOR LOGIN\n");
//		
//		try {
//			LoginService service2 = LoginServiceImpl.getInstance();
//			System.out.println(service2.changeRole("tanya21@gmail.com", ROLE.ROLE_ADMIN));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("\nTHIS IS FOR SUBSCRIPTION\n");
//		
//		Subscription subscription = null;
//		SubscriptionService service2 = null;
//		try {
//			service2 = SubscriptionServiceImpl.getInstance();
//			
//			subscription = new Subscription("sub009", "2019-04-13", 2599.0f, "credit", "2023-05-03", "active", "annual", "false", "ab00021");
//			String result = service2.addSubscription(subscription);
//			System.out.println(result);
//			
//		} catch (IOException | InvalidIdLengthException | InvalidAmountException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Optional<Subscription> subscription1 = null;
//		
//		try {
//			service2 = SubscriptionServiceImpl.getInstance();
//			subscription1 = service2.getSubscriptionById("sub009");
//			System.out.println(subscription1.get());
//		} catch (IdNotFoundException | InvalidIdLengthException | InvalidAmountException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			service2 = SubscriptionServiceImpl.getInstance();
//			Optional<List<Subscription>> optional = service2.getAllSubscription();
//			
//			if(optional.isEmpty()) {
//				System.out.println("there are no records");
//			}
//			else {
//				optional.get().forEach(e-> System.out.println(e));
//			}
//			}catch (InvalidIdLengthException | InvalidAmountException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//		
//		try {
//			service2 = SubscriptionServiceImpl.getInstance();
//			service2.deleteSubscription("sub009");
//		} catch (IOException | IdNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		try {
//			service2 = SubscriptionServiceImpl.getInstance();
//			Optional<List<Subscription>> optional = service2.getAllSubscription();
//			
//			if(optional.isEmpty()) {
//				System.out.println("there are no records");
//			}
//			else {
//				optional.get().forEach(e-> System.out.println(e));
//			}
//			}catch (InvalidIdLengthException | InvalidAmountException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//		
//	
//		System.out.println("\nTHIS IS FOR MOVIES\n");
//		
//		Movie movie = null;
//		MovieService service3 = null;
//		
//		try {
//			service3 = MovieServiceImpl.getInstance();
//			
//			movie = new Movie("mov009", "matrix", "hrk, srk, atb", "150", "2021-12-16", null, "english", "15", "action");
//			
//			String result = service3.addMovie(movie);
//			System.out.println(result);
//			
//		} catch (IOException | InvalidIdLengthException | NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Optional<Movie> movie1 = null;
//		
//		try {
//			service3 = MovieServiceImpl.getInstance();
//			movie1 = service3.getMovieById("mov009");
//			System.out.println(movie1.get());
//		} catch (IdNotFoundException | InvalidIdLengthException | IOException | NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			service3 = MovieServiceImpl.getInstance();
//			Optional<List<Movie>> optional = service3.getAllMovie();
//			
//			if(optional.isEmpty()) {
//				System.out.println("there are no records");
//			}
//			else {
//				optional.get().forEach(e-> System.out.println(e));
//			}
//			}catch (InvalidIdLengthException | IOException | NameNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//		
//		try {
//			service3 = MovieServiceImpl.getInstance();
//			service3.deleteMovie("mov009");
//		} catch (IOException | IdNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		try {
//			service3 = MovieServiceImpl.getInstance();
//			Optional<List<Movie>> optional = service3.getAllMovie();
//			
//			if(optional.isEmpty()) {
//				System.out.println("there are no records");
//			}
//			else {
//				optional.get().forEach(e-> System.out.println(e));
//			}
//			}catch (InvalidIdLengthException | IOException | NameNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//	
//		
//		System.out.println("\nTHIS IS FOR SERIES\n");
//		
//		Series series = null;
//		SeriesService service4 = null;
//		
//		try {
//			service4 = SeriesServiceImpl.getInstance();
//			
//			series = new Series("ser009", "dexter", "joy, ross", "1430", "2012-02-25", null, "english", "17", "thriller", 59);
//			
//			String result = service4.addSeries(series);
//			System.out.println(result);
//			
//		} catch (IOException | InvalidIdLengthException | NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Optional<Series> series1 = null;
//		
//		try {
//			service4 = SeriesServiceImpl.getInstance();
//			series1 = service4.getSeriesById("ser009");
//			System.out.println(series1.get());
//		} catch (IdNotFoundException | InvalidIdLengthException | IOException | NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			service4 = SeriesServiceImpl.getInstance();
//			Optional<List<Series>> optional = service4.getAllSeries();
//			
//			if(optional.isEmpty()) {
//				System.out.println("there are no records");
//			}
//			else {
//				optional.get().forEach(e-> System.out.println(e));
//			}
//			}catch (InvalidIdLengthException | IOException | NameNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				
//			}	
//		
//		try {
//			service4 = SeriesServiceImpl.getInstance();
//			service4.deleteSeries("ser009");
//		} catch (IOException | IdNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		try {
//			service4 = SeriesServiceImpl.getInstance();
//			Optional<List<Series>> optional = service4.getAllSeries();
//			
//			if(optional.isEmpty()) {
//				System.out.println("there are no records");
//			}
//			else {
//				optional.get().forEach(e-> System.out.println(e));
//			}
//			}catch (InvalidIdLengthException | IOException | NameNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//		
//	}
//	}
//
//

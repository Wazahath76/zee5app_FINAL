//package com.zee.zee5app;
//
//import java.math.BigDecimal;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import com.zee.zee5app.dto.Register;
//import com.zee.zee5app.repository.MovieRepository;
//import com.zee.zee5app.repository.UserRepository;
//import com.zee.zee5app.service.UserService;
//
//public class Main2 {
//
//	
//	public static void main(String[] args) {
//
//		ConfigurableApplicationContext applicationContext = SpringApplication.run(Zee5appspringbootApplication.class,
//				args);
//		
//		UserRepository repository =applicationContext.getBean(UserRepository.class);
//		MovieRepository repository2 =applicationContext.getBean(MovieRepository.class);
//		
//		
//		System.out.println(repository.existsByemail("waha@gmail.com"));
//		
//		MovieRepository movierepository =applicationContext.getBean(MovieRepository.class);
//		System.out.println(movierepository.findBymovienameAndlanguage("ddlj", "hindi"));
//		applicationContext.close();
//	}
//	
//}

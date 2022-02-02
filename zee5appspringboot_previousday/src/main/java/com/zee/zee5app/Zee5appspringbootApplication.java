package com.zee.zee5app;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.UserRepository;

@SpringBootApplication
public class Zee5appspringbootApplication {

	public static void main(String[] args) {
		
	ConfigurableApplicationContext applicationContext =	SpringApplication.run(Zee5appspringbootApplication.class, args);
	
	DataSource dataSource = applicationContext.getBean(DataSource.class);
	System.out.println(dataSource!=null);
	
	UserRepository userRepository  = applicationContext.getBean(UserRepository.class);
	Register register;
	try {
		register = new Register("waz00021","wazahath","hussain","waz123@gmail.com","sdSEs2",null);
		register.setContactnumber(new BigDecimal("9813973123"));
		
        System.out.println(userRepository.addUser(register));
	} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException | InvalidPasswordException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	}
	
	applicationContext.close();
	
	}

}

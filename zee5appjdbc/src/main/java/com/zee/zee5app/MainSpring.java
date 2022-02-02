package com.zee.zee5app;

import java.io.IOException;
import java.math.BigDecimal;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.Impl.UserServiceImpl;

public class MainSpring {
	
	public static void main(String[] args) {
		
		//we need to establish or create spring environment
		// this will kick start or spring application
		
		//application context container
		
		// java based configurations
		
		//abstract, interface, concrete layers are formed
		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
	
		UserRepository userRepository  =applicationContext.getBean(UserRepository.class);
//		System.out.println(userRepository);
//		
//		UserRepository userRepository2  =applicationContext.getBean(UserRepository.class);
//		System.out.println(userRepository2);
//		System.out.println(userRepository.hashCode());
//		System.out.println(userRepository2.hashCode());
//		System.out.println(userRepository.equals(userRepository2));
		
		
		
		DataSource dataSource = applicationContext.getBean("ds",DataSource.class);
//		System.out.println(dataSource.hashCode());
//		DataSource dataSource2 = applicationContext.getBean("ds",DataSource.class);
//		System.out.println(dataSource2.hashCode());
		
//		DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
		System.out.println(dataSource!=null);
		
		Register register;
		try {
			register = new Register("ab00211","riya","sharma","riy22a@gmail.com","sdSEs2",null);
			register.setContactnumber(new BigDecimal("9813973123"));
			
            System.out.println(userRepository.addUser(register));
		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException | InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		applicationContext.close();
	}

}

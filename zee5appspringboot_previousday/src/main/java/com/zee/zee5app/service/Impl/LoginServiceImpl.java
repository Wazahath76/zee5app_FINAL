package com.zee.zee5app.service.Impl;

import java.io.IOException;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.repository.LoginRepository;

import com.zee.zee5app.repository.Impl.LoginRepositoryImpl;
import com.zee.zee5app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
	DataSource dataSource;
	@Autowired
	private LoginRepository repository = null ;
	
	public LoginServiceImpl() throws IOException {
		
	}
	
//	private static LoginService service;
//	public static LoginService getInstance() throws IOException{
//		if(service==null)
//			service = new LoginServiceImpl();
//		return service;
//	}
	
	@Override
	public String addCredentials(Login login) {
		// TODO Auto-generated method stub
		return repository.addCredentials(login);
	}

	@Override
	public String deleteCredentials(String userName) {
		// TODO Auto-generated method stub
		return repository.deleteCredentials(userName);
	}

	@Override
	public String changePassword(String userName, String password) {
		// TODO Auto-generated method stub
		return repository.changePassword(userName, password);
	}

	@Override
	public String changeRole(String userName, ROLE role) {
		// TODO Auto-generated method stub
		return repository.changeRole(userName, role);
	}

}

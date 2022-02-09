package com.zee.zee5app.service.Impl;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.repository.Impl.UserRepositoryImpl;
import com.zee.zee5app.service.UserService;

public class UserServiceImpl implements UserService {

	
	private UserRepository repository = UserRepositoryImpl.getInstance() ;
	
	private UserServiceImpl() {
		
	}
	
	private static UserService service;
	public static UserService getInstance(){
		if(service==null)
			service = new UserServiceImpl();
		return service;
	}
	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		return this.repository.addUser(register);
	}

	@Override
	public String updateUser(String id, Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.updateUser(id, register);
	}

	@Override
	public Optional<Register> getUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.getUserById(id);
	}

	@Override
	public Register[] getAllUsers() {
		// TODO Auto-generated method stub
		return this.repository.getAllUsers();
	}

	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.deleteUserById(id);
	}
	@Override
	public List<Register> getAllUserDetails() {
		// TODO Auto-generated method stub
		return this.repository.getAllUserDetails();
	}
	
	

}

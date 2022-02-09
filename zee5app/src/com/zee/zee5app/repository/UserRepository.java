package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;


import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;

public interface UserRepository {
	
	//these are abstract methods as we have declared the methods but not their bodies
	
	public String addUser(Register register);
	public String updateUser(String id, Register register) throws IdNotFoundException;
	public Optional<Register> getUserById(String id) throws IdNotFoundException;
	public Register[] getAllUsers();
	public List<Register> getAllUserDetails();
	public String deleteUserById(String id) throws IdNotFoundException;

}

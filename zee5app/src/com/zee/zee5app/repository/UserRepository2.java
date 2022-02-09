package com.zee.zee5app.repository;
import com.zee.zee5app.dto.Register;

public class UserRepository2 {
	
	private Register[] registers = new Register[10];
	private static int count = -1;
	
	private UserRepository2() {
		
	}
	
	// to return all user details
	public Register[] getUser() {
		// we use square brackets bcz we need to return an array ie multiple users
		return registers;
	}
	
	// delete user - homework
	//delete user from 5th index
	//public String deleteUser(String id) {
	//	if(register.getId().equals(id))
	//	{
		//	remove new(id);
		//}
	//	return null;
	//}
	
	//to update details - homework
	public String updateUser(String id, Register register) {
		return null;
	}
	
	//this method should return the user details based on the id
	public Register getUserById(String id) {
		// we need to traverse the array
		
		for (Register register : registers) {
			// we check this null condition to avoid null pointer exception as size is 40 but occupancy is 20
			if(register!=null && register.getId().equals(id)) {
				return register;
			}
		}
		return(null);
	}
	
	// add a new user
	public String addUser(Register register) {
		
		// first check the restrictions or exceptions
		//registers.length : find total length availability
		// check for occupancy or availability ? - occupancy
		
		if(count==registers.length-1) {
			// array is full or we should go for dynamically increasing the size of array
			Register temp[] = new Register[registers.length*4];
			
			//now we need to copy the contents from old to new array
			System.arraycopy(registers, 0, temp, 0, registers.length);
			registers = temp;
			registers[++count] = register;
			
			
			return "sucesss";
		}
		// count is -1 initially, then here it will start with 0
		registers[++count]= register;
		return "success";
		
		
	}
	
	// we declare static here down below before so we can make its access available in userservice
	private static UserRepository2 userRepository;
	public static UserRepository2 getInstance() {
		
		if(userRepository==null)
			userRepository = new UserRepository2();
		return userRepository;	
		
	}

	
}

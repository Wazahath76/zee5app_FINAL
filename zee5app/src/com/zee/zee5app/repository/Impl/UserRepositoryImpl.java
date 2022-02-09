package com.zee.zee5app.repository.Impl;

import java.util.set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.UserRepository;

// this can be used for 1st database
//similary make another file and use it for 2nd database and so on
// here UserRepository act as an interface which has 5 specific functionalities
public class UserRepositoryImpl implements UserRepository {
	
	//private Register[] registers = new Register[10];
	
	//we will now implement set
	//java by default uses doubly linked list
	
	//this will only hold Register type specfications
	//to hold hetro specifications use: private set<> set = new set<>()
	//private List<Register> arraylist = new LinkedList<>();
	//private List<Register> arraylist = new ArrayList<>();
	//List here is a parent object for both set and LinkedList
	//when you will use DC for AL then by default it will construct list with capacity of 10 elements
	
	// if we do with overriding hashcodeandequals method and without we will get different outcomes
	
	//if we override it wont allow duplication
	
	//this wont follow order while inserting it iwll be random
	//private Set<Register> set = new HashSet<>();
	
	//this will follow order in which we provide the values while inserting
	//private Set<Register> set = new LinkedHashSet<>();
	//private Set<Register> set = new LinkedHashSet<>(0, 0);
	//Set is a parent class for hashset and treeset
	// set default capacity 16
	
	//set is only a reference name
	
//	private Set<Register> set = new TreeSet<>();
	private TreeSet<Register> set = new TreeSet<>();
	
	//private static int count = -1;
	
	
	//now we make an singleton object for this
	private UserRepositoryImpl() {
		
	}
	
	//we cant declare/create objects for interface
	// we can declare only references
	/// when we will refer the object whose class is implementing the interface
	// then object will behave like interface i.e.
	//w we can only access the interface overridden methods
	
	//we use UserRepository here coz we need reference sfrom UserRepository(interface class) not the impl thing.
	private static UserRepository repository;
	public static UserRepository getInstance() {
		if(repository ==null)
			//but we refer using interface only i.e.
			//repository = new UserRepository()
			// we can only access interface methods
			
			// this will use functionalities of the interface and both class only
			repository = new UserRepositoryImpl();
		return repository;
	}
	
	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		boolean result = this.set.add(register);
		//System.out.println(this.set.size());
		return null;
	}
	
	@Override
	public String updateUser(String id, Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Register> optional = this.getUserById(id);
		if(optional.isPresent()) {
			boolean result = set.remove(optional.get());
			set.add(register);
			if(result) {
				return null;
			}
			
		}
		return null;
	}
	
	@Override
	//Optional : used to handle null pointer exception
	public Optional<Register> getUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		//we need to traverse the set
		
//		for (Register register : set) {
//			if(register.getId().equals(id)) {
//				return Optional.of(register);
//		    }
//		}
//		return Optional.empty();
		
		Register register2 = null;
		for (Register register : set) {
			if(register.getId().equals(id)) {
				register2 = register;
				break;
		    }
		}
		
//if register2 is holding null it will act like an empty
//if register2 is holding object it will act like Of
		
		//here we can have 3 approaches for this with different return types that is optional, register and one if else
		//1st approach
		//return Optional.ofNullable(register2).orElseThrow(()-> new IdNotFoundException("id not found")); - for this make changes 
		//accordingly to register type everywhere
		
		//2nd approach
		// we can do the same using if else
		
		//3rd approach
		//using Optional.ofNullable on this inside means object may or may not be present
		return Optional.of(Optional.ofNullable(register2).orElseThrow(()-> new IdNotFoundException("id not found")));
		
		//Optional.empty() is for null pointers
		
		//this wont work coz when we use Optional.of we are sure that the object is present
		//return Optional.ofNullable(Optional.of(register2).orElseThrow(()-> new IdNotFoundException("id not found")));
                
	}
	
	@Override
	public Register[] getAllUsers() {
		// TODO Auto-generated method stub
		//transorm collection to array
		
		Register[] register = new Register[set.size()];
		return set.toArray(register);
		
		//return this.set.register;
		
	}
	
	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<Register> optional = this.getUserById(id);
		if(optional.isPresent()) {
			//removal
			boolean result = set.remove(optional.get());
			if(result) {
				return null;
			}
			else
				return("fail7");
		}
//		else {
//			throw new IdNotFoundException("id not found");
//		}
		return "fail7";
		
	}

	@Override
	public List<Register> getAllUserDetails() {
		// TODO Auto-generated method stub
		//now we need to return it in terms of sorted order
//		
//		Collections.sort(arraylist);
//		return arraylist;
		
//		return ArrayList(set);
		//to convert set to list
		
//usually follow the descending order
		//to give it in ascending order coz it follows descending order by default
//return new ArrayList<>(set.descendingSet())		
		
		List<Register> arrlist = new ArrayList<>(set);
		Collections.sort(arrlist);
		return arrlist;
	}
	
	
	

//	@Override
//	public String addUser(Register register) {
//		// TODO Auto-generated method stub
//		if(count==registers.length-1) {
//			// array is full or we should go for dynamically increasing the size of array
//			Register temp[] = new Register[registers.length*4];
//			
//			//now we need to copy the contents from old to new array
//			System.arraycopy(registers, 0, temp, 0, registers.length);
//			registers = temp;
//			registers[++count] = register;
//			
//			
//			return "sucesss";
//		}
//		// count is -1 initially, then here it will start with 0
//		registers[++count]= register;
//		return "success";
//	}
//
//	@Override
//	public String updateUser(String id, Register register) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Register getUserById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Register[] getAllUsers() {
//		// TODO Auto-generated method stub
//		return registers;
//	}
//
//	@Override
//	public String deleteUserById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

}

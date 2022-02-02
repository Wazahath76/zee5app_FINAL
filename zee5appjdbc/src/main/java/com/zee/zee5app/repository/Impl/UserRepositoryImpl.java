package com.zee.zee5app.repository.Impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.utils.PasswordUtils;

// this can be used for 1st database
//similary make another file and use it for 2nd database and so on
// here UserRepository act as an interface which has 5 specific functionalities
//@Component // it will create singleton object for us
@Repository
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
//	private TreeSet<Register> set = new TreeSet<>();

	//private static int count = -1;
	
	//now we make an singleton object for this
//	private UserRepositoryImpl() throws IOException{
//		
//	}
	@Autowired //it will bring already created object either by using name/type
    DataSource dataSource;
	@Autowired
	private LoginRepository loginrepository;
	@Autowired
	private PasswordUtils passwordUtils;
	
	
    public UserRepositoryImpl() throws IOException{
    	//dbUtils = DBUtils.getInstance();
    	//loginrepository = LoginRepositoryImpl.getInstance();
	}
    
	//we cant declare/create objects for interface
	// we can declare only references
	/// when we will refer the object whose class is implementing the interface
	// then object will behave like interface i.e.
	//w we can only access the interface overridden methods
	
	//we use UserRepository here coz we need references from UserRepository(interface class) not the impl thing.
	
//	public static UserRepository getInstance() throws IOException {
//		if(repository ==null)
//			//but we refer using interface only i.e.
//			//repository = new UserRepository()
//			// we can only access interface methods
//			
//			// this will use functionalities of the interface and both class only
//			repository = new UserRepositoryImpl();
//		return repository;
//	}
	
	
	
	
	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		//the user details should be stored in database
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertStatement = "insert into register"+
		       "(regID, firstname, lastname, email, contactnumber, password)"
		       +"values(?,?,?,?,?,?)";
		//we will use concatenate the values in values spec
		//we will use it or not?
		//here we will provide the values against ?(placeholder)
		
		//connection object
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			
			// do we need to provide the values against ? placeholder?
			preparedStatement.setString(1, register.getId());
			preparedStatement.setString(2, register.getFirstName());
			preparedStatement.setString(3, register.getLastName());
			preparedStatement.setString(4, register.getEmail());
			preparedStatement.setBigDecimal(5, register.getContactnumber());
			
			//encrypting and storing password
			String salt = passwordUtils.getSalt(30);
			String encryptedPassword = passwordUtils.generateSecurePassword(register.getPassword(), salt);
			preparedStatement.setString(6, encryptedPassword);
			
			///username: emailid
			//regid  :regid
			//password: password
			//storing these details in login table here only coz we will get all these details during registration
			
			
			int result = preparedStatement.executeUpdate();
			//the return integer in this statement is the number of rows affected by the DML statement
			//insert  1: one row is inserted
			// delete 3 : 3 rows deleted
			
			if(result>0) {
				connection.commit();
				Login login = new Login();
				login.setUserName(register.getEmail());
				login.setPassword(encryptedPassword);
				login.setRegId(register.getId());
				login.setRole(ROLE.ROLE_USER);
				
				String result2 = loginrepository.addCredentials(login);
				if(result2.equals("success")) {
				//	connection.commit();
					return "user added successfully";
					
				}
				else {
					connection.rollback();
					return "fail11";
				}
			}
			else {
				connection.rollback();
				return "fail11";
			}
				} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "fail11";
			}
			
		}
		
		return null;
	}
	
	@Override
	public String updateUser(String id, Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateStatement = "UPDATE register SET firstmame = ?, lastname=? where regId = ?";
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, register.getFirstName());
			preparedStatement.setString(2, register.getLastName());
			preparedStatement.setString(3, id);
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				connection.commit();
				return "user updated successfully";
			}
			else {
				connection.rollback();
				return "fail14";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail14";
		}
		
		
	}
	
	@Override
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidEmailException, InvalidPasswordException, InvalidNameException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String selectStatement = "select * from register where regId=?";
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, id);
			
			//result set will hold the complete result
			resultSet = preparedStatement.executeQuery();
			
			//RS object its a single one which is holding all the records
			//we traverse the resultSet
			
			//we use if instead of while to reduce the unnecessary 1 iteration
			if (resultSet.next()) {
				//next method is used to traverse the RS
				// initially RS is placed just above the 1st record
				//when you call the 1st time RS will retrieve the 1st record &
				//it will refer to the 2nd one
				
				Register register = new Register();
				register.setId(resultSet.getString("regId"));
				register.setFirstName(resultSet.getString("firstname"));
				register.setLastName(resultSet.getString("lastname"));
				register.setEmail(resultSet.getString("email"));
				register.setPassword(resultSet.getString("password"));
				register.setContactnumber(resultSet.getBigDecimal("contactnumber"));
				return Optional.of(register);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return "fail12";
		}
		return Optional.empty();
		
	}
	
	@Override
	public Register[] getAllUsers() throws InvalidIdLengthException, InvalidNameException, InvalidEmailException, InvalidPasswordException {
		// TODO Auto-generated method stub
		Optional<List<Register>> optional = getAllUserDetails();
		if(optional.isEmpty()) {
			return null;
		}
		else {
			List<Register> list = optional.get();
			Register[] registers = new Register[list.size()];
			return list.toArray(registers);
		}
		
	}
	
	@Override
	public Optional<List<Register>> getAllUserDetails() throws InvalidIdLengthException, InvalidNameException, InvalidEmailException, InvalidPasswordException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Register> arraylist = new ArrayList<>();
		
		String selectStatement = "select * from register";
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
			
				Register register = new Register();
				register.setId(resultSet.getString("regId"));
				register.setFirstName(resultSet.getString("firstname"));
				register.setLastName(resultSet.getString("lastname"));
				register.setEmail(resultSet.getString("email"));
				register.setPassword(resultSet.getString("password"));
				register.setContactnumber(resultSet.getBigDecimal("contactnumber"));
				arraylist.add(register);
			}
			//ofNullable coz we not 100% sure we will get the obj or not
			return Optional.ofNullable(arraylist);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return "fail12";
		}
		return Optional.empty();
	}
	
	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteStatement = "delete from register where regId=?";
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1,id);
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				//delete his credentials
				Register register3 = new Register();
				//login2.setUserName(register3.getEmail());
				String result4  = loginrepository.deleteCredentials(register3.getEmail());
				if(result4 == "success")
					//connection.commit(); - call in login
					return "user record deleted";
			}
			else {
				connection.rollback();
				return "fail15";
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "fail12";
		}
		
		return "fail15";
	}
	
	
	
//	@Override
//	public String addUser(Register register) {
//		// TODO Auto-generated method stub
//		boolean result = this.set.add(register);
//		//System.out.println(this.set.size());
//		return null;
//	}
//	
//	@Override
//	public String updateUser(String id, Register register) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Register> optional = this.getUserById(id);
//		if(optional.isPresent()) {
//			boolean result = set.remove(optional.get());
//			set.add(register);
//			if(result) {
//				return null;
//			}
//			
//		}
//		return null;
//	}
//	
//	@Override
//	//Optional : used to handle null pointer exception
//	public Optional<Register> getUserById(String id) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		//we need to traverse the set
//		
////		for (Register register : set) {
////			if(register.getId().equals(id)) {
////				return Optional.of(register);
////		    }
////		}
////		return Optional.empty();
//		
//		Register register2 = null;
//		for (Register register : set) {
//			if(register.getId().equals(id)) {
//				register2 = register;
//				break;
//		    }
//		}
//		
////if register2 is holding null it will act like an empty
////if register2 is holding object it will act like Of
//		
//		//here we can have 3 approaches for this with different return types that is optional, register and one if else
//		//1st approach
//		//return Optional.ofNullable(register2).orElseThrow(()-> new IdNotFoundException("id not found")); - for this make changes 
//		//accordingly to register type everywhere
//		
//		//2nd approach
//		// we can do the same using if else
//		
//		//3rd approach
//		//using Optional.ofNullable on this inside means object may or may not be present
//		return Optional.of(Optional.ofNullable(register2).orElseThrow(()-> new IdNotFoundException("id not found")));
//		
//		//Optional.empty() is for null pointers
//		
//		//this wont work coz when we use Optional.of we are sure that the object is present
//		//return Optional.ofNullable(Optional.of(register2).orElseThrow(()-> new IdNotFoundException("id not found")));
//                
//	}
//	
//	@Override
//	public Register[] getAllUsers() {
//		// TODO Auto-generated method stub
//		//transform collection to array
//		
//		Register[] register = new Register[set.size()];
//		return set.toArray(register);
//		
//		//return this.set.register;
//		
//	}
//	
//	@Override
//	public String deleteUserById(String id) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		
//		Optional<Register> optional = this.getUserById(id);
//		if(optional.isPresent()) {
//			//removal
//			boolean result = set.remove(optional.get());
//			if(result) {
//				return null;
//			}
//			else
//				return("fail7");
//		}
////		else {
////			throw new IdNotFoundException("id not found");
////		}
//		return "fail7";
//		
//	}
//
//	@Override
//	public List<Register> getAllUserDetails() {
//		// TODO Auto-generated method stub
//		//now we need to return it in terms of sorted order
////		
////		Collections.sort(arraylist);
////		return arraylist;
//		
////		return ArrayList(set);
//		//to convert set to list
//		
////usually follow the descending order
//		//to give it in ascending order coz it follows descending order by default
////return new ArrayList<>(set.descendingSet())		
//		
//		List<Register> arrlist = new ArrayList<>(set);
//		Collections.sort(arrlist);
//		return arrlist;
//	}
	
	
	

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
